/*
 * Copyright 2015 Julien Guerinet
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
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Creates various form items and adds them to a given container
 * @author Julien Guerinet
 * @version 1.0.0
 * @since 1.0.0
 */
public class FormGenerator {
	/* SETTINGS */
	/**
	 * The {@link LayoutInflater}
	 */
	private LayoutInflater mInflater;
	/**
	 * The {@link LinearLayout} used as the form container
	 */
	private LinearLayout mContainer;
	/**
	 * The default icon color Id, 0 if none
	 */
	private int mDefaultIconColorId;
	/**
	 * The default background Id, 0 if none
	 */
	private int mDefaultBackgroundId;
	/**
	 * The default space size, 0 if none (defaults to 10dp)
	 */
	private int mDefaultSpaceSize;
	/**
	 * The default text color Id, 0 if none (defaults to black)
	 */
	private int mDefaultTextColorId;
	/**
	 * The default text color state list, 0 if none
	 */
	private int mDefaultTextColorStateList;
	/**
	 * The default padding size for the non-space/line items, 0 if none (defaults to 8dp)
	 */
	private int mDefaultPaddingSize;
	/**
	 * The default line size, 0 if none (defaults to 0.5 dp)
	 */
	private int mDefaultLineSize;
	/**
	 * The default line color Id, 0 if none (defaults to #EEEEEE)
	 */
	private int mDefaultLineColorId;
	/**
	 * True if we should show a line after a form item, false otherwise (defaults to true)
	 */
	private boolean mShowLine;

	/* VIEWS */
	/**
	 * The space {@link View}
	 */
	private View mSpace;
	/**
	 * The line {@link View}
	 */
	private View mLine;
	/**
	 * The text {@link View}
	 */
	private View mText;
	/**
	 * The input {@link View}
	 */
	private View mInput;
	/**
	 * The button {@link View}
	 */
	private Button mButton;
	/**
	 * The swich {@link View}
	 */
	private View mSwitch;

	/**
	 * Default Constructor
	 *
	 * @param builder The {@link Builder} instance to construct the {@link FormGenerator} from
	 */
	private FormGenerator(Builder builder){
		mInflater = builder.mInflater;
		mContainer = builder.mContainer;
		mDefaultIconColorId = builder.mDefaultIconColorId;
		mDefaultBackgroundId = builder.mDefaultBackgroundId;
		mDefaultSpaceSize = builder.mDefaultSpaceSize;
		mDefaultTextColorId = builder.mDefaultTextColorId;
		mDefaultTextColorStateList = builder.mDefaultTextColorStateList;
		mDefaultPaddingSize = builder.mDefaultPaddingSize;
		mDefaultLineSize = builder.mDefaultLineSize;
		mDefaultLineColorId = builder.mDefaultLineColorId;
		mShowLine = builder.mShowLine;
	}

	/**
	 * Adds a space
	 */
	public void space(){
		if(mSpace == null){
			mSpace = mInflater.inflate(R.layout.space, mContainer, false);
		}

		//Set the height if there's a custom one
		if(mDefaultSpaceSize != 0){
			mSpace.getLayoutParams().height = mDefaultSpaceSize;
		}

		mContainer.addView(mSpace);
	}

	/**
	 * Adds a line
	 */
	public void line(){
		if(mLine == null){
			mLine = mInflater.inflate(R.layout.line, mContainer, false);
		}

		//Process it
		line(mLine, false);

		mContainer.addView(mLine);
	}

	/**
	 * Adds an input field with a left icon
	 *
	 * @param text        The input text
	 * @param hint        The input hint
	 * @param iconId      The Id of the icon to use
	 * @param iconVisible True if the icon should be visible, false otherwise
	 * @return The {@link EditText} where the user will be inputting
	 */
	public EditText input(String text, String hint, int iconId, boolean iconVisible){
		if(mInput == null){
			mInput = mInflater.inflate(R.layout.input, mContainer, false);
			line(mInput);
			background(mInput);
		}

		EditText input = (EditText) mInput.findViewById(R.id.input);
		textView(input, text, hint, iconId, 0, iconVisible);

		mContainer.addView(mInput);

		return input;
	}

	/**
	 * Adds a text box with left/right icons
	 *
	 * @param text        The text to show
	 * @param hint        The hint to show if there is no text
	 * @param leftIconId  The Id for the left icon, 0 if none
	 * @param rightIconId The Id for the right icon, 0 if none (used for chevrons)
	 * @param iconVisible True if the icon should be visible, false otherwise
	 * @return The {@link TextView}
	 */
	public TextView text(String text, String hint, int leftIconId, int rightIconId,
			boolean iconVisible){
		if(mText == null){
			mText = mInflater.inflate(R.layout.text, mContainer, false);
			line(mText);
			background(mText);
		}

		//Text
		TextView title = (TextView)mText.findViewById(R.id.title);
		textView(title, text, hint, leftIconId, rightIconId, iconVisible);

		//Set the button to not clickable
		mText.setClickable(false);

		mContainer.addView(mText);

		return title;
	}

