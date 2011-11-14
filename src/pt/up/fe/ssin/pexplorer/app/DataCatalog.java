package pt.up.fe.ssin.pexplorer.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.up.fe.ssin.pexplorer.utils.PermissionUtils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;

public class DataCatalog {

	private static DataCatalog instance;

	public static DataCatalog getInstance(Context context) {
		if (instance == null)
			instance = new DataCatalog(context);
		return instance;
	}

	private PackageManager packManager;

	private List<PermissionInfo> allPermissions = new ArrayList<PermissionInfo>();
	private Map<Integer, List<PermissionInfo>> permissionsByLevel = new HashMap<Integer, List<PermissionInfo>>();

	private DataCatalog(Context context) {
		packManager = context.getPackageManager();
		reload();
	}

	public void reload() {
		allPermissions.clear();
		permissionsByLevel.clear();

		List<PermissionGroupInfo> permGroups = getPermissionGroups();
		permGroups.add(null);

		for (PermissionGroupInfo pgi : permGroups) {
			List<PermissionInfo> perms;
			try {
				perms = getPermissionsByGroup(pgi == null ? null : pgi.name);
			} catch (NameNotFoundException e) {
				throw new AssertionError();
			}
			allPermissions.addAll(perms);
			for (PermissionInfo pi : perms) {
				if (!permissionsByLevel.containsKey(pi.protectionLevel))
					permissionsByLevel.put(pi.protectionLevel,
							new ArrayList<PermissionInfo>());
				permissionsByLevel.get(pi.protectionLevel).add(pi);
			}
		}

		Collections.sort(allPermissions, PermissionComparator.getInstance());
		for (List<PermissionInfo> perms : permissionsByLevel.values())
			Collections.sort(perms, PermissionComparator.getInstance());
	}

	public List<PermissionInfo> getAllPermissions() {
		return Collections.unmodifiableList(allPermissions);
	}

	public List<PermissionInfo> getPermissionsByLevel(int level) {
		return Collections.unmodifiableList(permissionsByLevel.get(level));
	}

	public List<PermissionGroupInfo> getPermissionGroups() {
		return packManager.getAllPermissionGroups(PackageManager.GET_META_DATA);
	}

	public List<PermissionInfo> getPermissionsByGroup(String group)
			throws NameNotFoundException {
		return packManager.queryPermissionsByGroup(group,
				PackageManager.GET_META_DATA);
	}

	public PermissionInfo getPermissionInfo(String name)
			throws NameNotFoundException {
		return packManager
				.getPermissionInfo(name, PackageManager.GET_META_DATA);
	}

	public String getPermissionDescription(PermissionInfo perm) {
		return perm.loadDescription(packManager).toString();
	}

	private static class PermissionComparator implements
			Comparator<PermissionInfo> {

		private static PermissionComparator instance;

		private PermissionComparator() {
		}

		public static Comparator<PermissionInfo> getInstance() {
			if (instance == null)
				instance = new PermissionComparator();
			return instance;
		}

		@Override
		public int compare(PermissionInfo arg0, PermissionInfo arg1) {
			return PermissionUtils.getShortName(arg0).compareTo(
					PermissionUtils.getShortName(arg1));
		}
	}
}
