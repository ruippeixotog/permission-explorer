package pt.up.fe.ssin.pexplorer.app;

import java.util.List;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.data.PermissionCatalog;
import pt.up.fe.ssin.pexplorer.utils.ApplicationDetailsHelper;
import pt.up.fe.ssin.pexplorer.utils.PermissionUtils;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class PermissionInfoActivity extends ListActivity {

	private static final String TAB_SPEC_INFO = "info";
	private static final String TAB_SPEC_ACTIONS = "actions";
	private static final String TAB_SPEC_APPS = "apps";

	private PermissionInfo perm;
	private String permDescription;
	private String extendedInfo;
	private List<ApplicationInfo> permittedApps;
	private List<ApplicationInfo> permittedDownloadedApps;

	private TabHost tabHost;
	private ApplicationListAdapter adapter;
	private boolean showAllApps = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PermissionCatalog catalog = PermissionCatalog.getInstance(this);
		SharedPreferences prefs = getSharedPreferences(Keys.PREFS_FILE,
				MODE_PRIVATE);

		String name = getIntent().getStringExtra(Keys.INTENT_EXT_NAME);
		try {
			perm = catalog.getInfo(name);
		} catch (NameNotFoundException e) {
			Toast.makeText(this, "permission does not exist",
					Toast.LENGTH_SHORT).show();
			finish();
			return;
		}

		permDescription = (String) perm.loadDescription(catalog
				.getPackageManager());

		permittedApps = catalog.getApplications(perm);
		permittedDownloadedApps = catalog.filterApplications(permittedApps,
				true);

		showAllApps = prefs.getBoolean(Keys.PREFS_SHOW_ALL_APPS,
				Keys.DEFAULT_SHOW_ALL_APPS);

		/*Bundle b = perm.metaData;
		try {
			PackageInfo info = catalog.getPackageManager().getPackageInfo(perm.packageName, 0);
			info.
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("bundle meta", b == null ? "NULL" : b.toString());*/

		drawActivity();
	}

	private void drawActivity() {
		setContentView(R.layout.info_main);
		setTitle(PermissionUtils.getShortName(perm));
		drawTabs();

		drawDetailedInfo();
		drawActionsList();
		drawAppsList();
	}

	private void drawTabs() {
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();

		tabHost.addTab(tabHost.newTabSpec(TAB_SPEC_INFO)
				.setContent(R.id.detailed_info)
				.setIndicator(getString(R.string.tab_detailed_info)));

		tabHost.addTab(tabHost.newTabSpec(TAB_SPEC_ACTIONS)
				.setContent(R.id.actions_list)
				.setIndicator(getString(R.string.tab_actions_list)));

		tabHost.addTab(tabHost
				.newTabSpec(TAB_SPEC_APPS)
				.setContent(android.R.id.list)
				.setIndicator(getString(R.string.tab_apps_list),
						getResources().getDrawable(R.drawable.ic_tab_apps)));
	}

	private void drawDetailedInfo() {
		TextView tv = (TextView) findViewById(R.id.name);
		tv.setText(perm.name);

		tv = (TextView) findViewById(R.id.group);
		tv.setText(perm.group);

		tv = (TextView) findViewById(R.id.level);
		tv.setText(getResources().getStringArray(R.array.level_labels)[perm.protectionLevel]);

		tv = (TextView) findViewById(R.id.description);
		tv.setText(permDescription);

		tv = (TextView) findViewById(R.id.ext_description);
		tv.setText(extendedInfo);
	}

	private void drawActionsList() {
		ViewGroup actionsView = (ViewGroup) findViewById(R.id.actions_list);
		for (PermissionAction action : ActionRegistry.getInstance()
				.getPermissionActions(perm.name)) {
			View.inflate(this, R.layout.action_button, actionsView);
			Button b = (Button) actionsView.getChildAt(actionsView
					.getChildCount() - 1);
			b.setText(action.getLabel(this));
			b.setOnClickListener(new OnActionButtonClickListener(action));
		}
	}

	private void drawAppsList() {
		adapter = new ApplicationListAdapter(this, showAllApps ? permittedApps
				: permittedDownloadedApps);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ApplicationInfo app = adapter.getItem(position);
		startActivity(ApplicationDetailsHelper
				.getApplicationDetailsIntent(app.packageName));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.info_apps, menu);
		return true;
	}

	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		if (!tabHost.getCurrentTabTag().equals(TAB_SPEC_APPS))
			return false;

		menu.getItem(0).setTitle(
				showAllApps ? R.string.show_only_downloaded_apps
						: R.string.show_all_apps);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.toggle_show: {
			showAllApps = !showAllApps;

			SharedPreferences.Editor prefs = getSharedPreferences(
					Keys.PREFS_FILE, MODE_PRIVATE).edit();
			prefs.putBoolean(Keys.PREFS_SHOW_ALL_APPS, showAllApps);
			prefs.commit();

			adapter = new ApplicationListAdapter(this,
					showAllApps ? permittedApps : permittedDownloadedApps);
			setListAdapter(adapter);
			return true;
		}

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private static class OnActionButtonClickListener implements OnClickListener {

		private PermissionAction action;

		public OnActionButtonClickListener(PermissionAction action) {
			this.action = action;
		}

		@Override
		public void onClick(View v) {
			action.execute(v.getContext());
		}
	}
}
