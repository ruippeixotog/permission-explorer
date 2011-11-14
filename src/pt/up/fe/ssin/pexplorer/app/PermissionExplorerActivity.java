package pt.up.fe.ssin.pexplorer.app;

import java.util.List;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.utils.FilterTextWatcher;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PermissionInfo;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class PermissionExplorerActivity extends ListActivity {

	public static final int FILTER_REQ_CODE = 0;

	private List<PermissionInfo> permissions;

	private PermissionListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PermissionCatalog catalog = PermissionCatalog.getInstance(this);
		permissions = catalog.filter(catalog.getAll(), (String) null, null,
				PermissionCatalog.RELEVANCE_SYSTEM);

		drawActivity();
	}

	private void drawActivity() {
		setContentView(R.layout.perm_list);

		adapter = new PermissionListAdapter(this, permissions);
		adapter.registerDataSetObserver(new ListCounterDataSetObserver());
		setListAdapter(adapter);

		EditText filterText = (EditText) findViewById(R.id.search_box);
		filterText.addTextChangedListener(new FilterTextWatcher(adapter));

		updateListCounter();
	}

	private void updateListCounter() {
		TextView tv = (TextView) findViewById(R.id.list_count);
		tv.setText(String.format(getString(R.string.msg_perm_list_count),
				adapter.getCount()));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		PermissionInfo perm = (PermissionInfo) getListAdapter().getItem(
				position);
		Intent intent = new Intent(this, PermissionInfoActivity.class);
		intent.putExtra(Keys.INTENT_EXT_NAME, perm.name);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.perm_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.filter: {
			Intent intent = new Intent(this, FilterConfigActivity.class);
			startActivityForResult(intent, FILTER_REQ_CODE);
			return true;
		}

		case R.id.reload: {
			PermissionCatalog.getInstance(this).reload();
			Intent intent = new Intent(this, PermissionExplorerActivity.class);
			startActivity(intent);
			finish();
			return true;
		}

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class ListCounterDataSetObserver extends DataSetObserver {

		@Override
		public void onChanged() {
			super.onChanged();
			updateListCounter();
		}

		@Override
		public void onInvalidated() {
			super.onInvalidated();
			updateListCounter();
		}
	}
}