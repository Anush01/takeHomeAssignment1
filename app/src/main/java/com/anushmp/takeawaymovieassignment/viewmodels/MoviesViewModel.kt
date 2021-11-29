package com.anushmp.takeawaymovieassignment.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushmp.takeawaymovieassignment.datamodels.remote.MovieDetailResponse
import com.anushmp.takeawaymovieassignment.datamodels.remote.Results
import com.anushmp.takeawaymovieassignment.repositories.MovieDataRepository
import com.anushmp.takeawaymovieassignment.views.MovieDetailActivity
import com.anushmp.takeawaymovieassignment.views.adapters.OnTapListner
import com.anushmp.takeawaymovieassignment.views.adapters.PopularMoviesAdapter

class MoviesViewModel(val repo: MovieDataRepository, val context:Context):ViewModel(),OnTapListner {

    var responseList = repo.responseList
    var responseListLiveData = MutableLiveData<List<Results?>>()
    var pageNumber: Int = 1
    var popularMoviesAdapter = PopularMoviesAdapter(context,responseList,this)
    var linearLayoutManager = LinearLayoutManager(context)

    var movieDetailResponse = repo.movieDetailResponse
    var movieDetailLiveData = MutableLiveData<MovieDetailResponse?>()


    fun incrementPageNumber(){
        pageNumber++
    }

    fun returnMoviesLiveData(): MutableLiveData<List<Results?>>{

        return responseListLiveData

    }

    fun returnMovieDetailLiveData(): MutableLiveData<MovieDetailResponse?>{

        return movieDetailLiveData

    }

    fun repoGetPopularMovies(){

        repo.RepoGetPopularMovies(pageNumber,responseListLiveData)

    }

    fun repoGetThisMoviesDetails(movieId:String?){

        repo.RepoGetMovieDetails(movieId, movieDetailLiveData)

    }

    fun returnMovieDetailResponse(): MovieDetailResponse?{

        return repo.movieDetailResponse

    }



    override fun onTap(result: Results?) {

        val i = Intent(context,MovieDetailActivity::class.java)

        i.putExtra("movieid",result?.id.toString())

        context.startActivity(i)

    }

}