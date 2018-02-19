/*
 * Copyright 2015-2017 Julien Guerinet
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
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Builder for a {@link TextView} form item (buttons, texts, switches, and inputs)
 * @author Julien Guerinet
 * @since 2.0.0
 */
public class TextViewFormItem extends LineItem {
    /**
     * The form item {@link View}
     */
    protected final View view;
	/**
	 * The {@link TextView}
	 */
	final TextView textView;
	/**
	 * The list of {@link Icon}s to add
	 */
	private final Icon[] icons;

	/**
	 * Default Constructor
	 *
	 * @param fg         The {@link FormGenerator} instance
	 * @param view       The {@link View}
     * @param textView   The {@link TextView}
     * @param background True if the default background should be applied, false otherwise
	 */
    TextViewFormItem(FormGenerator fg, View view, TextView textView, boolean background) {
		super(view.findViewById(R.id.fg_line), fg);
        this.view = view;
		this.textView = textView;
		this.view.setClickable(false);

		// Icons - set them all to nothing
		icons = new Icon[4];
		icons[0] = new Icon(0, 0, false);
		icons[1] = new Icon(0, 0, false);
		icons[2] = new Icon(0, 0, false);
		icons[3] = new Icon(0, 0, false);

        // Background
        if (background && this.fg.builder.defaultBackgroundId != null) {
            background(this.fg.builder.defaultBackgroundId);
        }

		// Text Color
        textColor(this.fg.builder.defaultTextColor);

		// Text Size
		textSize(this.fg.builder.defaultTextSize);

		// Padding
        if (this.fg.builder.defaultPaddingSize != -1) {
            padding(this.fg.builder.defaultPaddingSize);
        }

		// Typeface
		typeface(this.fg.builder.defaultTextTypeface);
	}

    /**
     * Sets the {@link TextView} text
     *
     * @param text Text to set
     * @return {@link TextViewFormItem} instance
     */
    public TextViewFormItem text(String text) {
        textView.setText(text);
        return this;
    }

    /**
     * Sets the {@link TextView} text
     *
     * @param text String Id of the text to set
     * @return {@link TextViewFormItem} instance
     */
    public TextViewFormItem text(@StringRes int text) {
        textView.setText(text);
        return this;
    }

	/**
	 * Sets the {@link TextView} hint
	 *
	 * @param hint The hint
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem hint(String hint) {
		textView.setHint(hint);
		return this;
	}

	/**
	 * Sets the {@link TextView} hint
	 *
	 * @param stringId The String Id
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem hint(@StringRes int stringId) {
		textView.setHint(stringId);
		return this;
	}

    /**
     * @param focusable True if the item should be focusable, false otherwise
     * @return {@link TextViewFormItem} instance
     */
	public TextViewFormItem focusable(boolean focusable) {
        textView.setFocusable(focusable);
        return this;
    }

    /**
     * @param enabled True if the item should be enabled, false otherwise
     * @return {@link TextViewFormItem} instance
     */
	public TextViewFormItem enabled(boolean enabled) {
        textView.setEnabled(enabled);
        return this;
    }

