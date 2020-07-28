package com.carlos.silva.inanimewetrust.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.carlos.silva.inanimewetrust.R
import com.carlos.silva.inanimewetrust.presentation.views.BottomNavigationViewBehavior
import com.carlos.silva.inanimewetrust.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        title = getString(R.string.app_name)

        (bottom_navigation.layoutParams as CoordinatorLayout.LayoutParams).behavior =
            BottomNavigationViewBehavior()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.home_navigation,
            R.navigation.search_navigation,
            R.navigation.favorite_navigation
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottom_navigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            setupWithNavController(toolbar, navController)
        })

        currentNavController = controller
    }

    override fun onNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}