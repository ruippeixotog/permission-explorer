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
import android.app.AlertDialog;
import android.content.Context;

public class AlertDialogHelper {

	private static AlertDialogHelper instance;

	public static AlertDialogHelper getInstance(Context context) {
		if (instance == null)
			instance = new AlertDialogHelper(context);
		else
			instance.context = context;
		return instance;
	}

	private Context context;

	private AlertDialogHelper(Context context) {
		this.context = context;
	}

	public AlertDialog createOkDialog(int labelResId, int textResId) {
		return createOkDialog(context.getString(labelResId),
				context.getString(textResId));
	}

	public AlertDialog createOkDialog(String label, String text) {
		return createOkDialog(label, text, 0);
	}

	public AlertDialog createOkDialog(String label, String text, int iconResId) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(text);
		builder.setCancelable(true);
		builder.setPositiveButton(R.string.ok, null);
		builder.setTitle(label);
		if (iconResId > 0)
			builder.setIcon(iconResId);
		return builder.create();
	}
}
