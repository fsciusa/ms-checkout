# Order Microservice

__To compile and run:__

$ mvn spring-boot:run


__Possible calls:__

1. GET All orders
	- http://localhost:8093/orders/

2. GET A specific order
	- http://localhost:8093/orders/30001
	- http://localhost:8093/orders/30002
	- http://localhost:8093/orders/30003

3. POST an order (create)
	- http://localhost:8093/orders/

4. DELETE an order
	- http://localhost:8093/orders/{id}

5. PUT an order (update)
	- http://localhost:8093/orders/{id}
