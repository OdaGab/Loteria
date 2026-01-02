package com.example.loteria

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
            Log.i("Teste", "Botao clicado viu Johann eu sei aonde voce mora")
        }

    }
}