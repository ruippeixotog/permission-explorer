package pt.up.fe.ssin.pexplorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class PermissionExplorerActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// get package manager
		PackageManager manager = getPackageManager();

		// get list of permission groups
		List<PermissionGroupInfo> permGroups = manager
				.getAllPermissionGroups(PackageManager.GET_META_DATA);

		// get list of permissions
		List<PermissionInfo> perms;
		try {
			perms = manager.queryPermissionsByGroup(null,
					PackageManager.GET_META_DATA);
			for (PermissionGroupInfo pgi : permGroups) {
				perms.addAll(manager.queryPermissionsByGroup(pgi.name,
						PackageManager.GET_META_DATA));
			}
		} catch (NameNotFoundException e) {
			throw new AssertionError();
		}

		// get labels for list and set adapter
		List<String> list = new ArrayList<String>();
		for (PermissionInfo pi : perms) {

			// filter by system package
			if (pi.packageName.equals("android"))
				list.add(pi.name);
		}
		Collections.sort(list);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list));
	}
}