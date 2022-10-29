package com.chirikualii.materi_recyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chirikualii.materi_recyclerview.databinding.CustomItemActivityMainBinding
import com.chirikualii.materi_recyclerview.databinding.ItemActivityMainBinding

class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProfile = mutableListOf<Profile>()

//    val VIEW_HEADER = 0
//    val HOLDER = 1

    enum class VIEW{
        VIEW_HOLDER,
        VIEW_HEADER
    }

    class ViewHolder(private val binding: ItemActivityMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(data: Profile){
            binding.nama.text = data.nama
            binding.phone.text = data.phone
            Glide.with(itemView.context).load(data.image).circleCrop().into(binding.image);
        }

        fun imageClick(data: Profile){
            binding.image.setOnClickListener(View.OnClickListener {
//                Toast.makeText(itemView.context,"Hello ${data.nama}", Toast.LENGTH_SHORT).show()

                val bundle: Bundle = Bundle()
                bundle.putParcelable("KEY", data)

                var intent: Intent = Intent(itemView.context, ViewActivity::class.java)
                intent.putExtras(bundle)
                itemView.context.startActivity(intent)
            })
        }


    }

    class HolderHeader(private val binding: CustomItemActivityMainBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindView(data: Profile){
            binding.tvName.text = data.nama
            binding.tvPhone.text = data.phone

            Glide.with(itemView.context).load(data.image).circleCrop().into(binding.ivProfile);
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0){
            return VIEW.VIEW_HEADER.ordinal
        }else{
            return VIEW.VIEW_HOLDER.ordinal
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        if (viewType == VIEW.VIEW_HEADER.ordinal){
            val customBinding = CustomItemActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return HolderHeader(customBinding)
        }else{
            val binding = ItemActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val profile: Profile = listProfile[position]
        if (holder is ViewHolder) {
            holder.bindView(profile)
            holder.imageClick(profile)
        }else if (holder is HolderHeader){
            holder.bindView(profile)
        }
    }

    override fun getItemCount(): Int {
        return listProfile.size
    }

    fun addList(listData: List<Profile>){
        listProfile.clear()
        listProfile.addAll(listData)

        notifyDataSetChanged()
    }
}