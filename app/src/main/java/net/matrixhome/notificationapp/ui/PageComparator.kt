package net.matrixhome.notificationapp.ui

import androidx.recyclerview.widget.DiffUtil
import net.matrixhome.notificationapp.model.PageRoom

class PageComparator(oldItems: MutableList<PageRoom>, newItems: MutableList<PageRoom>)
    : DiffUtil.Callback() {

    private var oldItems = ArrayList(oldItems)
    private var newItems = ArrayList(newItems)

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].lastViewed == newItems[newItemPosition].lastViewed
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}