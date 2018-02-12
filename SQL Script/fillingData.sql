INSERT INTO `mydb`.`place` (`id`, `name`, `isBusy`) VALUES ('0', 'place1', '0');
INSERT INTO `mydb`.`place` (`id`, `name`, `isBusy`) VALUES ('1', 'place2', '0');
INSERT INTO `mydb`.`place` (`id`, `name`, `isBusy`) VALUES ('2', 'place3', '1');
INSERT INTO `mydb`.`place` (`id`, `name`, `isBusy`) VALUES ('3', 'place4', '1');
INSERT INTO `mydb`.`place` (`id`, `name`, `isBusy`) VALUES ('4', 'place5', '1');

INSERT INTO `mydb`.`master` (`id`, `name`, `isWork`) VALUES ('0', 'master1', '0');
INSERT INTO `mydb`.`master` (`id`, `name`, `isWork`) VALUES ('1', 'master2', '1');
INSERT INTO `mydb`.`master` (`id`, `name`, `isWork`) VALUES ('2', 'master3', '0');
INSERT INTO `mydb`.`master` (`id`, `name`, `isWork`) VALUES ('3', 'master4', '1');
INSERT INTO `mydb`.`master` (`id`, `name`, `isWork`) VALUES ('4', 'master5', '4');

INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ('0', 'work1', '11.5',1);
INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ('1', 'work2', '15.5',1);
INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ('2', 'work3', '14.3',2);
INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ('3', 'work4', '85.2',2);
INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ('4', 'work5', '12.5',3);
INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ('5', 'work6', '60',4);
INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ('6', 'work7', '6.7',0);
INSERT INTO `mydb`.`work` (`id`, `nameOfService`, `price`, `idMaster`) VALUES ('7', 'work8', '2.3',0);

INSERT INTO `mydb`.`order` (`id`, `idService`, `idMaster`, `idPlace`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES ('1', '4', '1', '3', 'Opened', '2017.01.30', '2017.01.30', '2017.01.30');
INSERT INTO `mydb`.`order` (`id`, `idService`, `idMaster`, `idPlace`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES ('2', '3', '2', '2', 'Broned', '2017.01.30', '2017.01.30', '2017.01.30');
INSERT INTO `mydb`.`order` (`id`, `idService`, `idMaster`, `idPlace`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES ('3', '1', '4', '4', 'Opened', '2017.01.25', '2017.01.26', '2017.01.18');

commit;
