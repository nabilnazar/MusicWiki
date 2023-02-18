import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meghamlabs.musicwiki.data.model.Tag
import com.meghamlabs.musicwiki.databinding.ActivityMainBinding
import com.meghamlabs.musicwiki.databinding.GenreItemBinding

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
