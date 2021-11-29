package com.anushmp.takeawaymovieassignment.datamodels.remote

import com.anushmp.takeawaymovieassignment.datamodels.remote.apitest.Json4Kotlin_Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    //popular?api_key=328c283cd27bd1877d9080ccb1604c91&sort_by=release_date.desc&language=en-US&page=2
    @GET("popular")
    fun getPopularMovies(@Query("api_key") apikey:String,
                         @Query("sort_by") release_dateDOTdesc:String,
                         @Query("language") enDASHUS:String,
                         @Query("page") page:Int
    ): Call<PopularMoviesResponse>


    ///movie/675445?api_key=328c283cd27bd1877d9080ccb1604c91&language=en-US
    @GET("{id}")
    fun getMovieDetails(@Path("id") id:String?,
                        @Query("api_key")apikey: String,
                        @Query("language")enDASHUS: String
    ):Call<MovieDetailResponse>

    //movie/movie/ was the issue. lmao

    @GET("654974?api_key=328c283cd27bd1877d9080ccb1604c91&language=en-US")
    fun getMovieTest():Call<Json4Kotlin_Base>


}