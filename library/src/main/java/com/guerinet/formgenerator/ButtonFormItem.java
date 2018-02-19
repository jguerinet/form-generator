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
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Builder for a {@link Button} form item
 * @author Julien Guerinet
 * @since 2.0.0
 */
public class ButtonFormItem extends TextViewFormItem {

	/**
	 * Default Constructor
	 *
	 * @param fg       The {@link FormGenerator} instance
	 * @param view     The {@link View}
	 */
	ButtonFormItem(FormGenerator fg, View view) {
        super(fg, view, view.findViewById(R.id.fg_button), false);
        // Bold buttons
        style(fg.builder.defaultTextTypeface, Typeface.BOLD);
    }

    /**
     * Sets the {@link Button} text
     *
     * @param text Text to set
     * @return The {@link ButtonFormItem} instance
     */
    @Override
    public ButtonFormItem text(String text) {
        return (ButtonFormItem) super.text(text);
    }

    /**
     * Sets the {@link Button} text
     *
     * @param text String Id of text to set
     * @return The {@link ButtonFormItem} instance
     */
    @Override
    public ButtonFormItem text(@StringRes int text) {
        return (ButtonFormItem) super.text(text);
    }

	/**
	 * Sets the {@link Button} hint
	 *
	 * @param hint The hint
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem hint(String hint) {
		return (ButtonFormItem) super.hint(hint);
	}

	/**
	 * Sets the {@link Button} hint
	 *
	 * @param stringId The String Id
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem hint(@StringRes int stringId) {
		return (ButtonFormItem) super.hint(stringId);
	}

    /**
     * @param focusable True if the item should be focusable, false otherwise
     * @return {@link ButtonFormItem} instance
     */
    @Override
    public ButtonFormItem focusable(boolean focusable) {
        return (ButtonFormItem) super.focusable(focusable);
    }

    /**
     * @param enabled True if the item should be enabled, false otherwise
     * @return {@link EditTextFormItem} instance
     */
    @Override
    public ButtonFormItem enabled(boolean enabled) {
        return (ButtonFormItem) super.enabled(enabled);
    }

	/**
	 * Sets the {@link Button} text color
	 *
	 * @param color The color
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem textColor(@ColorInt int color) {
		return (ButtonFormItem) super.textColor(color);
	}

	/**
	 * Sets the {@link Button} text size
	 *
	 * @param size The text size from the dimensions file
     *             (use getResources().getDimension())
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem textSize(float size) {
		return (ButtonFormItem) super.textSize(size);
 	}

	/**
	 * Sets the {@link Button} padding
	 *
	 * @param left   The size for the left padding, in <strong>pixels</strong>
	 * @param top    The size for the top padding, in <strong>pixels</strong>
	 * @param right  The size for the right padding, in <strong>pixels</strong>
	 * @param bottom The size for the bottom padding, in <strong>pixels</strong>
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem padding(int left, int top, int right, int bottom) {
		return (ButtonFormItem) super.padding(left, top, right, bottom);
	}

	/**
	 * Sets the {@link Button} padding
	 *
	 * @param pixels The size to use for all sides, in <strong>pixels</strong>
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem padding(int pixels) {
		return (ButtonFormItem) super.padding(pixels);
	}

	/**
	 * Sets the {@link Button} {@link Typeface}
	 *
	 * @param typeface The {@link Typeface}
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem typeface(Typeface typeface) {
		return (ButtonFormItem) super.typeface(typeface);
	}

	/**
	 * Sets the {@link Button} {@link Typeface} and style
	 *
	 * @param typeface The {@link Typeface}
	 * @param style    The style
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem style(Typeface typeface, int style) {
		return (ButtonFormItem) super.style(typeface, style);
	}

	/**
	 * Sets the {@link Button} style. Note: if you are using a custom {@link Typeface},
	 *  use {@link #style(Typeface, int)}
	 *
	 * @param style The style
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem style(int style) {
		return (ButtonFormItem) super.style(style);
	}

	/**
	 * Sets the left icon
	 *
	 * @param iconId  The icon resource Id
	 * @param visible True if the icon should be visible, false otherwise
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem leftIcon(@DrawableRes int iconId, boolean visible) {
		return (ButtonFormItem) super.leftIcon(iconId, visible);
	}

	/**
	 * Sets the top icon
	 *
	 * @param iconId  The icon resource Id
	 * @param visible True if the icon should be visible, false otherwise
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem topIcon(@DrawableRes int iconId, boolean visible) {
		return (ButtonFormItem) super.topIcon(iconId, visible);
	}

	/**
	 * Sets the right icon
	 *
	 * @param iconId  The icon resource Id
	 * @param visible True if the icon should be visible, false otherwise
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem rightIcon(@DrawableRes int iconId, boolean visible) {
		return (ButtonFormItem) super.rightIcon(iconId, visible);
	}

	/**
	 * Sets the bottom icon
	 *
	 * @param iconId  The icon resource Id
	 * @param visible True if the icon should be visible, false otherwise
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem bottomIcon(@DrawableRes int iconId, boolean visible) {
		return (ButtonFormItem) super.bottomIcon(iconId, visible);
	}

	/**
	 * Sets the left icon
	 *
	 * @param iconId  The icon resource Id
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem leftIcon(@DrawableRes int iconId) {
		return (ButtonFormItem) super.leftIcon(iconId);
	}

	/**
	 * Sets the top icon
	 *
	 * @param iconId  The icon resource Id
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem topIcon(@DrawableRes int iconId) {
		return (ButtonFormItem) super.topIcon(iconId);
	}

	/**
	 * Sets the right icon
	 *
	 * @param iconId  The icon resource Id
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem rightIcon(@DrawableRes int iconId) {
		return (ButtonFormItem) super.rightIcon(iconId);
	}

	/**
	 * Sets the bottom icon
	 *
	 * @param iconId  The icon resource Id
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem bottomIcon(@DrawableRes int iconId) {
		return (ButtonFormItem) super.bottomIcon(iconId);
	}

	/**
	 * Sets the left icon
	 *
	 * @param iconId The icon resource Id
	 * @param color  The color
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem leftIcon(@DrawableRes int iconId, @ColorInt int color) {
		return (ButtonFormItem) super.leftIcon(iconId, color);
	}

	/**
	 * Sets the top icon
	 *
	 * @param iconId The icon resource Id
	 * @param color  The color
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem topIcon(@DrawableRes int iconId, @ColorInt int color) {
		return (ButtonFormItem) super.topIcon(iconId, color);
	}

	/**
	 * Sets the right icon
	 *
	 * @param iconId The icon resource Id
	 * @param color  The color
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem rightIcon(@DrawableRes int iconId, @ColorInt int color) {
		return (ButtonFormItem) super.rightIcon(iconId, color);
	}

	/**
	 * Sets the bottom icon
	 *
	 * @param iconId The icon resource Id
	 * @param color  The color
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem bottomIcon(@DrawableRes int iconId, @ColorInt int color) {
		return (ButtonFormItem) super.bottomIcon(iconId, color);
	}

	/**
	 * Sets the {@link OnClickListener}
	 *
	 * @param listener The {@link OnClickListener}
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem onClick(OnClickListener listener) {
		return (ButtonFormItem) super.onClick(listener);
	}

    /**
     * Sets the {@link TextView} gravity
     *
     * @param gravity The gravity
     * @return The {@link ButtonFormItem} instance
     */
    @Override
    public ButtonFormItem gravity(int gravity) {
        return (ButtonFormItem) super.gravity(gravity);
    }

