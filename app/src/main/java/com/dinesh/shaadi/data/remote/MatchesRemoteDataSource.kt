package com.dinesh.shaadi.data.remote

import javax.inject.Inject

class MatchesRemoteDataSource @Inject constructor(
    private val characterService: MatchesService
) : BaseDataSource() {
    suspend fun getMatches(limit: Int = 10) = getResult { characterService.getMatches(limit) }
}