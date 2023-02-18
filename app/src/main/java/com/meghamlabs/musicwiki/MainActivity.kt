package com.meghamlabs.musicwiki


import TopGenreAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.meghamlabs.musicwiki.databinding.ActivityMainBinding
import com.meghamlabs.musicwiki.domain.repository.Repository
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModel
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var genreAdapter: TopGenreAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var showAllBtn : Button
    private lateinit var hideAllBtn : Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        showAllBtn = binding.genreDown
        showAllBtn.setOnClickListener {
            showAll()
        }
        hideAllBtn = binding.genreUp
        hideAllBtn.setOnClickListener {
            hideAll()

        }



        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.topTagResponse.observe(this) { tags ->
            setUpRecyclerView()
            genreAdapter.genres = tags

    }


}

    private fun setUpRecyclerView()  = binding.genreRv.apply {
        genreAdapter = TopGenreAdapter(context)
        adapter = genreAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
    private fun showAll() {

         showAllBtn.visibility = View.GONE
         hideAllBtn.visibility = View.VISIBLE
        genreAdapter.setShowAllRows(true)
    }



    private fun hideAll() {

        showAllBtn.visibility = View.VISIBLE
        hideAllBtn.visibility =  View.GONE
        genreAdapter.setShowAllRows(false)
    }
}