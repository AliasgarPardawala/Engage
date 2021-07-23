package com.pardawala.aliasgar.engage.ui.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pardawala.aliasgar.engage.data.Hobby
import com.pardawala.aliasgar.engage.databinding.ItemChatBinding

class MessageAdapter(private val listener: OnItemClickListener) : ListAdapter<Hobby, MessageAdapter.LogViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class LogViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION) {
                        val chat =getItem(position)
                        listener.onItemClicked(chat)
                    }
                }
            }
        }

        fun bind(hobby: Hobby) {
            binding.apply {
                binding.tvHobby.text = hobby.title
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(hobby: Hobby)
    }

    class DiffCallback : DiffUtil.ItemCallback<Hobby>() {
        override fun areItemsTheSame(oldItem: Hobby, newItem: Hobby) = oldItem.hobbyid == newItem.hobbyid

        override fun areContentsTheSame(oldItem: Hobby, newItem: Hobby) = oldItem == newItem
    }
}