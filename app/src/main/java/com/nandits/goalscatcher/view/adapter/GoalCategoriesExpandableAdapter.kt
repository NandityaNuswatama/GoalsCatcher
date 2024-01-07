package com.nandits.goalscatcher.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nandits.goalscatcher.R
import com.nandits.goalscatcher.data.Category
import com.nandits.goalscatcher.data.Goal
import com.nandits.goalscatcher.data.toGoal
import com.nandits.goalscatcher.databinding.ItemGoalsCategoryBinding
import com.nandits.goalscatcher.utils.SelectableItem
import com.nandits.goalscatcher.utils.onlyVisibleIf

class GoalCategoriesExpandableAdapter(private val callback: GoalCategoriesExpandableListeners) :
    ListAdapter<SelectableItem<Category>, GoalCategoriesExpandableAdapter.ViewHolder>(CategoryComparator), GoalAdapterListener {

    private val goalAdapter by lazy { GoalAdapter(this) }
    private var totalAchieved = 0

    inner class ViewHolder(private val binding: ItemGoalsCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SelectableItem<Category>) {
            with(binding) {

                val goals: List<Goal> = data.value.goals.map { it.toGoal() }

                toggleCategory(this, data.isSelected)

                totalAchieved = data.value.totalAchieved

                goalAdapter.submitList(goals)
                tvCategory.text = data.value.categoryTitle
                tvGoalsCounter.text = itemView.context.getString(
                    R.string.format_one_slash_two,
                    totalAchieved.toString(),
                    goals.size.toString()
                )

                btnDown.setOnClickListener {
                    toggleCategory(binding, true)
                }

                btnUp.setOnClickListener {
                    toggleCategory(binding, false)
                }

                btnDetails.setOnClickListener {
                    callback.onDetailClicked(data.value)
                }

                btnAdd.setOnClickListener {
                    callback.onAddClicked(data.value)
                }

                btnDelete.setOnClickListener {
                    callback.onDeleteClicked(data.value)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemGoalsCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object CategoryComparator : DiffUtil.ItemCallback<SelectableItem<Category>>() {
        override fun areItemsTheSame(oldItem: SelectableItem<Category>, newItem: SelectableItem<Category>): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SelectableItem<Category>, newItem: SelectableItem<Category>): Boolean {
            return oldItem == newItem
        }
    }

    private fun toggleCategory(binding: ItemGoalsCategoryBinding, isExpanded: Boolean) {
        with(binding) {
            btnAdd.onlyVisibleIf(isExpanded)
            btnDelete.onlyVisibleIf(isExpanded)
            btnDetails.onlyVisibleIf(isExpanded)
            rvGoals.onlyVisibleIf(isExpanded)
            dividerBottom.onlyVisibleIf(isExpanded)
            dividerTop.onlyVisibleIf(isExpanded)
            btnUp.onlyVisibleIf(isExpanded)
            btnDown.onlyVisibleIf(!isExpanded)
        }
    }

    override fun onCheckBoxCheckChanged(isChecked: Boolean) {
        if (isChecked) totalAchieved += 1 else totalAchieved -= 1
    }
}

interface GoalCategoriesExpandableListeners {
    fun onDetailClicked(data: Category)
    fun onAddClicked(data: Category)
    fun onDeleteClicked(data: Category)
}