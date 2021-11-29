package com.anushmp.takeawaymovieassignment.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anushmp.takeawaymovieassignment.R
import com.anushmp.takeawaymovieassignment.datamodels.remote.Results
import com.bumptech.glide.Glide

class PopularMoviesAdapter(val context:Context,val resultList :ArrayList<Results?>,
val onTapListner: OnTapListner):
RecyclerView.Adapter<PopularMoviesAdapter.vh>(){

    inner class vh(view:View):RecyclerView.ViewHolder(view){

        var layout: RelativeLayout = view.findViewById(R.id.movieItemRelativeLayout)
        var poster:ImageView = view.findViewById(R.id.moviePosterSmall)
        var title:TextView = view.findViewById(R.id.movieName)
        var popularity:TextView = view.findViewById(R.id.moviePopularity)

       fun setDataInView(result:Results?){

           var url = "https://image.tmdb.org/t/p/w500/"
           url += result?.poster_path

           Glide.with(context)
               .load(url)
               .placeholder(R.drawable.ic_launcher_foreground)
               .into(poster)


           var titlePrefix = "Title: \n" + result?.title
           var popularityPrefix = "Popularity: \n" + result?.popularity.toString()

           title.text = titlePrefix
           popularity.text = popularityPrefix



       }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.popular_movie_item_layout,parent,false)
        return vh(view)

    }

    override fun onBindViewHolder(holder: vh, position: Int) {

        holder.setDataInView(resultList[position])

        holder.layout.setOnClickListener {
            onTapListner.onTap(resultList[position])
        }

    }

    override fun getItemCount(): Int {
        return resultList.size
    }

}