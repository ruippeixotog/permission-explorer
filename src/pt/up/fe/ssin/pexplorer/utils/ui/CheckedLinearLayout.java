/*
 * Copyright (C) 2012 Rui Gonçalves and Daniel Cibrão
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pt.up.fe.ssin.pexplorer.utils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckedLinearLayout extends LinearLayout implements Checkable {

	private Checkable checkable;

	public CheckedLinearLayout(Context context) {
		super(context);
	}
	
	public CheckedLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		for (int i = 0; i < getChildCount(); i++) {
			View v = getChildAt(i);
			if (v instanceof Checkable) {
				checkable = (Checkable) v;
				return;
			}
		}
		throw new ClassCastException(
				"No Checkable child view found in CheckedLinearLayout.");
	}

	@Override
	public boolean isChecked() {
		return checkable.isChecked();
	}

	@Override
	public void setChecked(boolean checked) {
		checkable.setChecked(checked);
	}

	@Override
	public void toggle() {
		checkable.toggle();
	}
}
