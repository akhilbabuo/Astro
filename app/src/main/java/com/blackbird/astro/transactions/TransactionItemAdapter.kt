package com.blackbird.astro.transactions

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackbird.astro.core.db.entity.Transaction
import com.blackbird.astro.databinding.TransactionListItemBinding

class TransactionItemAdapter :
    RecyclerView.Adapter<TransactionItemAdapter.TransactionItemViewHolder>() {

    private val _transactionsList: MutableList<Transaction> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionItemViewHolder {
        return TransactionItemViewHolder(
            TransactionListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return _transactionsList.size
    }

    override fun onBindViewHolder(
        holder: TransactionItemAdapter.TransactionItemViewHolder,
        position: Int
    ) {
        holder.onBind(_transactionsList[position])
    }


    inner class TransactionItemViewHolder(val binding: TransactionListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(transaction: Transaction) {
            binding.let {
                it.tvDate.text = transaction.time.toString()
                it.tvAmount.text = transaction.amount.toString()
                it.tvMethod.text = transaction.method
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTransactionList(dataList: List<Transaction>){
        _transactionsList.clear()
        _transactionsList.addAll(dataList)
        notifyDataSetChanged()
    }



}