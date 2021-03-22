# UpKeep

One to One [user & fullname]
||
One to Many [user & laptop]
||
Many to Many [user & parking]

https://www.javadevjournal.com/spring-boot/spring-boot-with-hibernate/
Laptop:
Post Requests: 
http://localhost:8080/laptop/register
{
    "lid":"20",
    "compname":"dell",
    "usrlaptop":{
        "id":"3",
    "username": "test",
    "password": "test"
}
}
//see that the user already exists otherwise create new user later
http://localhost:8080/laptop/reregister
{
    "lid":"1",    
    "compname": "dell",  
    "reassign": "true",
    "usrlaptop":{
        "id": "3",
    "username": "test",
   }
}
Get Requests:
http://localhost:8080/laptop/1
http://localhost:8080/laptop/show
 
User:
http://localhost:8080/users/logout
http://localhost:8080/users/login
http://localhost:8080/users/register
http://localhost:8080/users/laptop
http://localhost:8080/users/userdetails
 
{
"username": "shivauser",
"password": "password"
}
Fullname:
Request: http://localhost:8080/user/addFullName
{  "fname":"fname", "lname":"lname","mname":"maname",
"namefield":{    "username": "bro",    "password": "bro"}}
http://localhost:8080/users/registermultiplelaptops
User id should exist in databases otherwise one to many relationship will raise exception
{ 
    "id" : "13",
    "username" : "bro3",
    "password" : "bro3",
    "laptops" : [{ "lid":"22",  "compname":"dell"},
     { "lid":"23",   "compname":"dell"}]
}
Parking Requests
http://localhost:8080/parking/occupy
{
    "parkid": "1",
    "vehicletype": "Benz",
    "parkuser": 
        [{"id":"51",
        "username":"test",
        "password":"test"},
        {"id":"51",
        "username":"test",
        "password":"test"}]    
}
 
{
    "parkid": "100",
    "vehicletype": "benz",
    "parkuser": 
        [{"id":"54",
        "username":"shiva",
        "password":"shiva"},
        {"id":"51",
        "username":"test",
        "password":"test"}]    
}
