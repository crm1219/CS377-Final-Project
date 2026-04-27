package com.example.art.ui

// imports
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil3.load
import com.example.art.R
import com.example.art.databinding.FragmentArtDetailsBinding
import com.example.art.viewmodel.ArtDetailsViewModel

// UI for details for one selected artwork
class ArtDetailsFragment : Fragment(R.layout.fragment_art_details) {

    private var _binding: FragmentArtDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentArtDetailsBinding.bind(view)

        val artworkId = requireArguments().getInt("artworkId")
        viewModel.loadArtwork(artworkId)

        viewModel.artwork.observe(viewLifecycleOwner) { artwork ->
            artwork ?: return@observe

            binding.ivArtworkDetail.load(artwork.imageId)
            binding.tvArtworkTitle.text = artwork.title
            binding.tvArtist.text = "Artist: ${artwork.artistTitle ?: "Unknown artist."}"
            binding.tvDate.text = "Date: ${artwork.dateDisplay ?: "Unknown."}"
            binding.tvMedium.text = "Medium: ${artwork.mediumDisplay ?: "Unknown."}"
            binding.tvOrigin.text = "Origin: ${artwork.placeOfOrigin ?: "Unknown."}"
            binding.tvDescription.text = artwork.description ?: "No description available."
        }

        // change button text depending on favorite state (add/remove)
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            binding.btnFavorite.text =
                if (isFavorite) "Remove from Favorites" else "Add to Favorites"
        }

        // toggle favorite button when clicked
        binding.btnFavorite.setOnClickListener {
            viewModel.toggleFavorite()
        }

        // show errors (if needed)
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isNullOrBlank()) {
                binding.tvDetailError.visibility = View.GONE
            } else {
                binding.tvDetailError.visibility = View.VISIBLE
                binding.tvDetailError.text = errorMessage
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}