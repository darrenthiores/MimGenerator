package com.darrenthiores.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.darrenthiores.core.R
import com.darrenthiores.core.databinding.MainItemBinding
import com.darrenthiores.core.model.presenter.Image

class MainAdapter(private val onClick : (Image) -> Unit) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val items = ArrayList<Image>()

    fun setData(data:List<Image>){

        items.clear()
        items.addAll(data)
        notifyDataSetChanged()

    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = MainItemBinding.bind(itemView)

        fun bind(image: Image){

            binding.apply {

                Glide.with(itemView.context)
                    .load(image.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imageView)

            }

            itemView.setOnClickListener { onClick(image) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)

        return MainViewHolder(view)

    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}