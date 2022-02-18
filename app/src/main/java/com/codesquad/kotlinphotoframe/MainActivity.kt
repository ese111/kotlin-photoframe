package com.codesquad.kotlinphotoframe

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial


class MainActivity : AppCompatActivity() {
    private val activityName = this.localClassName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("$activityName", "onCreate")
        val text: TextView = findViewById(R.id.name)
        val name = "Funny"
        text.text = "${name}의 사진 액자"
        text.setTextColor(Color.parseColor("#3d00e0"))
        val button: Button = findViewById(R.id.button)
        val switch: SwitchMaterial = findViewById(R.id.darkMode)
        switch.text = "dark"
        setDarkMode(switch)
        val contextView = findViewById<View>(R.id.context)
        val msg = intent.getStringExtra("msg")
        msg?.Snackbar(contextView)
        intent.removeExtra("msg")
        val imageView: ImageView = findViewById(R.id.image_view)
        imageView.setBackgroundColor(Color.GRAY)
        imageLoad(button, imageView)
        val floatActionButton: FloatingActionButton = findViewById(R.id.floating_action_button)
        moveActivity(floatActionButton)
    }

    private fun setDarkMode(switch: SwitchMaterial) {
        switch.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun moveActivity(floatActionButton: FloatingActionButton){
        floatActionButton.setOnClickListener {
            val secondIntent = Intent(this, SecondActivity::class.java)
            startActivity(secondIntent)
        }
    }

    private fun imageLoad(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            val fileName = makeFileName((1..22).random())
            val image = resources.assets.open("$fileName.jpg")
            val bitmap = BitmapFactory.decodeStream(image)
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun makeFileName(fileName: Int) = when (fileName / 10 == 0) {
        true -> "0$fileName"
        else -> fileName.toString()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("$activityName", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("$activityName", "onStart")
    }

    override fun onResume() {
        Log.d("$activityName", "onResume")
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        Log.d("$activityName", "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("$activityName", "onPause")
    }
}