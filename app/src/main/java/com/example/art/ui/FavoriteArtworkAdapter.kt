package com.example.art.ui

// imports
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.art.data.model.Artwork
import com.example.art.data.model.FavoriteArtworkEntity
import com.example.art.databinding.ItemArtBinding

// RecyclerView adapter for the favorites screen
class FavoriteArtworkAdapter(
    private val artworks: MutableList<FavoriteArtworkEntity> = mutableListOf(),
    private val onClick: (FavoriteArtworkEntity) -> Unit
) : RecyclerView.Adapter<FavoriteArtworkAdapter.FavoriteArtworkViewHolder>() {

    inner class FavoriteArtworkViewHolder(
        private val binding: ItemArtBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(artwork: FavoriteArtworkEntity) {
            binding.tvArtworkTitle.text = artwork.title
            binding.tvArtistName.text = artwork.artist_display ?: "Unknown artist."
            binding.tvArtworkDate.text = artwork.date_display ?: "Unknown."
            binding.ivArtwork.load(artwork.image_id)
            binding.root.setOnClickListener {
                onClick(artwork)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteArtworkViewHolder {
        val binding = ItemArtBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FavoriteArtworkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteArtworkViewHolder, position: Int) {
        holder.bind(artworks[position])
    }

    override fun getItemCount(): Int = artworks.size

    fun submitList(newArtworks: List<FavoriteArtworkEntity>) {
        artworks.clear()
        artworks.addAll(newArtworks)
        notifyDataSetChanged()
    }
}