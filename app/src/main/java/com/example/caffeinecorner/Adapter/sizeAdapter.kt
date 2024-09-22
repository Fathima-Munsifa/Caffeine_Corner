package com.example.caffeinecorner.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caffeinecorner.R
import com.example.caffeinecorner.databinding.ViewholderSizeBinding

class sizeAdapter(val items:MutableList<String>): RecyclerView.Adapter<sizeAdapter.ViewHolder>() {

    private var selectedPosition=-1
    private var lastSelectedPosition    =   -1
    private lateinit var context: Context



   inner  class ViewHolder (val binding: ViewholderSizeBinding) :
       RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sizeAdapter.ViewHolder {
        context=parent.context
            val binding=ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: sizeAdapter.ViewHolder, position: Int) {
        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition    =   position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition==position){
            holder.binding.img.setBackgroundResource(R.drawable.orange_bg)
        }
        else{
            holder.binding.img.setBackgroundResource(R.drawable.size_bg)
        }
        val imageSize = when(position){
            0 ->45.dpToPx(context)
            1 ->50.dpToPx(context)
            2 ->55.dpToPx(context)
            3 ->65.dpToPx(context)
            else ->70.dpToPx(context)
        }

            val layoutParams=holder.binding.img.layoutParams
            layoutParams.width=imageSize
            layoutParams.height=imageSize
            holder.binding.img.layoutParams =   layoutParams
    }

    private fun Int.dpToPx(context: Context):Int{
        return (this*context.resources.displayMetrics.density).toInt()
    }
    override fun getItemCount(): Int = items.size
}