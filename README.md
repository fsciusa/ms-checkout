# Order Microservice

__To compile and run:__

$ mvn spring-boot:run


__Possible calls:__

1. GET All products
	- http://localhost:8093/orders/

2. GET A specific product
	- http://localhost:8093/orders/10001
	- http://localhost:8093/orders/10002

3. POST a product (create)
	- http://localhost:8093/orders/

4. DELETE a product
	- http://localhost:8093/orders/{id}

5. PUT a product (update)
	- http://localhost:8093/orders/{id}