	/**
	 * Adds a button box with left/right icons
	 *
	 * @param text        The button text to show
	 * @param hint        The hint to show if there is no text
	 * @param leftIconId  The Id for the left icon, 0 if none
	 * @param rightIconId The Id for the right icon, 0 if none
	 * @param iconVisible True if the icon should be visible, false otherwise
	 * @param listener    The {@link OnClickListener} to call if the button is pressed
	 * @return The {@link TextView}
	 */
	public TextView button(String text, String hint, int leftIconId, int rightIconId,
			boolean iconVisible, OnClickListener listener){
		TextView title = text(text, hint, leftIconId, rightIconId, iconVisible);

		//Set the OnClickListener on the parent
		((View)title.getParent()).setOnClickListener(listener);

		return title;
	}

	/**
	 * Adds a standard {@link Button} (centered capitalized text)
	 *
	 * @param title    The button title
	 * @param listener The {@link OnClickListener} to call when the button is clicked
	 * @return The {@link Button}
	 */
	public Button button(String title, OnClickListener listener){
		if(mButton == null){
			mButton = (Button)mInflater.inflate(R.layout.button, mContainer, false);
			background(mButton);
		}

		textView(mButton, title, "", 0, 0, true);
		mButton.setOnClickListener(listener);

		mContainer.addView(mButton);

		return mButton;
	}

	/**
	 * Adds a {@link SwitchCompat} with the given title
	 *
	 * @param title       The title of the field
	 * @param leftIconId  The Id of the left icon, 0 if none
	 * @param iconVisible True if the left icon should be visible, false otherwise
	 * @return The {@link SwitchCompat}
	 */
	public SwitchCompat aSwitch(String title, int leftIconId, boolean iconVisible){
		if(mSwitch == null){
			mSwitch = mInflater.inflate(R.layout.switch_field, mContainer, false);
			background(mSwitch);
			line(mSwitch);
		}

		SwitchCompat switchField = (SwitchCompat)mSwitch.findViewById(R.id.field_switch);
		textView(switchField, title, "", leftIconId, 0, iconVisible);

		mContainer.addView(switchField);

		return switchField;
	}

	/* HELPERS */

	/**
	 * Sets up the given {@link TextView}
	 *
	 * @param textView    The {@link TextView}
	 * @param text        The text
	 * @param hint        The hint
	 * @param leftIconId  The left icon Id
	 * @param rightIconId The right icon Id
	 * @param iconVisible True if the left icon should be visible, false otherwise
	 */
	private void textView(TextView textView, String text, String hint, int leftIconId,
			int rightIconId, boolean iconVisible){
		//Text
		textView.setHint(hint);
		textView.setText(text);
		textColor(textView);

		//Icons
		textView.setCompoundDrawablesWithIntrinsicBounds(leftIconId, 0, rightIconId, 0);
		icon(textView);
		if(leftIconId != 0){
			textView.getCompoundDrawables()[0].setAlpha(iconVisible ? 255 : 0);
		}

		//Padding
		padding(textView);
	}

