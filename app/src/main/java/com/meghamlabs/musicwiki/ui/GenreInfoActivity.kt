package com.meghamlabs.musicwiki.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.meghamlabs.musicwiki.ui.adapters.ViewPagerAdapter

import com.google.android.material.tabs.TabLayout
import com.meghamlabs.musicwiki.data.model.TagX
import com.meghamlabs.musicwiki.databinding.ActivityGenreInfoBinding
import com.meghamlabs.musicwiki.domain.repository.Repository
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModel
import com.meghamlabs.musicwiki.ui.viewmodel.MainViewModelFactory
import retrofit2.HttpException
import java.io.IOException


class GenreInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGenreInfoBinding
    lateinit var viewPagerAdapter  : ViewPagerAdapter

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ints = intent
        val name = ints.getStringExtra("NAME")

        viewPagerAdapter =  ViewPagerAdapter(this, name.toString())
        binding.viewpager.adapter = viewPagerAdapter


        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewpager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tablayout.getTabAt(position)!!.select()
            }
        })

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getTagInfo(name.toString())
        viewModel.tagInfoResponse.observe(this) { info: TagX ->
            binding.genreTitle.text = info.name
            binding.genreDesc.text = info.wiki.summary
        }



    }
}