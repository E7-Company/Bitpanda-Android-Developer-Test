package com.bitpanda.developertest.ui.wallets

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitpanda.developertest.R
import com.bitpanda.developertest.model.Asset
import com.bitpanda.developertest.ui.price.PriceActivity
import com.bitpanda.developertest.utils.extensions.format
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.fragment_wallets.*

@AndroidEntryPoint
class WalletFragment : Fragment() {

    companion object {
        val TAG = WalletFragment::class.java.simpleName
        fun newInstance() = WalletFragment()
    }

    private val viewModel by viewModels<WalletViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WalletAdapter(view.context, ::onItemClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        viewModel.wallets.observe(viewLifecycleOwner) { adapter.setData(it) }
        viewModel.title.observe(viewLifecycleOwner) { activity?.titleTextView?.text = it }
        fab.setOnClickListener { viewModel.changeCurrency() }
    }

    // Show detail view with price of the coin
    private fun onItemClickListener(asset: Asset) {
        val context = context ?: return
        startActivity(Intent(context, PriceActivity::class.java).apply {
            putExtra(PriceActivity.BUNDLE_KEY_PRICE, asset.price.format(asset.precision))
        })
    }

}