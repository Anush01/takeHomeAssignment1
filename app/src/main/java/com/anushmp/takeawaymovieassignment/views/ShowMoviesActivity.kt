package com.anushmp.takeawaymovieassignment.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.anushmp.takeawaymovieassignment.R
import com.anushmp.takeawaymovieassignment.repositories.MovieDataRepository
import com.anushmp.takeawaymovieassignment.viewmodels.MovieDataFactory
import com.anushmp.takeawaymovieassignment.viewmodels.MoviesViewModel
import com.anushmp.takeawaymovieassignment.views.adapters.OnTapListner

class ShowMoviesActivity : AppCompatActivity() {


    lateinit var pullToRefresh: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar
    lateinit var movieDisplayRecyclerView: RecyclerView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movies)

        val repository = MovieDataRepository()
        val moviesViewModel = ViewModelProvider(this,MovieDataFactory(repository, this))
            .get(MoviesViewModel::class.java)

        //view
        pullToRefresh = findViewById(R.id.pullToRefresh)
        progressBar = findViewById(R.id.LoadingMovies)
        movieDisplayRecyclerView = findViewById(R.id.MovieDisplayRecyclerView)

        //what happens when the user pulls to refresh?
        pull()

        //get data from api and fill list
        moviesViewModel.repoGetPopularMovies()

        //setup recyclerView
        movieDisplayRecyclerView.adapter = moviesViewModel.popularMoviesAdapter
        movieDisplayRecyclerView.layoutManager = moviesViewModel.linearLayoutManager


        movieDisplayRecyclerView.addOnScrollListener(object:OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

             if(!movieDisplayRecyclerView.canScrollVertically(1)){
                 Toast.makeText(this@ShowMoviesActivity,"End of List, Refreshing Now",Toast.LENGTH_LONG).show()
                 if(moviesViewModel.responseList.size>1){

                    // pageNumber++
                     moviesViewModel.incrementPageNumber()

                    // repository.RepoGetPopularMovies(pageNumber,responseListLiveData)
                     moviesViewModel.repoGetPopularMovies()
                 }
             }

            }
        })





        //subscribe to the livedata of popular movies.
        moviesViewModel.returnMoviesLiveData().observe(this,{

            movieDisplayRecyclerView.adapter?.notifyDataSetChanged()
            progressBar.visibility = View.GONE

        })







    }

    private fun pull() {

        pullToRefresh.setOnRefreshListener {

            Toast.makeText(this,"refreshing list",Toast.LENGTH_SHORT).show()

            pullToRefresh.isRefreshing = false
            //progressBar.visibility = View.GONE


        }

    }
}