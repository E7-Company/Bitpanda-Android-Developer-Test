package com.bitpanda.developertest.ui.wallets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import com.bitpanda.developertest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        showWalletFragment()
    }

    private fun showWalletFragment() = with(supportFragmentManager) {
        findFragmentByTag(WalletFragment.TAG)?.let { return }
        commitNow { replace(R.id.container, WalletFragment.newInstance(), WalletFragment.TAG) }
    }

}
