package com.example.loteria

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Referência dos objetos do arquivo .xml
        val editText: EditText      = findViewById(R.id.edit_Number)
        val textResultado: TextView = findViewById(R.id.text_Resultado)
        val btnGerador: Button      = findViewById(R.id.btn_gerador)

        //Entendendo o evendo setInClickListener
        btnGerador.setOnClickListener{
            val qtd = editText.text.toString()

            if (qtd.isEmpty()){
                Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val qtdInt = qtd.toInt()

            if (qtdInt < 6 || qtdInt > 15){
                Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val numbers = mutableSetOf<Int>()
            val random = Random()

            while(numbers.size < qtdInt){
                val number = random.nextInt(60) + 1
                numbers.add(number)
            }

            textResultado.text = numbers.sorted().joinToString(" - ")
        }
    }
}