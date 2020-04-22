package com.example.finalproj

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
//import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_main.*
//import jdk.nashorn.internal.runtime.ECMAException.getException
import com.google.firebase.auth.FirebaseUser
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.fragment.app.FragmentActivity
//import sun.jvm.hotspot.utilities.IntArray


//import sun.jvm.hotspot.utilities.IntArray



class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main)

    }


    override fun onStart() {
        super.onStart()


        SignIn.setOnClickListener {
            var email = Email.text.toString()
            var password = Password.text.toString()
            if (email != null && password != null) {
                mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(this){ task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        Toast.makeText(
                            this, "Authentication success.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val user = mAuth?.getCurrentUser()
                        val NewIntent = Intent(this, firstquestion::class.java)
                        NewIntent.putExtra("user", email)
                        startActivity(NewIntent)
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }


                }
            }
        }
        Create.setOnClickListener {
            var email = Email.text.toString()
            var password = Password.text.toString()
            mAuth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(
                            this, "created succesful.",
                            Toast.LENGTH_SHORT
                        ).show()

                        val user = mAuth.getCurrentUser()
                        val NewIntent = Intent(this, firstquestion::class.java)
                        NewIntent.putExtra("user", email)
                        startActivity(NewIntent)

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }

                    // ...
                }
        }

    }
}

