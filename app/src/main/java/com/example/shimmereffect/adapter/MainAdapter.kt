package com.example.shimmereffect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shimmereffect.R
import com.example.shimmereffect.models.User
import kotlinx.android.synthetic.main.item_data.view.*

class MainAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        return holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User){
            itemView.tvEmail.text = user.email
            itemView.tvUsername.text = user.name
            Glide.with(itemView)
                .load(user.avatar)
                .into(itemView.imgAvatar)
        }
    }

    fun addData(list: List<User>){
        users.addAll(list)
    }
}