package com.example.mobilliumcase.ui.main

import androidx.lifecycle.*
import com.example.mobilliumcase.data.model.Content
import com.example.mobilliumcase.data.resource.Resource
import com.example.mobilliumcase.repository.TmdbRepository
import com.github.ajalt.timberkt.i
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val tmdbRepository: TmdbRepository
) : ViewModel() {
    val _movieList: MutableLiveData<Resource<Content>> = MutableLiveData()
    val movies: LiveData<Resource<Content>>
        get() = _movieList

    val _nowPlayingList: MutableLiveData<Resource<Content>> = MutableLiveData()
    val nowPlayingList: LiveData<Resource<Content>>
        get() = _nowPlayingList

    fun getUpcomingMovies(map: Map<String, String>) {
        viewModelScope.launch {
            tmdbRepository.getUpcomingMovies(map).collect {
                _movieList.postValue(it)
            }
        }
    }

    fun getNowPlayingMovies(map: Map<String, String>){
        viewModelScope.launch {
            tmdbRepository.getNowPlayingMovies(map).collect {
                _nowPlayingList.postValue(it)
            }
        }
    }
}