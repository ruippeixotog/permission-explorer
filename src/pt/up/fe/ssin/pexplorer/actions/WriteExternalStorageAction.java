package pt.up.fe.ssin.pexplorer.actions;

import java.util.Random;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class WriteExternalStorageAction extends PermissionAction {

	AlertDialog.Builder builder;
	AlertDialog alertDialog;
	
	public WriteExternalStorageAction() {
		super(R.string.write_external_storage_label, R.string.write_external_storage_desc,
				PermissionAction.WARN);
	}
	
	@Override
	protected void doAction(final Context context) {
		Cursor cc = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,null);  
		String id = new String();
		int random = new Random().nextInt((cc.getCount() - 1) + 1);
		if(cc.getCount() > 0){
			 while (cc.moveToNext()){
				 if(cc.getPosition() == random)
					 break;
				 id = cc.getString(cc.getColumnIndex(MediaStore.Images.Media._ID));  
			 }
		}
		displayCustomDialogImageDisplay(context,id);
	}
	
	private void displayCustomDialogImageDisplay(Context context, String id){
		View getMain = new View(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.image_custom_dialog,(ViewGroup) getMain.findViewById(R.id.layout_root));

		ImageView image = (ImageView) layout.findViewById(R.id.gallery_image);
		image.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + id));

		builder = new AlertDialog.Builder(context);
		builder.setView(layout);
		alertDialog = builder.create();
		alertDialog.show();
	}
}