    /**
     * Sets the {@link TextView} to be multi line
     *
     * @return The {@link ButtonFormItem} instance
     */
    @Override
    public ButtonFormItem removeSingleLine() {
        return (ButtonFormItem) super.removeSingleLine();
    }

    /**
     * Sets the {@link TextView} ellipsize option
     *
     * @param type The ellipsize type
     * @return The {@link ButtonFormItem} instance
     */
    @Override
    public ButtonFormItem ellipsize(TextUtils.TruncateAt type) {
        return (ButtonFormItem) super.ellipsize(type);
    }

    /**
     * Sets the view visibility
     *
     * @param visibility View visibility, should be one of View.VISIBLE, View.INVISIBLE, View.GONE
     * @return The {@link ButtonFormItem} instance
     */
    @Override
    public ButtonFormItem visibility(int visibility) {
        return (ButtonFormItem) super.visibility(visibility);
    }

	/**
	 * Sets the background
	 *
	 * @param backgroundId The background Id
	 * @return The {@link ButtonFormItem} instance
	 */
	@Override
	public ButtonFormItem background(@ColorRes @DrawableRes int backgroundId) {
		return (ButtonFormItem) super.background(backgroundId);
	}

    /**
     * Sets the {@link LinearLayout.LayoutParams} for this view
     *
     * @param params The {@link LinearLayout.LayoutParams} to set
     * @return The {@link ButtonFormItem} instance
     */
    public ButtonFormItem layoutParams(LinearLayout.LayoutParams params) {
        return (ButtonFormItem) super.layoutParams(params);
    }

    /**
     * Sets the {@link LinearLayout.LayoutParams} for this view, as well as its layout_gravity
     *
     * @param params  The {@link LinearLayout.LayoutParams} to set
     * @param gravity The layout_gravity to set
     * @return The {@link ButtonFormItem} instance
     */
    public ButtonFormItem layoutParams(LinearLayout.LayoutParams params, int gravity) {
        return (ButtonFormItem) super.layoutParams(params, gravity);
    }

    /**
     * @return The {@link Button}
     */
    @Override
    public Button view() {
        return (Button) super.view();
    }

    /**
     * Builds the view and adds it to the container
     *
     * @return The {@link ButtonFormItem} instance
     */
    @Override
    public ButtonFormItem build() {
        return (ButtonFormItem) super.build();
    }
}
