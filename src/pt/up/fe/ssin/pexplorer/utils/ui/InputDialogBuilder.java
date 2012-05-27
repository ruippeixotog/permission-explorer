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

import pt.up.fe.ssin.pexplorer.R;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputDialogBuilder extends Builder {
	
	private View content;

	public InputDialogBuilder(Context context) {
		super(context);
		buildBase(context);
	}

	public EditText getEditText() {
		return (EditText) content.findViewById(R.id.input);
	}

	@Override
	public Builder setMessage(int messageId) {
		return setMessage(content.getContext().getString(messageId));
	}

	@Override
	public Builder setMessage(CharSequence message) {
		((TextView) content.findViewById(R.id.label)).setText(message);
		return this;
	}
	
	private void buildBase(Context context) {
		content = View.inflate(context, R.layout.input_dialog, null);
		setCancelable(true);
		setView(content);
	}
}