	/**
	 * Colors the {@link TextView} compound icons with the default icon color if there is one
	 * @param textView The {@link TextView}
	 */
	private void icon(TextView textView){
		if(mDefaultIconColorId != 0){
			//Get the color
			int color = textView.getResources().getColor(mDefaultIconColorId);

			for(int i = 0; i < 4; i ++){
				//Apply it to the compound drawable at the given position
				textView.getCompoundDrawables()[i].mutate().setColorFilter(
						new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
			}
		}
	}

	/**
	 * Colors the {@link TextView} text with the default color or color state list if there is one
	 *
	 * @param textView The {@link TextView}
	 */
	private void textColor(TextView textView){
		if(mDefaultTextColorId != 0){
			textView.setTextColor(textView.getResources().getColor(mDefaultTextColorId));
		}
		else if(mDefaultTextColorStateList != 0){
			textView.setTextColor(textView.getResources()
					.getColorStateList(mDefaultTextColorStateList));
		}
	}

	/**
	 * Sets the {@link View} padding with the default padding if there is one
	 *
	 * @param view The {@link View}
	 */
	private void padding (View view){
		if(mDefaultPaddingSize != 0){
			view.setPadding(mDefaultPaddingSize, mDefaultPaddingSize, mDefaultPaddingSize,
					mDefaultPaddingSize);
		}
	}

	/**
	 * Sets the background resource on the given view if there is one
	 *
	 * @param view The {@link View}
	 */
	private void background(View view){
		if(mDefaultBackgroundId != 0){
			view.setBackgroundResource(mDefaultBackgroundId);
		}
	}

	/**
	 * Sets the default line size and/or color on the given view
	 *
	 * @param view     The {@link View} containing the line
	 * @param hideLine True if we should hide the line if requested, false if shown regardless
	 */
	private void line(View view, boolean hideLine){
		//If there is no default size nor color, no need to continue
		if(mDefaultLineSize == 0 && mDefaultLineColorId == 0){
			return;
		}

		//Find the line
		View line = view.findViewById(R.id.line);
		if(line != null){
			//Hide the line if needed
			if(hideLine && !mShowLine){
				line.setVisibility(View.GONE);
				return;
			}
			//Set the size if there is one
			if(mDefaultLineSize != 0){
				line.getLayoutParams().height = mDefaultLineSize;
			}
			//Set the color if there is one
			if(mDefaultLineColorId != 0){
				line.setBackgroundResource(mDefaultLineColorId);
			}
		}
	}

	/**
	 * Sets the line parameters for the given {@link View}
	 *
	 * @param view The {@link View}
	 */
	private void line(View view){
		line(view, mShowLine);
	}

	/**
	 * The Form Generator builder
	 */
	public static class Builder {
		private LayoutInflater mInflater;
		private LinearLayout mContainer;
		private int mDefaultIconColorId = 0;
		private int mDefaultBackgroundId = 0;
		private int mDefaultSpaceSize = 0;
		private int mDefaultTextColorId = 0;
		private int mDefaultTextColorStateList = 0;
		private int mDefaultPaddingSize = 0;
		private int mDefaultLineSize = 0;
		private int mDefaultLineColorId = 0;
		private boolean mShowLine = true;

		/**
		 * Default Constructor
		 *
		 * @param inflater  The {@link LayoutInflater}
		 * @param container The container to put all of the generated form items in
		 */
		public Builder(LayoutInflater inflater, LinearLayout container){
			mInflater = inflater;
			mContainer = container;
		}

		/**
		 * Constructor that uses a {@link Context} to get the {@link LayoutInflater}
		 *
		 * @param context   The app {@link Context}
		 * @param container The container to put all of the generated form items in
		 */
		public Builder(Context context, LinearLayout container){
			this(LayoutInflater.from(context), container);
		}

		/**
		 * Sets the default icon color
		 *
		 * @param colorId The color resource Id
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultIconColor(int colorId){
			mDefaultIconColorId = colorId;
			return this;
		}

		/**
		 * Sets the default background
		 *
		 * @param backgroundId The background resource Id (can be a color or a drawable)
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultBackground(int backgroundId){
			mDefaultBackgroundId = backgroundId;
			return this;
		}

		/**
		 * Sets the default space size
		 *
		 * @param dimenId The dimension Id (in dp)
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultSpaceDimen(int dimenId){
			mDefaultSpaceSize = mInflater.getContext().getResources()
					.getDimensionPixelSize(dimenId);
			return this;
		}

		/**
		 * Sets the default space size
		 *
		 * @param pixels The space size in pixels
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultSpaceSize(int pixels){
			mDefaultSpaceSize = pixels;
			return this;
		}

		/**
		 * Sets the default text color
		 *
		 * @param colorId   The color Id
		 * @param stateList True if this is a state list, false if it's a solid color
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultTextColor(int colorId, boolean stateList){
			if(stateList){
				mDefaultTextColorStateList = colorId;
			}
			else{
				mDefaultTextColorId = colorId;
			}
			return this;
		}

		/**
		 * Sets the default padding size
		 *
		 * @param dimenId The dimension Id (in dp)
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultPaddingDimen(int dimenId){
			mDefaultPaddingSize = mInflater.getContext().getResources()
					.getDimensionPixelSize(dimenId);
			return this;
		}

		/**
		 * Sets the default padding size
		 *
		 * @param pixels The padding size in pixels
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultPaddingSize(int pixels){
			mDefaultPaddingSize = pixels;
			return this;
		}

		/**
		 * Sets the default line size
		 *
		 * @param dimenId The dimension Id (in dp)
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultLineDimen(int dimenId){
			mDefaultLineSize = mInflater.getContext().getResources().getDimensionPixelSize(dimenId);
			return this;
		}

		/**
		 * Sets the default line size
		 *
		 * @param pixels The line size in pixels
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultLineSize(int pixels){
			mDefaultLineSize = pixels;
			return this;
		}

		/**
		 * Sets the default line color Id
		 *
		 * @param colorId The color resource Id
		 * @return The {@link Builder} instance
		 */
		public Builder setDefaultLineColorId(int colorId){
			mDefaultLineColorId = colorId;
			return this;
		}

		/**
		 * Sets if the line should be shown after a form item by default or not
		 *
		 * @param showLine True if a line should be shown after a form item, false otherwise
		 * @return The {@link Builder} instance
		 */
		public Builder setShowLine(boolean showLine){
			mShowLine = showLine;
			return this;
		}
	}
}
