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

package pt.up.fe.ssin.pexplorer.app;

import java.util.List;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.data.PermissionCatalog;
import pt.up.fe.ssin.pexplorer.utils.ui.SimpleObjectAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplicationListAdapter extends
		SimpleObjectAdapter<ApplicationInfo> {

	private PermissionCatalog catalog;

	public ApplicationListAdapter(Context context, List<ApplicationInfo> objects) {
		super(context, R.layout.app_row, objects);
		catalog = PermissionCatalog.getInstance(context);
	}

	@Override
	public View getView(View inflatedView, ApplicationInfo app) {

		TextView tv = (TextView) inflatedView.findViewById(R.id.app_name);
		tv.setText(app.loadLabel(catalog.getPackageManager()));

		ImageView iv = (ImageView) inflatedView.findViewById(R.id.app_icon);
		iv.setImageDrawable(app.loadIcon(catalog.getPackageManager()));

		tv = (TextView) inflatedView.findViewById(R.id.app_package);
		tv.setText(app.packageName);

		return inflatedView;
	}
}