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

import java.io.IOException;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import android.content.Context;
import android.widget.Toast;

public class RebootAction extends PermissionAction {

	public RebootAction() {
		super(R.string.reboot_label, R.string.reboot_desc,
				PermissionAction.WARN);
	}

	@Override
	protected void doAction(Context context) {
		try {
			Runtime.getRuntime().exec(new String[] { "su", "-c", "reboot" });
		} catch (IOException e) {
			Toast.makeText(context, R.string.no_root, Toast.LENGTH_SHORT);
			return;
		}
	}
}
