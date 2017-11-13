package Menu;

public class MenuItem {
	private String title;
	private IAction action;
	private Menu nextMenu;

	public MenuItem(final String title, final Menu next, final IAction action) {
		this.title = title;
		this.nextMenu = next;
		this.action = action;
	}

	public void doAction() {
		action.excute();
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	@Override
	public String toString() {
		return title;
	}

}
