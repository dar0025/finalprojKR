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
import kotlinx.android.synthetic.main.fragment_loginscreen.*
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
                mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(this){ task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        Toast.makeText(
                            this, "Login success.",
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

            if (password.length < 6) {
                Toast.makeText(
                    this, "Password must be at least 6 characters long.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                mAuth?.createUserWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener(this) { task ->

                        println(task.result)

                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(
                                this, "Account creation successful.",
                                Toast.LENGTH_SHORT
                            ).show()
                            println("made it 1")

                            val user = mAuth.getCurrentUser()
                            val NewIntent = Intent(this, firstquestion::class.java)
                            NewIntent.putExtra("user", email)
                            startActivity(NewIntent)
                            println("made it 2")

                        } else {
                            // If sign in fails, display a message to the user.
                            println("made it 3")
                            Toast.makeText(
                                this, "Creation failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            //updateUI(null)
                            println("made it 4")
                        }

                        // ...
                    }
            }

        }
    }
}

