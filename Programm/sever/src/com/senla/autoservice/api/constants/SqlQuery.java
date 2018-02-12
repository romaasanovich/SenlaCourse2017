package com.senla.autoservice.api.constants;

public class SqlQuery {
	public static final String GET_PLACES = "SELECT * FROM 3_task.place;";
	public static final String GET_FREE_PLACES = "select * from mydb.place where mydb.place.isBusy = 0";
	public static final String GET_ORDERS = "select mydb.order.id,mydb.master.name,mydb.order.orderDate,mydb.order.plannedStartDate,mydb.order.completionDate,work.nameOfService,work.price FROM mydb.order JOIN mydb.work JOIN mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id  order by";
	public static final String GET_CUR_ORDERS = "select mydb.order.id,mydb.master.name,mydb.order.orderDate,mydb.order.plannedStartDate,mydb.order.completionDate,work.nameOfService,work.price FROM mydb.order JOIN mydb.work JOIN mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.status=\"Opened\" and  order by";
	public static final String GET_ORDER_CUR_MASTER= "select mydb.order.id,mydb.master.name,mydb.order.orderDate,mydb.order.plannedStartDate,mydb.order.completionDate,work.nameOfService,work.price FROM mydb.order JOIN mydb.work JOIN mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.status=\"Opened\" AND mydb.master.id = ";
	public static final String GET_ORD_FOR_PER_TIME="select mydb.order.id,mydb.master.name,mydb.order.orderDate,mydb.order.plannedStartDate,mydb.order.completionDate,work.nameOfService,work.price  FROM mydb.order JOIN mydb.work JOIN mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.orderDate > str_to_date(\"%s\",'%Y,%m,%d') AND mydb.order.completionDate < str_to_date(\"%s\",'%Y,%m,%d')";
	public static final String GET_MASTERS ="SELECT * FROM mydb.master order by";
	public static final String GET_MASTER_ON_ORDER =  "select mydb.master.id, mydb.master.name FROM mydb.order JOIN mydb.work JOIN mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.id =";
	public static final String ADD_PLACE = "INSERT INTO `mydb`.`place` (`id`, `name`, `isBusy`) VALUES ";
	public static final String ADD_MASTER = "INSERT INTO `mydb`.`master` (`id`, `name`, `isWork`) VALUES ";
	public static final String ADD_WORK = "INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ";
	public static final String ADD_ORDER  = "INSERT INTO `mydb`.`order` (`id`, `idService`, `idMaster`, `idPlace`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES";
	public static final String GET_ID_MASTER = "SELECT count(id) FROM mydb.master";
	public static final String GET_ID_ORDER = "SELECT count(id) FROM mydb.order";
	public static final String GET_ID_WORK = "SELECT count(id) FROM mydb.work";
	public static final String GET_ID_PLACE = "SELECT count(id) FROM mydb.place";
	public static final String CHANGE_STATUS = "UPDATE `mydb`.`order` SET `status`='%s' WHERE `id`='%d'; ";
	public static final String GET_COUNT_OF_ORDERS_ON_DATE = "select count(mydb.order.id)  FROM mydb.order JOIN mydb.work JOIN  mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.plannedStartDate< str_to_date(\"%s\",'%Y,%m,%d') AND mydb.order.completionDate > str_to_date(\"\"%s\",'%Y,%m,%d')";
	public static final String GET_COUNT_PLACE = "SELECT count(id) FROM mydb.place";
	public static final String  GET_ORDER_BY_ID ="select mydb.order.* FROM mydb.order JOIN mydb.work JOIN  mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.id =";
}
