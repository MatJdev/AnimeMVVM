package com.example.animemvvm.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.animemvvm.R
import com.example.animemvvm.core.RetrofitHelper
import com.example.animemvvm.data.network.AnimeApiClient
import com.example.animemvvm.data.repository.AnimeRepository
import com.example.animemvvm.databinding.ActivityMainBinding
import com.example.animemvvm.ui.viewmodel.AnimesViewModel
import com.example.animemvvm.ui.viewmodel.AnimesViewModelFactory

class MainActivity : AppCompatActivity() {

    //viewBinding para enlazar componentes graficos con el código
    private lateinit var binding: ActivityMainBinding

    private lateinit var animesViewModel: AnimesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //viewBinding
        setContentView(binding.root) //viewBinding

        val apiInterface = RetrofitHelper.getRetrofit().create(AnimeApiClient::class.java)

        val animesRepository = AnimeRepository(apiInterface)

        animesViewModel = ViewModelProvider(this, AnimesViewModelFactory(animesRepository)).get(
            AnimesViewModel::class.java)


        binding.btnGenerateAnime.setOnClickListener {
            randomAnime()
        }

        binding.btnAnimeList.setOnClickListener {
            startActivity(Intent(this, AnimesRecyclerView::class.java))
        }
    }

    private fun randomAnime() {
        animesViewModel.animes.observe(this, Observer { currentMovie ->
            var rand = (0..99).random()
            binding.tvMovie.text = currentMovie.data[rand].title
            binding.tvYear.text = "Genre: " + currentMovie.data[rand].genres[0]

            if (currentMovie.data[rand].hasEpisode) {
                binding.tvGenre.text = "Episodes: " + currentMovie.data[rand].episodes.toString()
            }


            //Glide.with(this).load(currentMovie.data[rand].image).into(binding.iv1)
            Glide.with(this).load(currentMovie.data[rand].image).into(binding.ivPortada)
        })
    }
}