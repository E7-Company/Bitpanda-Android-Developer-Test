package com.bitpanda.developertest.ui.price

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bitpanda.developertest.R
import com.bitpanda.developertest.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_price.*

@AndroidEntryPoint
class PriceFragment : Fragment() {
    companion object {
        val TAG = PriceFragment::class.java.simpleName
        private const val BUNDLE_KEY_PRICE = "BUNDLE_KEY_PRICE"
        fun newInstance(price: String?) = PriceFragment().apply {
            arguments = bundleOf(Pair(BUNDLE_KEY_PRICE, price))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_price, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        priceTextView.text = "${getPrice()}${Constants.euro}"
    }

    private fun getPrice(): String? {
        return requireArguments().getString(BUNDLE_KEY_PRICE)
    }
}