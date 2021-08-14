package com.bitpanda.developertest.ui.wallets

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.databinding.ItemWalletBinding
import com.bitpanda.developertest.model.Asset
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.model.Metal
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class WalletAdapter internal constructor(
    private val listener: (Asset) -> Unit
) : RecyclerView.Adapter<WalletAdapter.WalletViewHolder>() {

    private var data = emptyList<Wallet>()

    override fun getItemCount() = data.size
    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val binding: ItemWalletBinding = ItemWalletBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WalletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) = with(holder) {
        val item = data[position]
        holder.bind(item)
        if (item.currency is Asset)
            holder.itemView.setOnClickListener { listener(item.currency as Asset) }
        else
            holder.itemView.setOnClickListener(null)
    }

    internal fun setData(data: List<Wallet>) {
        this.data = data
        notifyDataSetChanged()
    }

    class WalletViewHolder(private val itemWalletBinding: ItemWalletBinding) : RecyclerView.ViewHolder(
        itemWalletBinding.root
    ) {
        fun bind(item: Wallet) {
            GlideToVectorYou
                .init()
                .with(itemView.context)
                .load(Uri.parse(item.currency.logo), itemWalletBinding.iconImage)

            itemWalletBinding.balanceTextView.text = item.balance.toString()

            var symbol = item.currency.symbol
            if (item.currency is Metal)
                symbol += " - ${item.currency.name}"
            itemWalletBinding.symbolTextView.text = symbol
        }
    }

}