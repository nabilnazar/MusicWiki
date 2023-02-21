package com.meghamlabs.musicwiki.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.meghamlabs.musicwiki.data.model.Album
import com.meghamlabs.musicwiki.databinding.FragmentGenreAlbumsBinding
import com.meghamlabs.musicwiki.domain.repository.Repository
import com.meghamlabs.musicwiki.ui.adapters.GenreAlbumsAdapter
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModel
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModelFactory
import retrofit2.HttpException
import java.io.IOException


class GenreAlbumsFragment(var genreName: String) : Fragment() {

    private lateinit var binding: FragmentGenreAlbumsBinding
    private lateinit var genreAlbumsAdapter: GenreAlbumsAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentGenreAlbumsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getTagTopAlbums(genreName.toString())
        viewModel.tagTopAlbumsResponse.observe(viewLifecycleOwner) { topAlbums ->
            setupRecyclerView()
            genreAlbumsAdapter.genreAlbums = topAlbums
        }


    }

    private fun setupRecyclerView() =binding.albumsRv.apply {
        genreAlbumsAdapter = GenreAlbumsAdapter(context)
        adapter = genreAlbumsAdapter
        layoutManager = LinearLayoutManager(context)
    }
}
