package com.bitpanda.developertest.ui.wallets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.repository.Repository
import com.bitpanda.developertest.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var index = 0

    private val _title = MutableLiveData<String>()
    var title: LiveData<String> = _title

    val wallets: MutableLiveData<List<Wallet>> = repository.getWalletsFlow()

    // Function to change currency in list and title
    fun changeCurrency() = viewModelScope.launch {
        if(index >= Constants.types.size - 1) index = 0 else index++
        wallets.value = repository.getWalletsByCurrency(Constants.types[index].first)
        _title.value = Constants.types[index].second
    }
}