package com.anushmp.takeawaymovieassignment.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.anushmp.takeawaymovieassignment.R
import com.anushmp.takeawaymovieassignment.datamodels.remote.MovieDetailResponse
import com.anushmp.takeawaymovieassignment.datamodels.remote.MoviesApi
import com.anushmp.takeawaymovieassignment.datamodels.remote.RetrofitNetworker
import com.anushmp.takeawaymovieassignment.datamodels.remote.apitest.Json4Kotlin_Base
import com.anushmp.takeawaymovieassignment.repositories.MovieDataRepository
import com.anushmp.takeawaymovieassignment.viewmodels.MovieDataFactory
import com.anushmp.takeawaymovieassignment.viewmodels.MoviesViewModel
import com.google.android.material.button.MaterialButton
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {


    lateinit var movieDetailTitle:TextView
    lateinit var movieDetailSynopsis:TextView
    lateinit var movieDetailGenres:TextView
    lateinit var movieDetailLanguage:TextView
    lateinit var movieDetailDuration:TextView
    lateinit var bookMovieButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieDetailTitle = findViewById(R.id.MovieDetailTitle)
        movieDetailSynopsis = findViewById(R.id.MovieDetailSynopsis)
        movieDetailGenres = findViewById(R.id.MovieDetailGenres)
        movieDetailLanguage = findViewById(R.id.MovieDetailLanguage)
        movieDetailDuration = findViewById(R.id.MovieDetailDuration)
        bookMovieButton = findViewById(R.id.BookMovieButton)

        val repository = MovieDataRepository()
        val movieDetailViewModel = ViewModelProvider(this, MovieDataFactory(repository, this))
            .get(MoviesViewModel::class.java)



        var movieId = intent.getStringExtra("movieid")

        Log.d("AnushLogsQ",movieId.toString())

        movieDetailViewModel.repoGetThisMoviesDetails(movieId)

        var titleToSend:String? = ""


        movieDetailViewModel.returnMovieDetailLiveData().observe(this,{

            movieDetailTitle.text = it?.title
            movieDetailSynopsis.text = it?.overview
            titleToSend = it?.title

            if(it !== null){

                for(i in 1 until it.genres.size){

                    movieDetailGenres.append(it.genres[i].name)
                    movieDetailGenres.append("\n")

                }

            }

            movieDetailLanguage.text = "Language: " + it?.original_language
            movieDetailDuration.text = "Duration: " + it?.runtime.toString() + " minutes"



        })

        bookMovieButton.setOnClickListener {

            var i = Intent(this, BookMovieActivity::class.java)
            i.putExtra("title",titleToSend)
            startActivity(i)

        }

        /*

        val retrofit = RetrofitNetworker.getRetrofitInstance()
        val api = retrofit.create(MoviesApi::class.java)

        api.getMovieTest().enqueue(object: Callback<Json4Kotlin_Base>{
            override fun onResponse(
                call: Call<Json4Kotlin_Base>,
                response: Response<Json4Kotlin_Base>
            ) {
                Log.d("AnushLogsQ",response.code().toString())
                Log.d("AnushLogsQ",response.raw().toString())
            }

            override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {
                Log.d("AnushLogsQ","Lmao it did not work")
            }



        })


         */
    }
}