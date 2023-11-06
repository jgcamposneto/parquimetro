# Fluxo de trabalho

### 1. **O condutor se registra no sistema, fornecendo informações pessoais.**
 
- Chamada da API para cadastro de Condutor (`POST {{url}}/condutores`):
```json
// Request
{
  "nome": "Joao",
    "endereco": {
      "logradouro": "logradouro1",
      "bairro": "bairro1",
      "cep": "57057000",
      "cidade": "cidade1",
      "uf": "uf1"
  },
  "contato": "828989654654"
}

// Response
{
  "id": "6929b1ab-13cf-4297-8bfd-46b0832d65d9",
  "nome": "Maria",
  "endereco": {
    "logradouro": "logradouro1",
    "bairro": "bairro1",
    "cep": "57057000",
    "numero": null,
    "complemento": null,
    "cidade": "cidade1",
    "uf": "uf1"
  },
  "contato": "828989654654",
  "formaDePagamento": null
}
```

- Chamada da API para cadastro de Veículo (`POST {{url}}/condutores/6929b1ab-13cf-4297-8bfd-46b0832d65d9/veiculo`):
```json
// Request
{
  "placa":"AAA2222"
}

// Response
{
  "id": "1a8746e9-1c64-44df-90ab-37aaf6f36379",
  "placa": "AAA2222",
  "idCondutor": "6929b1ab-13cf-4297-8bfd-46b0832d65d9"
}
```


### 2. O condutor registra sua forma de pagamento preferida (cartão de crédito = 0, débito = 1 ou PIX = 2).

 - Chamada da API para registrar Forma de Pagamento
(`{{url}}/condutores/6929b1ab-13cf-4297-8bfd-46b0832d65d9/formapagamentopreferida`)

```json
// Request
{
  "formaDePagamento": 1
}

// Response
{
  "id": "6929b1ab-13cf-4297-8bfd-46b0832d65d9",
  "nome": "Maria",
  "endereco": {
    "logradouro": "logradouro1",
    "bairro": "bairro1",
    "cep": "57057000",
    "numero": null,
    "complemento": null,
    "cidade": "cidade1",
    "uf": "uf1"
  },
  "contato": "828989654654",
  "formaDePagamento": "DEBITO"
}
```

### 3. O condutor inicia o registro de tempo no sistema, escolhendo entre tempo fixo (indicando a duração desejada) ou por hora.

 - Chmada da API para iniciar o registro do estacionamento (`POST {{url}}/estacionamentos`). Se o condutor indicar 0
ou não informar o tempo em horas, o registro fica por tempo variável (por hora). Caso registre um valor maior do que
0, o tempo é fixo.

```json
// Request
{
  "entrada": "06-11-2023 14:20:00",
  "duracaoEmHoras": 0,
  "idVeiculo": "1a8746e9-1c64-44df-90ab-37aaf6f36379"
}

// Response
{
  "id": "3516546b-0e14-4a09-b6ca-877fdd483e1a",
  "entrada": "2023-11-06T14:20:00",
  "duracaoContratadaEmHoras": 0,
  "idVeiculo": "1a8746e9-1c64-44df-90ab-37aaf6f36379",
  "pago": false,
  "ativo": true
}
```
### 4. O sistema monitora o tempo de estacionamento e cobra o valor adequado com base nas opções de pagamento selecionadas.

- Chamada da API para monitorar o tempo de uso do estacionamento (`GET {{url}}/estacionamentos/3516546b-0e14-4a09-b6ca-877fdd483e1a`)
```json
// SEM Request Body

// Response
{
  "id": "3516546b-0e14-4a09-b6ca-877fdd483e1a",
  "entrada": "2023-11-06T14:20:00",
  "duracaoContratadaEmHoras": 0,
  "tempoDecorrido": {
    "horas": 2,
    "minutos": 41
  },
  "idVeiculo": "1a8746e9-1c64-44df-90ab-37aaf6f36379",
  "ativo": true,
  "pago": false
}
```

- Chamada da API para encerrar o tempo de uso do estacionamento (`GET {{url}}/estacionamentos/3516546b-0e14-4a09-b6ca-877fdd483e1a/encerrar`)
```json
// SEM Request Body

// Response
{
  "valor": 20.00,
  "encerrado": true
}
```

- Chamada da API para pagar o uso do estacionamento (`PUT {{url}}/estacionamentos/3516546b-0e14-4a09-b6ca-877fdd483e1a/pagar`) 
```json
// SEM Request Body

// Response
{
  "id": "3516546b-0e14-4a09-b6ca-877fdd483e1a",
  "entrada": "2023-11-06T14:20:00",
  "duracaoContratadaEmHoras": 0,
  "idVeiculo": "1a8746e9-1c64-44df-90ab-37aaf6f36379",
  "pago": true,
  "ativo": false
}
```

- Chamada da API para emitir o recibo do uso do estacionamento (`GET {{url}}/estacionamentos/3516546b-0e14-4a09-b6ca-877fdd483e1a/emitirecibo`)
```json
// SEM Request Body

// Response
{
  "entrada": "2023-11-06T14:20:00",
  "saida": "2023-11-06T17:07:03.941387",
  "tempoEstacionado": {
    "horas": 2,
    "minutos": 47
  },
  "tarifaAplicada": 10.00,
  "valorPago": 20.00
}
```

### 5. Para horários fixos, o sistema emite um alerta quando o tempo está prestes a expirar. e 6 Para períodos variáveis, o sistema emite um alerta informando que estenderá automaticamente o estacionamento por mais uma hora, a menos que o condutor desligue o registro.

Uso das anotações `org.springframework.scheduling.annotation.EnableScheduling` e
`org.springframework.scheduling.annotation.Scheduled` para habilitar o mecanismo de Scheduling Tasks do Spring.
O envio das notificações são simuladas, apenas com geração de log de envio. Classe de implementação: `EstacionamentoScheduledTasks`.

### 7. Quando o tempo de estacionamento é encerrado, o sistema emite um recibo para o condutor.

Detalhado no tópico 4.