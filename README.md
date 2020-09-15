# topjava 20 graduation project
project hasn't finnished, but work with all tasks.\
for current moment doesn't added  cache
## Authentication
there are 1 admin and 11 ordinary hardcoded users\
Admin  -  admin\
User - password\
User[1-10] - password
## Main
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
GET /rest/admin/votes\
Menu\
GET /rest/admin/menus\
User\
GET /rest/admin/users
## Update
Exception handling added\
validation and curl examples added