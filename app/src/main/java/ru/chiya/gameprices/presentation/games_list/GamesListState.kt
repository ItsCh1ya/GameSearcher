package ru.chiya.gameprices.presentation.games_list

import ru.chiya.gameprices.domain.model.Game

data class GamesListState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val error: String = ""
)
