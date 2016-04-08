/*
 * Copyright 2015-2016 Julien Guerinet
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

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Creates various form items and adds them to a given container
 * @author Julien Guerinet
 * @since 1.0.0
 */
public class FormGenerator {
	/**
	 * The singleton instance of the default {@link Builder} to use
	 */
	private static Builder singleton = null;

	/* SETTINGS */
	/**
	 * The {@link LayoutInflater}
	 */
	private LayoutInflater inflater;
	/**
	 * The {@link LinearLayout} used as the form container
	 */
	LinearLayout container;
    /**
     * The {@link Builder} instance to use to construct this FormGenerator
     */
    protected Builder builder;

	/**
	 * Binds the default {@link FormGenerator} to the given layout and returns it.
     *  This will use either the default generator set by the user,
     *  or a generator with the default values
	 *
	 * @param inflater  The {@link LayoutInflater}
	 * @param container The container that the items should be in
	 * @return The default {@link FormGenerator}
	 */
	public static FormGenerator bind(LayoutInflater inflater, LinearLayout container) {
		// No singleton set, bind from the default settings
		if (singleton == null) {
			singleton = new Builder();
		}

        return new FormGenerator(singleton, inflater, container);
	}

	/**
	 * Binds the default {@link FormGenerator} to the given layout and returns it.
     *  This will use either the default generator set by the user,
     *  or a generator with the default values
	 *
	 * @param context   The {@link LayoutInflater}
	 * @param container The container that the items should be in
	 * @return The default {@link FormGenerator}
	 */
	public static FormGenerator bind(Context context, LinearLayout container) {
		return bind(LayoutInflater.from(context), container);
	}

    /**
     * @return The default {@link Builder} instance
     */
    public static Builder get() {
        if (singleton == null) {
            singleton = new Builder();
        }
        return singleton;
    }

	/**
	 * Sets the default instance of the {@link FormGenerator} to use when
	 *  {@link #get()}, {@link #bind(Context, LinearLayout)} or
     *  {@link #bind(LayoutInflater, LinearLayout)} is called
	 *
	 * @param builder The {@link Builder} instance
	 */
	public static void set(Builder builder) {
		singleton = builder;
	}

	/**
	 * Default Constructor
	 *
	 * @param builder   The {@link Builder} instance to construct the {@link FormGenerator} from
	 * @param inflater  The {@link LayoutInflater} instance
	 * @param container The container to add the form items to
	 */
	private FormGenerator(Builder builder, LayoutInflater inflater, LinearLayout container) {
		this.inflater = inflater;
		this.container = container;
        this.builder = builder;
	}

	/**
	 * Adds a space
	 */
	public SpaceItem space() {
		return new SpaceItem(this, inflater.inflate(R.layout.fg_space, container, false));
	}

	/**
	 * Adds a line
	 *
	 * @return The {@link LineItem}
	 */
	public LineItem line() {
		return new LineItem(this, inflater.inflate(R.layout.fg_line, container, false));
	}

	/**
	 * Adds an input item
	 *
	 * @param text The text
	 * @return The {@link EditTextFormItem}
	 */
	public EditTextFormItem input(String text) {
		return new EditTextFormItem(this, inflater.inflate(R.layout.fg_input, container, false),
				text);
	}

    /**
     * Adds an input item
     *
     * @param text The text Id
     * @return The {@link EditTextFormItem}
     */
    public EditTextFormItem input(@StringRes int text) {
        return new EditTextFormItem(this, inflater.inflate(R.layout.fg_input, container, false),
                text);
    }

	/**
	 * Adds a text item
	 *
	 * @param text The text
	 * @return The {@link TextViewFormItem}
	 */
	public TextViewFormItem text(String text) {
		View view = inflater.inflate(R.layout.fg_text, container, false);
		return new TextViewFormItem(this, view, (TextView)view.findViewById(R.id.fg_text), text,
                true);
	}

    /**
     * Adds a text item
     *
     * @param text The text Id
     * @return The {@link TextViewFormItem}
     */
    public TextViewFormItem text(@StringRes int text) {
        View view = inflater.inflate(R.layout.fg_text, container, false);
        return new TextViewFormItem(this, view, (TextView)view.findViewById(R.id.fg_text), text,
                true);
    }

