package com.bitpanda.developertest.ui.price

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import com.bitpanda.developertest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PriceActivity : AppCompatActivity() {

    companion object {
        const val BUNDLE_KEY_PRICE = "BUNDLE_KEY_PRICE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price)
        val price = intent.getStringExtra(BUNDLE_KEY_PRICE)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, PriceFragment.newInstance(price), PriceFragment.TAG)
            }
        }
    }

}