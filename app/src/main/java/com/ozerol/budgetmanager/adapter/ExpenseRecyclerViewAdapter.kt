package com.ozerol.budgetmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.databinding.ItemExpenseBinding

class ExpenseRecyclerViewCallBack : DiffUtil.ItemCallback<Expense>() {
    override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem == newItem
    }
}

class ClickWatcher(val clickWatcher: (expenseId: Long) -> Unit) {
    fun clicked(expense: Expense) = clickWatcher(expense.id)
}

class ExpenseRecyclerViewAdapter(private val clickWatcher: ClickWatcher) :
    ListAdapter<Expense, ExpenseRecyclerViewAdapter.ViewHolder>(ExpenseRecyclerViewCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseRecyclerViewAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ExpenseRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickWatcher)
    }

    class ViewHolder private constructor(private val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Expense, clickWatcher: ClickWatcher) {
            binding.expense = item
            binding.executePendingBindings()
            binding.clickWatcher = clickWatcher
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemExpenseBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}