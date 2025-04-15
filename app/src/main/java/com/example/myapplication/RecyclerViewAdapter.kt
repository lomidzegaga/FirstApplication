package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.RecyclerSingleItemWithImageBinding
import com.example.myapplication.databinding.RecyclerSingleItemWithoutImageBinding

class RecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var list = mutableListOf<String>()

    private val icons = listOf(
        R.drawable.ac,
        R.drawable.accessibility,
        R.drawable.adb,
    )

    companion object {
        private const val WITH_IMAGE = 0
        private const val WITHOUT_IMAGE = 1
    }

    fun submitData(name: String) {
        list.add(name)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) WITHOUT_IMAGE else WITH_IMAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            WITH_IMAGE -> ViewHolderWithImage(
                RecyclerSingleItemWithImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            WITHOUT_IMAGE -> ViewHolderWithoutImage(
                RecyclerSingleItemWithoutImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> ViewHolderWithoutImage(
                RecyclerSingleItemWithoutImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewType = getItemViewType(position)

        when (viewType) {
            WITH_IMAGE -> (holder as ViewHolderWithImage).bind()
            WITHOUT_IMAGE -> (holder as ViewHolderWithoutImage).bind()
        }
    }

    inner class ViewHolderWithImage(
        private val binding: RecyclerSingleItemWithImageBinding
    ) : ViewHolder(binding.root) {

        fun bind() = with(binding) {
            tvName.text = list[adapterPosition]
            ivImage.setImageResource(icons.random())
        }
    }

    inner class ViewHolderWithoutImage(
        private val binding: RecyclerSingleItemWithoutImageBinding
    ) : ViewHolder(binding.root) {

        fun bind() = with(binding) {
            tvName.text = list[adapterPosition]
        }
    }
}