package pt.up.fe.ssin.pexplorer.app;

import java.util.List;

import pt.up.fe.ssin.pexplorer.utils.SimpleObjectAdapter;
import android.content.Context;
import android.content.pm.PermissionInfo;
import android.view.View;
import android.widget.TextView;

public class PermissionListAdapter extends SimpleObjectAdapter<PermissionInfo> {

	public PermissionListAdapter(Context context, List<PermissionInfo> objects) {
		super(context, android.R.layout.simple_list_item_2, objects);
	}

	@Override
	public View getView(View inflatedView, PermissionInfo perm) {
		TextView tv = (TextView) inflatedView.findViewById(android.R.id.text1);
		int lastDot = perm.name.lastIndexOf('.');
		tv.setText(perm.name.substring(lastDot + 1));

		if (perm.descriptionRes != 0) {
			tv = (TextView) inflatedView.findViewById(android.R.id.text2);
			tv.setText(perm.name.substring(0, lastDot));
		}
		return inflatedView;
	}

	@Override
	public boolean isFilterMatch(CharSequence constraint, PermissionInfo perm) {
		return true;
	}
}