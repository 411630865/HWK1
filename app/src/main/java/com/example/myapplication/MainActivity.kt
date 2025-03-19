package com.example.myapplication

import android.app.Activity
import android.app.Instrumentation
import android.app.Instrumentation.ActivityResult
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result: androidx.activity.result.ActivityResult->
        // Step12：判斷回傳結果是否為 RESULT_OK，若是則執行以下程式碼
        if (result.resultCode == Activity.RESULT_OK) {
            // Step13：取得回傳的 Intent，並從 Intent 中取得
            val intent = result.data
            val Meal = intent?.getStringExtra("Meal")
            val Drink = intent?.getStringExtra("Drink")
            val Combo = intent?.getStringExtra("Combo")

            // Step14：設定 tvMeal 的文字
            val tvMeal = findViewById<TextView>(R.id.tvMeal)
            tvMeal.text = "餐點：$Meal\n飲料：$Drink\n配餐：$Combo"

            val Note = intent?.getStringExtra("Note")

            val tvNote = findViewById<TextView>(R.id.tvNote)
            tvNote.text=Note
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnChoice = findViewById<Button>(R.id.btnChoice)
        btnChoice.setOnClickListener{
            val intent = Intent(this, secActivity::class.java)
            startForResult.launch(intent)
        }
    }
}