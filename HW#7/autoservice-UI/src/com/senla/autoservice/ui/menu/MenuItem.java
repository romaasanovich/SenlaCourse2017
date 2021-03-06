package com.senla.autoservice.ui.menu;

import com.senla.autoservice.ui.action.IAction;

public class MenuItem {
	private String title;
	private IAction action;
	private Menu nextMenu;

	MenuItem(final String title, final Menu next, final IAction action) {
		this.title = title;
		this.nextMenu = next;
		this.action = action;
	}

	void doAction() {
		action.excute();
	}

	Menu getNextMenu() {
		return nextMenu;
	}

	@Override
	public String toString() {
		return title;
	}

}
