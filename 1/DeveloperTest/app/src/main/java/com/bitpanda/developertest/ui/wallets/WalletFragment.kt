package com.bitpanda.developertest.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitpanda.developertest.R
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
        binding.titleView.setContent {
            ShowTitle()
        }
        binding.fab.setContent {
            ChangeButton()
        }

        val adapter = WalletAdapter(::onItemClickListener)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        viewModel.wallets.observe(viewLifecycleOwner) { adapter.setData(it) }
    }

    // Show detail view with price of the coin
    private fun onItemClickListener(asset: Asset) {
        val action = WalletFragmentDirections.actionWalletsFragmentToPriceFragment()
            .setPriceArg(asset.price.format(asset.precision))
        findNavController().navigate(action)
    }

    @Composable
    private fun ChangeButton() {
        FloatingActionButton(
            onClick = { viewModel.changeCurrency() },
            modifier = Modifier
                .padding(dimensionResource(R.dimen.margin_fragment))
        ) {
            Icon(
                Icons.Filled.Refresh,
                contentDescription = getString(R.string.change_type),
                tint = Color.White
            )
        }
    }

    @Composable
    private fun ShowTitle() {
        val title: String by viewModel.title.observeAsState("")
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.margin_default))
                    .fillMaxWidth()
            )
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }

}