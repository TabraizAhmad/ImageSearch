package com.electrolux.imagesearchapp.ui.main.imagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.electrolux.imagesearchapp.R
import com.electrolux.imagesearchapp.databinding.FragmentImageDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class ImageDetailFragment : Fragment() {

    //binding for detail view
    private lateinit var binding: FragmentImageDetailBinding


    private val args: ImageDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photo = args.photo
        binding.apply {
            imageTitle.text = photo.title

            Glide.with(originalImage)
                .load(photo.urlQ)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(originalImage)

            textPublic.text = if (photo.ispublic ==1)"Yes" else  "No"

        }
    }
}