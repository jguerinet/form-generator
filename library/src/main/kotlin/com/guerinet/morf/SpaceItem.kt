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

package com.guerinet.morf

import android.view.View
import com.guerinet.morf.base.Item
import com.guerinet.morf.util.Layout

/**
 * Space in the form
 * @author Julien Guerinet
 * @since 2.0.0
 *
 * @param fg [FormGenerator] instance
 */
class SpaceItem(fg: FormGenerator) : Item<SpaceItem, View>(fg, View(fg.context)) {

    init {
        // Set the width
        layout(Layout.MATCH_PARENT)

        // Height
        val spaceHeightId = fg.settings.spaceHeightId
        if (spaceHeightId != null) {
            heightId(spaceHeightId)
        } else {
            val spacePixelHeight = fg.settings.spacePixelHeight
            if (spacePixelHeight != null) {
                pixelHeight(spacePixelHeight)
            } else {
                dpHeight(fg.settings.spaceDpHeight)
            }
        }

        // Background
        val spaceColor = fg.settings.spaceColor
        if (spaceColor != null) {
            backgroundColor(spaceColor)
        } else {
            backgroundId(fg.settings.spaceBackgroundId)
        }

        build()
    }
}