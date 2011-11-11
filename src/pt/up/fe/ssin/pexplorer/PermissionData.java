package pt.up.fe.ssin.pexplorer;

import android.content.pm.PermissionInfo;

public class PermissionData {
	
	private PermissionInfo data;
	private String extendedDesc;
	
	public PermissionData(PermissionInfo data) {
		this.data = data;
	}

	public PermissionData(PermissionInfo data, String extendedDesc) {
		this.data = data;
		this.extendedDesc = extendedDesc;
	}

	public PermissionInfo getData() {
		return data;
	}
	
	public void setData(PermissionInfo data) {
		this.data = data;
	}
	public String getExtendedDesc() {
		return extendedDesc;
	}
	public void setExtendedDesc(String extendedDesc) {
		this.extendedDesc = extendedDesc;
	}
}
