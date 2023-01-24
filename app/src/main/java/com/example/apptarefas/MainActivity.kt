package com.example.apptarefas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)

        setTheme(R.style.Theme_AppTarefas)
        setContentView(R.layout.activity_main)
    }
}