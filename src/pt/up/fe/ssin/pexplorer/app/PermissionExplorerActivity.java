package pt.up.fe.ssin.pexplorer.app;

import java.util.List;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.utils.FilterTextWatcher;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class PermissionExplorerActivity extends ListActivity {

	private List<PermissionInfo> permissions;

	private PermissionListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		permissions = DataCatalog.getInstance(this).getAllPermissions();
		/*if (pi.packageName.equals("android"))
			list.add(pi.name);*/

		drawActivity();
	}

	private void drawActivity() {
		setContentView(R.layout.perm_list);
		adapter = new PermissionListAdapter(this, permissions);
		setListAdapter(adapter);

		EditText filterText = (EditText) findViewById(R.id.search_box);
		filterText.addTextChangedListener(new FilterTextWatcher(adapter));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		PermissionInfo perm = (PermissionInfo) getListAdapter().getItem(
				position);
		Intent intent = new Intent(this, PermissionInfoActivity.class);
		intent.putExtra(IntentKeys.PERM_NAME, perm.name);
		startActivity(intent);
	}
}