package com.anushmp.takeawaymovieassignment.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.anushmp.takeawaymovieassignment.datamodels.remote.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataRepository {

    val api_Key = "328c283cd27bd1877d9080ccb1604c91"

    var Retrofit = RetrofitNetworker.getRetrofitInstance()
    var api = Retrofit.create(MoviesApi::class.java)

    var responseList:ArrayList<Results?> = ArrayList()
    var movieDetailResponse:MovieDetailResponse? = null


    fun RepoGetPopularMovies(page:Int, ld:MutableLiveData<List<Results?>> ){

        //var repoResponse:PopularMoviesResponse? = null

         api.getPopularMovies(api_Key,
               "release_date.desc",
               "en-US",
               page).enqueue(object : Callback<PopularMoviesResponse>{
             override fun onResponse(
                 call: Call<PopularMoviesResponse>,
                 response: Response<PopularMoviesResponse>
             ) {
                 var repoResponse = response.body()

                 if(repoResponse != null){

                     responseList.addAll(repoResponse.results)

                 }

                 ld.postValue(responseList)

             }

             override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                 Log.d("MovieDataRepositoryLogs","Failed to get OnFailure Triggered")
             }

         })


       //return repoResponse

    }

    fun RepoGetMovieDetails(MovieId:String?, moviedetailLiveData: MutableLiveData<MovieDetailResponse?>){

        Log.d("AnushLogsQ",MovieId.toString())

        api.getMovieDetails(MovieId,
        api_Key,
        "en-US").enqueue(object: Callback<MovieDetailResponse>{
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                //RepoResponse = response.body()
                movieDetailResponse = response.body()

                Log.d("AnushLogsQ",response.body().toString())
                response.body()?.let { Log.d("AnushLogsQ", it.title) }
                moviedetailLiveData.postValue(movieDetailResponse)
            }


            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.d("MovieDataRepositoryLogs","Failed to get OnFailure Triggered")
            }



        })

    }

}