INSERT INTO `mydb`.`place` (`name`, `isBusy`) VALUES ('place1', '0');
INSERT INTO `mydb`.`place` ( `name`, `isBusy`) VALUES ('place2', '0');
INSERT INTO `mydb`.`place` ( `name`, `isBusy`) VALUES ('place3', '1');
INSERT INTO `mydb`.`place` ( `name`, `isBusy`) VALUES ('place4', '1');
INSERT INTO `mydb`.`place` ( `name`, `isBusy`) VALUES ('place5', '1');

INSERT INTO `mydb`.`master` ( `name`, `isWork`) VALUES ('master1', '0');
INSERT INTO `mydb`.`master` ( `name`, `isWork`) VALUES ('master2', '1');
INSERT INTO `mydb`.`master` ( `name`, `isWork`) VALUES ('master3', '0');
INSERT INTO `mydb`.`master` ( `name`, `isWork`) VALUES ('master4', '1');
INSERT INTO `mydb`.`master` ( `name`, `isWork`) VALUES ('master5', '1');

INSERT INTO `mydb`.`work` ( `nameOfService`, `price`, `idMaster`) VALUES ('work1', '11.5',1);
INSERT INTO `mydb`.`work` ( `nameOfService`, `price`, `idMaster`) VALUES ('work2', '15.5',1);
INSERT INTO `mydb`.`work` ( `nameOfService`, `price`, `idMaster`) VALUES ('work3', '14.3',2);
INSERT INTO `mydb`.`work` ( `nameOfService`, `price`, `idMaster`) VALUES ('work4', '85.2',2);
INSERT INTO `mydb`.`work` ( `nameOfService`, `price`, `idMaster`) VALUES ('work5', '12.5',3);
INSERT INTO `mydb`.`work` ( `nameOfService`, `price`, `idMaster`) VALUES ('work6', '60.0',4);
INSERT INTO `mydb`.`work` ( `nameOfService`, `price`, `idMaster`) VALUES ('work7', '6.7',1);
INSERT INTO `mydb`.`work` ( `nameOfService`, `price`, `idMaster`) VALUES ('work8', '2.3',2);

INSERT INTO `mydb`.`order` ( `idService`, `idMaster`, `idPlace`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES ('4', '1', '3', 'Opened', '2017.01.30', '2017.01.30', '2017.01.30');
INSERT INTO `mydb`.`order` ( `idService`, `idMaster`, `idPlace`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES ('3', '2', '2', 'Broned', '2017.01.30', '2017.01.30', '2017.01.30');
INSERT INTO `mydb`.`order` ( `idService`, `idMaster`, `idPlace`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES ('1', '4', '4', 'Opened', '2017.01.25', '2017.01.26', '2017.01.18');

commit;
