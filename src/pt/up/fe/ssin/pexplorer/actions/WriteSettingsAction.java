package pt.up.fe.ssin.pexplorer.actions;

import java.util.Random;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class WriteSettingsAction extends PermissionAction {
	
	public WriteSettingsAction() {
		super(R.string.write_settings_label, R.string.write_settings_desc,
				PermissionAction.WARN);
	}
	
	@Override
	protected void doAction(final Context context) {
		Random rand = new Random();
		int SysBackLightValue = rand.nextInt((255 - 1) + 1);  
		android.provider.Settings.System.putInt(context.getContentResolver(),android.provider.Settings.System.SCREEN_BRIGHTNESS,SysBackLightValue);
		
		new AlertDialog.Builder(context)
        .setTitle(R.string.wifi_state_label)
        .setMessage("Screen brightness has changed!")
        .setCancelable(true)
        .setPositiveButton(R.string.continue_,new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {}
        }).show();	
	}
}

