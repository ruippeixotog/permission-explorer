package pt.up.fe.ssin.pexplorer.actions;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

public class TakePicutreAction extends PermissionAction {
	
	public TakePicutreAction() {
		super(R.string.take_picture_label, R.string.take_picture_label,
				PermissionAction.DO_NOTHING);
	}
	
	@Override
	protected void doAction(final Context context) {
		final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		context.startActivity(intent);
	}	
}
