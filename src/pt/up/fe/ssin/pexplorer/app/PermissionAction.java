package pt.up.fe.ssin.pexplorer.app;

import android.content.Context;
import android.content.pm.PermissionInfo;

public abstract class PermissionAction {
	
	// public static final int 
	public static final int WARN = 1;
	// public static final int 

	private String label;
	private String description;
	private int warnLevel;

	public PermissionAction(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void execute(Context context) {
		
	}
	
	public abstract void doAction(Context context);
}
