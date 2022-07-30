package com.example.ceiba_test.feature.user.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceiba_test.R
import com.example.ceiba_test.feature.user.ui.models.ItemUser

class UserAdapter(var actionClic:(ItemUser)->Unit, var actionListEmpty:(Boolean)->Unit):RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    var all:List<ItemUser> = ArrayList()
    var filterList:ArrayList<ItemUser> =ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_user, parent, false))


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = filterList[position].name
        holder.phone.text = filterList[position].phoneNumber
        holder.email.text = filterList[position].email
        holder.button.setOnClickListener { actionClic(filterList[position]) }
    }



    class ViewHolder(v:View):RecyclerView.ViewHolder(v) {

        var name:TextView = v.findViewById(R.id.txtName)
        var phone:TextView = v.findViewById(R.id.txtNumberPhone)
        var email:TextView = v.findViewById(R.id.txtEmail)
        var button:TextView = v.findViewById(R.id.btnVerPublicaciones)

    }

    override fun getItemCount()=filterList.size

    fun setData(list:List<ItemUser>){
        this.all = list
        setWordSearch("")
    }

    fun setWordSearch(word:String){
        if (word.isBlank()){
            filterList.clear()
            filterList.addAll(all)
            actionListEmpty(filterList.isEmpty())
            notifyDataSetChanged()
        }else{
            filterList.clear()
            filterList.addAll(all.filter { it.name.startsWith(word,true) })
            actionListEmpty(filterList.isEmpty())
            notifyDataSetChanged()
        }
    }


}