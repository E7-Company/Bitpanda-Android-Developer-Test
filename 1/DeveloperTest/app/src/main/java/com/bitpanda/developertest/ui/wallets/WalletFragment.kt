package com.bitpanda.developertest.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitpanda.developertest.databinding.FragmentWalletsBinding
import com.bitpanda.developertest.model.Asset
import com.bitpanda.developertest.utils.autoCleared
import com.bitpanda.developertest.utils.extensions.format
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : Fragment() {

    private var binding: FragmentWalletsBinding by autoCleared()
    private val viewModel by viewModels<WalletViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup UI
        val adapter = WalletAdapter(::onItemClickListener)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        viewModel.wallets.observe(viewLifecycleOwner) { adapter.setData(it) }
        viewModel.title.observe(viewLifecycleOwner) { binding.titleTextView.text = it }
        binding.fab.setOnClickListener { viewModel.changeCurrency() }
    }

    // Show detail view with price of the coin
    private fun onItemClickListener(asset: Asset) {
        val action = WalletFragmentDirections.actionWalletsFragmentToPriceFragment()
            .setPriceArg(asset.price.format(asset.precision))
        findNavController().navigate(action)
    }

}