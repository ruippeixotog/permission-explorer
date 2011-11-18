package pt.up.fe.ssin.pexplorer.actions;

import pt.up.fe.ssin.pexplorer.R;
import pt.up.fe.ssin.pexplorer.app.PermissionAction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AccessFineLocationAction extends PermissionAction {

	private SampleLocationListener mLocationListener;
	private LocationManager m_location_manager;
	
	public AccessFineLocationAction() {
		super(R.string.access_fine_location_label, R.string.access_fine_location_desc,
				PermissionAction.WARN);
	}

	@Override
	protected void doAction(final Context context) {
		  m_location_manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		  Boolean gpsEnabled = m_location_manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		  Log.d("onCreateonCreate",String.valueOf(gpsEnabled));
		  if(gpsEnabled)
			  Toast.makeText(context,"GPS is enabled. Getting you current location",Toast.LENGTH_SHORT).show();
		  else
			  Toast.makeText(context,"Your gps is not enabled! Please turn it on.",Toast.LENGTH_SHORT).show();
	}
}
	
class SampleLocationListener implements LocationListener {
	
	  Context context;
	
	  public SampleLocationListener(Context context){
		  this.context = context;
	  }
	
	  public void onLocationChanged(Location location) {
		  location.getLatitude();
		  location.getLongitude();
		  String Text = "Your current location\n:"  + "\nLatitude\n" + location.getLatitude() +
		  "\nLongitude\n" + location.getLongitude();

		  new AlertDialog.Builder(context)
	        .setTitle(R.string.read_contact_title)
	        .setMessage(Text)
	        .setCancelable(true)
	        .setPositiveButton(R.string.continue_,new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {}
	        }).show();	
	  }
	  
	  public void onProviderDisabled(String provider) {
	    Log.d("SampleLocationListener onProviderDisabled",provider);
	  }
	  public void onProviderEnabled(String provider) {
	    Log.d("SampleLocationListener onProviderEnabled",provider);
	  }
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    Log.d("SampleLocationListener onStatusChanged",provider);
	  }
}
