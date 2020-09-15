# curl samples (application deployed at application context `restaurantvoting`).
### get all Restaurants
`curl -s http://localhost:8080/restaurant_voting/rest/restaurants --user User:password`
### get all Restaurants with menu
`curl -s http://localhost:8080/restaurant_voting/rest/restaurants/with-menu --user User:password`
### get Restaurant with id 100012
`curl -s http://localhost:8080/restaurant_voting/rest/restaurants/100012 --user User:password`
### get Restaurant with id 100012 with menu
`curl -s http://localhost:8080/restaurant_voting/rest/restaurants/100012/with-menu --user User:password`
### add Restaurant
`curl -s -X POST -d '{"name":"new"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant_voting/rest/admin/restaurants --user Admin:admin`
### try to add not valid Restaurant
`curl -s -X POST -d '{"name":"n"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant_voting/rest/admin/restaurants --user Admin:admin`
### update Restaurant
`curl -s -X POST -d '{"id":"100013","name":"updated"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant_voting/rest/admin/restaurants --user Admin:admin`
### delete Restaurant with id 100014
`curl -s -X DELETE  http://localhost:8080/restaurant_voting/rest/admin/restaurants/100014 --user Admin:admin`
### try to delete not existed Restaurant
`curl -s -X DELETE  http://localhost:8080/restaurant_voting/rest/admin/restaurants/1004 --user Admin:admin`
### add Food for restaurant 100013
`curl -s -X POST -d '{"name":"new","price":1000}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant_voting/rest/admin/restaurants/100013/food --user Admin:admin`
### try to add not valid Food
`curl -s -X POST -d '{"name":"n","price":0}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant_voting/rest/admin/restaurants/100013/food --user Admin:admin`
### delete Food with id 100045
`curl -s -X DELETE  http://localhost:8080/restaurant_voting/rest/admin/restaurants/food/100045 --user Admin:admin`
### try to delete not existed Food
`curl -s -X DELETE  http://localhost:8080/restaurant_voting/rest/admin/restaurants/food/1004 --user Admin:admin`
### vote for restaurant 100013
`curl -s -X POST http://localhost:8080/restaurant_voting/rest/restaurants/100013/vote --user User:password`
### try to vote for not existed restaurant 1000
`curl -s -X POST http://localhost:8080/restaurant_voting/rest/restaurants/1000/vote --user User:password`
### vote for already voted user for restaurant 100013
`curl -s -X POST http://localhost:8080/restaurant_voting/rest/restaurants/100013/vote --user User7:password`
### get all Votes
`curl -s http://localhost:8080/restaurant_voting/rest/admin/votes --user Admin:admin`
### get all Food
`curl -s http://localhost:8080/restaurant_voting/rest/admin/menus --user Admin:admin`
### get all Users
`curl -s http://localhost:8080/restaurant_voting/rest/admin/users --user Admin:admin`
