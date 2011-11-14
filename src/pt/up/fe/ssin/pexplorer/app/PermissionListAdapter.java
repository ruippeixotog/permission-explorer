package pt.up.fe.ssin.pexplorer.app;

import java.util.List;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.utils.Pair;
import pt.up.fe.ssin.pexplorer.utils.PermissionUtils;
import pt.up.fe.ssin.pexplorer.utils.SimpleObjectAdapter;
import android.content.Context;
import android.content.pm.PermissionInfo;
import android.view.View;
import android.widget.TextView;

public class PermissionListAdapter extends SimpleObjectAdapter<PermissionInfo> {

	public PermissionListAdapter(Context context, List<PermissionInfo> objects) {
		super(context, R.layout.perm_row, objects);
	}

	@Override
	public View getView(View inflatedView, PermissionInfo perm) {
		Pair<String, String> parsedName = PermissionUtils.decomposeName(perm);

		TextView tv = (TextView) inflatedView.findViewById(android.R.id.text1);
		tv.setText(parsedName.getSecond());

		tv = (TextView) inflatedView.findViewById(android.R.id.text2);
		tv.setText(parsedName.getFirst());

		return inflatedView;
	}

	@Override
	public boolean isFilterMatch(CharSequence constraint, PermissionInfo perm) {
		String prefix = constraint.toString().toLowerCase();
		String[] words = PermissionUtils.getShortName(perm).split("_");
		for (String word : words)
			if (word.toLowerCase().startsWith(prefix))
				return true;
		return false;
	}
}