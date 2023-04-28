package ru.chiya.gameprices.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.chiya.gameprices.common.Resource
import ru.chiya.gameprices.domain.model.Game
import ru.chiya.gameprices.domain.repository.GameRepository
import java.io.IOException
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(title: String): Flow<Resource<List<Game>>> = flow {
        try {
            emit(Resource.Loading())
            val games = repository.getGames(title).map { it.toGame() }
            emit(Resource.Success(games))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}
