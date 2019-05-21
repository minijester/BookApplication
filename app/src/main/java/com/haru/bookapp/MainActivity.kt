package com.haru.bookapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private val TAG: String = "Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        if (mAuth!!.currentUser != null){
            Log.d(TAG,"Continue with :" + mAuth!!.currentUser!!.email)
            goToBookList()
        }

        loginBtn?.setOnClickListener { doLogin() }
        registerBtn?.setOnClickListener { doRegister() }
    }

    private fun doRegister() {
        Toast.makeText(this, "Register", Toast.LENGTH_LONG).show()
    }

    private fun doLogin() {
        val userName = userId.text.toString()
        val password = userPassword.text.toString()

        if (userName.isEmpty()){
            Toast.makeText(this, "Please enter your email address !", Toast.LENGTH_LONG).show()
            Log.d(TAG,"Email was empty")
            return
        }
        if (password.isEmpty()){
            Toast.makeText(this, "Please enter your password !", Toast.LENGTH_LONG).show()
            Log.d(TAG,"Password was empty")
            return
        }

        mAuth!!.signInWithEmailAndPassword(userName,password).addOnCompleteListener{task ->
            if(!task.isSuccessful){
                Toast.makeText(this, "Authentication Failed :" + task.exception, Toast.LENGTH_LONG).show()
                Log.d(TAG, "Authentication Failed :" + task.exception)
            }
            else{
                Toast.makeText(this, "Authentication Successful :", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Sign in successful")
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
