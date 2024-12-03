
Feature: pagamento
    Scenario: Novo pagamento com cartão de crédito
        When eu finalizar a compra com cartão de crédito
        Then a compra é efetuada com sucesso

    Scenario: Novo pagamento com boleto
        When eu finalizar a compra com boleto
        Then a compra é efetuada com sucesso usando boleto

    Scenario: Novo pagamento com débito
        When eu finalizar a compra com débito
        Then a compra é efetuada com sucesso usando débito

    Scenario: Buscar pagamento
        Given que eu tenha um pagamento registrado
        When eu buscar o pagamento usando o id do pedido
        Then o pagamento é encontrado

    Scenario: Atualizar pagamento
        Given que eu tenha um pagamento registrado
        When eu atualizar o pagamento usando o id do pedido
        Then o pagamento é atualizado com sucesso
