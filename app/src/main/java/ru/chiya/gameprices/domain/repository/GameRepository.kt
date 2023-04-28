package ru.chiya.gameprices.domain.repository

import ru.chiya.gameprices.data.remote.dto.GameDto

interface GameRepository {
    suspend fun getGames(title: String): List<GameDto>
}
