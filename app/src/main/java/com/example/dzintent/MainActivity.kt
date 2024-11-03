package com.example.dzintent

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var resultTV: TextView
    private lateinit var callCalculateBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "ДЗ Скрытый калькулятор"
        toolbarMain.subtitle = "Версия 1. Главная активити"
        toolbarMain.setLogo(R.drawable.ic_android_black_24dp)

        resultTV = findViewById(R.id.resultTV)
        callCalculateBTN = findViewById(R.id.callCalculateBTN)

        callCalculateBTN.setOnClickListener { view ->
            val intent = Intent(this, MainActivity2::class.java)
            lunchSomeActivity.launch(intent)
        }

        val result = intent.getStringExtra("result")
        if (result == null) resultTV.text = "Результат"
        else resultTV.text = "$result"



        }

    private val lunchSomeActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val resultOnCalculate = data!!.getStringExtra("resultOnCalculate")
            resultTV.text = resultOnCalculate
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.infoMenuMain -> {
                Toast.makeText(applicationContext, "Автор Ефремов О.В. Версия 1.1.",
                    Toast.LENGTH_LONG).show()
            }
            R.id.exitMenuMain ->{
                Toast.makeText(applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
