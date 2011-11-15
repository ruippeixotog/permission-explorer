package pt.up.fe.ssin.pexplorer.operations;

import java.util.HashSet;
import java.util.Set;

import pt.up.fe.ssin.pexplorer.contentprovider.PermissionExplorerContentProvider;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class PermissionDBOperations{ 	
	
	
	public static String getPermissionDescription(Context context, String name){
        Cursor cursor = context.getContentResolver().query(PermissionExplorerContentProvider.CONTENT_URI, null,  
        		PermissionExplorerContentProvider.PERMISSION_NAME + "= ?", new String[]{name}, null); 
        String description = new String();
        while (cursor.moveToNext()) { 
                 description = cursor.getString( cursor.getColumnIndex(PermissionExplorerContentProvider.PERMISSION_DESCRIPTION)); 
         } 
        cursor.close();
		Log.w("Returned " + name, description);
        return description;
     }
	
	public static Set<String> getCommonPermissions(Context context){
        Cursor cursor = context.getContentResolver().query(PermissionExplorerContentProvider.CONTENT_URI, null,  
        		PermissionExplorerContentProvider.PERMISSION_COMMON + "= ?", new String[]{"1"}, null); 
        Set<String> permissions = new HashSet<String>();
        while (cursor.moveToNext()) { 
                 permissions.add(cursor.getString(cursor.getColumnIndex(PermissionExplorerContentProvider.PERMISSION_NAME))); 
         } 
        cursor.close();
		Log.w("Returned All Common", Integer.toString(permissions.size()));
        return permissions;
     }
	
	
}
