package ru.chiya.gameprices.data.repository

import ru.chiya.gameprices.data.remote.CheapSharkApi
import ru.chiya.gameprices.data.remote.dto.GameDto
import ru.chiya.gameprices.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: CheapSharkApi
) : GameRepository {
    override suspend fun getGames(title: String): List<GameDto> {
        return api.getGames(title)
    }
}
