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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.telephony.TelephonyManager;

public class ReadPhoneStateAction extends PermissionAction {

	public ReadPhoneStateAction() {
		super(R.string.read_phone_state_label, R.string.read_phone_state_desc,
				PermissionAction.DO_NOTHING);
	}

	@Override
	protected void doAction(final Context context) {

		TelephonyManager mTelephonyMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String imei = mTelephonyMgr.getDeviceId();
		String phoneNumber = mTelephonyMgr.getLine1Number();
		String softwareVer = mTelephonyMgr.getDeviceSoftwareVersion();
		String simSerial = mTelephonyMgr.getSimSerialNumber();
		String subscriberId = mTelephonyMgr.getSubscriberId();

		new AlertDialog.Builder(context)
				.setTitle(R.string.read_phone_state_title)
				.setMessage(
						String.format(context
								.getString(R.string.read_phone_state_entry),
								imei, phoneNumber, softwareVer, simSerial,
								subscriberId))
				.setCancelable(true)
				.setPositiveButton(R.string.continue_,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						}).show();
	}

}
