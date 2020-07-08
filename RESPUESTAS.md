Crear cuenta:

	curl -d '{"accountNumber":"0215678231","balance":102,"currencyType":{"id":1}}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/currentAccount/save
	
Listar cuentas:

	curl -X GET "http://localhost:8080/api/currentAccount/findAll"	

Agregar movimiento 1:

	curl -d '{"movementType": {"id": 2,"operationType":{"id":2,"code":"CRE"}},"currentAccount": {"id": 1},"description": "Movimiento especifico","amount": 1829.1234}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/movement/save
	
Agregar movimiento 2:

	curl -d '{"movementType": {"id": 2,"operationType":{"id":2,"code":"CRE"}},"currentAccount": {"id": 1},"description": "Movimiento 2","amount": 500}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/movement/save
	
Listar movimientos por cuenta:

	curl -X GET "http://localhost:8080/api/movement/findMovementsByAccount/1"
	
Eliminar cuenta:

	curl -X DELETE "http://localhost:8080/api/currentAccount/delete/1"
