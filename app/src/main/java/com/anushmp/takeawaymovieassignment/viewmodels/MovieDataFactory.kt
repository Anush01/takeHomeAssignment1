package com.anushmp.takeawaymovieassignment.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushmp.takeawaymovieassignment.repositories.MovieDataRepository

class MovieDataFactory(val repo:MovieDataRepository, val context:Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(repo, context) as T
    }
}