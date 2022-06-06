package com.example.appchat.adapte

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appchat.R
import com.example.appchat.model.User
import de.hdodenhof.circleimageview.CircleImageView

//import com.google.firebase.database.core.Context

class UserAdapter(private val context: Context, private val userList: ArrayList<User>):
RecyclerView.Adapter<UserAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val user = userList[position]
        holder.txtUsername.text = user.userName
        Glide.with(context).load(user.userImage).placeholder(R.drawable.anh).into(holder.imgUser)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtUsername:TextView = view. findViewById(R.id.userName)
        val txtTemp:TextView = view. findViewById(R.id.temp)
        val imgUser:CircleImageView = view. findViewById(R.id.userImage)

    }
}