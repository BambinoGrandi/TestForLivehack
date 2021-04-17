package ru.grandibambino.testforlifehackstudio.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import ru.grandibambino.testforlifehackstudio.R
import ru.grandibambino.testforlifehackstudio.presentation.fragments.CompaniesScreen
import ru.grandibambino.testforlifehackstudio.utils.navigate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        startFragment()
    }

    private fun startFragment() {
        navigate()
    }
}