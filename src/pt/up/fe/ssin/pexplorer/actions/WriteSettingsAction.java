package pt.up.fe.ssin.pexplorer.actions;

import java.util.Locale;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

public class WriteSettingsAction extends PermissionAction {
	
	public WriteSettingsAction() {
		super(R.string.write_settings_label, R.string.write_settings_desc,
				PermissionAction.WARN);
	}
	
	@Override
	protected void doAction(final Context context) {

		 Resources res = context.getResources();
		 DisplayMetrics dm = res.getDisplayMetrics();
		 android.content.res.Configuration conf = res.getConfiguration();
		 conf.locale = new Locale(Locale.FRANCE.getLanguage().toLowerCase());
		 res.updateConfiguration(conf, dm);
        
		new AlertDialog.Builder(context)
        .setTitle(R.string.write_settings_label)
        .setMessage("Screen brightness has changed!")
        .setCancelable(true)
        .setPositiveButton(R.string.continue_,new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {}
        }).show();	
	}
}

