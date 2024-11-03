package com.example.dzintent

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzintent.R.id

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarSec: Toolbar

    private lateinit var firstOperandTE: EditText
    private lateinit var secondOperandTE: EditText

    private lateinit var sumButtonBTN: Button
    private lateinit var difButtonBTN: Button
    private lateinit var mulButtonBTN: Button
    private lateinit var divButtonBTN: Button

    private lateinit var resultCalculateTV: TextView

    private lateinit var buttonLoadtoMainBTN: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbarSec = findViewById(R.id.toolbarSec)
        setSupportActionBar(toolbarSec)
        title = "ДЗ Скрытый калькулятор"
        toolbarSec.subtitle = "Активити ДВА"
        toolbarSec.setLogo(R.drawable.ic_android_black_24dp)

        firstOperandTE = findViewById(id.firstOperandET)
        secondOperandTE = findViewById(id.secondOperandET)
        sumButtonBTN = findViewById(id.buttonSumBTN)
        difButtonBTN = findViewById(id.buttonDifBTN)
        mulButtonBTN = findViewById(id.buttonMulBTN)
        divButtonBTN = findViewById(id.buttonDivBTN)
        buttonLoadtoMainBTN = findViewById(id.buttonLoadtoMainBTN)
        resultCalculateTV = findViewById(id.resultCalculateTV)

        sumButtonBTN.setOnClickListener(this)
        difButtonBTN.setOnClickListener(this)
        mulButtonBTN.setOnClickListener(this)
        divButtonBTN.setOnClickListener(this)
        buttonLoadtoMainBTN.setOnClickListener(this)

        buttonLoadtoMainBTN.setOnClickListener{view ->
            if (resultCalculateTV.text.isEmpty()) return@setOnClickListener
            val resultOnCalculate = resultCalculateTV.text.toString()
            val intent = Intent()
            intent.putExtra("resultOnCalculate", resultOnCalculate)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onClick(v: View?) {
        var check = true

        if (firstOperandTE.text.isEmpty() || secondOperandTE.text.isEmpty()) {
            return
        }

        val first = firstOperandTE.text.toString().toDouble()
        val second = secondOperandTE.text.toString().toDouble()

        val result = when(v?.id) {
            id.buttonSumBTN -> Operation(first, second).sum()
            id.buttonDifBTN -> Operation(first, second).dif()
            id.buttonMulBTN -> Operation(first, second).mul()
            id.buttonDivBTN -> Operation(first, second).div()
            else -> 0.0
        }
        if (!check) resultCalculateTV.text = "Результат" else resultCalculateTV.text = result.toString()
    }

}
