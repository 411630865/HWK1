package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent

class secActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sec)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edMeal = findViewById<TextView>(R.id.edMeal)
        val rgDrink = findViewById<RadioGroup>(R.id.rgDrink)
        val rgCombo = findViewById<RadioGroup>(R.id.rgCombo)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val edNote = findViewById<TextView>(R.id.edNote)

        btnSend.setOnClickListener{
            if(edMeal.text.isEmpty()){
                Toast.makeText(this,"請輸入餐點名稱", Toast.LENGTH_SHORT).show()
            }else{
                val b = bundleOf(
                    "Meal" to edMeal.text.toString(),
                    "Drink" to rgDrink.findViewById<RadioButton>(rgDrink.checkedRadioButtonId).text.toString(),
                    "Combo" to rgCombo.findViewById<RadioButton>(rgCombo.checkedRadioButtonId).text.toString(),
                    "Note" to edNote.text.toString()
                )

                val intent = Intent()
                intent.putExtras(b)
                setResult(RESULT_OK,intent)
                finish()
            }
        }

    }
}