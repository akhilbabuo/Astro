package com.blackbird.astro.core.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.blackbird.astro.R
import com.blackbird.astro.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUserFlow()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d("akhil", "onNewToken - frg: $it ")
        }
    }

    private fun setUserFlow() {
        lifecycleScope.launch {
            mainViewModel.getUserStatus().collect{ userName ->
                if (userName.isNullOrBlank()) {
                    startUserInitiations()
                } else {
                    loadHomeScreen()
                }
            }
        }
    }

    private fun setUpNavigation(flow: NavigationFlow) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        val navController = navHostFragment.navController
        val destination = when (flow) {
            NavigationFlow.Home -> {
                R.id.homeFragment
            }

            NavigationFlow.UserInitiation -> {
                R.id.userEntry
            }
        }
        navGraph.setStartDestination(destination)
        navController.graph = navGraph

    }

    private fun loadHomeScreen() {
        setUpNavigation(NavigationFlow.Home)
    }

    private fun startUserInitiations() {
        setUpNavigation(NavigationFlow.UserInitiation)
    }


}

enum class NavigationFlow {
    Home,
    UserInitiation
}
