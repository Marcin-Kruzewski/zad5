```http://localhost:8080/Items``` - list all items

```http://localhost:8080/Items?fromprice=200&toprice=300``` - list items with 200 <= price <= 300

```http://localhost:8080/Items?sort=category``` - list items by category

```http://localhost:8080/Items?category=HDD``` - list all items with category hdd

```http://localhost:8080/Items?name='HDD 1000GB'``` - show items with name HDD 1000GB

```http://localhost:8080/Items?name='SDD.*'``` - list items by name (regex)

```http://localhost:8080/Item?id=1``` - show item with id 1

```http://localhost:8080/Item - POST (?id=1&category=HDD/)``` - update item with id 1 set category HDD

```http://localhost:8080/Item - POST (?id=1)``` - show item with id 1

```http://localhost:8080/Items - POST (?json='{"name":"HDD 500GB","category":"HDD","price":"240"}'``` - add item to database

```http://localhost:8080/Item - POST (?delete=1)``` - delete item with id 1
