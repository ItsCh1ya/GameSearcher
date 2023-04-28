package ru.chiya.gameprices.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.chiya.gameprices.data.remote.dto.GameDto

interface CheapSharkApi {
    @GET("/api/1.0/games")
    suspend fun getGames(
        @Query("title") title: String
    ) : List<GameDto>
}
