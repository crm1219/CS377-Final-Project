package com.example.art.ui

// imports
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.art.R
import com.example.art.databinding.FragmentArtBinding
import com.example.art.viewmodel.ArtViewModel

// main UI that displays artwork list and search bar
class ArtFragment : Fragment(R.layout.fragment_art) {

    private var _binding: FragmentArtBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtViewModel by viewModels()

    private lateinit var adapter: ArtAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentArtBinding.bind(view)

        // when the user clicks an artwork, navigate to the details screen
        adapter = ArtAdapter { artwork ->
            val bundle = Bundle().apply {
                putInt("artworkId", artwork.id)
            }

            findNavController().navigate(
                R.id.action_artFragment_to_artDetailsFragment,
                bundle
            )
        }

        binding.rvArtworks.adapter = adapter

        // search button
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text?.toString().orEmpty()
            viewModel.searchArtworks(query)
        }

        // update RecyclerView when artwork list changes
        viewModel.artworks.observe(viewLifecycleOwner) { artworks ->
            adapter.submitList(artworks)
        }

        // show/hide loading spinner
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility =
                if (isLoading) View.VISIBLE else View.GONE
        }

        // show/hide error test
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isNullOrBlank()) {
                binding.tvError.visibility = View.GONE
            } else {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = errorMessage
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}