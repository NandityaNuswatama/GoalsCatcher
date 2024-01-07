package com.nandits.goalscatcher.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nandits.goalscatcher.data.Goal
import com.nandits.goalscatcher.databinding.ItemGoalsBinding

class GoalAdapter(private val callback: GoalAdapterListener) :
    ListAdapter<Goal, GoalAdapter.ViewHolder>(GoalComparator) {

    inner class ViewHolder(private val binding: ItemGoalsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Goal) {
            with(binding) {
                tvGoal.text = data.goalTitle
                cbGoal.isChecked = data.isAchieved
                cbGoal.setOnCheckedChangeListener { _, isChecked ->
                    callback.onCheckBoxCheckChanged(isChecked)
                }
            }
        }
    }


    object GoalComparator : DiffUtil.ItemCallback<Goal>() {
        override fun areItemsTheSame(oldItem: Goal, newItem: Goal): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Goal, newItem: Goal): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemGoalsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

interface GoalAdapterListener {
    fun onCheckBoxCheckChanged(isChecked: Boolean)
}