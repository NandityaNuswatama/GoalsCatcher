package com.nandits.goalscatcher.utils

data class SelectableItem<T>(
    val value: T,
    val name: String = value.toString(),
    var isSelected: Boolean = false,
    var isSelectable: Boolean = false,
)

typealias OnItemClickListener<T> = (item: T, isSelected: Boolean) -> Unit