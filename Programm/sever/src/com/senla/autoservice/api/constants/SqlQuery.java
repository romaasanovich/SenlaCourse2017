package com.senla.autoservice.api.constants;

public class SqlQuery {
	public static final String GET_BY_ID = "SELECT * FROM mydb.? where id= ?;";
	public static final String GET_PLACES = "SELECT * FROM mydb.place;";
	public static final String GET_WORKS = "SELECT * FROM mydb.work;";
	public static final String GET_FREE_PLACES = "select * from mydb.place where mydb.place.isBusy = 0";
	public static final String GET_SORTED_PLACES = "select * from mydb.place group by (?)";
	public static final String GET_ORDER_ALL_INFO = "select * FROM mydb.order JOIN mydb.work JOIN mydb.master JOIN mydb.place ON mydb.order.idService = mydb.work.id AND  mydb.order.idPlace = mydb.place.id AND mydb.order.idMaster= mydb.master.id order by";
	public static final String GET_CUR_ORDERS = "select * FROM mydb.order JOIN mydb.work JOIN mydb.master JOIN mydb.place ON mydb.order.idService = mydb.work.id AND  mydb.order.idPlace = mydb.place.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.status=\"Opened\" order by";
	public static final String GET_ORDER_CUR_MASTER= "select * FROM mydb.order JOIN mydb.work JOIN mydb.master JOIN mydb.place ON mydb.order.idService = mydb.work.id AND  mydb.order.idPlace = mydb.place.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.status=\"Opened\" AND mydb.master.id = ";
	public static final String GET_ORD_FOR_PER_TIME="select mydb.order.id,mydb.master.nameMaster,mydb.order.orderDate,mydb.order.plannedStartDate,mydb.order.completionDate,work.nameOfService,work.price  FROM mydb.order JOIN mydb.work JOIN mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.orderDate > str_to_date(\"?\",'%Y,%m,%d') AND mydb.order.completionDate < str_to_date(\"?\",'%Y,%m,%d')";
	public static final String GET_MASTERS ="SELECT * FROM mydb.master order by";
	public static final String GET_MASTER_ON_ORDER =  "select mydb.master.id, mydb.master.name FROM mydb.order JOIN mydb.work JOIN mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.id =";
	public static final String ADD_PLACE = "INSERT INTO `mydb`.`place` (`id`, `placeName`, `isBusy`) VALUES (?,?,?)";
	public static final String ADD_MASTER = "INSERT INTO `mydb`.`master` (`id`, `nameMaster`, `isWork`) VALUES (?,?,?,?)";
	public static final String ADD_WORK = "INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ( ?,?,?,?)";
	public static final String ADD_ORDER  = "INSERT INTO `mydb`.`order` (`id`, `idService`, `idMaster`, `idPlace`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES (?,?,?,?,?,?,?,?)";
	public static final String GET_ID_MASTER = "SELECT count(id) FROM mydb.master";
	public static final String GET_ID_ORDER = "SELECT count(id) FROM mydb.order";
	public static final String GET_ID_WORK = "SELECT count(id) FROM mydb.work";
	public static final String GET_ID_PLACE = "SELECT count(id) FROM mydb.place";
	public static final String CHANGE_STATUS = "UPDATE `mydb`.`order` SET `status`='?' WHERE `id`='?'; ";
	public static final String GET_COUNT_OF_ORDERS_ON_DATE = "select count(mydb.order.id)  FROM mydb.order JOIN mydb.work JOIN  mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.plannedStartDate< str_to_date(\"%s\",'%Y,%m,%d') AND mydb.order.completionDate > str_to_date(\"\"%s\",'%Y,%m,%d')";
	public static final String GET_COUNT_PLACE = "SELECT count(id) FROM mydb.place";
	public static final String  GET_ORDER_BY_ID ="select mydb.order.* FROM mydb.order JOIN mydb.work JOIN  mydb.master ON mydb.order.idService = mydb.work.id AND mydb.order.idMaster= mydb.master.id AND mydb.order.id =";
	public static final String GET_ORDERS = "SELECT mydb.order.* FROM mydb.order JOIN mydb.work ON mydb.order.idService=mydb.work.id order by";
	public static final String UPDATE_PLACE= "UPDATE `mydb`.`place` SET `placeName`='?', `isBusy`='?' WHERE `id`='?'";
	public static final String UPDATE_MASTER= "UPDATE `mydb`.`master` SET `nameMaster`='?', `isWork	`='?' WHERE `id`='?'";
	public static final String UPDATE_WORK= "UPDATE `mydb`.`work` SET `nameOfService`='?', `price`='?', `idMaster`='?' WHERE `id`='?'";
}
