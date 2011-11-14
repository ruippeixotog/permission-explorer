package pt.up.fe.ssin.pexplorer.app;

import java.util.List;

import pt.up.fe.ssin.pexplorer.R;
import android.app.Activity;
import android.content.pm.PermissionGroupInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class FilterConfigActivity extends Activity {

	private static final String TAB_SPEC_GROUP = "group";
	private static final String TAB_SPEC_LEVEL = "level";
	private static final String TAB_SPEC_RELEVANCE = "relevance";

	private List<PermissionGroupInfo> groups;

	private int selectedLevel = 0;
	private int numLevels;

	private int selectedRelevance = 0;
	private int numRelevances;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PermissionCatalog catalog = PermissionCatalog.getInstance(this);
		groups = catalog.getAllGroups();
		numLevels = catalog.getNumberOfLevels();
		numRelevances = catalog.getNumberOfRelevances();

		drawActivity();
	}

	private void drawActivity() {
		setContentView(R.layout.filter_config);
		drawTabs();

		RadioGroup rg = (RadioGroup) findViewById(R.id.level);
		for (int i = 0; i < numLevels; i++) {
			RadioButton rb = (RadioButton) View.inflate(this,
					R.layout.filter_config_radio, null);
			rb.setId(i);
			rb.setText(getResources().getStringArray(R.array.level_labels)[i]);
			if (i == selectedLevel)
				rb.setChecked(true);
			rg.addView(rb);
		}
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				selectedLevel = checkedId;
				updateLevelDescription();
			}
		});
		updateLevelDescription();

		rg = (RadioGroup) findViewById(R.id.relevance);
		for (int i = 0; i < numRelevances; i++) {
			RadioButton rb = (RadioButton) View.inflate(this,
					R.layout.filter_config_radio, null);
			rb.setId(i);
			rb.setText(getResources().getStringArray(R.array.relevance_labels)[i]);
			if (i == selectedRelevance)
				rb.setChecked(true);
			rg.addView(rb);
		}
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				selectedRelevance = checkedId;
				updateRelevanceDescription();
			}
		});
		updateRelevanceDescription();
	}

	private void drawTabs() {
		TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec(TAB_SPEC_GROUP);
		spec1.setContent(R.id.filter_groups);
		spec1.setIndicator(getString(R.string.tab_filter_groups));
		tabHost.addTab(spec1);

		TabSpec spec2 = tabHost.newTabSpec(TAB_SPEC_LEVEL);
		spec2.setContent(R.id.filter_level);
		spec2.setIndicator(getString(R.string.tab_filter_level));
		tabHost.addTab(spec2);

		TabSpec spec3 = tabHost.newTabSpec(TAB_SPEC_RELEVANCE);
		spec3.setContent(R.id.filter_relevance);
		spec3.setIndicator(getString(R.string.tab_filter_relevance));
		tabHost.addTab(spec3);
	}
	
	private void updateLevelDescription() {
		TextView tv = (TextView) findViewById(R.id.level_description);
		tv.setText(getResources().getStringArray(R.array.level_descriptions)[selectedLevel]);
	}

	private void updateRelevanceDescription() {
		TextView tv = (TextView) findViewById(R.id.relevance_description);
		tv.setText(getResources()
				.getStringArray(R.array.relevance_descriptions)[selectedRelevance]);
	}
}
