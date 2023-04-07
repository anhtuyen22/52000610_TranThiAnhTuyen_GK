create database TranTuyen;
use TranTuyen;
# drop database TranTuyen;
create table `orders`
(
    id           bigint primary key auto_increment,
    `status`     varchar(50)  not null,
    quantity     int          not null,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    company_name varchar(255) null,
    country      varchar(255)  null,
    address      varchar(255)  null,
    phone        varchar(255)  null,
    email        varchar(255) not null,
    price       decimal(10, 2) not null,
    created_at   date         not null,
    customer_id  bigint       not null
);
create table cart
(
    id          bigint primary key auto_increment,
    quantity    int    not null,
    customer_id bigint not null,
    product_id  bigint not null
);
create table customer
(
    id       bigint primary key auto_increment,
    name     varchar(255) not null,
    username varchar(255) not null,
    password varchar(64)  not null,
    avatar   varchar(500) null
);
create table order_detail
(
    id         bigint primary key auto_increment,
    quantity   int    not null,
    order_id   bigint not null,
    product_id bigint not null
);
create table product
(
    id          bigint primary key auto_increment,
    name        varchar(255)   not null,
    price       decimal(10, 2) not null,
    color       varchar(255)   not null,
    brand       varchar(255)   not null,
    image       varchar(255)   null,
    description varchar(1000)  null,
    category_id bigint         not null
);
create table category
(
    id   bigint primary key auto_increment,
    name varchar(255) not null
);
alter table `orders`
    add foreign key (customer_id) references customer (id);
alter table cart
    add foreign key (customer_id) references customer (id);
alter table cart
    add foreign key (product_id) references product (id);
alter table order_detail
    add foreign key (order_id) references `orders` (id);
alter table order_detail
    add foreign key (product_id) references product (id);
alter table product
    add foreign key (category_id) references category (id);

insert into category(name)
values ('Hamburger'),
       ('Cake'),
       ('Pizza');
insert into product(`name`, price, image, color, category_id, brand, `description`)
values ('SHRIMP HAMBURGER', 15, 'shop-shrimp-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('CHICKEN HAMBURGER', 18, 'shop-chicken-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('BEEF HAMBURGER', 20, 'shop-beef-burger.png', 'RED', 1, 'Lotteria', null),
       ('Big Mac Cheeseburger', 18, 'shop-big-mac-cheese-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Double Buffalo burger', 16, 'shop-double-buffalo-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Double Chicken burger', 21, 'shop-double-chicken-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Fried Fish burger', 17, 'shop-fried-fish-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Cheese Chicken burger', 13, 'shop-chese-chicken-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Bacon burger', 22, 'shop-bacon-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Bacon cheeseburger', 24, 'shop-bacon-chese-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Jumpo Chicken burger', 17, 'shop-jumpo-chicken-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Cheeseburger', 15, 'shop-chese-burger.png', 'RED', 1, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),

       ('Chocolate Cupcake', 26, 'shop-chocolate-cup-cake.png', 'RED', 2, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Red Velvet Cake', 30, 'shop-red-velvet-cake.png', 'RED', 2, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Chocolate Muffin Cake', 27, 'shop-chocolate-muffin-cake.png', 'RED', 2, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Chocolate Cake', 19, 'shop-chocolate-cake.png', 'RED', 2, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Peanut Chocolate Cake', 10, 'shop-peanut-chocolate-cake.png', 'RED', 2, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Strawberry Cake', 19, 'shop-strawberry-cake.png', 'RED', 2, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Mint Ice Cream Cake', 25, 'shop-mint-ice-cream-cake.png', 'RED', 2, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),

       ('Summer Pizza', 22, 'shop-summer-pizza.png', 'RED', 3, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Primo Meat Pizza', 16, 'shop-primo-meat-pizza.png', 'RED', 3, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Pepperoni Pizza', 19, 'shop-pepperoni-pizza.png', 'RED', 3, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! '),
       ('Italiano Original Pizza', 18, 'shop-italiano-original-pizza.png', 'RED', 3, 'KFC',
        'Testing description for product .......... ..................... ......................... ....................... ................ end ! ');
