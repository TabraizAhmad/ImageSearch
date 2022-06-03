package com.electrolux.imagesearchapp.ui.main.seachImage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.electrolux.imagesearchapp.databinding.FragmentSearchImageBinding
import com.electrolux.imagesearchapp.network.model.Photo
import com.electrolux.imagesearchapp.utils.DebouncingTextChangeListener
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@WithFragmentBindings
@AndroidEntryPoint
class SearchImageFragment : Fragment(), ImageRVAdapter.OnItemClicked {


    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val viewModel: SearchImageViewModel by viewModels()

    //binding for searching views
    private lateinit var binding: FragmentSearchImageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchImageBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeWatcher()
        initializeRV()
    }

    private fun initializeWatcher() {
        binding.searchQueryET.addTextChangedListener(
            DebouncingTextChangeListener(viewLifecycleOwner.lifecycle) { queryText ->
                queryText?.apply {
                    if (length >= 3)
                        viewModel.setKeyword(this)
                }
            }
        )
    }
    private fun initializeRV() {


        val repoRVAdapter = ImageRVAdapter(this  )
        binding.apply {

            imageListRV.adapter = repoRVAdapter
            viewModel.searchApiResponseLD.observe(viewLifecycleOwner) { resource ->
                repoRVAdapter.submitData(viewLifecycleOwner.lifecycle, resource)
            }
        }

        binding.searchQueryET.setText("Electrolux");

    }

    override fun onItemClicked(view: View, photoItem: Photo) {
        TODO("Not yet implemented")
    }
}