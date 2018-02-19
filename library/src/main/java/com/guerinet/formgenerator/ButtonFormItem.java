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

package com.guerinet.formgenerator;

import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;

import com.guerinet.fg.TextViewItem;

/**
 * Builder for a {@link Button} form item
 * @author Julien Guerinet
 * @since 2.0.0
 */
public class ButtonFormItem extends TextViewItem<ButtonFormItem, Button> {

	/**
	 * Default Constructor
	 *
	 * @param fg       The {@link FormGenerator} instance
	 * @param view     The {@link View}
	 */
	ButtonFormItem(FormGenerator fg, View view) {
        super(fg, view, view.findViewById(R.id.fg_button), false);
        // Bold buttons
        style(Typeface.BOLD, fg.builder.defaultTextTypeface);
    }
}
