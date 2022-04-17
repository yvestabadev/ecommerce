CREATE TABLE orders (
payment_id VARCHAR(127) PRIMARY KEY,
user_email VARCHAR(127),
user_name VARCHAR(127),
coupon_id VARCHAR(127),
status VARCHAR(31),
delivery_zipcode VARCHAR(127) NOT NULL,
delivery_city VARCHAR(127) NOT NULL,
delivery_state VARCHAR(127) NOT NULL,
delivery_number VARCHAR(15) NOT NULL,
delivery_additional_info VARCHAR(255),
product_id BIGINT,
FOREIGN KEY (product_id) REFERENCES product (id)
);