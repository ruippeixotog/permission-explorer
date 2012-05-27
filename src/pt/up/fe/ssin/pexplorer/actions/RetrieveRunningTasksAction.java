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

import java.util.Iterator;
import java.util.List;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class RetrieveRunningTasksAction extends PermissionAction {

	public RetrieveRunningTasksAction() {
		super(R.string.retrive_running_tasks_label,
				R.string.retrive_running_tasks_label,
				PermissionAction.DO_NOTHING);
	}

	@Override
	protected void doAction(final Context context) {
		ActivityManager mgr = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		List<RunningTaskInfo> tasks = mgr.getRunningTasks(100);

		String text = context.getString(R.string.retrieve_running_tasks_intro);

		for (Iterator<RunningTaskInfo> i = tasks.iterator(); i.hasNext();) {
			RunningTaskInfo p = (RunningTaskInfo) i.next();
			text += p.baseActivity.flattenToShortString() + "\n";
		}

		new AlertDialog.Builder(context)
				.setTitle(R.string.retrive_running_tasks_label)
				.setMessage(text)
				.setCancelable(true)
				.setPositiveButton(R.string.continue_,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						}).show();
	}
}
