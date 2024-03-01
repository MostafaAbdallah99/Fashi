-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile

create table customer (
                          customer_id int primary key AUTO_INCREMENT,
                          customer_name VARCHAR(255) NOT NULL,
                          birthday date NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          job VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          credit_limit DECIMAL(15, 2) DEFAULT 0,
                          city VARCHAR(255) NOT NULL,
                          country VARCHAR(255) NOT NULL,
                          street_no VARCHAR(255) NOT NULL,
                          street_name VARCHAR(255) NOT NULL,
                          interests VARCHAR(255) NOT NULL
);

create table category (
                          category_id int primary key auto_increment,
                          category_name varchar(255) not null
);

create table tag (
                     tag_id int primary key auto_increment,
                     tag_name varchar(255) not null
);

create table product (
                         product_id int primary key auto_increment,
                         product_name varchar(255) not null,
                         product_image blob not null,
                         stock_quantity int not null default 0,
                         product_description varchar(255),
                         product_price decimal(15, 2) not null,
                         category_id int not null,
                         tag_id int not null,
                         foreign key (category_id) references category(category_id),
                         foreign key (tag_id) references tag(tag_id)
);

create table cart (
                      cart_id int primary key auto_increment,
                      customer_id int not null,
                      foreign key (customer_id) references customer(customer_id)
);

create table cart_item (
                           cart_item_id int primary key auto_increment,
                           cart_id int not null,
                           product_id int not null,
                           quantity int not null,
                           amount decimal(15, 2) not null,
                           foreign key (cart_id) references cart(cart_id),
                           foreign key (product_id) references product(product_id)
);

create table orders (
                        order_id int primary key auto_increment,
                        customer_id int not null,
                        ordered_at timestamp not null default current_timestamp,
                        foreign key (customer_id) references customer(customer_id)
);

create table orders_items (
                              order_item_id int primary key auto_increment,
                              order_id int not null,
                              product_id int not null,
                              quantity int not null,
                              amount decimal(15, 2) not null,
                              foreign key (order_id) references orders(order_id),
                              foreign key (product_id) references product(product_id)
);