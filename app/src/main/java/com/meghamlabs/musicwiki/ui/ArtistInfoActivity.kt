package com.meghamlabs.musicwiki.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.meghamlabs.musicwiki.databinding.ActivityArtistInfoBinding
import com.meghamlabs.musicwiki.domain.repository.Repository
import com.meghamlabs.musicwiki.loadImage
import com.meghamlabs.musicwiki.ui.adapters.AlbumInfoGenreAdapter
import com.meghamlabs.musicwiki.ui.adapters.ArtistTopAlbumsAdapter
import com.meghamlabs.musicwiki.ui.adapters.ArtistTopTracksAdapter
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModel
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModelFactory

class ArtistInfoActivity : AppCompatActivity() {

        private lateinit var binding: ActivityArtistInfoBinding
        lateinit var albumInfoGenreAdapter: AlbumInfoGenreAdapter
        lateinit var artistTopTracksAdapter: ArtistTopTracksAdapter
        lateinit var artistTopAlbumsAdapter: ArtistTopAlbumsAdapter
        private lateinit var viewModel: MainViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityArtistInfoBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setUpRecyclerView()
            setUpRecyclerView1()
            setUpRecyclerView2()

            val aints = intent
            val arname = aints.getStringExtra("AR-NAME")

            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
            viewModel.getArtistInfo(arname.toString())
            viewModel.artistInfoResponse.observe(this) { response ->

                setUpRecyclerView()
                albumInfoGenreAdapter.albumInfoGenre = response.tags.tag
                binding.artistTitle.text = response.name
                binding.artistPlaycount.text = response.stats.playcount
                binding.artistListeners.text = response.stats.listeners
                binding.artistImg.loadImage(response.image[0].text)

            }

            viewModel.getArtistTopTracks(arname.toString())
            viewModel.artistTopTracksResponse.observe(this) { topTracks ->
                setUpRecyclerView1()
                artistTopTracksAdapter.artistInfoTopTracks = topTracks
            }



            viewModel.getArtistTopAlbums(arname.toString())
            viewModel.artistTopAlbumsResponse.observe(this) { topAlbums ->
                setUpRecyclerView2()
                artistTopAlbumsAdapter.artistTopAlbums = topAlbums
            }


        }
        private fun setUpRecyclerView()  = binding.artistInfoGenreRv.apply {
            albumInfoGenreAdapter = AlbumInfoGenreAdapter(this@ArtistInfoActivity)
            adapter = albumInfoGenreAdapter
            layoutManager = LinearLayoutManager(this@ArtistInfoActivity, LinearLayoutManager.HORIZONTAL,false)
        }

        private fun setUpRecyclerView1() = binding.artistInfoToptracksRv.apply {
            artistTopTracksAdapter = ArtistTopTracksAdapter()
            adapter = artistTopTracksAdapter
            layoutManager = LinearLayoutManager(this@ArtistInfoActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        private fun setUpRecyclerView2() = binding.artistInfoTopalbumsRv.apply {
            artistTopAlbumsAdapter = ArtistTopAlbumsAdapter(this@ArtistInfoActivity)
            adapter = artistTopAlbumsAdapter
            layoutManager = LinearLayoutManager(this@ArtistInfoActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

