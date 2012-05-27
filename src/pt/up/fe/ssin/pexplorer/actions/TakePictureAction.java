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
import android.content.Context;
import android.content.Intent;

public class TakePictureAction extends PermissionAction {

	public TakePictureAction() {
		super(R.string.take_picture_label, R.string.take_picture_label,
				PermissionAction.DO_NOTHING);
	}

	@Override
	protected void doAction(final Context context) {
		final Intent intent = new Intent(context, TakePictureActivity.class);
		context.startActivity(intent);
	}
}
