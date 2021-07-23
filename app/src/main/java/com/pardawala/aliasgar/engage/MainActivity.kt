package com.pardawala.aliasgar.engage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.pardawala.aliasgar.engage.repository.HobbyRepository
import com.pardawala.aliasgar.engage.ui.HomeFragment
import com.pardawala.aliasgar.engage.ui.message.MessageViewModel
import com.pardawala.aliasgar.engage.ui.message.MessagesFragment
import com.pardawala.aliasgar.engage.ui.message.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: MessageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val radius = resources.getDimension(R.dimen.radius_small)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val bottomNavigationViewBackground = bottomNavigationView.background as MaterialShapeDrawable
        bottomNavigationViewBackground.shapeAppearanceModel =
            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, radius)
                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                .build()

        val homeFragment = HomeFragment()
        val messagesFragment = MessagesFragment()
        val hobbyRepository = HobbyRepository()
        viewModelProviderFactory = ViewModelProviderFactory(application, hobbyRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MessageViewModel::class.java)

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnItemSelectedListener {
            val cf = supportFragmentManager.findFragmentById(R.id.flFragment)
            when(it.itemId) {
                R.id.mi_home -> {
                    if(cf is MessagesFragment) {
                        cf.closeAnimation()
                    }
                    setCurrentFragment(homeFragment)
                }
                R.id.mi_messages -> {
                    if(cf is HomeFragment) {
                        cf.onBackPressed()
                    }
                    setCurrentFragment(messagesFragment)
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}