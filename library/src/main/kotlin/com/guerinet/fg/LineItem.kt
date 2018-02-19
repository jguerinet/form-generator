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

import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.view.View
import com.guerinet.formgenerator.FormGenerator

/**
 * Line within the form. Base class for all other items except for space
 * @author Julien Guerinet
 * @since 2.0.0
 *
 * @param fg    [FormGenerator] instance
 * @param view  Item [View]
 * @param line  Line [View] (same as [view] if this is just a line]
 */
@Suppress("UNCHECKED_CAST")
open class LineItem<T : LineItem<T>>(fg: FormGenerator, view: View, private val line: View = view) :
        Item<T>(fg, view) {

    init {
        if (fg.builder.defaultLineSize != -1) {
            // Set the default line size if there is one
            lineSize(fg.builder.defaultLineSize)
        }

        if (fg.builder.defaultLineColorId != -1) {
            // Set the default background if there is one
            lineBackground(fg.builder.defaultLineColorId)
        }

        if (view != line) {
            // Set the line visibility if this is not an independent item
            showLine(fg.builder.showLine)
        }
    }

    /**
     * @return [LineItem] instance with its new [size] set
     */
    fun lineSize(size: Int): T {
        line.layoutParams.height = size
        return this as T
    }

    /**
     * @return [LineItem] instance with its new background with the given [resId] set
     */
    fun lineBackground(@DrawableRes @ColorRes resId: Int): T {
        line.setBackgroundResource(resId)
        return this as T
    }


    /**
     * @return Item with its new line [color] set
     */
    fun lineColor(@ColorInt color: Int): T {
        line.setBackgroundColor(color)
        return this as T
    }

    /**
     * @return Item with the line [show]n or hidden
     */
    fun showLine(show: Boolean): T {
        line.visibility = if (show) View.VISIBLE else View.GONE
        return this as T
    }
}