package pt.up.fe.ssin.pexplorer.app;

import java.util.List;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.data.PermissionCatalog;
import pt.up.fe.ssin.pexplorer.utils.ApplicationDetailsHelper;
import android.app.ListActivity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
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

	private ApplicationListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String name = getIntent().getStringExtra(Keys.INTENT_EXT_NAME);
		try {
			PermissionCatalog catalog = PermissionCatalog.getInstance(this);
			perm = catalog.getInfo(name);
			permDescription = catalog.getDescription(perm);
			permittedApps = catalog.getApplications(perm);

		} catch (NameNotFoundException e) {
			Toast.makeText(this, "permission does not exist",
					Toast.LENGTH_SHORT).show();
			finish();
			return;
		}

		drawActivity();
	}

	private void drawActivity() {
		setContentView(R.layout.info_main);
		setTitle(perm.name);
		drawTabs();

		drawDetailedInfo();
		drawActionsList();
		drawAppsList();
	}

	private void drawTabs() {
		TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
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
		if (perm.descriptionRes != 0)
			((TextView) findViewById(R.id.test)).setText(permDescription);
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
			// actionsView.addView(b);
		}
	}

	private void drawAppsList() {
		adapter = new ApplicationListAdapter(this, permittedApps);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ApplicationInfo app = adapter.getItem(position);
		startActivity(ApplicationDetailsHelper
				.getApplicationDetailsIntent(app.packageName));
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
