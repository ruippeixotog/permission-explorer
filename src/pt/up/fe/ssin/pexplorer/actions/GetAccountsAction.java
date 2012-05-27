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
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class GetAccountsAction extends PermissionAction {

	public GetAccountsAction() {
		super(R.string.get_accounts_label, R.string.get_accounts_label,
				PermissionAction.DO_NOTHING);
	}

	@Override
	protected void doAction(final Context context) {
		Account[] accounts = AccountManager.get(context).getAccounts();
		String currentAccounts = context.getString(R.string.get_accounts_intro);
		for (Account account : accounts)
			currentAccounts += String.format(
					context.getString(R.string.get_accounts_entry),
					account.name, account.type);

		new AlertDialog.Builder(context)
				.setTitle(R.string.get_accounts_tile)
				.setMessage(currentAccounts)
				.setCancelable(true)
				.setPositiveButton(R.string.continue_,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						}).show();
	}
}
