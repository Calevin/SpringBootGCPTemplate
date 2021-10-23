--docker run --name dockerspringdb -e "MYSQL_ROOT_PASSWORD=pass" -e "MYSQL_DATABASE=springdb" -p 3306:3306 -d mariadb --log-bin --binlog-format=MIXED
drop table if exists entity_example;
drop table if exists category_example;

create table category_example (
    id int primary key auto_increment,
    name varchar(30) not null,
    description varchar(200)
);

create table entity_example (
    id int primary key auto_increment,
    name varchar(30) not null,
    description varchar(200),
    category_example_id int not null,
    foreign key (category_example_id) references category_example(id)
);

create table user_roles (
    user_id int,
    roles varchar(30) not null
);

create table users (
    id int primary key auto_increment,
    username varchar(30) not null,
    password varchar(200),
    creation_date DATETIME,
    last_date_password_modification DATETIME
);
