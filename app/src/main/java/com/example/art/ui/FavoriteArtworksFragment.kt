package com.example.art.ui

// imports
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.art.R
import com.example.art.databinding.FragmentFavoriteArtworksBinding
import com.example.art.viewmodel.FavoriteArtworksViewModel

// UI for all saved favorites from database
class FavoriteArtworksFragment : Fragment(R.layout.fragment_favorite_artworks) {

    private var _binding: FragmentFavoriteArtworksBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteArtworksViewModel by viewModels()

    // TODO
    private lateinit var adapter: FavoriteArtworkAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavoriteArtworksBinding.bind(view)

        // clicking a favorite artwork opens details screen
        adapter = FavoriteArtworkAdapter { artwork ->
            val bundle = Bundle().apply {
                putInt("artworkId", artwork.id)
            }

            findNavController().navigate(
                R.id.action_favoriteArtworksFragment_to_artDetailsFragment,
                bundle
            )
        }

        binding.rvFavorites.adapter = adapter

        // observe favorites list from Room database
        viewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            adapter.submitList(favorites)

            binding.tvEmpty.visibility =
                if (favorites.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}