	/**
	 * Sets the {@link TextView} text color
	 *
	 * @param color The color
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem textColor(@ColorInt int color) {
        textView.setTextColor(color);
		return this;
	}

	/**
	 * Sets the {@link TextView} text size
	 *
	 * @param size The text size from the dimensions file
     *             (use getResources().getDimension())
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem textSize(float size) {
        // If it's -1, don't do anything
        if (size != -1) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
        return this;
	}

	/**
	 * Sets the {@link TextView} padding
	 *
	 * @param left   The size for the left padding, in <strong>pixels</strong>
	 * @param top    The size for the top padding, in <strong>pixels</strong>
	 * @param right  The size for the right padding, in <strong>pixels</strong>
	 * @param bottom The size for the bottom padding, in <strong>pixels</strong>
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem padding(int left, int top, int right, int bottom) {
        textView.setPadding(left, top, right, bottom);
        return this;
	}

	/**
	 * Sets the {@link TextView} padding
	 *
	 * @param pixels The size to use for all sides, in <strong>pixels</strong>
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem padding(int pixels) {
		return padding(pixels, pixels, pixels, pixels);
	}

	/**
	 * Sets the {@link TextView} {@link Typeface}
	 *
	 * @param typeface The {@link Typeface}
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem typeface(Typeface typeface) {
		textView.setTypeface(typeface);
		return this;
	}

	/**
	 * Sets the {@link TextView} {@link Typeface} and style
	 *
	 * @param typeface The {@link Typeface}
	 * @param style    The style
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem style(Typeface typeface, int style) {
		textView.setTypeface(typeface, style);
		return this;
	}

	/**
	 * Sets the {@link TextView} style. Note: if you are using a custom {@link Typeface},
	 *  use {@link #style(Typeface, int)}
	 *
	 * @param style The style
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem style(int style) {
		textView.setTypeface(fg.builder.defaultTextTypeface, style);
		return this;
	}

	/**
	 * Sets up the icon
	 *
	 * @param position The icon position
	 * @param iconId   The icon Id
	 * @param color    The color
	 * @param visible  True if the icon should be visible, false otherwise
	 */
	private void icon(int position, @DrawableRes int iconId, @ColorInt int color, boolean visible) {
		icons[position] = new Icon(iconId, color, visible);
	}

	/**
	 * Sets up the icon with drawable
	 *
	 * @param position The icon position
	 * @param drawableIcon   The icon drawable
	 * @param color    The color
	 * @param visible  True if the icon should be visible, false otherwise
	 */
	private void icon(int position, Drawable drawableIcon, @ColorInt int color, boolean visible) {
		icons[position] = new Icon(drawableIcon, color, visible);
	}

