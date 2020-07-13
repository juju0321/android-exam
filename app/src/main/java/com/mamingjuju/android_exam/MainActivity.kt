package com.mamingjuju.android_exam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mamingjuju.android_exam.list.PersonListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,
                PersonListFragment()
            )
            .commit()
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}