insert into USERS(ID, USERNAME, PASSWORD) values (1, 'nicu', 'nicu_parola');
insert into USERS(ID, USERNAME, PASSWORD) values (2, 'dana', 'dana_parola');
insert into USERS(ID, USERNAME, PASSWORD) values (3, 'mariuscoman', 'mariuscoman_parola');
insert into USERS(ID, USERNAME, PASSWORD) values (4, 'ioana', 'ioanas_parola');
insert into USERS(ID, USERNAME, PASSWORD) values (5, 'user', 'parola');

insert into USERROLE(ID, ROLE, USERID) values (1, 'USER', 1);
insert into USERROLE(ID, ROLE, USERID) values (2, 'USER', 2);
insert into USERROLE(ID, ROLE, USERID) values (3, 'USER', 3);
insert into USERROLE(ID, ROLE, USERID) values (4, 'USER', 4);
insert into USERROLE(ID, ROLE, USERID) values (5, 'USER', 5);

insert into CUSTOMER(ID, FIRSTNAME, LASTNAME, USERID) values (1, 'Nicolae', 'Ionescu', 1);
insert into CUSTOMER(ID, FIRSTNAME, LASTNAME, USERID) values (2, 'Daniela', 'Popescu', 2);
insert into CUSTOMER(ID, FIRSTNAME, LASTNAME, USERID) values (3, 'Marius', 'Coman', 3);
insert into CUSTOMER(ID, FIRSTNAME, LASTNAME, USERID) values (4, 'Ioana', 'Sorescu', 4);
insert into CUSTOMER(ID, FIRSTNAME, LASTNAME, USERID) values (5, 'User', 'User', 5);

insert into ADDRESS(ID, COUNTRY, CITY, COUNTY, STREET) values(1,'Romania', 'Cluj-Napoca', 'Cluj', 'Brassay 9');
insert into ADDRESS(ID, COUNTRY, CITY, COUNTY, STREET) values(2,'Romania', 'Cluj-Napoca', 'Cluj', 'Eroilor 3');
insert into ADDRESS(ID, COUNTRY, CITY, COUNTY, STREET) values(3,'Romania', 'Bucuresti', 'Bucuresti', 'Unirii 25');
insert into ADDRESS(ID, COUNTRY, CITY, COUNTY, STREET) values(4,'Romania', 'Constanta', 'Constanta', 'Mamaia 17');
insert into ADDRESS(ID, COUNTRY, CITY, COUNTY, STREET) values(5,'Germania', 'Passau', 'Bayer', 'Dr.-Hans-Kapfinger-Straße 30');
insert into ADDRESS(ID, COUNTRY, CITY, COUNTY, STREET) values(6,'Romania', 'Floresti', 'Cluj', 'Avram Iancu 30');
insert into ADDRESS(ID, COUNTRY, CITY, COUNTY, STREET) values(7,'Romania', 'Timisoara', 'Timis', 'Victoriei 1');
insert into ADDRESS(ID, COUNTRY, CITY, COUNTY, STREET) values(8,'Romania', 'Bucuresti', 'Bucuresti', 'Militari 15');


insert into PRODUCTCATEGORY(ID, NAME, DESCRIPTION) values (1, 'IT', 'Laptop-uri, Desktop, Monitoare, Gaming, Periferice');
insert into PRODUCTCATEGORY(ID, NAME, DESCRIPTION) values (2, 'Telefon', 'Telefoane, Tablete');
insert into PRODUCTCATEGORY(ID, NAME, DESCRIPTION) values (3, 'Electronice', 'Televizoare, Audio, Foto');
insert into PRODUCTCATEGORY(ID, NAME, DESCRIPTION) values (4, 'Electrocasnice', 'Electrocasnice mici, Electrocasnice mari');
insert into PRODUCTCATEGORY(ID, NAME, DESCRIPTION) values (5, 'Sport', 'Sporturi de exterior, Sporturi de interior, Articole sportive');


insert into SUPPLIER(ID, NAME) values (1, 'Media Galaxy');
insert into SUPPLIER(ID, NAME) values (2, 'Flanco');
insert into SUPPLIER(ID, NAME) values (3, 'Altex');
insert into SUPPLIER(ID, NAME) values (4, 'Intersport');
insert into SUPPLIER(ID, NAME) values (5, 'Decathlon');

insert into LOCATION(ID, NAME, ADDRESSID) values (1, 'Depozit Cluj', 6);
insert into LOCATION(ID, NAME, ADDRESSID) values (2, 'Depozit Bucuresti', 8);
insert into LOCATION(ID, NAME, ADDRESSID) values (3, 'Depozit Timisoara', 7);

