package com.example.flixter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
class MovieAdapter(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    //Expensive operation:create a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent, false)
        return ViewHolder(view)
    }

    //Cheap simply bind data to an existing viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() =  movies.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        //where were going to attach the logic to attack a onClickListener
        fun bind(movie: Movie){
        tvTitle.text = movie.title
        tvOverview.text = movie.overview
        Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
        }
        // init block needed to register the clickListener
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            //1. get notified of the particular movie we just tapped on
            val movie = movies[adapterPosition]
            //2. Use the intent system to navigate to the new screen
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }

}