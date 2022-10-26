package com.test.mytask.neosoft.adapter;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.test.mytask.databinding.ItemImageViewHolderBinding
import com.test.mytask.neosoft.repository.ImageDataSource
import com.test.mytask.neosoft.repository.ImageModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstListAdapter(private var images: MutableList<ImageModel> = ImageDataSource.getImages()) :
    RecyclerView.Adapter<FirstListAdapter.HomeViewHolder>(),
    Filterable {
    private lateinit var context: Context
    private var filteredImages: MutableList<ImageModel> = mutableListOf<ImageModel>().apply {
        addAll(images)
    }

    inner class HomeViewHolder(private val binding: ItemImageViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ImageModel) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    binding.ivImage.setImageResource(data.imgUrl)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            binding.tvName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemImageViewHolderBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(filteredImages[position])
    }

    override fun getItemCount(): Int = filteredImages.size

    override fun getFilter(): Filter = FilterData()

    inner class FilterData : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            filteredImages = if (constraint == null || constraint.isEmpty()) {
                images
            } else {
                val newList = mutableListOf<ImageModel>()
                images.filter {
                    it.name.lowercase().contains(constraint.toString().lowercase())
                }.forEach {
                    newList.add(it)
                }
                newList
            }

            return FilterResults().apply { values = filteredImages }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredImages =
                if (results?.values == null) mutableListOf() else results.values as MutableList<ImageModel>
            notifyDataSetChanged()
        }
    }
}