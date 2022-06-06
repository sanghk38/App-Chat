package com.example.appchat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appchat.R
import com.example.appchat.adapte.UserAdapter
import com.example.appchat.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {
    var userList = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        userRe.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        getUsersList()
    }

    fun getUsersList(){
        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val databaseReference:DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children){
                    val user = dataSnapShot.getValue(User::class.java)
                    if (!user!!.userId.equals(firebase.uid)){
                        userList.add(user)
                    }
                }
                val userAdapter = UserAdapter(this@UsersActivity,userList)
                userRe.adapter = userAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,error.message,Toast.LENGTH_LONG).show()
            }

        })
    }
}