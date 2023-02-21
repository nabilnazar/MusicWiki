import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meghamlabs.musicwiki.R
import com.meghamlabs.musicwiki.data.model.Tag
import com.meghamlabs.musicwiki.databinding.ActivityMainBinding
import com.meghamlabs.musicwiki.databinding.GenreItemBinding
import com.meghamlabs.musicwiki.ui.GenreInfoActivity

class TopGenreAdapter(var mContext: Context) : RecyclerView.Adapter<TopGenreAdapter.TopGenreViewHolder>() {

    inner class TopGenreViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root)

    var genres: List<Tag> = emptyList()
    private var showAllRows: Boolean = false




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopGenreViewHolder {
        return TopGenreViewHolder(GenreItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TopGenreViewHolder, position: Int) {
        holder.binding.apply {
            // Calculate the indices of the genres to be displayed in the row
            val startIndex = position * 3
            val endIndex = minOf(startIndex + 3, genres.size)
            val rowGenres = genres.subList(startIndex, endIndex)

            // Set the text of each TextView to the name of the corresponding genre in the row
            genre1.text = rowGenres.getOrNull(0)?.name ?: ""
            genre2.text = rowGenres.getOrNull(1)?.name ?: ""
            genre3.text = rowGenres.getOrNull(2)?.name ?: ""

            // Hide the row if it's not the first row and showAllRows is false
            root.visibility = if (position > 0 && !showAllRows) View.GONE else View.VISIBLE
        }
        holder.binding.genre1.setOnClickListener {
            // Calculate the actual position of the clicked genre
            val row = position
            val col = 0
            val actualPos = row * 3 + col

            // Set click listener to open GenreInfoActivity with the name of the selected genre
            val mIntent = Intent(mContext, GenreInfoActivity::class.java)
            mIntent.putExtra("NAME", genres.getOrNull(actualPos)?.name ?: "")
            mContext.startActivity(mIntent)
        }
        holder.binding.genre2.setOnClickListener {
            // Calculate the actual position of the clicked genre
            val row = position
            val col = 1
            val actualPos = row * 3 + col

            // Set click listener to open GenreInfoActivity with the name of the selected genre
            val mIntent = Intent(mContext, GenreInfoActivity::class.java)
            mIntent.putExtra("NAME", genres.getOrNull(actualPos)?.name ?: "")
            mContext.startActivity(mIntent)
        }
        holder.binding.genre3.setOnClickListener {
            // Calculate the actual position of the clicked genre
            val row = position
            val col = 2
            val actualPos = row * 3 + col

            // Set click listener to open GenreInfoActivity with the name of the selected genre
            val mIntent = Intent(mContext, GenreInfoActivity::class.java)
            mIntent.putExtra("NAME", genres.getOrNull(actualPos)?.name ?: "")
            mContext.startActivity(mIntent)
        }
    }


    override fun getItemCount(): Int {
        // Calculate the number of rows based on the number of genres
        return (genres.size + 2) / 3
    }

    fun setShowAllRows(showAll: Boolean) {
        showAllRows  = showAll
        notifyDataSetChanged()
    }
}
