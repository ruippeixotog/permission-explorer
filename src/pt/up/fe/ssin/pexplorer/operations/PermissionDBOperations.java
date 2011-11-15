package pt.up.fe.ssin.pexplorer.operations;

import java.util.HashSet;
import java.util.Set;

import pt.up.fe.ssin.pexplorer.contentprovider.PermissionExplorerContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class PermissionDBOperations{ 	
	
	public static boolean createPermission(Context context){
		
		ContentValues values = new ContentValues();
		
		values.put(PermissionExplorerContentProvider.PERMISSION_NAME, "lulz");
		values.put(PermissionExplorerContentProvider.PERMISSION_COMMON, 0);
		values.put(PermissionExplorerContentProvider.PERMISSION_DESCRIPTION,"description lulz");
		Uri uri = context.getContentResolver().insert(PermissionExplorerContentProvider.CONTENT_URI, values);
		Log.w("TROLL", "created permission");
		return (uri != null);
	}
	
	public static String getPermissionDescription(Context context, String name){
        Cursor cursor = context.getContentResolver().query(PermissionExplorerContentProvider.CONTENT_URI, null,  
        		PermissionExplorerContentProvider.PERMISSION_NAME + "= ?", new String[]{name}, null); 
        String description = new String();
        while (cursor.moveToNext()) { 
                 description = cursor.getString( cursor.getColumnIndex(PermissionExplorerContentProvider.PERMISSION_DESCRIPTION)); 
         } 
        cursor.close();
		Log.w("Returned", description);
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
		Log.w("Returned", Integer.toString(permissions.size()));
        return permissions;
     }
	
	
}
