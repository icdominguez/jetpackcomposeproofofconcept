package com.example.jetpackcomposeproofofconcept.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeproofofconcept.data.Constants
import com.example.jetpackcomposeproofofconcept.data.Utils
import com.example.jetpackcomposeproofofconcept.data.model.BaseResult
import com.example.jetpackcomposeproofofconcept.data.model.entities.CharacterEntity
import com.example.jetpackcomposeproofofconcept.domain.usecase.api.GetCharactersUseCase
import com.example.jetpackcomposeproofofconcept.domain.usecase.localdatabase.GetStoredCharactersUseCase
import com.example.jetpackcomposeproofofconcept.domain.usecase.localdatabase.InsertCharacterInBbddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
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
    private val insertCharacterInBbddUseCase: InsertCharacterInBbddUseCase
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
            getStoredCharactersUseCase.invoke().onStart {
                Timber.d("Getting stored characters")
            }.catch {
                Timber.d("Couldn't get character because of: ${it.message}")
            }.collect { characters ->
                if (characters.isNotEmpty()) {
                    state.update { it.copy(listCharacter = characters) }
                } else {
                    val date = Date().time
                    getCharacterUseCase.invoke(
                        Constants.API_KEY,
                        Utils.md5("${date}${Constants.PRIVATE_API_KEY}${Constants.API_KEY}"),
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

                                val characterList = baseResult.data.map { character ->
                                    CharacterEntity(
                                        character.id!!,
                                        character.name!!,
                                        character.description!!,
                                        "${character.thumbnail?.path}.${character.thumbnail?.extension}",
                                        false
                                    )
                                }

                                viewModelScope.launch {
                                    insertCharacterInBbddUseCase.invoke(characterList = characterList)
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
        val listCharacter: List<CharacterEntity> = emptyList(),
        val isLoading: Boolean = true
    )
}
