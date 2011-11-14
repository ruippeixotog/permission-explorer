package pt.up.fe.ssin.pexplorer.app;

import pt.up.fe.ssin.pexplorer.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

public abstract class PermissionAction {

	public static final int DO_NOTHING = 0;
	public static final int WARN = 1;
	public static final int WARN_DANGEROUS = 2;

	private String label;
	private String description;
	private int warnLevel;
	
	private Dialog dialog;

	public PermissionAction(String label, String description, int warnLevel) {
		this.label = label;
		this.description = description;
		this.warnLevel = warnLevel;
	}

	public String getLabel() {
		return label;
	}

	public void execute(final Context context) {
		if (warnLevel >= WARN)
			getWarnDialog(context).show();
		else
			doAction(context);
	}

	public abstract void doAction(Context context);
	
	private Dialog getWarnDialog(final Context context) {
		if(dialog == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(
					String.format(
							context.getString(R.string.dialog_msg_action_warn),
							description));
			builder.setCancelable(true);
			builder.setPositiveButton(R.string.continue_,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							doAction(context);
						}
					}).setNegativeButton(R.string.cancel, null);
			builder.setTitle(R.string.warning);
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			dialog = builder.create();
		}
		return dialog;
	}
}
