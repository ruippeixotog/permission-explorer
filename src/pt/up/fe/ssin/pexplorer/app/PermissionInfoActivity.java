package pt.up.fe.ssin.pexplorer.app;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PermissionInfoActivity extends Activity {

	PermissionInfo perm;
	String extendedInfo = "<More detailed description>";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String name = getIntent().getStringExtra("name");
		try {
			perm = DataCatalog.getInstance(this).getPermissionInfo(name);
		} catch (NameNotFoundException e) {
			Toast.makeText(this, "permission does not exist",
					Toast.LENGTH_SHORT).show();
			finish();
			return;
		}

		setTitle(name);
		TextView tv = new TextView(this);
		if (perm.descriptionRes != 0)
			tv.setText(perm.descriptionRes);
		setContentView(tv);
	}
}
