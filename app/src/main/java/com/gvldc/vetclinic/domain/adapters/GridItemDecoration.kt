package com.gvldc.vetclinic.domain.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        // Calculate span index based on number of columns
        val spanIndex = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex

        // Add spacing between items (only for left and right items)
        val spacingLeft = if (includeEdge && spanIndex == 0) spacing else spacing / 2
        val spacingRight = if (includeEdge && spanIndex == spanCount - 1) spacing else spacing / 2

        // Set horizontal offset for left and right items
        outRect.left = spacingLeft
        outRect.right = spacingRight

        // Set vertical offset for top items
        if (position < spanCount) {
            outRect.top = spacing
        } else {
            outRect.top = 0
        }

        // Set vertical offset for bottom items
        if (position >= spanCount) {
            outRect.bottom = spacing
        } else {
            outRect.bottom = 0
        }
    }
}