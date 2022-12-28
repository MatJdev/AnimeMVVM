package com.example.animemvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.GridLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.animemvvm.R
import com.example.animemvvm.core.RetrofitHelper
import com.example.animemvvm.data.model.Data
import com.example.animemvvm.data.network.AnimeApiClient
import com.example.animemvvm.data.repository.AnimeRepository
import com.example.animemvvm.databinding.ActivityAnimesRecyclerViewBinding
import com.example.animemvvm.databinding.ActivityMainBinding
import com.example.animemvvm.ui.viewmodel.AnimesViewModel
import com.example.animemvvm.ui.viewmodel.AnimesViewModelFactory

class AnimesRecyclerView : AppCompatActivity() {

    //viewBinding para enlazar componentes graficos con el código
    private lateinit var binding: ActivityAnimesRecyclerViewBinding

    private lateinit var animeList: ArrayList<Data>
    private lateinit var animeAdapter: AnimeAdapter

    private lateinit var animesViewModel: AnimesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimesRecyclerViewBinding.inflate(layoutInflater) //viewBinding
        setContentView(binding.root) //viewBinding

        init()


    }

    private fun init(){
        val apiInterface = RetrofitHelper.getRetrofit().create(AnimeApiClient::class.java)

        val animesRepository = AnimeRepository(apiInterface)

        animesViewModel = ViewModelProvider(this, AnimesViewModelFactory(animesRepository)).get(
            AnimesViewModel::class.java)

        binding.animeRecyclerView.setHasFixedSize(true)
        binding.animeRecyclerView.layoutManager = GridLayoutManager(this, 2)

        animeList = ArrayList()

        animesViewModel.animes.observe(this, Observer { currentMovie ->
            for (i in currentMovie.data) {
                animeList.add(i)
                Log.i("TAG", i.toString())
            }
            binding.animeRecyclerView.adapter = AnimeAdapter(this, animeList)
        })

        //animeList = animesViewModel.animes


    }
}