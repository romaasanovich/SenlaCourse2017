CREATE SCHEMA `test`;
insert into `test`.`product`(maker,model,type) values('1corp','laptop1','laptop');
insert into `test`.`product`(maker,model,type) values('1corp','laptop2','laptop');
insert into `test`.`product`(maker,model,type) values('1corp','laptop3','laptop');
insert into `test`.`product`(maker,model,type) values('1corp','laptop4','laptop');

insert into `test`.`product`(maker,model,type) values('2corp','pc1','pc');
insert into `test`.`product`(maker,model,type) values('2corp','pc2','pc');
insert into `test`.`product`(maker,model,type) values('2corp','pc3','pc');
insert into `test`.`product`(maker,model,type) values('2corp','pc4','pc');

insert into `test`.`product`(maker,model,type) values('4corp','printer1','printer');
insert into `test`.`product`(maker,model,type) values('4corp','printer2','printer');
insert into `test`.`product`(maker,model,type) values('4corp','printer3','printer');
insert into `test`.`product`(maker,model,type) values('4corp','printer4','printer');

insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(1,'pc1',25,4,500,'4x',400);
insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(2,'pc2',147,16,2000,'8x',1500);
insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(3,'pc3',74,8,1000,'24x',320);
insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(4,'pc4',42,2,250,'12x',200);

insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(1,'laptop1',25,4,500,400,17);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(2,'laptop2',18,16,200,1000,15.6);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(3,'laptop3',32,8,125,320,13);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(4,'lap\top4',65,2,250,200,13);

insert into `test`.`printer`(code,model,color,type,price) values(1,'printer1','y','Laser',300);
insert into `test`.`printer`(code,model,color,type,price) values(1,'printer2','','Jet',150);
insert into `test`.`printer`(code,model,color,type,price) values(1,'printer3','','Laser',200);
insert into `test`.`printer`(code,model,color,type,price) values(1,'printer4','y','Matrix',400);
commit;