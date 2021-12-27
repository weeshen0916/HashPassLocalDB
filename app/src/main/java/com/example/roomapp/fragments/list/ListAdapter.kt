package com.example.roomapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.data.Password
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var passwordList = emptyList<Password>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
       return passwordList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = passwordList[position]
//        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.tvPass.text = currentItem.password
        holder.itemView.tvHashPass.text = currentItem.hashpass
        holder.itemView.tvHashType.text = currentItem.hashtype
    }

    fun setData(user: List<Password>){
        this.passwordList = user
        notifyDataSetChanged()
    }
}