package pt.up.fe.ssin.pexplorer.app;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PermissionExplorerActivity extends ListActivity {

	private List<PermissionInfo> permissions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		permissions = DataCatalog.getInstance(this).getAllPermissions();
		/*if (pi.packageName.equals("android"))
			list.add(pi.name);*/

		drawActivity();
	}

	private void drawActivity() {
		setListAdapter(new PermissionListAdapter(this, permissions));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String name = (String) getListAdapter().getItem(position);
		Intent intent = new Intent(this, PermissionInfoActivity.class);
		intent.putExtra("name", name);
		startActivity(intent);
	}
}