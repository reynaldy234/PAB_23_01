package com.example.pab_23_01

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object RegistrationRepository {
    private val historyList = mutableListOf<RegistrationData>()

    init {
        // Sample initial registration history entries
        historyList.add(
            RegistrationData(
                id = "REG-1001",
                nama = "Budi Santoso",
                gender = "Laki-Laki",
                kota = "Jakarta",
                timestamp = "2026-10-10 10:30",
                status = "Terdaftar"
            )
        )
        historyList.add(
            RegistrationData(
                id = "REG-1002",
                nama = "Siti Rahma",
                gender = "Perempuan",
                kota = "Bandung",
                timestamp = "2026-10-11 14:15",
                status = "Terdaftar"
            )
        )
    }

    fun addRegistration(nama: String, gender: String, kota: String) {
        val nextId = "REG-${1001 + historyList.size}"
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val currentTime = sdf.format(Date())

        val newData = RegistrationData(
            id = nextId,
            nama = nama,
            gender = gender,
            kota = kota,
            timestamp = currentTime,
            status = "Terdaftar"
        )
        historyList.add(0, newData)
    }

    fun getAllRegistrations(): List<RegistrationData> {
        return historyList
    }
}
