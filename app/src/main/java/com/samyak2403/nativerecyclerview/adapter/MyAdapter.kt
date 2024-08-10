/**
 * Created by Samyak kamble on 8/10/24, 11:37 PM
 *  Copyright (c) 2024 . All rights reserved.
 *  Last modified 8/10/24, 11:37 PM
 */

package com.samyak2403.nativerecyclerview.adapter




import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.com.samyak2403.nativerecyclerview.databinding.UserItemsBinding
import com.samyak2403.nativerecyclerview.model.UserModel


class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private lateinit var binding: UserItemsBinding
    private lateinit var context: Context

    inner class ViewHolder(): RecyclerView.ViewHolder(binding.root){
        fun set(item: UserModel){
            binding.apply {
                nameTxt.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = UserItemsBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.set(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<UserModel>(){
        override fun areItemsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}