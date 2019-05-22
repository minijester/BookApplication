package com.haru.bookapp.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.haru.bookapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private val tag = "Register Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        if (mAuth!!.currentUser != null){
            Log.d(tag,"Continue with :" + mAuth!!.currentUser!!.email)
            goToBookList()
        }

        registerButton?.setOnClickListener { registerToFireBase() }
    }

    private fun registerToFireBase() {
        val username = userEmail.text.toString()
        val password = userPass.text.toString()

        if (username.isEmpty()){
            Toast.makeText(this, "Please enter your email address !", Toast.LENGTH_LONG).show()
            Log.d(tag,"Email was empty")
            return
        }
        if (password.isEmpty()){
            Toast.makeText(this, "Please enter your password !", Toast.LENGTH_LONG).show()
            Log.d(tag,"Password was empty")
            return
        }

        mAuth!!.createUserWithEmailAndPassword(username,password).addOnCompleteListener { task ->
            if(!task.isSuccessful){
                Toast.makeText(this, "Authentication Failed : " +  task.exception, Toast.LENGTH_LONG).show()
                Log.d(tag,"Authentication Failed :" + task.exception)
            }
            else{
                Toast.makeText(this, "Create account successful !", Toast.LENGTH_LONG).show()
                Log.d(tag,"Create account successful!")
                goToBookList()
            }
        }

    }

    private fun goToBookList(){
        val intent = Intent(this, BookActivity::class.java)
        startActivity(intent)
        finish()
    }

}
