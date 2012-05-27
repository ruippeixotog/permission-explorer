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
import android.net.wifi.WifiManager;

public class ChangeWifiStateAction extends PermissionAction {

	public ChangeWifiStateAction() {
		super(R.string.wifi_state_label, R.string.write_calendar_label,
				PermissionAction.DO_NOTHING);
	}

	@Override
	protected void doAction(final Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		String message = new String();

		if (wifi.isWifiEnabled()) {
			wifi.setWifiEnabled(false);
			message = context.getString(R.string.wifi_state_shutdown);
		} else {
			wifi.setWifiEnabled(true);
			message = context.getString(R.string.wifi_state_activated);
		}

		new AlertDialog.Builder(context)
				.setTitle(R.string.wifi_state_label)
				.setMessage(message)
				.setCancelable(true)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						}).show();
	}
}
