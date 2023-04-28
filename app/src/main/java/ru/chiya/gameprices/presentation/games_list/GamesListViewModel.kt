package ru.chiya.gameprices.presentation.games_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.chiya.gameprices.common.Resource
import ru.chiya.gameprices.domain.use_case.GetGamesUseCase
import javax.inject.Inject

@HiltViewModel
class GamesListViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(GamesListState())
    val state: State<GamesListState> = _state

    init {
        getGames("Batman") //TODO: hardcoded text
    }

    fun getGames(string: String){
        getGamesUseCase(string).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = GamesListState(games = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = GamesListState(error = result.message ?: "The bruh moment")
                }
                is Resource.Loading -> {
                    _state.value = GamesListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
