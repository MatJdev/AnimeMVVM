package com.example.animemvvm.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animemvvm.data.model.Animes
import com.example.animemvvm.data.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimesViewModel (private val animesRepository: AnimeRepository): ViewModel() {

    init {
        viewModelScope.launch (Dispatchers.IO) {
            animesRepository.getAnimes()
        }
    }

    val animes : LiveData<Animes>
    get() = animesRepository.animes
}