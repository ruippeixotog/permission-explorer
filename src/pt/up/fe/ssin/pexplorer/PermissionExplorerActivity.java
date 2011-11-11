package pt.up.fe.ssin.pexplorer;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PermissionExplorerActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// get labels for list and set adapter
		List<String> list = new ArrayList<String>();
		for (PermissionInfo pi : DataCatalog.getInstance(this)
				.getAllPermissions()) {

			// filter by system package
			if (pi.packageName.equals("android"))
				list.add(pi.name);
		}

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String name = (String) getListAdapter().getItem(position);
		Intent intent = new Intent(this, PermissionInfoActivity.class);
		intent.putExtra("name", name);
		startActivity(intent);
	}
}