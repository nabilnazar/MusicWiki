package com.meghamlabs.musicwiki.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meghamlabs.musicwiki.data.model.TagXX
import com.meghamlabs.musicwiki.databinding.CardItemBinding
import com.meghamlabs.musicwiki.databinding.GenreItemBinding
import com.meghamlabs.musicwiki.loadImage
import com.meghamlabs.musicwiki.ui.AlbumInfoActivity
import com.meghamlabs.musicwiki.ui.GenreInfoActivity

class AlbumInfoGenreAdapter(var mContext: Context) : RecyclerView.Adapter<AlbumInfoGenreAdapter.AlbumInfoGenreViewHolder>() {
    inner class AlbumInfoGenreViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root)

    var albumInfoGenre:List<TagXX> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumInfoGenreViewHolder {
        return AlbumInfoGenreViewHolder(GenreItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: AlbumInfoGenreViewHolder, position: Int) {
        holder.binding.apply {
            genre1.text = albumInfoGenre[position].name
            if (position + 1 < albumInfoGenre.size) {
                genre2.text = albumInfoGenre[position + 1].name
                genre2.visibility = View.VISIBLE
            } else {
                genre2.visibility = View.GONE
            }
            if (position + 2 < albumInfoGenre.size) {
                genre3.text = albumInfoGenre[position + 2].name
                genre3.visibility = View.VISIBLE
            } else {
                genre3.visibility = View.GONE
            }
        }
        holder.binding.root.setOnClickListener {
            val mIntent = Intent(mContext, GenreInfoActivity::class.java)
            mIntent.putExtra("NAME",albumInfoGenre[position].name)
            mContext.startActivity(mIntent)
        }
    }


    override fun getItemCount(): Int {
        return  albumInfoGenre.size
    }
}