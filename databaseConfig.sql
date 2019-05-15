create schema fruitdb;
use fruitdb;
create table player (
id int primary key not null auto_increment,
nickname varchar(50) not null,
password varchar(50) not null,
name varchar(35) not null,
surname varchar(35) not null,
email varchar(50) not null
);
create table board(
id int not null primary key auto_increment,
playerid int not null,
score int not null,
pDate date not null,
duration int not null,
foreign key (playerid) references player(id)
);