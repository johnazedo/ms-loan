# Microservice: Loan

## Empréstimo Consignado: 

*Endpoints (Emprestimo Consignado):*
| Endpoint | Http Method | Descrição |
| -------- | --------- | ----------
| loan/payroll/v1/simulation | GET | Receber valor total, quantidade de vezes e retornar o valor das parcelas com os juros aplicados |
| loan/payroll/v1/ | POST | Solicitar emprestimo consignado |
| loan/payroll/v1/ | PUT |Atualizar status corrente do empréstimo |
| loan/payroll/v1/recurrence | POST | Informa sobre a recorrência de cobranças ao módulo de pagamentos/conta corrente* |
| loan/payroll/v1/request-detail | GET | Retorno sobre o resultado da solicitação do empréstimo |
| loan/payroll/v1/cancel | PUT | Negar solicitação de empréstimo |
| loan/payroll/v1/fee | PUT | Definir a taxa de juros praticada |



### Casos de uso: Empréstimo Consignado

-  O cliente pode simular um empréstimo, carece das informações do cliente ok

- O cliente pode solicitar um empréstimo, carece de informações do cliente ok
  
- Caso o empréstimo seja aprovado, enviar para o sistema de pagamento/conta corrente* um débito automático igual ao valor da parcela aprovada e um em número de parcelas aprovadas para o empréstimo ok

- Feedback ao cliente sobre a solicitação do empréstimo ok

- Mecanismo de proteção caso não sejam creditados valores na conta para pagamento da pendência, por exemplo, qualquer valor creditado será direcionado para o quitamento da dívida 

- Atualização sobre os empréstimos com relação ao pagamento das parcelas (sincronização)

- Negar empréstimo

- Definir taxa de juros
  
