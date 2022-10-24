package com.example.jetpackcomposeproofofconcept.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeproofofconcept.data.Utils
import com.icdominguez.core.api.Character
import com.icdominguez.database.api.MarvelDatabaseRepository
import com.icdominguez.database.domain.GetStoredCharactersUseCase
import com.icdominguez.database.domain.InsertCharacterInBbddUseCase
import com.icdominguez.network.api.BaseResult
import com.icdominguez.network.api.Constants.API_KEY
import com.icdominguez.network.api.Constants.PRIVATE_API_KEY
import com.icdominguez.network.api.MarvelApiRepository
import com.icdominguez.network.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharactersUseCase,
    private val getStoredCharactersUseCase: GetStoredCharactersUseCase,
    private val marvelApiRepository: MarvelApiRepository,
    private val marvelDatabaseRepository: MarvelDatabaseRepository
) : ViewModel() {

    private val state = MutableStateFlow(CharacterScreenState())
    val mState: StateFlow<CharacterScreenState> get() = state

    private fun setLoading() {
        state.update { it.copy(isLoading = true) }
    }

    private fun hideLoading() {
        state.update { it.copy(isLoading = false) }
    }

    init {
        Timber.i("View model created")

        viewModelScope.launch {
            marvelDatabaseRepository.getAllCharacters().onStart {
                Timber.d("Getting stored characters")
            }.catch {
                Timber.d("Couldn't get character because of: ${it.message}")
            }.collect { characters ->
                if (characters.isNotEmpty()) {
                    state.update { it.copy(listCharacter = characters) }
                } else {
                    val date = Date().time
                    getCharacterUseCase.invoke(
                        API_KEY,
                        Utils.md5("${date}${PRIVATE_API_KEY}$API_KEY"),
                        0,
                        date
                    ).onStart {
                        Timber.d("Getting characters")
                        setLoading()
                    }.catch { exception ->
                        Timber.e("There was an error getting the characters : $exception")
                        hideLoading()
                    }.collect { baseResult ->
                        hideLoading()
                        when (baseResult) {
                            is BaseResult.Success -> {
                                Timber.i("Characters received")

                                // TODO: Create a module with common classes to export same character object for all the modules
                                val characterList = baseResult.data

                                viewModelScope.launch {

                                    marvelDatabaseRepository.insertCharacters(characterList)
                                }

                                state.update { it.copy(listCharacter = characterList) }
                            }

                            is BaseResult.Failure -> {
                                Timber.e(baseResult.rawResponse.message)
                            }
                        }
                    }
                }
            }
        }
    }

    data class CharacterScreenState(
        val listCharacter: List<Character> = emptyList(),
        val isLoading: Boolean = true
    )
}
