package com.applichic.chicsecret.ui.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.applichic.chicsecret.R


class IconAdapter(val context: Context, private var icons: List<String>) : BaseAdapter() {
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    var selectedIcon = 0
    var selectedColor = ContextCompat.getColor(context, R.color.blue)

    override fun getCount(): Int {
        return icons.size
    }

    override fun getItem(index: Int): String {
        return icons[index]
    }

    override fun getItemId(index: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var v = view
        if (v == null) {
            v = inflater.inflate(R.layout.item_icon, parent, false)
        }

        val backgroundView = v!!.findViewById<ConstraintLayout>(R.id.icon_background_color_view)
        val imageView = v.findViewById<ImageView>(R.id.icon_item_icon)
        val drawable =
            context.resources.getIdentifier(icons[position], "drawable", context.packageName)
        imageView.setImageDrawable(ContextCompat.getDrawable(context, drawable))

        if (selectedIcon == position) {
            backgroundView.setBackgroundColor(selectedColor)
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.white))
        } else {
            backgroundView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.secondaryBackground
                )
            )
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.text))
        }

        return v
    }

}