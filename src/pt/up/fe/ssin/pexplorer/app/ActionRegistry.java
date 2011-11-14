package pt.up.fe.ssin.pexplorer.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActionRegistry {

	private static ActionRegistry instance;

	public static ActionRegistry getInstance() {
		if (instance == null)
			instance = new ActionRegistry();
		return instance;
	}

	private Map<String, List<PermissionAction>> actions;

	private ActionRegistry() {
	}
	
	public List<PermissionAction> getPermissionActions(String permName) {
		if (actions.containsKey(permName))
			return actions.get(permName);

		List<PermissionAction> permActions = new ArrayList<PermissionAction>();
		actions.put(permName, permActions);
		return permActions;
	}

	public void addAction(String permName, PermissionAction action) {
		addAction(permName, action, -1);
	}

	public void addAction(String permName, PermissionAction action, int position) {
		List<PermissionAction> permActions;
		if (!actions.containsKey(permName)) {
			permActions = new ArrayList<PermissionAction>();
			actions.put(permName, permActions);
		} else
			permActions = actions.get(permName);

		if (position >= 0)
			permActions.add(position, action);
		else
			permActions.add(action);
	}
}
