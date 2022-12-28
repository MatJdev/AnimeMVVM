package com.example.animemvvm.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animemvvm.R
import com.example.animemvvm.data.model.Data
import com.example.animemvvm.databinding.EachItemBinding
import android.content.Context
import com.example.animemvvm.data.model.Animes

class AnimeAdapter(var context: Context,
                   private val animeList:ArrayList<Data>
                   ) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    class AnimeViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val binding = EachItemBinding.bind(itemView)

        fun bind(animes: Data, context: Context) {
            Glide.with(context).load(animes.image).into(binding.imageView)
            binding.textView.text = animes.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.each_item,parent,false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        holder.bind(anime, context)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}