package com.example.pab_23_01


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DuaActivity : AppCompatActivity(){

    private var progress = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedStateHandle: Bundle?) {
        super.onCreate(savedStateHandle)
        setContentView(R.layout.activity_dua)

        val pbLoading = findViewById<ProgressBar>(R.id.pbLoading)
        val tvHasil = findViewById< TextView>(R.id.tvHasil)
        val btnKembali = findViewById< Button>(R.id.btnKembali)

        val data = intent.getStringExtra("DATA_HASIL")

        tvHasil.text = data

        Thread {
            while (progress < 100){
                try {
                    Thread.sleep(50)
                }catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progress++
                val p = progress
                handler.post {pbLoading.progress = p}
            }
        }.start()

        btnKembali.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }
}