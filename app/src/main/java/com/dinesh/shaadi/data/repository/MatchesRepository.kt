package com.dinesh.shaadi.data.repository

import androidx.lifecycle.MutableLiveData
import com.dinesh.shaadi.data.entities.MatchDisplayModel
import com.dinesh.shaadi.data.local.MatchesDao
import com.dinesh.shaadi.data.remote.MatchesRemoteDataSource
import com.dinesh.shaadi.util.RequestResource
import javax.inject.Inject

class MatchesRepository @Inject constructor(
    private val mRemoteDataSource: MatchesRemoteDataSource,
    private val mLocalDataSource: MatchesDao
) {
    fun getMatches() = mLocalDataSource.getAllMatches()

    suspend fun updateMatchData(item: MatchDisplayModel) = mLocalDataSource.update(item)

    suspend fun requestMatches(limit: Int, requestStates: MutableLiveData<RequestResource.Status>) {
        mLocalDataSource.deleteAll()
        requestStates.value = RequestResource.Status.LOADING
        mRemoteDataSource.getMatches(limit).also {
            if (it.status == RequestResource.Status.SUCCESS) {
                mLocalDataSource.insertAll(it.data?.results?.map { respModel ->
                    MatchDisplayModel(
                        name = "${respModel.name?.title} ${respModel.name?.first} ${respModel.name?.last}",
                        location = "${respModel.location?.city}, ${respModel.location?.state}",
                        image = respModel.picture?.large ?: "",
                        age = "${respModel.dob?.age} yrs",
                        gender = "${respModel.gender}"
                    )
                }!!.toMutableList())
            }
            requestStates.value = it.status
        }

    }
}