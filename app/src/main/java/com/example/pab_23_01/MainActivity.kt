package com.example.pab_23_01

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var edtNama = findViewById<EditText>(R.id.edtNama)
        var rgJK = findViewById<RadioGroup>(R.id.rgJK)
        var spKota = findViewById<Spinner>(R.id.spKota)
        var swMenyetujui = findViewById<Switch>(R.id.swMenyetujui)
        var btnSimpan = findViewById<Button>(R.id.btn_submit)

        var kota = arrayOf("Pilih Kota", "Jakarta", "Bandung", "Surabaya", "Padang")
        var adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, kota)
        spKota.adapter = adapter

        btnSimpan.setOnClickListener {
            if (edtNama.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Nama Tidak Boleh Kosong!!!",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (! swMenyetujui.isChecked){
                Toast.makeText(this, "Anda Harus Menyetujui S&K",
                    Toast.LENGTH_SHORT).show()

            }
            val jk = when (rgJK.checkedRadioButtonId){
                R.id.rbLaki -> "Laki-Laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> "Belum Dipilih"
            }

            val data = "Nama : ${edtNama.text}" +
                    "\nJenis Kelamin : $jk" +
                    "\nKota : ${spKota.selectedItem}"

            val intent = Intent(this, DuaActivity::class.java)
            intent.putExtra("DATA_HASIL", data)
            startActivity(intent)
        }
    }
}