package com.bitpanda.developertest.ui.wallets

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.R
import com.bitpanda.developertest.model.Asset
import com.bitpanda.developertest.model.Wallet
import com.bitpanda.developertest.model.Metal
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class WalletAdapter internal constructor(
    context: Context,
    private val listener: (Asset) -> Unit
) : RecyclerView.Adapter<WalletAdapter.WalletViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var data = emptyList<Wallet>()

    override fun getItemCount() = data.size
    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        return WalletViewHolder(
           inflater.inflate(R.layout.item_wallet, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) = with(holder) {
        val item = data[position]

        GlideToVectorYou
                .init()
                .with(holder.itemView.context)
                .load(Uri.parse(item.currency.logo), iconImageView)

        balanceTextView.text = item.balance.toString()

        var symbol = item.currency.symbol
        if (item.currency is Metal)
            symbol += " - ${item.currency.name}"
        symbolTextView.text = symbol

        if (item.currency is Asset)
            holder.itemView.setOnClickListener { listener(item.currency as Asset) }
        else
            holder.itemView.setOnClickListener(null)
    }

    internal fun setData(data: List<Wallet>) {
        this.data = data
        notifyDataSetChanged()
    }

    class WalletViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconImageView: ImageView = itemView.findViewById(R.id.iconImage)
        val symbolTextView: TextView = itemView.findViewById(R.id.symbolTextView)
        val balanceTextView: TextView = itemView.findViewById(R.id.balanceTextView)
    }

}