package com.nandits.goalscatcher.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.nandits.goalscatcher.R
import com.nandits.goalscatcher.utils.loadFileImage

class GoalPhotoAdapter(
    private val items: List<String>
) : LoopingPagerAdapter<String>(items, true) {
    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        with(convertView) {
            findViewById<ImageView>(R.id.imgGoalPhoto).loadFileImage(items[listPosition])
        }
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(container.context).inflate(R.layout.item_goal_photo, container, false)
    }
}