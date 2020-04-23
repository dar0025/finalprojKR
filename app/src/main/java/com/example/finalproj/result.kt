package com.example.finalproj

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_firstquestion.*
import kotlinx.android.synthetic.main.fragment_secondquestion.*
import kotlinx.android.synthetic.main.fragment_loginscreen.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import kotlinx.android.synthetic.main.results.*

//import sun.jvm.hotspot.utilities.IntArray




class result : AppCompatActivity() {

    var user = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.results)
        var intent: Intent = getIntent()

        user = (intent.getStringExtra("user"))




    }

    override fun onStart() {
        super.onStart()

        user_text.text = user


    }

}
