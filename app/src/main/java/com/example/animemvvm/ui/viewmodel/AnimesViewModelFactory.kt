package com.example.animemvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animemvvm.data.repository.AnimeRepository

class AnimesViewModelFactory(private val animesRepository: AnimeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimesViewModel(animesRepository) as T
    }
}