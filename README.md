# Invillia recruitment challenge

Projeto desenvolvido como parte do processo seletivo

* O Banco selecionado para esse projeto foi o H2.
* A implementação para popular o banco e efetuar os testes se encontra na classe InvilliaApplication.
* Como forma de segurança, eu implementaria a segurança baseada em Token(JWT).


URI para Store:

* localhost:8080/store (método GET)-> listagem das lojas cadastradas
* localhost:8080/store/{id} (método GET)-> acessar o cadastro de alguma loja
* localhost:8080/store/{id} (método PUT)-> efetuar alteração no cadastro da loja

URI para Order:

* localhost:8080/order (método GET)-> listagem dos pedidos cadastrados
* localhost:8080/order (método POST)-> cadastro de um pedido
* localhost:8080/order/{id} (método GET)-> acessar o cadastro de algum pedido
* localhost:8080/order/confirmpayment/{id} (método PUT)-> confirma pagamento
* localhost:8080/order/refunded/{id} (método GET)-> solicita devolução, a mesma só será permitida caso o status não esteja como confirmado e/ou tenha menos de 10 dias.

# Modelo para inserção de dados

Store

{
    "name": "Alex",
    "street": "Rua Vereador Moises Pereira",
		"state": "Bahia",
    "district": "Centro",
    "country": "Brazil",
    "city": "Paulo Afonso",
    "number": "157"
    
}

Order

{
    "address": {
        "street": "Rua Tiradentes",
        "state": "Bahia",
        "district": "BTN",
        "country": "Brazil",
        "city": "Paulo Afonso",
        "number": "85"
    },
    "confirmationDate": "2019-03-15",
    "status": "confirmed",
    "payment": {
        "paymentStatus": "confirmed",
        "credcardNumber": 987654321,
        "paymentDate": null
    },
    "items": 
    [
        {
            "description": "trem",
            "unitPrice": 7540.74,
            "quantity": 2
        },
        {
            "description": "Macbook",
            "unitPrice": 2856.95,
            "quantity": 5
        },
        {
            "description": "Avião",
            "unitPrice": 2333.0,
            "quantity": 3
        }
    ],
}

