package com.electrolux.imagesearchapp.ui.main.seachImage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.electrolux.imagesearchapp.R
import com.electrolux.imagesearchapp.databinding.ItemFlikrPhotoBinding
import com.electrolux.imagesearchapp.network.model.Photo
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageRVAdapter (
    private  val itemClickListener: OnItemClicked?,
    private val picasso: Picasso)
    : PagingDataAdapter<Photo,ImageRVAdapter.PhotoViewHolder>(PHOTO_ITEM_COMPARATOR) {

    interface OnItemClicked{
        fun onItemClicked(view: View, photoItem: Photo)

    }



    private lateinit var binding: ItemFlikrPhotoBinding

    class PhotoViewHolder(private val viewBinding:ItemFlikrPhotoBinding): RecyclerView.ViewHolder(viewBinding.root) {

        fun bindClickListener(photoItem: Photo, itemClickListener: OnItemClicked?) {
            viewBinding.root.setOnClickListener {
                itemClickListener?.onItemClicked(it, photoItem)
            }
        }


        val flickrImage = viewBinding.imageView

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        this.binding = ItemFlikrPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoItem = getItem(position)
        photoItem?.apply {
            picasso
                 .load(photoItem.urlQ)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.placeholder_img)
                .into(holder.flickrImage)

            holder.bindClickListener(this,itemClickListener)
        }

    }

    companion object {
        private val PHOTO_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem == newItem
        }
    }

}