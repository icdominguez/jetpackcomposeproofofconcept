package com.example.jetpackcomposeproofofconcept.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeproofofconcept.data.Constants
import com.example.jetpackcomposeproofofconcept.data.Utils
import com.example.jetpackcomposeproofofconcept.data.model.BaseResult
import com.example.jetpackcomposeproofofconcept.data.model.entities.CharacterEntity
import com.example.jetpackcomposeproofofconcept.domain.usecase.api.GetCharacterUseCase
import com.example.jetpackcomposeproofofconcept.domain.usecase.localdatabase.GetStoredCharacterUseCase
import com.example.jetpackcomposeproofofconcept.domain.usecase.localdatabase.UpdateCharacterIsFavoriteValueUseCase
import com.example.jetpackcomposeproofofconcept.presentation.EventLogic
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
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val updateCharacterIsFavoriteValueUseCase: UpdateCharacterIsFavoriteValueUseCase,
    private val getStoredCharacterUseCase: GetStoredCharacterUseCase
) : EventLogic<CharacterDetailViewModel.CharacterScreenEvent>() {

    private val state = MutableStateFlow(CharacterDetailScreenState())
    val mState: StateFlow<CharacterDetailScreenState> get() = state

    init {
        val argument = savedStateHandle.get<Int>("id")

        state.update { it.copy(characterId = argument!!) }

        viewModelScope.launch {
            argument?.let {
                val date = Date().time
                getCharacterUseCase.invoke(
                    it,
                    Constants.API_KEY,
                    Utils.md5("${date}${Constants.PRIVATE_API_KEY}${Constants.API_KEY}"),
                    date
                ).onStart {
                    Timber.d("Getting character")
                }.catch {
                    Timber.e("There was an error getting the character with id ${it.cause}, ${it.message}")
                }.collect { baseResult ->
                    Timber.d("Nice call")

                    when (baseResult) {
                        is BaseResult.Success -> {
                            state.update { it.copy(character = baseResult.data) }
                        }

                        is BaseResult.Failure -> {
                            Timber.e(baseResult.rawResponse.message)
                        }
                    }
                }
            }
        }
    }

    data class CharacterDetailScreenState(
        val characterId: Int = 0,
        val character: CharacterEntity? = null
    )

    sealed class CharacterScreenEvent {
        data class OnFavoriteClicked(val characterId: Int, val isFavorite: Boolean) : CharacterScreenEvent()
    }

    override fun uiEvent(event: CharacterScreenEvent) {
        when (event) {
            is CharacterScreenEvent.OnFavoriteClicked -> {
                viewModelScope.launch {
                    updateCharacterIsFavoriteValueUseCase.invoke(event.characterId, event.isFavorite)

                    // Here we need to update the state, that's why I need to ask to the bbdd which is the value of isFavorite getting the whole character object
                    state.update { it.copy(character = getStoredCharacterUseCase.invoke(event.characterId)) }
                }
            }
        }
    }
}
