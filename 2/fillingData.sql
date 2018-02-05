INSERT INTO `3_task`.`master` (`id`, `name`, `isWork`) VALUES ('0', 'master1', '0');
INSERT INTO `3_task`.`master` (`id`, `name`, `isWork`) VALUES ('1', 'master2', '0');
INSERT INTO `3_task`.`master` (`id`, `name`, `isWork`) VALUES ('2', 'master4', '0');
INSERT INTO `3_task`.`master` (`id`, `name`, `isWork`) VALUES ('3', 'master4', '0');
INSERT INTO `3_task`.`master` (`id`, `name`, `isWork`) VALUES ('4', 'master5', '0');

INSERT INTO `3_task`.`place` (`id`, `name`, `isBusy`) VALUES ('0', 'place1', '0');
INSERT INTO `3_task`.`place` (`id`, `name`, `isBusy`) VALUES ('1', 'place2', '0');
INSERT INTO `3_task`.`place` (`id`, `name`, `isBusy`) VALUES ('2', 'place3', '0');
INSERT INTO `3_task`.`place` (`id`, `name`, `isBusy`) VALUES ('3', 'place4', '0');
INSERT INTO `3_task`.`place` (`id`, `name`, `isBusy`) VALUES ('4', 'place5', '0');

INSERT INTO `3_task`.`work` (`id`, `nameOfService`, `price`) VALUES ('0', 'work1', '11.5');
INSERT INTO `3_task`.`work` (`id`, `nameOfService`, `price`) VALUES ('1', 'work2', '15.5');
INSERT INTO `3_task`.`work` (`id`, `nameOfService`, `price`) VALUES ('2', 'work3', '14.3');
INSERT INTO `3_task`.`work` (`id`, `nameOfService`, `price`) VALUES ('3', 'work4', '85.2');
INSERT INTO `3_task`.`work` (`id`, `nameOfService`, `price`) VALUES ('4', 'work5', '7.2');

INSERT INTO `3_task`.`order` (`id`, `id_service`, `id_master`, `id_place`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES ('1', '4', '1', '3', 'Opened', '2017.01.30', '2017.01.30', '2017.01.30');
INSERT INTO `3_task`.`order` (`id`, `id_service`, `id_master`, `id_place`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES ('2', '3', '2', '2', 'Broned', '2017.01.30', '2017.01.30', '2017.01.30');
INSERT INTO `3_task`.`order` (`id`, `id_service`, `id_master`, `id_place`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES ('3', '1', '4', '2', 'Opened', '2017.01.25', '2017.01.26', '2017.01.18');

commit;
