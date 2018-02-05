#1
select model,speed,hd from `test`.`pc`
where price<500;

#2
select maker from `test`.`product`
where type='printer';

#3
select model,ram,screen from `test`.`laptop`
where price>1000;

#4
select * from `test`.`printer`
where color='y';

#5
select model,speed,hd from `test`.`pc`
where ((cd='12x' or cd='24x') and price<600);

#6
SELECT distinct
   p.maker, l.speed
FROM
    laptop l
        JOIN
    product p ON p.model = l.model
WHERE
    l.hd >= 10;

#7
SELECT DISTINCT product.model, pc.price 
FROM Product JOIN pc ON product.model = pc.model WHERE maker = '1corp' 
UNION 
SELECT DISTINCT product.model, laptop.price 
FROM product JOIN laptop ON product.model=laptop.model WHERE maker='1corp' 
UNION 
SELECT DISTINCT product.model, printer.price 
FROM product JOIN printer ON product.model=printer.model WHERE maker='1corp';

#8
SELECT DISTINCT
    maker
FROM
    product
WHERE
    maker NOT IN (SELECT 
            maker
        FROM
            product
        WHERE
            type = 'Laptop')
        AND type = 'PC';
        
#9
SELECT DISTINCT product.maker 
FROM pc 
JOIN product ON pc.model = product.model 
WHERE pc.speed >= 450;												


#10
SELECT model, price 
FROM printer 
WHERE price = 
(SELECT MAX(price) 
FROM printer );

#11
SELECT AVG(speed) FROM pc;

#12
SELECT AVG(speed) 
FROM laptop 
WHERE price > 1000;

#13
SELECT AVG(pc.speed) 
FROM pc, product 
WHERE pc.model = product.model AND product.maker = '2corp';

#14
SELECT 
    speed 'Speed', AVG(price) 'Price'
FROM
    pc
GROUP BY speed;

#15
SELECT hd FROM pc GROUP BY (hd) HAVING COUNT(model) >= 2;

#16
SELECT DISTINCT
    p1.model, p2.model, p1.ram, p1.speed
FROM
    pc p1, pc p2
WHERE
    p1.speed = p2.speed AND p1.ram = p2.ram AND p1.model > p2.model;
    
#17 	
select distinct p.type,p.model,l.speed  
from laptop l join product p on l.model=p.model
where l.speed <(select min(speed) from pc);

#18
SELECT DISTINCT product.maker, printer.price 
FROM product, printer 
WHERE product.model = printer.model 
AND printer.color = 'y' 
AND printer.price = ( 
SELECT MIN(price) FROM printer 
WHERE printer.color = 'y' 
) ;

#19
SELECT DISTINCT product.maker, Avg(screen)
from laptop
left join product on product.model = laptop.model
group by product.maker;

#20
SELECT 
    product.maker, COUNT(model)
FROM
    product
WHERE
    type = 'pc'
GROUP BY product.maker
HAVING COUNT(DISTINCT model) >= 3;

#21 
select product.maker,max(pc.price)
from product, pc 
where pc.model =product.model
group by product.maker;

#22 
select pc.speed, avg(pc.price)
from pc 
where pc.speed>600
group by pc.speed;

#23
SELECT DISTINCT
    maker
FROM
    product t1
        JOIN
    pc t2 ON t1.model = t2.model
WHERE
    speed >= 750
        AND maker IN (SELECT 
            maker
        FROM
            product t1
                JOIN
            laptop t2 ON t1.model = t2.model
        WHERE
            speed >= 750);

#24
SELECT model 
FROM ( 
 SELECT model, price 
 FROM pc 
 UNION 
 SELECT model, price 
 FROM Laptop 
 UNION 
 SELECT model, price 
 FROM Printer 
) t1 
WHERE price = ( 
 SELECT MAX(price) 
 FROM ( 
  SELECT price 
  FROM pc 
  UNION 
  SELECT price 
  FROM Laptop 
  UNION 
  SELECT price 
  FROM Printer 
  ) t2 
 )	;


#25
SELECT DISTINCT maker 
FROM product 
WHERE model IN ( 
SELECT model 
FROM pc 
WHERE ram = ( 
  SELECT MIN(ram) 
  FROM pc 
  ) 
AND speed = ( 
  SELECT MAX(speed) 
  FROM pc 
  WHERE ram = ( 
   SELECT MIN(ram) 
   FROM pc 
   ) 
  ) 
) 
AND 
maker IN ( 
SELECT maker 
FROM product 
WHERE type='printer' 
)
