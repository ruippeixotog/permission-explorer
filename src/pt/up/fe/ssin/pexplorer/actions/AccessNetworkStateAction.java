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
import android.net.ConnectivityManager;

public class AccessNetworkStateAction extends PermissionAction {

	private String textInfo;

	public AccessNetworkStateAction() {
		super(R.string.network_state_label, R.string.network_state_label,
				PermissionAction.DO_NOTHING);
	}

	@Override
	protected void doAction(final Context context) {

		textInfo = context.getString(R.string.network_state_not_connected);
		if (isConnected(context))
			textInfo += context.getString(R.string.network_state_connected);
		;

		new AlertDialog.Builder(context)
				.setTitle(R.string.network_state_label)
				.setMessage(textInfo)
				.setCancelable(true)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						}).show();
	}

	private boolean isConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager != null) {
			if (connectivityManager.getNetworkInfo(
					ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting()) {
				textInfo = context.getString(R.string.network_state_wifi_on);
				return true;
			} else if (connectivityManager.getNetworkInfo(
					ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting()) {
				textInfo = context.getString(R.string.network_state_mobile_on);
				return true;
			}
		}
		return false;
	}
}
