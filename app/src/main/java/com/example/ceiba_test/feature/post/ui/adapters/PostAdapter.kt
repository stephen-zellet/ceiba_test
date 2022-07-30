package com.example.ceiba_test.feature.post.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceiba_test.R
import com.example.ceiba_test.feature.post.ui.models.ItemPost

class PostAdapter(var list: List<ItemPost> = ArrayList()): RecyclerView.Adapter<PostAdapter.ViewHolder>() {



    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title: TextView = v.findViewById(R.id.item_title)
        var body: TextView = v.findViewById(R.id.item_text)

    }


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = list[position].title
        holder.body.text = list[position].body

    }

    override fun getItemCount() = list.size


}