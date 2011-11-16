package pt.up.fe.ssin.pexplorer.actions;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import pt.up.fe.ssin.pexplorer.data.ReadContactsOperations;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class PhoneCallAction extends PermissionAction {

	public PhoneCallAction() {
		super(R.string.call_phone_label, R.string.call_phone_desc,
				PermissionAction.WARN);
	}

	@Override
	protected void doAction(final Context context) {
		Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(R.string.call_phone_title)
        .setMessage(R.string.call_phone_dialog_label)
        .setPositiveButton(R.string.call, new DialogInterface.OnClickListener() {

        	public void onClick(DialogInterface dialog, int id) {
				try {
			        Intent callIntent = new Intent(Intent.ACTION_CALL);
			        callIntent.setData(Uri.parse("tel:" + ReadContactsOperations.getRandomContact(context).getPhoneNumber())); 
			        context.startActivity(callIntent);
			    } catch (ActivityNotFoundException activityException) {
			         Log.e("Phone Call Failed", "It Failed");
			    }
			}

        })
        .setNegativeButton(R.string.cancel, null)
        .show();
	}

}
