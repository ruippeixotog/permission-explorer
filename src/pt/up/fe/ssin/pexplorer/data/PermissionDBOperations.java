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

package pt.up.fe.ssin.pexplorer.data;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class PermissionDBOperations {

	public static String getPermissionDescription(Context context, String name) {
		Cursor cursor = context.getContentResolver().query(
				PermissionExplorerContentProvider.CONTENT_URI, null,
				PermissionExplorerContentProvider.PERMISSION_NAME + "= ?",
				new String[] { name }, null);
		String description = new String();
		while (cursor.moveToNext()) {
			description = cursor
					.getString(cursor
							.getColumnIndex(PermissionExplorerContentProvider.PERMISSION_DESCRIPTION));
		}
		cursor.close();
		Log.w("Returned", description);
		return description;
	}

	public static Set<String> getCommonPermissions(Context context) {
		Cursor cursor = context.getContentResolver().query(
				PermissionExplorerContentProvider.CONTENT_URI, null,
				PermissionExplorerContentProvider.PERMISSION_COMMON + "= ?",
				new String[] { "1" }, null);
		Set<String> permissions = new HashSet<String>();
		while (cursor.moveToNext()) {
			permissions
					.add(cursor.getString(cursor
							.getColumnIndex(PermissionExplorerContentProvider.PERMISSION_NAME)));
		}
		cursor.close();
		Log.w("Returned", Integer.toString(permissions.size()));
		return permissions;
	}

}
