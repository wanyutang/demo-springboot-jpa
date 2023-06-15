-- DROP SCHEMA dbo;

CREATE SCHEMA dbo;
-- hncb.dbo.API_FUNCTION definition

-- Drop table

-- DROP TABLE hncb.dbo.API_FUNCTION;

CREATE TABLE API_FUNCTION (
	API_UID varchar(8) COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL,
	API_NAME varchar(30) COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL,
	API_STATUS char(1) COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL,
	API_DESC varchar(300) COLLATE Chinese_Taiwan_Stroke_CI_AS NULL,
	CREATE_TIME datetime NOT NULL,
	UPDATE_TIME datetime NULL,
	CREATE_USER varchar(20) COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL,
	UPDATE_USER varchar(20) COLLATE Chinese_Taiwan_Stroke_CI_AS NULL,
	CONSTRAINT PUSH_API_PK PRIMARY KEY (API_UID)
);


-- hncb.dbo.Products definition

-- Drop table

-- DROP TABLE hncb.dbo.Products;

CREATE TABLE Products (
	product_id int NOT NULL,
	product_name varchar(100) COLLATE Chinese_Taiwan_Stroke_CI_AS NULL,
	description varchar(255) COLLATE Chinese_Taiwan_Stroke_CI_AS NULL,
	price decimal(10,2) NULL,
	CONSTRAINT PK__Products__47027DF501B7D753 PRIMARY KEY (product_id)
);


-- hncb.dbo.Users definition

-- Drop table

-- DROP TABLE hncb.dbo.Users;

CREATE TABLE Users (
	user_id int NOT NULL,
	username varchar(50) COLLATE Chinese_Taiwan_Stroke_CI_AS NULL,
	email varchar(100) COLLATE Chinese_Taiwan_Stroke_CI_AS NULL,
	password varchar(100) COLLATE Chinese_Taiwan_Stroke_CI_AS NULL,
	CONSTRAINT PK__Users__B9BE370F0382DC86 PRIMARY KEY (user_id)
);


-- hncb.dbo.Shopping_Carts definition

-- Drop table

-- DROP TABLE hncb.dbo.Shopping_Carts;

CREATE TABLE Shopping_Carts (
	cart_id int NOT NULL,
	user_id int NULL,
	created_at datetime NULL,
	CONSTRAINT PK__Shopping__2EF52A27F3A89236 PRIMARY KEY (cart_id),
	CONSTRAINT FK__ShoppingC__user___34C8D9D1 FOREIGN KEY (user_id) REFERENCES Users(user_id)
);


-- hncb.dbo.Cart_Items definition

-- Drop table

-- DROP TABLE hncb.dbo.Cart_Items;

CREATE TABLE Cart_Items (
	item_id int NOT NULL,
	cart_id int NULL,
	product_id int NULL,
	quantity int NULL,
	CONSTRAINT PK__CartItem__52020FDDE38CCFF4 PRIMARY KEY (item_id),
	CONSTRAINT FK__CartItems__cart___37A5467C FOREIGN KEY (cart_id) REFERENCES Shopping_Carts(cart_id),
	CONSTRAINT FK__CartItems__produ__38996AB5 FOREIGN KEY (product_id) REFERENCES Products(product_id)
);
