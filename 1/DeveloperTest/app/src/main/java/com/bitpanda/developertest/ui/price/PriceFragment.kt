package com.bitpanda.developertest.ui.price

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitpanda.developertest.databinding.FragmentPriceBinding
import com.bitpanda.developertest.utils.Constants
import com.bitpanda.developertest.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PriceFragment : Fragment() {

    companion object {
        const val BUNDLE_KEY_PRICE = "BUNDLE_KEY_PRICE"
    }

    private var binding: FragmentPriceBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.priceTextView.text = "${getPrice()}${Constants.euro}"
    }

    private fun getPrice(): String? {
        return requireArguments().getString(BUNDLE_KEY_PRICE)
    }
}