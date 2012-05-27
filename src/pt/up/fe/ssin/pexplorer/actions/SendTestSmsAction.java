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

package pt.up.fe.ssin.pexplorer.actions;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import pt.up.fe.ssin.pexplorer.utils.ui.InputDialogBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.telephony.SmsManager;
import android.text.InputType;
import android.widget.EditText;

public class SendTestSmsAction extends PermissionAction {

	public SendTestSmsAction() {
		super(R.string.send_test_sms_label, R.string.send_test_sms_desc,
				PermissionAction.WARN);
	}

	@Override
	protected void doAction(final Context context) {
		InputDialogBuilder builder = new InputDialogBuilder(context);
		final EditText inputText = builder.getEditText();
		inputText.setInputType(InputType.TYPE_CLASS_PHONE);

		builder.setMessage(R.string.send_test_sms_dialog_label);
		builder.setCancelable(true);
		builder.setPositiveButton(R.string.send,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						SmsManager
								.getDefault()
								.sendTextMessage(
										inputText.getText().toString(),
										null,
										context.getString(R.string.send_test_sms_sms_text),
										null, null);
					}
				}).setNegativeButton(R.string.cancel, null);
		builder.setTitle(R.string.send_test_sms_title);
		builder.create().show();
	}
}
