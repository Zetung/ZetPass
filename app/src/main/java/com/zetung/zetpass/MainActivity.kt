package com.zetung.zetpass

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.zetung.zetpass.databinding.ActivityMainBinding
import com.zetung.zetpass.repository.AppSettingsDbAPI
import com.zetung.zetpass.utils.singleton.CurrentAppSettings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    @Inject
    lateinit var currentAppSettings: CurrentAppSettings
    @Inject
    lateinit var appSettingsDbAPI: AppSettingsDbAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentAppSettings.appSettingsModel = appSettingsDbAPI.getAppSettings()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                //R.id.homeFragment,  R.id.inboxFragment, R.id.friendsFragment,  R.id.inviteFragment
                R.id.nav_home,  R.id.nav_inbox, R.id.nav_friends,  R.id.nav_invite
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.authFragment ) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.nav_home)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.nav_inbox -> {
                    navController.navigate(R.id.nav_inbox)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.nav_friends -> {
                    navController.navigate(R.id.nav_friends)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.nav_invite -> {
                    navController.navigate(R.id.nav_invite)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.nav_exit -> {
                    navController.navigate(R.id.authFragment)
                    true
                }
                else -> false
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> navController.navigate(R.id.optionsFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}