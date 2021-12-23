package com.shang.viewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigationView)

        Log.d("DEBUG", "MainActivity : ${mainViewModel.hashCode()}")

        mainViewModel.jumpLiveData.observe(this, {
            when (it) {
                0 -> {
                    //do switch Fragment
                }
                1 -> {
                }
                2 -> {
                }
            }
        })

        addHideFunction()
//        replaceFunction()
    }

    private fun addHideFunction() {
        val aFragment = AFragment()
        val bFragment = BFragment()
        val cFragment = CFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, aFragment, AFragment.TAG)
        transaction.add(R.id.fragmentContainer, bFragment, BFragment.TAG)
        transaction.add(R.id.fragmentContainer, cFragment, CFragment.TAG)
        transaction.show(aFragment)
        transaction.hide(bFragment)
        transaction.hide(cFragment)
        transaction.commit()

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.A -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.show(aFragment)
                    transaction.hide(bFragment)
                    transaction.hide(cFragment)
                    transaction.commit()
                }
                R.id.B -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.hide(aFragment)
                    transaction.show(bFragment)
                    transaction.hide(cFragment)
                    transaction.commit()
                }
                R.id.C -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.hide(aFragment)
                    transaction.hide(bFragment)
                    transaction.show(cFragment)
                    transaction.commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun replaceFunction() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.A -> {
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.fragmentContainer, AFragment(), AFragment.TAG).commit()
                }
                R.id.B -> {
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.fragmentContainer, BFragment(), BFragment.TAG).commit()
                }
                R.id.C -> {
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.fragmentContainer, CFragment(), CFragment.TAG).commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }


        bottomNavigation.selectedItemId = R.id.A
    }


}