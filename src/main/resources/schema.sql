-- テーブル削除
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS categories;
-- カテゴリーテーブル
CREATE TABLE categories(
id SERIAL PRIMARY KEY,
name TEXT
);

-- 商品テーブル
CREATE TABLE items(
id SERIAL PRIMARY KEY,
category_id INTEGER NOT NULL,
name TEXT NOT NULL,
price INTEGER NOT NULL,
origin TEXT,
feature TEXT,
ranking INTEGER,
image_path TEXT,
FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- 顧客テーブル
CREATE TABLE customers(
id SERIAL PRIMARY KEY,
name TEXT,
address TEXT,
tel TEXT,
email TEXT UNIQUE,
password TEXT,
icon_path TEXT
);

-- 注文テーブル
CREATE TABLE orders(
id SERIAL PRIMARY KEY,
customer_id INTEGER,
ordered_on DATE,
total_price INTEGER,
delivery_address TEXT,
delivery_tel TEXT,
FOREIGN KEY (customer_id) REFERENCES customers(id)
);


-- 注文詳細テーブル
CREATE TABLE order_details(
id SERIAL PRIMARY KEY,
order_id INTEGER,
item_id INTEGER,
quantity INTEGER,
FOREIGN KEY (order_id) REFERENCES orders(id),
FOREIGN KEY (item_id) REFERENCES items(id)
);