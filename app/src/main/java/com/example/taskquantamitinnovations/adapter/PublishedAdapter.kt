package com.example.taskquantamitinnovations.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.taskquantamitinnovations.databinding.LayoutPublishedItemBinding
import com.example.taskquantamitinnovations.services.PublishedModel
import java.util.*
import kotlin.collections.ArrayList

class PublishedAdapter(
    private var context: Context,
    private var list: MutableList<PublishedModel>
) : RecyclerView.Adapter<PublishedAdapter.MyViewHolder>(), Filterable {

    inner class MyViewHolder(private val binding: LayoutPublishedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binder(model: PublishedModel) {
            binding.model = model
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PublishedAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutPublishedItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PublishedAdapter.MyViewHolder, position: Int) {
        holder.binder(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getFilter(): Filter {
        return stateSearchList
    }

    private val stateSearchList: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterList: MutableList<PublishedModel> = ArrayList()
            if (charSequence.isEmpty()) {
                filterList.addAll(list)
            } else {
                val filterPattren =
                    charSequence.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (stateModel in list) {
                    if (stateModel.title?.lowercase(Locale.getDefault())
                            ?.contains(filterPattren) == true) {
                        filterList.add(stateModel)
                    }
                }
            }
            val results = FilterResults()
            results.values = filterList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            list.clear()
            list.addAll(filterResults.values as MutableList<PublishedModel>)
            notifyDataSetChanged()
        }
    }
}