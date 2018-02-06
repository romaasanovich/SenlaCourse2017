insert into `test`.`product`(maker,model,type) values('1corp','laptop1','laptop');
insert into `test`.`product`(maker,model,type) values('1corp','laptop2','laptop');
insert into `test`.`product`(maker,model,type) values('1corp','laptop3','laptop');
insert into `test`.`product`(maker,model,type) values('1corp','laptop4','laptop');
insert into `test`.`product`(maker,model,type) values('1corp','laptop5','laptop');
insert into `test`.`product`(maker,model,type) values('6corp','laptop6','laptop');
insert into `test`.`product`(maker,model,type) values('1corp','laptop7','laptop');
insert into `test`.`product`(maker,model,type) values('5corp','laptop8','laptop');

insert into `test`.`product`(maker,model,type) values('5corp','pc1','pc');
insert into `test`.`product`(maker,model,type) values('2corp','pc2','pc');
insert into `test`.`product`(maker,model,type) values('2corp','pc3','pc');
insert into `test`.`product`(maker,model,type) values('2corp','pc4','pc');
insert into `test`.`product`(maker,model,type) values('1corp','pc5','pc');
insert into `test`.`product`(maker,model,type) values('3corp','pc6','pc');
insert into `test`.`product`(maker,model,type) values('1corp','pc7','pc');

insert into `test`.`product`(maker,model,type) values('4corp','printer1','printer');
insert into `test`.`product`(maker,model,type) values('4corp','printer2','printer');
insert into `test`.`product`(maker,model,type) values('4corp','printer3','printer');
insert into `test`.`product`(maker,model,type) values('4corp','printer4','printer');
insert into `test`.`product`(maker,model,type) values('5corp','printer6','printer');
insert into `test`.`product`(maker,model,type) values('5corp','printer7','printer');

insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(1,'pc1',25,1,500,'4x',400);
insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(2,'pc2',147,16,2000,'8x',1500);
insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(3,'pc3',800,8,1000,'24x',320);
insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(4,'pc4',35,2,250,'12x',800);
insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(5,'pc5',15,4,250,'1x',200);
insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(6,'pc6',98,2,357,'8x',800);
insert into `test`.`pc`(code,model,speed,ram,hd,cd,price) values(7,'pc7',1000,8,1024,'24x',1500);

insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(1,'laptop1',1500,4,500,400,17);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(2,'laptop2',10,16,200,1000,15.6);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(3,'laptop3',32,8,125,320,13);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(4,'laptop4',65,2,250,200,13);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(5,'laptop5',65,2,250,200,13);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(6,'laptop6',65,2,250,200,13);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(7,'laptop7',65,2,250,200,13);
insert into `test`.`laptop`(code,model,speed,ram,hd,price,screen) values(8,'laptop8',65,2,250,200,13);

insert into `test`.`printer`(code,model,color,type,price) values(1,'printer1','y','Laser',300);
insert into `test`.`printer`(code,model,color,type,price) values(2,'printer2','','Jet',150);
insert into `test`.`printer`(code,model,color,type,price) values(3,'printer3','','Laser',200);
insert into `test`.`printer`(code,model,color,type,price) values(4,'printer4','y','Matrix',400);
insert into `test`.`printer`(code,model,color,type,price) values(5,'printer5','','Laser',500);
insert into `test`.`printer`(code,model,color,type,price) values(6,'printer6','y','Matrix',700);
insert into `test`.`printer`(code,model,color,type,price) values(7,'printer7','','Jet',300);
commit;