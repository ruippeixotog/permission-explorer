package pt.up.fe.ssin.pexplorer.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pt.up.fe.ssin.pexplorer.utils.PermissionUtils;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;

public class PermissionCatalog {

	public static final int RELEVANCE_COMMON = 0;
	public static final int RELEVANCE_SYSTEM = 1;
	public static final int RELEVANCE_ALL = 2;

	private static final String SYS_PACKAGE = "android";

	/*private static final List<Integer> permLevels = Collections
			.unmodifiableList(Arrays.asList(PermissionInfo.PROTECTION_NORMAL,
					PermissionInfo.PROTECTION_DANGEROUS,
					PermissionInfo.PROTECTION_SIGNATURE,
					PermissionInfo.PROTECTION_SIGNATURE_OR_SYSTEM));

	private static final List<Integer> permRelevances = Collections
			.unmodifiableList(Arrays.asList(RELEVANCE_COMMON, RELEVANCE_SYSTEM,
					RELEVANCE_ALL));*/

	private static PermissionCatalog instance;

	public static PermissionCatalog getInstance(Context context) {
		if (instance == null)
			instance = new PermissionCatalog(context);
		return instance;
	}

	private PackageManager packManager;

	private List<PermissionInfo> allPerms;
	private Map<Integer, List<PermissionInfo>> permsByLevel;

	private Set<String> commonPermNames;

	private PermissionCatalog(Context context) {
		packManager = context.getPackageManager();
	}

	public void reload() {
		allPerms = null;
		permsByLevel = null;
	}

	public List<PermissionInfo> getAll() {
		if (allPerms == null) {
			allPerms = new ArrayList<PermissionInfo>();

			List<PermissionGroupInfo> permGroups = getAllGroups();
			permGroups.add(null);

			for (PermissionGroupInfo pgi : permGroups) {
				List<PermissionInfo> perms;
				try {
					perms = getByGroup(pgi == null ? null : pgi.name);
				} catch (NameNotFoundException e) {
					throw new AssertionError();
				}
				allPerms.addAll(perms);
			}
			Collections.sort(allPerms, PermissionComparator.getInstance());
		}

		return Collections.unmodifiableList(allPerms);
	}

	public int getNumberOfLevels() {
		return 4;
	}
	/*public List<Integer> getAllLevels() {
		return permLevels;
	}*/

	public List<PermissionInfo> getByLevel(int level) {
		if (permsByLevel == null) {
			permsByLevel = new HashMap<Integer, List<PermissionInfo>>();

			for (PermissionInfo pi : getAll()) {
				if (!permsByLevel.containsKey(pi.protectionLevel))
					permsByLevel.put(pi.protectionLevel,
							new ArrayList<PermissionInfo>());
				permsByLevel.get(pi.protectionLevel).add(pi);
			}

			for (List<PermissionInfo> perms : permsByLevel.values())
				Collections.sort(perms, PermissionComparator.getInstance());
		}

		return Collections.unmodifiableList(permsByLevel.get(level));
	}

	public List<PermissionGroupInfo> getAllGroups() {
		return packManager.getAllPermissionGroups(PackageManager.GET_META_DATA);
	}

	public List<PermissionInfo> getByGroup(String group)
			throws NameNotFoundException {
		return packManager.queryPermissionsByGroup(group,
				PackageManager.GET_META_DATA);
	}

	public int getNumberOfRelevances() {
		return 3;
	}
	/*public List<Integer> getAllRelevances() {
		return permRelevances;
	}*/

	public List<PermissionInfo> filter(List<PermissionInfo> list, String group,
			Integer level, Integer relevance) {
		Set<String> groups = null;
		if (group != null) {
			groups = new HashSet<String>(1);
			groups.add(group);
		}
		return filter(list, groups, level, relevance);
	}

	public List<PermissionInfo> filter(List<PermissionInfo> list,
			List<String> groups, Integer level, Integer relevance) {
		return filter(list, new HashSet<String>(groups), level, relevance);
	}

	public List<PermissionInfo> filter(List<PermissionInfo> list,
			Set<String> groups, Integer level, Integer relevance) {
		List<PermissionInfo> filteredList = new ArrayList<PermissionInfo>();
		for (PermissionInfo perm : list) {
			if (groups != null && !groups.contains(perm.group) || level != null
					&& perm.protectionLevel < level)
				continue;

			if (relevance != null)
				if (relevance == RELEVANCE_COMMON
						&& !getCommonPermissionNames().contains(perm.name)
						|| relevance == RELEVANCE_SYSTEM
						&& !perm.packageName.equals(SYS_PACKAGE))
					continue;

			filteredList.add(perm);
		}
		return filteredList;
	}

	public PermissionInfo getInfo(String permName) throws NameNotFoundException {
		return packManager.getPermissionInfo(permName,
				PackageManager.GET_META_DATA);
	}

	public String getDescription(PermissionInfo perm) {
		return perm.loadDescription(packManager).toString();
	}

	private Set<String> getCommonPermissionNames() {
		if (commonPermNames == null) {
			commonPermNames = new HashSet<String>();
			// TODO ir buscar a BD
		}
		return commonPermNames;
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