	/**
	 * Adds a standard button
	 *
	 * @param text     The text
	 * @param listener The {@link View.OnClickListener}
	 * @return The {@link TextViewFormItem}
	 */
	public ButtonFormItem button(String text, View.OnClickListener listener) {
		return new ButtonFormItem(this, inflater.inflate(R.layout.fg_button, container, false),
				text, listener);
	}

    /**
     * Adds a standard button
     *
     * @param text     The text Id
     * @param listener The {@link View.OnClickListener}
     * @return The {@link TextViewFormItem}
     */
    public ButtonFormItem button(@StringRes int text, View.OnClickListener listener) {
        return new ButtonFormItem(this, inflater.inflate(R.layout.fg_button, container, false),
                text, listener);
    }

    /**
     * Adds a borderless button
     *
     * @param text     The text
     * @param listener The {@link View.OnClickListener}
     * @return The {@link ButtonFormItem}
     */
    public ButtonFormItem borderlessButton(String text, View.OnClickListener listener) {
        return new ButtonFormItem(this, inflater.inflate(
                R.layout.fg_button_borderless, container, false), text, listener);
    }

    /**
     * Adds a borderless button
     *
     * @param text     The text Id
     * @param listener The {@link View.OnClickListener}
     * @return The {@link ButtonFormItem}
     */
    public ButtonFormItem borderlessButton(@StringRes int text, View.OnClickListener listener) {
        return new ButtonFormItem(this, inflater.inflate(
                R.layout.fg_button_borderless, container, false), text, listener);
    }

	/**
	 * Adds a switch item
	 *
	 * @param text The text
	 * @return The {@link SwitchFormItem}
	 */
	public SwitchFormItem aSwitch(String text) {
		return new SwitchFormItem(this, inflater.inflate(R.layout.fg_switch, container, false),
				text);
	}

    /**
     * Adds a switch item
     *
     * @param text The text Id
     * @return The {@link SwitchFormItem}
     */
    public SwitchFormItem aSwitch(@StringRes int text) {
        return new SwitchFormItem(this, inflater.inflate(R.layout.fg_switch, container, false),
                text);
    }

	/**
	 * The Form Generator builder
	 */
	public static class Builder {
        /**
         * The default icon color Id, -1 if none
         */
        @ColorInt
        int defaultIconColor = -1;
        /**
         * The default background Id, null if none
         */
        @ColorRes
        @DrawableRes
        Integer defaultBackgroundId = null;
        /**
         * The default background Id for the input item, null if none
         */
        @ColorRes
        @DrawableRes
        Integer defaultInputBackgroundId = null;
        /**
         * The default color for the space, transparent if none
         */
        @ColorRes
        @DrawableRes
        int defaultSpaceColorId = android.R.color.transparent;
        /**
         * The default space size Id, 0 if none
         */
        int defaultSpaceSize = 0;
        /**
         * The default text size, app default if none
         */
        int defaultTextSize = -1;
        /**
         * The default text color Id, black if none
         */
        @ColorInt
        int defaultTextColor = Color.BLACK;
        /**
         * The default typeface to use, null if none
         */
        Typeface defaultTextTypeface = null;
        /**
         * The default padding size for the non-space/line items, 0 if none
         */
        int defaultPaddingSize = 0;
        /**
         * Default padding size between a view and its compound drawable, defaults to 0
         */
        int defaultDrawablePaddingSize = 0;
        /**
         * The default line size, 0 if none
         */
        int defaultLineSize = 0;
        /**
         * The default line color Id, #EEEEEE if none
         */
        @ColorRes
        @DrawableRes
        int defaultLineColorId = R.color.line;
        /**
         * True if we should show a line after a form item, false otherwise (defaults to true)
         */
        boolean showLine = true;

		/**
		 * Default Constructor
		 */
		public Builder() {}

