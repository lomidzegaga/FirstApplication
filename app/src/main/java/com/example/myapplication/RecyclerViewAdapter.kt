package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerSingleItemBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var list = mutableListOf<String>()

    private val icons = listOf(
        R.drawable.ac,
        R.drawable.accessibility,
        R.drawable.adb,
    )

    fun submitData(name: String) {
        list.add(name)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        RecyclerSingleItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(
        private val binding: RecyclerSingleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() = with(binding) {
            tvName.text = list[adapterPosition]
            ivImage.setImageResource(icons[(0..2).random()])
        }
    }
}