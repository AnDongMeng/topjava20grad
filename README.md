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
POST /rest/admin/restaurant\
{\
"id": id\
"name": restaurant name\
 }
update:\
PUT /rest/admin/restaurant\
{\
"id": id\
"name": restaurant name\
 }
delete:\
DELETE /rest/admin/restaurant/{id}\

Authenticated users can get restaurants
get:\
GET /rest/restaurant/{id}\
get with menu:\
GET /rest/restaurant/{id}/with-menu\
GET /rest/restaurant/100012/with-menu\

get all:\
GET /rest/restaurant\
get all with menu:\
GET /rest/restaurant/with-menu\
### Menu
Admin can create and delete today's menu food\
create:\
POST /rest/admin/restaurant/{restaurant-id}/food/\
{\
"name": food name\
"price": food price\
 }
delete:\
DELETE /rest/admin/restaurant/food/{id}\
### Vote
users can vote for preferred restaurnt\
POST /rest/restaurant/{restaurant-id}/vote/\
## Testing
For testing current date and time gets from util's class DateTimeUtil where you can 
set LocalDateTime mockDateTime parameter and boolean useRealTime parameter\
Also there is an ability to get votes,food and menu by admin\
Votes\
GET /rest/admin/data/votes\
Menu\
GET /rest/admin/data/menus\
User\
GET /rest/admin/data/users
## Update
Exception handling added\
validation and curl examples added
