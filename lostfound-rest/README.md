To run LostFoundRest:

* List all items: curl -i -X GET http://localhost:8080/pa165/rest/items
* List an item by its id: curl -i -X GET http://localhost:8080/pa165/rest/items/1

* Create a item:
    * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"mobile iphone","description":"white with    headphone","keywords":"iphone 6, mobile, apple"}' http://localhost:8080/pa165/rest/items/create
 
