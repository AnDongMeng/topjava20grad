# topjava 20 graduation project
## Task
Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.
## Run
mvn clean package -DskipTests=true org.codehaus.cargo:cargo-maven2-plugin:1.7.10:run
## Authentication
there are 1 admin and 11 ordinary hardcoded users\
Admin  -  admin\
User - password\
User[1-10] - password
## Main
curl examples are located in curl.md
### Restaurants
Admin can create, update and delete restaurants\
create:\
POST /rest/admin/restaurants\
{\
"id": id\
"name": restaurant name\
 }
update:\
PUT /rest/admin/restaurants/{id}\
{\
"id": id\
"name": restaurant name\
 }
delete:\
DELETE /rest/admin/restaurants/{id}\

Authenticated users can get restaurants
get:\
GET /rest/restaurants/{id}\
get with menu:\
GET /rest/restaurants/{id}/with-menu\

get all:\
GET /rest/restaurants\
get all with menu:\
GET /rest/restaurants/with-menu\
### Menu
Admin can create and delete today's menu food\
create:\
POST /rest/admin/restaurants/{restaurant-id}/food/\
{\
"name": food name\
"price": food price\
 }
 update:\
 POST /rest/admin/restaurants/{restaurant-id}/food/{food-id}\
 {\
 "name": food name\
 "price": food price\
 "date": date\
  }
delete:\
DELETE /rest/admin/restaurants/{restaurant-id}/food/{id}\
### Vote
users can vote for preferred restaurnt\
POST /rest/restaurants/{restaurant-id}/vote/\
### Reote
users can change his vote before 11:00\
PUT /rest/restaurants/{restaurant-id}/vote/\
