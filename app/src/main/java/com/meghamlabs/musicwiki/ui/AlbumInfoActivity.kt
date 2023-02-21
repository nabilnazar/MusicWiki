package com.meghamlabs.musicwiki.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.meghamlabs.musicwiki.R
import com.meghamlabs.musicwiki.data.model.TagX
import com.meghamlabs.musicwiki.databinding.ActivityAlbumInfoBinding
import com.meghamlabs.musicwiki.domain.repository.Repository
import com.meghamlabs.musicwiki.loadImage
import com.meghamlabs.musicwiki.ui.adapters.AlbumInfoGenreAdapter
import com.meghamlabs.musicwiki.ui.adapters.GenreAlbumsAdapter
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModel
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModelFactory


class AlbumInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumInfoBinding
    lateinit var albumInfoGenreAdapter: AlbumInfoGenreAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val aints = intent
        val aname = aints.getStringExtra("ANAME")
        val arname = aints.getStringExtra("ARNAME")


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAlbumInfo(arname.toString(), aname.toString())
        viewModel.albumInfoResponse.observe(this) { info ->

            setUpRecyclerView()
            albumInfoGenreAdapter.albumInfoGenre = info.tags.tag
            binding.albumTitle.text = info.name
            binding.artistTitle.text = info.artist
            binding.albumDesc.text = info.wiki.summary
            binding.albumImg.loadImage(info.image[0].text)

        }

        /*lifecycleScope.launchWhenCreated {
            binding.albumInfoPb.isVisible = true
            val response  = try {
                RetrofitInstance.api.getAlbumInfo(arname.toString(), aname.toString())
            }catch (e: IOException){
                Log.d("TASG", "IOException "+e)
                binding.albumInfoPb.isVisible = false
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.d("TASG", "HttpException "+e)
                binding.albumInfoPb.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                albumInfoGenreAdapter.albumInfoGenre = response.body()!!.album.tags.tag
                binding.albumTitle.text = response.body()!!.album.name
                binding.artistTitle.text = response.body()!!.album.artist
                binding.albumDesc.text = response.body()!!.album.wiki.summary
                binding.albumImg.loadImage(response.body()!!.album.image[0].text)
            }else{
                Log.d("TASG", "Response not successful")
            }
            binding.albumInfoPb.isVisible = false
        }*/
    }

    private fun setUpRecyclerView()  = binding.albumInfoGenreRv.apply {
        albumInfoGenreAdapter = AlbumInfoGenreAdapter(this@AlbumInfoActivity)
        adapter = albumInfoGenreAdapter
        layoutManager = LinearLayoutManager(this@AlbumInfoActivity, LinearLayoutManager.HORIZONTAL,false)
    }
}