package com.example.art.ui

// imports
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.art.data.model.Artwork
import com.example.art.databinding.ItemArtBinding

// RecyclerView adapter for the main artwork list
class ArtAdapter(
    private val artworks: MutableList<Artwork> = mutableListOf(),
    private val onClick: (Artwork) -> Unit
) : RecyclerView.Adapter<ArtAdapter.ArtViewHolder>() {

    inner class ArtViewHolder(
        private val binding: ItemArtBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(artwork: Artwork) {
            binding.tvArtworkTitle.text = artwork.title
            binding.tvArtistName.text = artwork.artistDisplay ?: "Unknown artist."
            binding.tvArtworkDate.text = artwork.dateDisplay ?: "Unknown."
            binding.ivArtwork.load(artwork.imageId)
            binding.root.setOnClickListener {
                onClick(artwork)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val binding = ItemArtBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ArtViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        holder.bind(artworks[position])
    }

    override fun getItemCount(): Int = artworks.size

    fun submitList(newArtworks: List<Artwork>) {
        artworks.clear()
        artworks.addAll(newArtworks)
        notifyDataSetChanged()
    }
}