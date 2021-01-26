package com.applichic.chicsecret.ui.categories

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.applichic.chicsecret.R


class ColorAdapter(val context: Context, private var colors: List<Int>) : BaseAdapter() {
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    var selectedColor = 0

    override fun getCount(): Int {
        return colors.size
    }

    override fun getItem(index: Int): Int {
        return colors[index]
    }

    override fun getItemId(index: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var v = view
        if (v == null) {
            v = inflater.inflate(R.layout.item_color, parent, false)
        }

        val backgroundView = v!!.findViewById<ConstraintLayout>(R.id.color_background_color_view)
        val backgroundViewBorder =
            v.findViewById<ConstraintLayout>(R.id.color_background_color_view_border)
        val drawable = backgroundView.background as GradientDrawable
        drawable.setColor(ContextCompat.getColor(context, colors[position]))

        if (selectedColor == position) {
            val drawableBorder = backgroundViewBorder.background as GradientDrawable
            drawableBorder.setColor(ContextCompat.getColor(context, R.color.text))
        } else {
            val drawableBorder = backgroundViewBorder.background as GradientDrawable
            drawableBorder.setColor(ContextCompat.getColor(context, R.color.secondaryBackground))
        }

        return v
    }
}