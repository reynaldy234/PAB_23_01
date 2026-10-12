package com.example.pab_23_01

data class RegistrationData(
    val id: String,
    val nama: String,
    val gender: String,
    val kota: String,
    val timestamp: String,
    val status: String = "Terdaftar"
)
