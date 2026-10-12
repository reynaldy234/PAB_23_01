package com.example.pab_23_01

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class RiwayatActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var rvRiwayat: RecyclerView
    private lateinit var tvEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)
        rvRiwayat = findViewById(R.id.rvRiwayat)
        tvEmpty = findViewById(R.id.tvEmpty)

        // Setup ActionBarDrawerToggle for strip 3 / hamburger button
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setCheckedItem(R.id.nav_riwayat)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_form -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_riwayat -> {
                    drawerLayout.closeDrawers()
                }
                R.id.nav_feedback -> {
                    val intent = Intent(this, FeedbackActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val dataList = RegistrationRepository.getAllRegistrations()

        if (dataList.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            rvRiwayat.visibility = View.GONE
        } else {
            tvEmpty.visibility = View.GONE
            rvRiwayat.visibility = View.VISIBLE
            rvRiwayat.layoutManager = LinearLayoutManager(this)
            rvRiwayat.adapter = RiwayatAdapter(dataList)
        }
    }
}