        /**
         * @return A new {@link Builder} instance, generated from the given one
         */
        public Builder newInstance() {
            Builder builder = new Builder();

            //Set the values from the given instance
            builder.defaultIconColor = defaultIconColor;
            builder.defaultBackgroundId = defaultBackgroundId;
            builder.defaultInputBackgroundId = defaultInputBackgroundId;
            builder.defaultSpaceColorId = defaultSpaceColorId;
            builder.defaultSpaceSize = defaultSpaceSize;
            builder.defaultTextSize = defaultTextSize;
            builder.defaultTextColor = defaultTextColor;
            builder.defaultTextTypeface = defaultTextTypeface;
            builder.defaultPaddingSize = defaultPaddingSize;
            builder.defaultDrawablePaddingSize = defaultDrawablePaddingSize;
            builder.defaultLineSize = defaultLineSize;
            builder.defaultLineColorId = defaultLineColorId;
            builder.showLine = showLine;
            return builder;
        }

		/**
		 * Binds a {@link Builder} to a given view
		 *
		 * @param inflater  The {@link LayoutInflater} instance
		 * @param container The container for the views
		 * @return The created {@link FormGenerator} instance
		 */
		public FormGenerator bind(LayoutInflater inflater, LinearLayout container) {
			return new FormGenerator(this, inflater, container);
		}

		/**
		 * Builds a {@link FormGenerator} based off of this {@link Builder}
		 *
		 * @param context   The app {@link Context}
		 * @param container The container for the views
		 * @return The created {@link FormGenerator} instance
		 */
		public FormGenerator bind(Context context, LinearLayout container) {
			return bind(LayoutInflater.from(context), container);
		}

		/**
		 * Sets the default icon color
		 *
		 * @param color The color
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultIconColor(@ColorInt int color) {
			defaultIconColor = color;
			return this;
		}

		/**
		 * Sets the default background
		 *
		 * @param backgroundId The background resource Id (can be a color or a drawable)
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultBackground(@ColorRes @DrawableRes int backgroundId) {
			defaultBackgroundId = backgroundId;
			return this;
		}

		/**
		 * Sets the default background for the input fields
		 *
		 * @param backgroundId The background resource Id (can be a color or drawable)
		 * @return The {@link Builder} instance
		 */
		public Builder setInputDefaultBackground(@ColorRes @DrawableRes int backgroundId) {
			defaultInputBackgroundId = backgroundId;
			return this;
		}

		/**
		 * Sets the default space color
		 *
		 * @param colorId The space color Id
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultSpaceColorId(@ColorRes int colorId) {
			defaultSpaceColorId = colorId;
			return this;
		}

		/**
		 * Sets the default space size
		 *
		 * @param pixels The size in <strong>pixels</strong>
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultSpaceSize(int pixels) {
			defaultSpaceSize = pixels;
			return this;
		}

		/**
		 * Sets the default text size
		 *
		 * @param pixels Default text size, in <strong>pixels</strong>
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultTextSize(int pixels) {
			defaultTextSize = pixels;
			return this;
		}

		/**
		 * Sets the default text color
		 *
		 * @param color The color
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultTextColor(@ColorInt int color) {
            defaultTextColor = color;
			return this;
		}

		/**
		 * Sets the default typeface for the text items
		 *
		 * @param typeface The {@link Typeface} to use
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultTypeface(Typeface typeface) {
			defaultTextTypeface = typeface;
			return this;
		}

		/**
		 * Sets the default padding size
		 *
		 * @param pixels The padding size in <strong>pixels</strong>
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultPaddingSize(int pixels) {
			defaultPaddingSize = pixels;
			return this;
		}

        /**
         * Sets the default padding size between a view and its compound drawable
         *
         * @param pixels The padding size, in <strong>pixels</strong>
         * @return The {@link Builder} instance
         */
        public Builder setDefaultDrawablePaddingSize(int pixels) {
            defaultDrawablePaddingSize = pixels;
            return this;
        }

		/**
		 * Sets the default line size
		 *
		 * @param pixels The line size, in <strong>pixels</strong>
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultLineSize(int pixels) {
			defaultLineSize = pixels;
			return this;
		}

		/**
		 * Sets the default line color Id
		 *
		 * @param colorId The color resource Id
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultLineColorId(@ColorRes @DrawableRes int colorId) {
			defaultLineColorId = colorId;
			return this;
		}

		/**
		 * Sets if the line should be shown after a form item by default or not
		 *
		 * @param showLine True if a line should be shown after a form item, false otherwise
		 * @return The {@link Builder} instance
		 */
		public Builder setShowLine(boolean showLine) {
			this.showLine = showLine;
			return this;
		}
	}
}