	/**
	 * Sets the left icon
	 *
	 * @param iconId  The icon resource Id
	 * @param visible True if the icon should be visible, false otherwise
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem leftIcon(@DrawableRes int iconId, boolean visible) {
		icon(0, iconId, fg.builder.defaultIconColor, visible);
		return this;
	}

	/**
	 * Sets the top icon
	 *
	 * @param iconId  The icon resource Id
	 * @param visible True if the icon should be visible, false otherwise
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem topIcon(@DrawableRes int iconId, boolean visible) {
		icon(1, iconId, fg.builder.defaultIconColor, visible);
		return this;
	}

	/**
	 * Sets the right icon
	 *
	 * @param iconId  The icon resource Id
	 * @param visible True if the icon should be visible, false otherwise
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem rightIcon(@DrawableRes int iconId, boolean visible) {
		icon(2, iconId, fg.builder.defaultIconColor, visible);
		return this;
	}

	/**
	 * Sets the bottom icon
	 *
	 * @param iconId  The icon resource Id
	 * @param visible True if the icon should be visible, false otherwise
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem bottomIcon(@DrawableRes int iconId, boolean visible) {
		icon(3, iconId, fg.builder.defaultIconColor, visible);
		return this;
	}

	/**
	 * Sets the left icon
	 *
	 * @param iconId  The icon resource Id
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem leftIcon(@DrawableRes int iconId) {
		icon(0, iconId, fg.builder.defaultIconColor, true);
		return this;
	}

	/**
	 * Sets the left icon with drawable
	 *
	 * @param drawableIcon  The icon drawable
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem leftIcon(Drawable drawableIcon) {
		icon(0, drawableIcon, fg.builder.defaultIconColor, true);
		return this;
	}

	/**
	 * Sets the top icon
	 *
	 * @param iconId  The icon resource Id
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem topIcon(@DrawableRes int iconId) {
		icon(1, iconId, fg.builder.defaultIconColor, true);
		return this;
	}

	/**
	 * Sets the right icon
	 *
	 * @param iconId  The icon resource Id
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem rightIcon(@DrawableRes int iconId) {
		icon(2, iconId, fg.builder.defaultIconColor, true);
		return this;
	}

	/**
	 * Sets the bottom icon
	 *
	 * @param iconId  The icon resource Id
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem bottomIcon(@DrawableRes int iconId) {
		icon(3, iconId, fg.builder.defaultIconColor, true);
		return this;
	}

	/**
	 * Sets the left icon
	 *
	 * @param iconId The icon resource Id
	 * @param color  The color
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem leftIcon(@DrawableRes int iconId, @ColorInt int color) {
		icon(0, iconId, color, true);
		return this;
	}

	/**
	 * Sets the top icon
	 *
	 * @param iconId The icon resource Id
	 * @param color  The color
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem topIcon(@DrawableRes int iconId, @ColorInt int color) {
		icon(1, iconId, color, true);
		return this;
	}

	/**
	 * Sets the right icon
	 *
	 * @param iconId  The icon resource Id
	 * @param color The color
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem rightIcon(@DrawableRes int iconId, @ColorInt int color) {
		icon(2, iconId, color, true);
		return this;
	}

	/**
	 * Sets the bottom icon
	 *
	 * @param iconId  The icon resource Id
	 * @param colorId The color Id
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem bottomIcon(@DrawableRes int iconId, @ColorInt int colorId) {
		icon(3, iconId, colorId, true);
		return this;
	}

	/**
	 * Sets the {@link OnClickListener}
	 *
	 * @param listener The {@link OnClickListener} instance
	 * @return The {@link TextViewFormItem} instance
	 */
	public TextViewFormItem onClick(final OnClickListener listener) {
		view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(TextViewFormItem.this);
            }
        });
		return this;
	}

    /**
     * Sets the {@link TextView} gravity
     *
     * @param gravity The gravity
     * @return The {@link TextViewFormItem} instance
     */
    public TextViewFormItem gravity(int gravity) {
        textView.setGravity(gravity);
        return this;
    }

    /**
     * Sets the {@link TextView} to be multi line
     *
     * @return The {@link TextViewFormItem} instance
     */
    public TextViewFormItem removeSingleLine() {
        textView.setSingleLine(false);
        return this;
    }

    /**
     * Sets the {@link TextView} ellipsize option
     *
     * @param type The ellipsize type
     * @return The {@link TextViewFormItem} instance
     */
    public TextViewFormItem ellipsize(TextUtils.TruncateAt type) {
        textView.setEllipsize(type);
        return this;
    }

    /**
     * Sets the view visibility
     *
     * @param visibility View visibility, should be one of View.VISIBLE, View.INVISIBLE, View.GONE
     * @return The {@link TextViewFormItem} instance
     */
    public TextViewFormItem visibility(int visibility) {
        view.setVisibility(visibility);
        return this;
    }

    /**
	 * Sets the line size
	 *
	 * @param pixels The line size dimension Id
	 * @return The {@link TextViewFormItem} instance
	 */
	@Override
	public TextViewFormItem lineSize(int pixels) {
		return (TextViewFormItem) super.lineSize(pixels);
	}

	/**
	 * Sets the line color
	 *
	 * @param colorId The color Id
	 * @return The {@link TextViewFormItem} instance
	 */
	@Override
	public TextViewFormItem lineColor(@ColorRes @DrawableRes int colorId) {
		return (TextViewFormItem)super.lineColor(colorId);
	}

	/**
	 * Sets the line visibility
	 *
	 * @param show True if the line should be visible, false otherwise
	 * @return The {@link LineItem} instance
	 */
	@Override
	public TextViewFormItem showLine(boolean show) {
		return (TextViewFormItem)super.showLine(show);
	}

    /**
     * Sets the background
     *
     * @param backgroundId The background Id
     * @return The {@link TextViewFormItem} instance
     */
    public TextViewFormItem background(int backgroundId) {
        view.setBackgroundResource(backgroundId);
        return this;
    }

    /**
     * Sets the {@link LinearLayout.LayoutParams} for this view
     *
     * @param params The {@link LinearLayout.LayoutParams} to set
     * @return The {@link TextViewFormItem} instance
     */
    public TextViewFormItem layoutParams(LinearLayout.LayoutParams params) {
        view.setLayoutParams(params);
        return this;
    }

    /**
     * Sets the {@link LinearLayout.LayoutParams} for this view, as well as its layout_gravity
     *
     * @param params  The {@link LinearLayout.LayoutParams} to set
     * @param gravity The layout_gravity to set
     * @return The {@link TextViewFormItem} instance
     */
    public TextViewFormItem layoutParams(LinearLayout.LayoutParams params, int gravity) {
        params.gravity = gravity;
        return layoutParams(params);
    }

    /**
     * @return Parent {@link View}
     */
    public View parent() {
        return view;
    }

    /**
     * @return The {@link TextView}
     */
    @Override
    public TextView view() {
        return textView;
    }

    /**
     * Updates the shown icons without re-adding the view to the container
     *
     * @return {@link TextViewFormItem} instance
     */
    public TextViewFormItem updateIcons() {
        Drawable[] drawables = new Drawable[4];
        // Get all of the icons
        drawables[0] = getDrawable(icons[0]);
        drawables[1] = getDrawable(icons[1]);
        drawables[2] = getDrawable(icons[2]);
        drawables[3] = getDrawable(icons[3]);

        // Set the compound drawable padding
        if (fg.builder.defaultDrawablePaddingSize != -1) {
            textView.setCompoundDrawablePadding(fg.builder.defaultDrawablePaddingSize);
        }

        // Set the correct tinting and alpha
        for (int i = 0; i < 4; i++) {
            Icon icon = icons[i];
            Drawable drawable = drawables[i];
            if (drawable != null) {
                // Wrap it in the design support library
                drawable = DrawableCompat.wrap(drawable.mutate());
                if (!icon.visible) {
                    drawable.setAlpha(0);
                } else if (icon.color != -1) {
                    DrawableCompat.setTint(drawable, icon.color);
                }
            }
        }

        // Set the drawables on the view
        textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2],
                drawables[3]);

        return this;
    }

    /**
     * Builds the view (including the icons) and adds it to the container
     *
     * @return The {@link TextViewFormItem} instance
     */
    @CallSuper
	public TextViewFormItem build() {
        updateIcons();

        // Add the view to the container
        fg.container.addView(view);
        return this;
	}

    /**
     * @param icon {@link Icon} instance
     * @return Drawable to use, null if none
     */
    @Nullable
    private Drawable getDrawable(Icon icon) {
    	Drawable returnedDrawable = null;

    	if (icon.drawableId != 0) {
    		returnedDrawable = ContextCompat.getDrawable(view.getContext(), icon.drawableId);
		} else if (icon.drawableIcon != null) {
			returnedDrawable = icon.drawableIcon;
		}

		return returnedDrawable;

    }

	/**
	 * Keeps track of the icons to add
	 */
	private class Icon {
		/**
		 * The drawable resource
		 */
        @DrawableRes
		private final int drawableId;

		/**
		 * The drawable icon
		 */
		private Drawable drawableIcon;

		/**
		 * The icon color
		 */
        @ColorInt
		private final int color;
		/**
		 * True if the icon should be visible, false otherwise
		 */
		private final boolean visible;

		/**
		 * Default Constructor
		 *
		 * @param drawableId The drawable resource
		 * @param color      The color
		 * @param visibility True if the icon should be visible, false otherwise
		 */
		private Icon(@DrawableRes int drawableId, @ColorInt int color, boolean visibility){
			this.drawableId = drawableId;
			this.color = color;
			visible = visibility;
		}

		/**
		 * Alternate Constructor
		 *
		 * @param drawableIcon The drawable url image
		 * @param color      The color
		 * @param visibility True if the icon should be visible, false otherwise
		 */
		private Icon(Drawable drawableIcon, @ColorInt int color, boolean visibility){
			this.drawableId = 0;
			this.drawableIcon = drawableIcon;
			this.color = color;
			visible = visibility;
		}
	}

    /**
     * Custom {@link View.OnClickListener} implementation to have access to the {@link TextView}
     */
    public interface OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param item The clicked {@link TextViewFormItem} instance
         */
        void onClick(TextViewFormItem item);
    }
}
