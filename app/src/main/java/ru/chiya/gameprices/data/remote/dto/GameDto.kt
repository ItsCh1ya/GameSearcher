package ru.chiya.gameprices.data.remote.dto

import ru.chiya.gameprices.domain.model.Game

data class GameDto(
    val cheapest: String,
    val cheapestDealID: String,
    val external: String,
    val gameID: String,
    val internalName: String,
    val steamAppID: String,
    val thumb: String
) {
    fun toGame(): Game {
        return Game(
            cheapest, external, gameID, thumb
        )
    }
}
