package pt.up.fe.ssin.pexplorer.data;

import pt.up.fe.ssin.pexplorer.entities.Contact;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

public class ReadContactsOperations {
	
	public static Contact getRandomContact(Context context){
		Cursor cur = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
		String name=null, phoneNumber = null;
		if (cur.getCount() > 0) {
		    while (cur.moveToNext()) {
		        String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
		        name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	 		
				if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
		             Cursor pCur = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
				    new String[]{id}, null);
			        while (pCur.moveToNext()) {
			        	phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			        } 
			        pCur.close();
			    }
		    }
		}
		return new Contact(name,phoneNumber,"");
	}
}
