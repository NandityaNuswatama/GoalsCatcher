package com.nandits.goalscatcher.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nandits.goalscatcher.data.Goal
import com.nandits.goalscatcher.databinding.ItemDetailGoalBinding
import com.nandits.goalscatcher.utils.SelectableItem
import com.nandits.goalscatcher.utils.loadFileImage
import com.nandits.goalscatcher.utils.onlyVisibleIf

class DetailGoalsAdapter(private val callback: DetailGoalListeners) :
    ListAdapter<SelectableItem<Goal>, DetailGoalsAdapter.ViewHolder>(GoalComparator) {
    inner class ViewHolder(private val binding: ItemDetailGoalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SelectableItem<Goal>) {
            with(binding) {
                cbGoal.isChecked = data.value.isAchieved
                cbGoal.text = data.value.goalTitle
                imgPlaceholder.onlyVisibleIf(data.value.photos.isEmpty())
                imgGoal.onlyVisibleIf(data.value.photos.isNotEmpty())
                imgGoal.loadFileImage(data.value.photos.random())

                cardGoal.setOnClickListener {
                    if (data.isSelectable) callback.onCardClicked(data.value)
                }

                cardGoal.setOnLongClickListener {
                    callback.onCardLongClicked(data.value)
                    data.isSelectable = true
                    notifyItemChanged(adapterPosition)
                    true
                }

                cardGoal.isActivated = data.isSelected
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDetailGoalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object GoalComparator : DiffUtil.ItemCallback<SelectableItem<Goal>>() {
        override fun areItemsTheSame(oldItem: SelectableItem<Goal>, newItem: SelectableItem<Goal>): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SelectableItem<Goal>, newItem: SelectableItem<Goal>): Boolean {
            return oldItem == newItem
        }
    }
}

interface DetailGoalListeners {
    fun onCardClicked(goal: Goal)
    fun onCardLongClicked(goal: Goal)
}