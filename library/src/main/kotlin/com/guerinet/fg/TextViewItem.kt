/*
 * Copyright 2015-2018 Julien Guerinet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.guerinet.fg

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.guerinet.formgenerator.FormGenerator
import com.guerinet.formgenerator.R

/**
 * Builder for a [TextView] form item (buttons, texts, switches, inputs)
 * @author Julien Guerinet
 * @since 2.0.0
 */
@Suppress("UNCHECKED_CAST")
open class TextViewItem<T : TextViewItem<T, TextView>, out V : TextView>(
        fg: FormGenerator,
        view: View,
        protected val childView: V,
        background: Boolean) : LineItem<T>(fg, view, view.findViewById(R.id.fg_line)
) {

    /**
     * List of empty [Icon]s to keep track of the compound drawables to set
     *  Order: start, top, end, bottom
     */
    private val icons: Array<Icon> = arrayOf(Icon(), Icon(), Icon(), Icon())

    init {
        view.isClickable = false

        // Background
        if (background && fg.builder.defaultBackgroundId != null) {
            backgroundId(fg.builder.defaultBackgroundId)
        }

        // Text Color
        textColor(fg.builder.defaultTextColor)

        // Text Size
        textSizeId(fg.builder.defaultTextSize)

        // Padding
        if (fg.builder.defaultPaddingSize != -1) {
            padding(fg.builder.defaultPaddingSize)
        }

        // Typeface
        typeface(fg.builder.defaultTextTypeface)
    }

    /**
     * @return Item with the [text] set
     */
    fun text(text: String?): T {
        childView.text = text
        return this as T
    }

    /**
     * @return Item with the text with [stringId] set
     */
    fun text(@StringRes stringId: Int): T {
        childView.setText(stringId)
        return this as T
    }

    /**
     * @return Item with the [hint] set
     */
    open fun hint(hint: String?): T {
        childView.hint = hint
        return this as T
    }

    /**
     * @return Item with the hint with [stringId] set
     */
    open fun hint(@StringRes stringId: Int): T {
        childView.setHint(stringId)
        return this as T
    }

    /**
     * Sets whether the returned item [isFocusable] or not
     */
    open fun isFocusable(isFocusable: Boolean): T {
        childView.isFocusable = isFocusable
        return this as T
    }

    /**
     * Sets whether the returned item [isEnabled] or not
     */
    open fun isEnabled(isEnabled: Boolean): T {
        childView.isEnabled = isEnabled
        return this as T
    }

    /**
     * @return Item with the text set to the given [color]
     */
    fun textColor(@ColorInt color: Int): T {
        childView.setTextColor(color)
        return this as T
    }

    /**
     * @return Item with the text size set to the dimension with the [sizeId]
     */
    fun textSizeId(@DimenRes sizeId: Int?): T {
        // If it's null, don't do anything
        if (sizeId != null) {
            childView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    fg.container.resources.getDimension(sizeId))
        }
        return this as T
    }

    /**
     * Sets the [start], [top], [end], and [bottom] paddings (in pixels) on the returned item.
     *  If one of these is null, the padding remains what it is currently for that side
     */
    fun padding(start: Int? = null, top: Int? = null, end: Int? = null, bottom: Int? = null): T {
        // Use the current paddings if one of them is null
        childView.setPaddingRelative(start ?: childView.paddingStart, top ?: childView.paddingTop,
                end ?: childView.paddingEnd, bottom ?: childView.paddingBottom)
        return this as T
    }

    /**
     * @return Item with the given [padding] (in pixels) on all 4 sides
     */
    fun padding(padding: Int): T = padding(padding, padding, padding, padding)

    /**
     * @return Item with the text in the given [typeface]
     */
    open fun typeface(typeface: Typeface): T {
        childView.typeface = typeface
        return this as T
    }

    /**
     * @return Item with the applied text [style] and [typeface]
     *  (which defaults to the default one if none specified)
     */
    fun style(style: Int, typeface: Typeface = fg.builder.defaultTextTypeface): T {
        childView.setTypeface(typeface, style)
        return this as T
    }

    /**
     * Sets up an icon at the given [position] with the given [drawableId],
     *  [color] (defaults to the default icon color), and whether it [isVisible] (defaults to true)
     */
    fun icon(@Position position: Int, @DrawableRes drawableId: Int?,
             @ColorInt color: Int? = fg.builder.defaultIconColor,
             isVisible: Boolean = true) {
        icons[position] = Icon(drawableId, color = color, isVisible = isVisible)
    }

    /**
     * Sets up an icon at the given [position] with the given [drawable],
     *  [color] (defaults to the default icon color), and whether it [isVisible] (defaults to true)
     */
    fun icon(@Position position: Int, drawable: Drawable?,
             @ColorInt color: Int? = fg.builder.defaultIconColor,
             isVisible: Boolean = true) {
        icons[position] = Icon(drawable = drawable, color = color, isVisible = isVisible)
    }


    /**
     * @return Item with the given [OnClickListener] set for click events
     */
    open fun onClick(listener: OnClickListener<T>?): T {
        view.setOnClickListener { _ -> listener?.onClick(this as T) }
        return this as T
    }

    /**
     * @return Item with the given [gravity] set
     */
    fun gravity(gravity: Int): T {
        childView.gravity = gravity
        return this as T
    }

    /**
     * @return Item with the single line option removed
     */
    fun removeSingleLine(): T {
        childView.setSingleLine(false)
        return this as T
    }

    /**
     * @return Item with the given ellipsize [type] set
     */
    fun ellipsize(type: TextUtils.TruncateAt): T {
        childView.ellipsize = type
        return this as T
    }

    /**
     * @return Item with the given [visibility] set
     */
    fun visibility(visibility: Int): T {
        childView.visibility = visibility
        return this as T
    }

    /**
     * @return Item with the given background with [backgroundId] set
     */
    open fun backgroundId(backgroundId: Int): T {
        view.setBackgroundResource(backgroundId)
        return this as T
    }

    /**
     * @return Item with the given layout [params] and [gravity] (null if none, defaults to null)
     *  set
     */
    fun layoutParams(params: LinearLayout.LayoutParams, gravity: Int? = null): T {
        if (gravity != null) {
            params.gravity = gravity
        }
        view.layoutParams = params
        return this as T
    }

    /**
     * Updates the shown icons on the returned item, without re-adding the view to the container
     */
    fun updateIcons(): T {
        // Get all of the drawables
        val drawables = arrayOf(getDrawable(icons[0]), getDrawable(icons[1]), getDrawable(icons[2]),
                getDrawable(icons[3]))

        // Set the compound drawable padding
        if (fg.builder.defaultDrawablePaddingSize != -1) {
            childView.compoundDrawablePadding = fg.builder.defaultDrawablePaddingSize
        }

        // Set the correct tinting and alpha for each drawable
        for (i in drawables.indices) {
            val icon = icons[i]
            var drawable = drawables[i]
            if (drawable != null) {
                if (!icon.isVisible) {
                    // Make it invisible if necessary
                    drawable.alpha = 0
                } else if (icon.color != null) {
                    // Wrap it in the design support library and set the tint if we have a color
                    drawable = DrawableCompat.wrap(drawable.mutate())
                    DrawableCompat.setTint(drawable, icon.color)
                }
            }
        }

        // Set the drawables on the view
        childView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1],
                drawables[2], drawables[3])

        return this as T
    }

    /**
     * @return parent [View]
     */
    fun parent(): View = view

    /**
     * @return [TextView] instance
     */
    override fun view(): V = childView

    override fun build(): T {
        updateIcons()
        return super.build()
    }

    /**
     * @return [Drawable] to use for the given [icon], null if none
     */
    private fun getDrawable(icon: Icon): Drawable? {
        return if (icon.drawableId != null) {
            ContextCompat.getDrawable(view.context, icon.drawableId)
        } else {
            icon.drawable
        }
    }

    companion object {
        /**
         * Defines the different positions of an icon
         */
        @IntDef(START, TOP, END, BOTTOM)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Position

        const val START = 0L
        const val TOP = 1L
        const val END = 2L
        const val BOTTOM = 3L
    }

    /**
     * Custom listener implmentation to listen for item clicks which returns the clicked form item
     */
    interface OnClickListener<in T> {

        /**
         * Called when the [item] has been clicked
         */
        fun onClick(item: T)
    }

    /**
     * Keeps track of the icons to add
     *
     * @param drawableId    Id of the resource to load, null if none (defaults to null)
     * @param drawable      Drawable to use, null if none (defaults to null)
     * @param color         Icon color, null if none (defaults to null)
     * @param isVisible     True if the icon should be visible, false otherwise (defaults to false)
     */
    private class Icon(@DrawableRes val drawableId: Int? = null, val drawable: Drawable? = null,
                       @ColorInt val color: Int? = null, val isVisible: Boolean = false)
}