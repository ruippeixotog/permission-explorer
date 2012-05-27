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
import android.net.Uri;

public class InternetAccessAction extends PermissionAction {

	private static final String URL = "http://images.wikia.com/meme/images/d/d2/14-me-gusta-22vmrft.png";

	public InternetAccessAction() {
		super(R.string.internet_access_label, R.string.internet_access_desc,
				PermissionAction.WARN);
	}

	@Override
	protected void doAction(final Context context) {
		context.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri
				.parse(URL)));
	}

}
