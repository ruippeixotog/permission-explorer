package pt.up.fe.ssin.pexplorer.app;

import pt.up.fe.ssin.pexplorer.R;
import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class PermissionInfoActivity extends Activity {

	private static final String TAB_SPEC_INFO = "info";
	private static final String TAB_SPEC_ACTIONS = "actions";
	private static final String TAB_SPEC_APPS = "apps";

	PermissionInfo perm;
	String permDescription;
	String extendedInfo = "<More detailed description>";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String name = getIntent().getStringExtra(Keys.INTENT_EXT_NAME);
		try {
			PermissionCatalog catalog = PermissionCatalog.getInstance(this);
			perm = catalog.getInfo(name);
			permDescription = catalog.getDescription(perm);
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

		TabSpec spec1 = tabHost.newTabSpec(TAB_SPEC_INFO);
		spec1.setContent(R.id.detailed_info);
		spec1.setIndicator(getString(R.string.tab_detailed_info));
		tabHost.addTab(spec1);

		TabSpec spec2 = tabHost.newTabSpec(TAB_SPEC_ACTIONS);
		spec2.setContent(R.id.actions_list);
		spec2.setIndicator(getString(R.string.tab_actions_list));
		tabHost.addTab(spec2);

		TabSpec spec3 = tabHost.newTabSpec(TAB_SPEC_APPS);
		spec3.setContent(R.id.apps_list);
		spec3.setIndicator(getString(R.string.tab_apps_list));
		tabHost.addTab(spec3);
	}

	private void drawDetailedInfo() {
		if (perm.descriptionRes != 0)
			((TextView) findViewById(R.id.test)).setText(permDescription);
	}

	private void drawActionsList() {
		ViewGroup actionsView = (ViewGroup) findViewById(R.id.actions_list);
		for (PermissionAction action : ActionRegistry.getInstance()
				.getPermissionActions(perm.name)) {
			Button b = (Button) View
					.inflate(this, R.layout.action_button, null);
			b.setText(action.getLabel(this));
			b.setOnClickListener(new OnActionButtonClickListener(action));
			actionsView.addView(b);
		}
	}

	private void drawAppsList() {
	}

	private static class OnActionButtonClickListener implements OnClickListener {

		private PermissionAction action;

		public OnActionButtonClickListener(PermissionAction action) {
			this.action = action;
		}

		@Override
		public void onClick(View v) {
			action.doAction(v.getContext());
		}
	}
}
