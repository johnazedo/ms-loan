# Microservice: Loan

## Empréstimo Consignado: 

*Endpoints (Emprestimo Consignado):*
| Endpoint | Http Method | Descrição |
| -------- | --------- | ----------
| loan/v1/simulation | GET | Receber valor total, quantidade de vezes e retornar o valor das parcelas com os juros aplicados |
| loan/v1/payroll | POST | Solicitar emprestimo consignado |
| loan/v1/payroll | PUT |Atualizar status corrente do empréstimo |
| loan/v1/recurrence | POST | Informa sobre a recorrência de cobranças ao módulo de pagamentos/conta corrente* |
| loan/v1/request-detail | GET | Retorno sobre o resultado da solicitação do empréstimo |
| loan/v1/fee | POST | Criar uma nova taxa de juros |
| loan/v1/fee | PUT | Atualizar uma taxa de juros praticada |



### Casos de uso: Empréstimo Consignado

-  O cliente pode simular um empréstimo, carece das informações do cliente ok

- O cliente pode solicitar um empréstimo, carece de informações do cliente ok
  
- Caso o empréstimo seja aprovado, enviar para o sistema de pagamento/conta corrente* um débito automático igual ao valor da parcela aprovada e um em número de parcelas aprovadas para o empréstimo ok

- Feedback ao cliente sobre a solicitação do empréstimo ok

- Mecanismo de proteção caso não sejam creditados valores na conta para pagamento da pendência, por exemplo, qualquer valor creditado será direcionado para o quitamento da dívida 

- Atualização sobre os empréstimos com relação ao pagamento das parcelas (sincronização)

- Negar empréstimo

- Definir taxa de juros
  
