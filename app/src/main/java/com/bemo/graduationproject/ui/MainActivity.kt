package com.bemo.graduationproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bemo.graduationproject.R
import com.bemo.graduationproject.databinding.ActivityMainBinding
import com.bemo.graduationproject.databinding.ActivitySignUpBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    binding.signUp.setOnClickListener {
        startActivity(Intent(this,SignUp::class.java))
    }

    }
}