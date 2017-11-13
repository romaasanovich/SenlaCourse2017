package Menu;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;

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

	public void orderMenuInit(Autoservice autoservice, final Menu orderMenu, final Menu orderSortMenu,
			final Menu curOrderSortMenu, final Menu orderStatusMenu) {
		orderMenu.addItem(new MenuItem("Show order", orderSortMenu, () -> {
		}));

		orderMenu.addItem(new MenuItem("Show current order", curOrderSortMenu, () -> {
		}));

		orderMenu.addItem(new MenuItem("Show order carried out on master", rootMenu, new IAction() {
			@Override
			public void excute() {
				try {
					Printer.printMessage(autoservice.showMastersByAlpha());
					Printer.printMessage("Choose order");
					int id = Reader.readInt();
					Master master = autoservice.getCurMaster(id);
					autoservice.showOrderCarriedOutByMaster(master);
				} catch (final IOException e) {
					Printer.printMessage(Constants.ERROR_WRONG_INPUT);
				}
			}
		}));

		orderMenu.addItem(new MenuItem("Show order for peroid time. Status:", orderStatusMenu, () -> {
		}));

		orderSortMenu.addItem(new MenuItem("By Price", rootMenu, new IAction() {
			@Override
			public void excute() {
				Printer.printMessage(autoservice.showOrdersByPrice());
			}
		}));

		orderSortMenu.addItem(new MenuItem("By Start Date", rootMenu, new IAction() {
			@Override
			public void excute() {
				Printer.printMessage(autoservice.showOrdersByDateOfStart());
			}
		}));

		orderSortMenu.addItem(new MenuItem("By Date of order", rootMenu, new IAction() {
			@Override
			public void excute() {
				Printer.printMessage(autoservice.showOrdersByOrderDate());
			}
		}));

		orderSortMenu.addItem(new MenuItem("By Date of completion", rootMenu, new IAction() {
			@Override
			public void excute() {
				Printer.printMessage(autoservice.showOrdersByDateOfCompletion());
			}
		}));

		/////////////

		curOrderSortMenu.addItem(new MenuItem("By Price", rootMenu, new IAction() {
			@Override
			public void excute() {
				Printer.printMessage(autoservice.showCurrentOrdersPrice());
			}
		}));

		curOrderSortMenu.addItem(new MenuItem("By Date of order", rootMenu, new IAction() {
			@Override
			public void excute() {
				Printer.printMessage(autoservice.showCurrentOrdersByDateOfOrder());
			}
		}));

		curOrderSortMenu.addItem(new MenuItem("By Date of completion", rootMenu, new IAction() {
			@Override
			public void excute() {
				Printer.printMessage(autoservice.showCurrentOrdersByDateOfCompletion());
			}
		}));

		//////////////////

		orderStatusMenu.addItem(new MenuItem("Opened", rootMenu, new IAction() {
			@Override
			public void excute() {
				try {
					String date = Reader.readline();
					Date fDate = Convert.fromStrToDate(date);
					date = Reader.readline();
					Date sDate = Convert.fromStrToDate(date);
					Printer.printMessage(autoservice.showOrdersForPeriodTime(StatusOrder.Opened, fDate, sDate));
				} catch (final IOException e) {
					Printer.printMessage(Constants.ERROR_WRONG_INPUT);
				}
			}
		}));

		orderStatusMenu.addItem(new MenuItem("Broned", rootMenu, new IAction() {
			@Override
			public void excute() {
				try {
					String date = Reader.readline();
					Date fDate = Convert.fromStrToDate(date);
					date = Reader.readline();
					Date sDate = Convert.fromStrToDate(date);
					Printer.printMessage(autoservice.showOrdersForPeriodTime(StatusOrder.Broned, fDate, sDate));
				} catch (final IOException e) {
					Printer.printMessage(Constants.ERROR_WRONG_INPUT);
				}
			}
		}));

		orderStatusMenu.addItem(new MenuItem("Deleted", rootMenu, new IAction() {
			@Override
			public void excute() {
				try {
					String date = Reader.readline();
					Date fDate = Convert.fromStrToDate(date);
					date = Reader.readline();
					Date sDate = Convert.fromStrToDate(date);
					Printer.printMessage(autoservice.showOrdersForPeriodTime(StatusOrder.Deleted, fDate, sDate));
				} catch (final IOException e) {
					Printer.printMessage(Constants.ERROR_WRONG_INPUT);
				}
			}
		}));

		orderStatusMenu.addItem(new MenuItem("Closed", rootMenu, new IAction() {
			@Override
			public void excute() {
				try {
					String date = Reader.readline();
					Date fDate = Convert.fromStrToDate(date);
					date = Reader.readline();
					Date sDate = Convert.fromStrToDate(date);
					Printer.printMessage(autoservice.showOrdersForPeriodTime(StatusOrder.Closed, fDate, sDate));
				} catch (final IOException e) {
					Printer.printMessage(Constants.ERROR_WRONG_INPUT);
				}
			}
		}));

	}

	public void masterMenuInit(Autoservice autoservice, final Menu masterMenu, final Menu masterSortMenu) {
		masterMenu.addItem(new MenuItem("Show masters", masterSortMenu, () -> {
		}));

		masterMenu.addItem(new MenuItem("Show master carried out on order", rootMenu, new IAction() {
			@Override
			public void excute() {
				try {
					Printer.printMessage(autoservice.showOrdersByPrice());
					Printer.printMessage("Choose order");
					int id = Reader.readInt();
					Order ord = autoservice.getCurrentOrder(id);
					autoservice.showMasterCarriedOutOrder(ord);
				} catch (final IOException e) {
					Printer.printMessage(Constants.ERROR_WRONG_INPUT);
				}

			}
		}));

		masterMenu.addItem(new MenuItem("Add Master", rootMenu, new IAction() {
			@Override
			public void excute() {
				try {
					String name = Reader.readline();
					int id = Reader.readInt();
					autoservice.addMaster(new Master(id, name, null, null));
				} catch (final IOException e) {
					Printer.printMessage(Constants.ERROR_WRONG_INPUT);
				}
			}
		}));

		/*
		 * masterMenu.addItem(new MenuItem("Add order to master", rootMenu, new
		 * IAction() {
		 * 
		 * @Override public void excute() { try { String name = Reader.readline(); int
		 * id = Reader.readInt(); autoservice.addMaster(new Master(id, name, null,
		 * null)); } catch (final IOException e) {
		 * Printer.printMessage(Constants.ERROR_WRONG_INPUT); } } }));
		 */
		masterMenu.addItem(new MenuItem("Show close free date", rootMenu, new IAction() {
			@Override
			public void excute() {
				Printer.printMessage(autoservice.showCloseFreeDate());
			}
		}));

		masterSortMenu.addItem(new MenuItem("By busyising", rootMenu, new IAction() {

			@Override
			public void excute() {
				Printer.printMessage(autoservice.showMastersByBusying());
			}
		}));

		masterSortMenu.addItem(new MenuItem("By alpha", rootMenu, new IAction() {

			@Override
			public void excute() {
				Printer.printMessage(autoservice.showMastersByAlpha());
			}
		}));
	}

	public void placeMenuInit(Autoservice autoservice, final Menu placeMenu) {
		placeMenu.addItem(new MenuItem("Show Free Places", rootMenu, new IAction() {

			@Override
			public void excute() {
				Printer.printMessage(autoservice.showAllFreePlaces());
			}
		}));

		placeMenu.addItem(new MenuItem("Show count of free places on date", rootMenu, new IAction() {
			@Override
			public void excute() {
				try {
					String date = Reader.readline();
					Date curDate = Convert.fromStrToDate(date);
					autoservice.showCountOfFreePlacesOnDate(curDate);
				} catch (final IOException e) {
					Printer.printMessage(Constants.ERROR_WRONG_INPUT);
				}
			}
		}));

		placeMenu.addItem(new MenuItem("Add Place", rootMenu, new IAction() {
			@Override
			public void excute() {
				try {
					String name = Reader.readline();
					int id = Reader.readInt();
					autoservice.addPlace(new Place(id, name));
				} catch (final IOException e) {
					Printer.printMessage(Constants.ERROR_WRONG_INPUT);
				}
			}
		}));
	}

	public void rootMenuInit(final Menu placeMenu, final Menu orderMenu, final Menu masterMenu) {
		rootMenu.addItem(new MenuItem("Place Menu", placeMenu, () -> {

		}));
		rootMenu.addItem(new MenuItem("Order Menu", orderMenu, () -> {

		}));
		rootMenu.addItem(new MenuItem("Master Menu", masterMenu, () -> {

		}));
	}

	public Menu getRootMenu() {
		return rootMenu;
	}
}
