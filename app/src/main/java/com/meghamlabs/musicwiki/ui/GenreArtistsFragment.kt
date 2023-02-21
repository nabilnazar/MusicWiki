package com.meghamlabs.musicwiki.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.meghamlabs.musicwiki.databinding.FragmentGenreArtistsBinding
import com.meghamlabs.musicwiki.domain.repository.Repository
import com.meghamlabs.musicwiki.ui.adapters.GenreArtistsAdapter
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModel
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModelFactory

class GenreArtistsFragment(var genreName: String) : Fragment() {

    private lateinit var binding: FragmentGenreArtistsBinding
    private lateinit var genreArtistsAdapter: GenreArtistsAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenreArtistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getTagTopArtists(genreName)
        viewModel.tagTopArtistsResposne.observe(viewLifecycleOwner) { topArtists ->
            setupRecyclerView()
            genreArtistsAdapter.genreArtists = topArtists
        }


    }

    private fun setupRecyclerView() =binding.artistsRv.apply {
        genreArtistsAdapter = GenreArtistsAdapter(context)
        adapter = genreArtistsAdapter
        layoutManager = LinearLayoutManager(context)
    }
}