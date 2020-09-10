DELETE
FROM votes;
DELETE
FROM food;
DELETE
FROM restaurants;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (NAME, PASSWORD)
VALUES ('Admin', 'admin'),
       ('User', 'password'),
       ('User1', 'password'),
       ('User2', 'password'),
       ('User3', 'password'),
       ('User4', 'password'),
       ('User5', 'password'),
       ('User6', 'password'),
       ('User7', 'password'),
       ('User8', 'password'),
       ('User9', 'password'),
       ('User10', 'password');

INSERT INTO USER_ROLES (USER_ID, ROLE)
VALUES ('100000', 'ADMIN'),
       ('100001', 'USER'),
       ('100002', 'USER'),
       ('100003', 'USER'),
       ('100004', 'USER'),
       ('100005', 'USER'),
       ('100006', 'USER'),
       ('100007', 'USER'),
       ('100008', 'USER'),
       ('100009', 'USER'),
       ('100010', 'USER'),
       ('100011', 'USER');

INSERT INTO restaurants (NAME)
VALUES ('restorano'),
       ('caffeeterio'),
       ('romano'),
       ('petros'),
       ('ivano'),
       ('divano'),
       ('lomano'),
       ('grigorio'),
       ('alehandro'),
       ('condrato'),
       ('borshtchi u ruslana');

INSERT INTO food (NAME, PRICE, DATE, RESTAURANT_ID)
VALUES ('pasta', 300, '2020-01-30', 100012),
       ('antipasto', 1400, '2020-01-30', 100012),
       ('borstch', 500, '2020-01-30', 100013),
       ('antiborstch', 2400, '2020-01-30', 100013),
       ('vine', 200, '2020-01-30', 100014),
       ('ne pasta', 100, '2020-01-30', 100014),
       ('soup', 100, '2020-01-30', 100015),
       ('ne risotto', 1700, '2020-01-30', 100015),
       ('potato', 200, '2020-01-30', 100016),
       ('carrot', 100, '2020-01-30', 100016),
       ('whiskey', 700, '2020-01-30', 100017),
       ('mayonnaise', 300, '2020-01-30', 100018),
       ('sushi', 400, '2020-01-30', 100018),
       ('apple', 050, '2020-01-30', 100018),
       ('pancake', 200, '2020-01-30', 100019),
       ('beef', 400, '2020-01-30', 100019),
       ('pork', 200, '2020-01-30', 100020),
       ('chicken', 900, '2020-01-30', 100020),
       ('sushi', 500, '2020-01-30', 100021),
       ('sausage', 800, '2020-01-30', 100021),
       ('bread', 700, '2020-01-30', 100021),
       ('onion', 700, '2020-01-30', 100021);
INSERT INTO food (NAME, PRICE, RESTAURANT_ID)
VALUES ('chef dish', 900, 100012),
       ('potato', 6600, 100012),
       ('fries', 700, 100013),
       ('crisps', 400, 100013),
       ('bread', 300, 100014),
       ('cake', 300, 100014),
       ('vine', 200, 100015),
       ('watter', 500, 100015),
       ('latte', 100, 100016),
       ('pancakes', 600, 100016),
       ('sandwich', 1200, 100017),
       ('pasta', 1400, 100017),
       ('antipasto', 1400, 100017),
       ('beef', 400, 100017),
       ('sausage', 800, 100017),
       ('soup carrot', 2300, 100018),
       ('ne sup', 1400, 100018);

INSERT INTO votes (DATE, USER_ID, RESTAURANT_ID)
VALUES ('2020-01-30', 100000, 100012),
       ('2020-01-30', 100001, 100012),
       ('2020-01-31', 100002, 100014),
       ('2020-01-30', 100003, 100012),
       ('2020-01-30', 100004, 100012),
       ('2020-01-30', 100005, 100012),
       ('2020-01-30', 100007, 100015),
       ('2020-01-30', 100008, 100016),
       ('2020-01-30', 100009, 100014),
       ('2020-01-30', 100010, 100018),
       ('2020-01-30', 100011, 100012);
INSERT INTO votes (USER_ID, RESTAURANT_ID)
VALUES (100005, 100014),
       (100004, 100016),
       (100007, 100017),
       (100008, 100012),
       (100009, 100012),
       (100010, 100012);
