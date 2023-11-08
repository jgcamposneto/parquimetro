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

# Requisitos da solução:

### Utilize APIs modernas para melhorar a eficiência do sistema. Por exemplo, utilize classes e métodos da API de datas do Java para facilitar o cálculo do tempo de estaciomanento.
- Solução:
    - Uso da nova API de datas introduzidas no java 8.
        - Classes `java.time.LocalDateTime`, `java.time.temporal.ChronoUnit` para armazenamento e cálculos
do tempo.

### Implemente uma estrutura de persistência de dados eficiente. Utilize um banco de dados (pode ser em memória ou físico, SQL ou NoSQL), para armazenar as informações sobre os veículos estacionados. Isso permitirá um acesso rápido e confiável dos dados.
- Solução:
    - Utilização de banco de dados Postgresql, acessado na API através de Spring Data.

### Otimize os processos de gravação e leitura dos dados. Utilize técnicas para minimizar a necessidade de acesso frequente ao banco de dados. Isso ajudará a reduzir os atrasos e melhorar o desempenho geral do sistema.
- Solução
  - Utilização do atributo `fetch = FetchType.EAGER` nos relacionamentos `@ManyToOne` e `@OneToMany`
das entidades JPA `Estacionamento`, `Veiculo` e `Condutor`,
para recuperar as entidades associadas numa única consulta, evitando assim o problema dos _N+1 selects_.
  - Utilização das interfaces `org.springframework.data.domain.Pageable` e `org.springframework.data.web.PageableDefault`
para limitar o número de resultados de uma consulta, na listagem de `Veiculo`, obtendo assim tempo de resposta mais rápido,
uma vez que é carregado um número menor de dados.
  - Utilização das anotações `org.springframework.cache.annotation.EnableCaching` e `org.springframework.cache.annotation.Cacheable` para
permitir que a aplicação processe anotações de cache e para que a consulta de `Veículo` por `id` fique em cache
(armazenada em memória, para acesso mais rápido) uma vez que os
dados de um veículo (como a placa, por exemplo) quando não mudam com frequência (alteração da lei, por exemplo).
    
### Considere a escalabilidade do sistema. Embora o desafio não exija a implementação de um sistema distribuído, é importante projetar a solução de forma que ela possa lidar com um grande volume de dados e ser facilmente escalável no futuro.
- Solução
  - Utilização do serviço Netflix Eureka para Registry e Discovery, utilização de Api Gateway com Spring Cloud Gateway
e balanceamento de carga com escalabilidade horizontal