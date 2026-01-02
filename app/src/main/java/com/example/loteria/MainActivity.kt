package com.example.loteria

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
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

    private lateinit var prefs: SharedPreferences

    // O companion object agrupa propriedades e funções que pertencem à classe em si, e não a uma instância específica dela (como membros estáticos em outras linguagens).
    // É um ótimo lugar para definir constantes que serão usadas em vários locais, pois garante que o nome (a chave) seja sempre o mesmo, evitando erros de digitação.
    companion object {
        const val PREFS_FILE_KEY = "com.example.loteria.PREFERENCE_FILE_KEY" // Nome do nosso "arquivo" de preferências.
        const val LAST_RESULT_KEY = "last_result" // Chave para acessar o último resultado salvo.
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

        // 1. Acessar o nosso "arquivo de anotações"
        prefs = getSharedPreferences(PREFS_FILE_KEY, Context.MODE_PRIVATE)

        //Referência dos objetos do arquivo .xml
        val editText: EditText      = findViewById(R.id.edit_Number)
        val textResultado: TextView = findViewById(R.id.text_Resultado)
        val btnGerador: Button      = findViewById(R.id.btn_gerador)

        // 2. Ler e exibir o último resultado salvo ao iniciar o app
        val lastResult = prefs.getString(LAST_RESULT_KEY, null)
        lastResult?.let {
            textResultado.text = it
        }

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

            val resultString = numbers.sorted().joinToString(" - ")
            textResultado.text = resultString

            // 3. Salvar o novo resultado gerado
            prefs.edit().putString(LAST_RESULT_KEY, resultString).apply()
        }
    }
}