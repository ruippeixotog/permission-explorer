/*
 * Copyright (C) 2012 Rui Gonçalves and Daniel Cibrão
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pt.up.fe.ssin.pexplorer.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

public class WriteExternalStorageAction extends PermissionAction {

	AlertDialog.Builder builder;
	AlertDialog alertDialog;
	
	public WriteExternalStorageAction() {
		super(R.string.write_external_storage_label, R.string.write_external_storage_desc,
				PermissionAction.WARN);
	}
	
	@Override
	protected void doAction(final Context context) {
		try {
		    File root = Environment.getExternalStorageDirectory();
		    if (root.canWrite()){
		        File PermissionExplorer = new File(root, "PermissionExplorer.txt");
		        FileWriter permissionWriter = new FileWriter(PermissionExplorer);
		        BufferedWriter out = new BufferedWriter(permissionWriter);
		        Cursor cc = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,null);  
				if(cc.getCount() > 0)
					 while (cc.moveToNext())
						out.write(cc.getString(cc.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)) + "\n");  
				else
					out.write(R.string.write_external_file_no_photos);
		        out.close();
		    }
		} catch (IOException e) {
		    
		}
		
		Toast.makeText(context, R.string.write_external_file_storage,Toast.LENGTH_SHORT).show();
	}
}

