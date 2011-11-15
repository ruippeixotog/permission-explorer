package pt.up.fe.ssin.pexplorer.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pt.up.fe.ssin.pexplorer.operations.PermissionDBOperations;
import pt.up.fe.ssin.pexplorer.utils.PermissionUtils;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;

public class PermissionCatalog {

	public static final int RELEVANCE_ALL = 0;
	public static final int RELEVANCE_SYSTEM = 1;
	public static final int RELEVANCE_COMMON = 2;

	private static final int NUM_LEVELS = 4;
	private static final int NUM_RELEVANCES = 3;
	private static final String SYS_PACKAGE = "android";

	private static PermissionCatalog instance;
	
	public static PermissionCatalog getInstance(Context context) {
		if (instance == null)
			instance = new PermissionCatalog(context);
		return instance;
	}

	private Context context;
	private PackageManager packManager;

	private List<PermissionInfo> allPerms;
	private Map<Integer, List<PermissionInfo>> permsByLevel;

	private Set<String> commonPermNames;

	private PermissionCatalog(Context context) {
		this.context = context;
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
		return NUM_LEVELS;
	}

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

	public List<PermissionInfo> getByGroup(String groupName)
			throws NameNotFoundException {
		return packManager.queryPermissionsByGroup(groupName,
				PackageManager.GET_META_DATA);
	}

	public int getNumberOfRelevances() {
		return NUM_RELEVANCES;
	}

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
			String[] groups, Integer level, Integer relevance) {
		return filter(list, groups == null ? null : new HashSet<String>(Arrays.asList(groups)), level,
				relevance);
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

	public String getGroupDescription(PermissionGroupInfo group) {
		return group.loadDescription(packManager).toString();
	}

	private Set<String> getCommonPermissionNames() {
		if (commonPermNames == null) {
			commonPermNames = PermissionDBOperations.getCommonPermissions(this.context);
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
