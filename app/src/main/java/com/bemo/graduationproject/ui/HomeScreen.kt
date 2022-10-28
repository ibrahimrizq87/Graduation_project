package com.bemo.graduationproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bemo.graduationproject.R
import com.bemo.graduationproject.databinding.ActivityHomeScreenBinding
import com.bemo.graduationproject.databinding.ActivitySignUpBinding
import com.bemo.graduationproject.ui.fragments.HomeFragment
import com.bemo.graduationproject.ui.fragments.LecturesFragment
import com.bemo.graduationproject.ui.fragments.NotificationsFragment
import com.bemo.graduationproject.ui.fragments.ProfileFragment

class HomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
binding.bottomNavigationView.setOnItemSelectedListener {

    when(it.itemId){
        R.id.home -> replaceFragment(HomeFragment())
        R.id.notifications -> replaceFragment(NotificationsFragment())
        R.id.profile -> replaceFragment(ProfileFragment())
        R.id.lectures -> replaceFragment(LecturesFragment())
        else -> {

        }

    }
    true
}
    }
private fun replaceFragment(fragment: Fragment){
    val fragmentManager=supportFragmentManager
    val fragmentTransaction =fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.fragment_container,fragment)
    fragmentTransaction.commit()

}
}