package ru.grandibambino.testforlifehackstudio.utils

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.grandibambino.testforlifehackstudio.R


fun logError(tag: String, message: String) {
    Log.e(tag, message)
}

fun logDebug(tag: String, message: String) {
    Log.d(tag, message)
}

fun Fragment.showToast(message: String){
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun Fragment.navigate() : NavController =
    NavHostFragment.findNavController(this)

fun AppCompatActivity.navigate() : NavController{
    val navHostFragment = supportFragmentManager?.findFragmentById(R.id.fragment_container) as NavHostFragment
    return navHostFragment.navController
}