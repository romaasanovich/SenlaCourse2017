package com.senla.autoservice.ui.menu;

import java.util.HashMap;
import java.util.Map;

import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.mastermenu.*;
import com.senla.autoservice.ui.action.ordermenu.*;
import com.senla.autoservice.ui.action.placemenu.*;

class Builder {
	private Menu rootMenu;

	Map<MenuType, Menu> buildMenu() {
		final Autoservice autoservice = Autoservice.getInstance();
		final Map<MenuType, Menu> response = new HashMap<>();

		rootMenu = new Menu("Root Menu");
		final Menu placeMenu = new Menu("Place Menu");

		final Menu orderMenu = new Menu("Order Menu");
		final Menu orderSortMenu = new Menu("Order sort Menu");
		final Menu curOrderSortMenu = new Menu("Current order sort Menu");
		final Menu orderStatusMenu = new Menu("Status sort Menu");

		final Menu masterMenu = new Menu("MasterMenu");
		final Menu masterSortMenu = new Menu("Master sort Menu");

		rootMenuInit(placeMenu, orderMenu, masterMenu);
		response.put(MenuType.Root, rootMenu);

		placeMenuInit(autoservice, placeMenu);
		response.put(MenuType.Place, placeMenu);

		masterMenuInit(autoservice, masterMenu, masterSortMenu);
		response.put(MenuType.Master, masterMenu);

		orderMenuInit(autoservice, orderMenu, orderSortMenu, curOrderSortMenu, orderStatusMenu);
		response.put(MenuType.Order, orderMenu);

		return response;
	}

	private void orderMenuInit(Autoservice autoservice, final Menu orderMenu, final Menu orderSortMenu,
			final Menu curOrderSortMenu, final Menu orderStatusMenu) {
		orderMenu.addItem(new MenuItem("Show orders", orderSortMenu, new ShowOrdersAction()));
		orderMenu.addItem(new MenuItem("Show current order", curOrderSortMenu, new ShowCurrentOrderAction()));
		orderMenu.addItem(
				new MenuItem("Show order carried out on master", rootMenu, new ShowOrderCarriedOutOnMasterAction()));
		orderMenu.addItem(new MenuItem("Show order for peroid time. Status:", orderStatusMenu,
				new ShowOrderForPeroidTimeAction()));

		orderSortMenu.addItem(new MenuItem("By Price", rootMenu, new ByPriceAction()));
		orderSortMenu.addItem(new MenuItem("By Start Date", rootMenu, new ByStartDateAction()));
		orderSortMenu.addItem(new MenuItem("By Date of order", rootMenu, new ByOrderDateAction()));
		orderSortMenu.addItem(new MenuItem("By Date of completion", rootMenu, new ByDateOfCompletionAction()));

		curOrderSortMenu.addItem(new MenuItem("By Price", rootMenu, new CurrByPriceAction()));
		curOrderSortMenu.addItem(new MenuItem("By Date of order", rootMenu, new CurrByOrderDateAction()));
		curOrderSortMenu.addItem(new MenuItem("By Date of completion", rootMenu, new CurrByDateOfCompletionAction()));

		orderStatusMenu.addItem(new MenuItem("Opened", rootMenu, new StatusOpenedAction()));
		orderStatusMenu.addItem(new MenuItem("Broned", rootMenu, new StatusBronedAction()));
		orderStatusMenu.addItem(new MenuItem("Deleted", rootMenu, new StatusDeletedAction()));
		orderStatusMenu.addItem(new MenuItem("Closed", rootMenu, new StatusClosedAction()));
	}

	private void masterMenuInit(Autoservice autoservice, final Menu masterMenu, final Menu masterSortMenu) {
		masterMenu.addItem(new MenuItem("Show masters", masterSortMenu, new ShowMastersAction()));
		masterMenu.addItem(
				new MenuItem("Show master carried out on order", rootMenu, new ShowMasterCarrOutOnMasterAction()));
		masterMenu.addItem(new MenuItem("Add Master", rootMenu, new AddMasterAction()));
		masterMenu.addItem(new MenuItem("Show close free date", rootMenu, new ShowCloseFreeDateAction()));

		masterSortMenu.addItem(new MenuItem("By busying", rootMenu, new ByBusyingAction()));
		masterSortMenu.addItem(new MenuItem("By alpha", rootMenu, new ByAlphaAction()));
	}

	private void placeMenuInit(Autoservice autoservice, final Menu placeMenu) {
		placeMenu.addItem(new MenuItem("Show Free Places", rootMenu, new ShowFreePlacesAction()));
		placeMenu.addItem(new MenuItem("Show count of free places on date", rootMenu, new CountOfFreePlacesAction()));
		placeMenu.addItem(new MenuItem("Add Place", rootMenu, new AddPlace()));
	}

	private void rootMenuInit(final Menu placeMenu, final Menu orderMenu, final Menu masterMenu) {
		rootMenu.addItem(new MenuItem("Place Menu", placeMenu, () -> {
		}));
		rootMenu.addItem(new MenuItem("Order Menu", orderMenu, () -> {
		}));
		rootMenu.addItem(new MenuItem("Master Menu", masterMenu, () -> {
		}));
	}

}
