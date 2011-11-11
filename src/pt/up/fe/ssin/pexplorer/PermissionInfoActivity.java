package pt.up.fe.ssin.pexplorer;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PermissionInfoActivity extends Activity {

	PermissionData data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String name = getIntent().getStringExtra("name");
		try {
			data = DataCatalog.getInstance(this).getPermissionData(name);
		} catch (NameNotFoundException e) {
			Toast.makeText(this, "permission not exists", Toast.LENGTH_SHORT)
					.show();
			finish();
			return;
		}

		setTitle(name);
		TextView tv = new TextView(this);
		if (data.getData().descriptionRes != 0)
			tv.setText(data.getData().descriptionRes);
		setContentView(tv);
	}
}
