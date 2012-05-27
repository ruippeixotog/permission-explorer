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

package pt.up.fe.ssin.pexplorer.utils;

import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;

public class PermissionUtils {

	public static String getShortName(PermissionInfo perm) {
		int lastDot = perm.name.lastIndexOf('.');
		return perm.name.substring(lastDot + 1);
	}

	public static String getShortName(PermissionGroupInfo group) {
		int lastDot = group.name.lastIndexOf('.');
		return group.name.substring(lastDot + 1);
	}

	public static Pair<String, String> decomposeName(PermissionInfo perm) {
		int lastDot = perm.name.lastIndexOf('.');
		return new Pair<String, String>(lastDot < 0 ? "" : perm.name.substring(
				0, lastDot), perm.name.substring(lastDot + 1));
	}

	public static Pair<String, String> decomposeName(PermissionGroupInfo group) {
		int lastDot = group.name.lastIndexOf('.');
		return new Pair<String, String>(lastDot < 0 ? ""
				: group.name.substring(0, lastDot),
				group.name.substring(lastDot + 1));
	}
}
