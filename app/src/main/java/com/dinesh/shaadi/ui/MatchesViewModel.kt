package com.dinesh.shaadi.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinesh.shaadi.data.entities.MatchDisplayModel
import com.dinesh.shaadi.data.repository.MatchesRepository
import com.dinesh.shaadi.util.RequestResource
import kotlinx.coroutines.launch

class MatchesViewModel @ViewModelInject constructor(
    private val localRepository: MatchesRepository
) : ViewModel() {
    private val _loadingStates = MutableLiveData<RequestResource.Status>()
    val loadingObserver: LiveData<RequestResource.Status>
        get() = _loadingStates

    val matches = localRepository.getMatches()

    fun requestMatches(limit: Int = 10) = viewModelScope.launch {
        localRepository.requestMatches(limit, _loadingStates)
    }

    fun updateItem(matchDisplayModel: MatchDisplayModel) = viewModelScope.launch {
        localRepository.updateMatchData(matchDisplayModel)
    }
}