package com.faezolfp.dripcontrol

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.faezolfp.dripcontrol.core.service.AlarmNotificationService
import com.faezolfp.dripcontrol.core.utils.FormatPersentase
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_notifications, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        displayObserver()
    }

    private fun displayObserver() {
        viewModel.getDataMax.observe(this) { dataMax ->
            if (dataMax != null) {
                viewModel.getDataInfus.observe(this) { dataRealtime ->
                    if (dataRealtime != null) {
                        val persentase = FormatPersentase.persentaseRealtime(dataMax, dataRealtime)
                        if (persentase <= 20) {
                            shownotif()
                        }
                    }
                }
            }
        }
    }

    private fun shownotif() {
        val serviceIntent = Intent(this, AlarmNotificationService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
    }
}