insert into PRODUCT(ID, NAME, DESCRIPTION, PRICE, WEIGHT, PRODUCTCATEGORYID, SUPPLIERID) values (1, 'Laptop ASUS A541UV-XX1237', 'Laptop ASUS A541UV-XX1237 cu procesor Intel® Core™ i3-6006U 2.00 GHz, Skylake, 15.6", 4GB, 500GB, NVIDIA® GeForce® 920MX 2GB, Endless OS, Chocolate Black',
    1599.99, 2.0, 1, 1);
insert into PRODUCT(ID, NAME, DESCRIPTION, PRICE, WEIGHT, PRODUCTCATEGORYID, SUPPLIERID) values (2, 'Laptop HP ENVY 13-ad004nn', 'Laptop HP ENVY 13-ad004nn cu procesor Intel® Core™ i5-7200U pana la 3.10 GHz, Kaby Lake, 13.3", Full HD, IPS, 8GB, 256GB, Intel® HD Graphics, Microsoft Windows 10, Silver',
    3999.99, 1.34, 1, 2);
insert into PRODUCT(ID, NAME, DESCRIPTION, PRICE, WEIGHT, PRODUCTCATEGORYID, SUPPLIERID) values (3, 'Telefon Apple iPhone X', 'Telefon mobil Apple iPhone X, 64GB, 4G, Space Grey',
    5499.99, 0.24, 2, 3);
insert into PRODUCT(ID, NAME, DESCRIPTION, PRICE, WEIGHT, PRODUCTCATEGORYID, SUPPLIERID) values (4, 'Telefon Samsung Galaxy Note 8', 'Telefon mobil Samsung Galaxy Note 8, Dual SIM, 64GB, 4G, Midnight Black',
    3749.99, 0.30, 2, 2);
insert into PRODUCT(ID, NAME, DESCRIPTION, PRICE, WEIGHT, PRODUCTCATEGORYID, SUPPLIERID) values (5, 'Televizor LED Smart Samsung 50MU6102', 'Televizor LED Smart Samsung, 125 cm, 50MU6102, 4K Ultra HD',
    2299.99, 12.2, 3, 1);
insert into PRODUCT(ID, NAME, DESCRIPTION, PRICE, WEIGHT, PRODUCTCATEGORYID, SUPPLIERID) values (6, 'Combina frigorifica Hotpoint XH8 T4Z XOJZV', 'Combina frigorifica Hotpoint XH8 T4Z XOJZV, No frost, 340 l, Clasa A+++, H 189, Inox',
    3499.99, 32, 4, 1);
insert into PRODUCT(ID, NAME, DESCRIPTION, PRICE, WEIGHT, PRODUCTCATEGORYID, SUPPLIERID) values (7, 'Bicicleta MTB 26" Velors 2609A', 'Bicicleta MTB 26" Velors 2609A, Nergu/Verde',
    939.99, 12, 5, 4);
insert into PRODUCT(ID, NAME, DESCRIPTION, PRICE, WEIGHT, PRODUCTCATEGORYID, SUPPLIERID) values (8, 'Role/Patine Wildstar ABEC-7', 'Role/Patine Wildstar ABEC-7, Aluminiu, 39-42, Galben',
    168.99, 1.2, 5, 5);

insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (1, 1, 1, 200);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (2, 2, 1, 50);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (3, 3, 1, 0);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (4, 4, 1, 3);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (5, 5, 1, 1);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (6, 6, 1, 4);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (7, 7, 1, 6);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (8, 8, 1, 0);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (9, 1, 2, 2);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (10, 2, 2, 100);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (11, 3, 2, 15);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (12, 4, 2, 0);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (13, 5, 2, 0);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (14, 6, 2, 2);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (15, 7, 2, 1);
insert into STOCK(ID, PRODUCTID, LOCATIONID, QUANTITY) values (16, 8, 2, 7);


insert into ORDERS(ID, CUSTOMERID, LOCATIONID, ADDRESSID) values (1, 1, 1, 1);
insert into ORDERS(ID, CUSTOMERID, LOCATIONID, ADDRESSID) values (2, 1, 1, 2);
insert into ORDERS(ID, CUSTOMERID, LOCATIONID, ADDRESSID) values (3, 2, 2, 3);
insert into ORDERS(ID, CUSTOMERID, LOCATIONID, ADDRESSID) values (4, 4, 2, 4);

insert into ORDERDETAIL(ID, ORDERID, PRODUCTID, QUANTITY) values (1, 1, 1, 1);
insert into ORDERDETAIL(ID, ORDERID, PRODUCTID, QUANTITY) values (2, 2, 7, 1);
insert into ORDERDETAIL(ID, ORDERID, PRODUCTID, QUANTITY) values (3, 2, 8, 2);
insert into ORDERDETAIL(ID, ORDERID, PRODUCTID, QUANTITY) values (4, 3, 4, 1);
insert into ORDERDETAIL(ID, ORDERID, PRODUCTID, QUANTITY) values (5, 4, 5, 1);
insert into ORDERDETAIL(ID, ORDERID, PRODUCTID, QUANTITY) values (6, 4, 6, 1);