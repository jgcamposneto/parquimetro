Requisitos da solução:

**1. Utilize APIs modernas para melhorar a eficiência do sistema. Por exemplo,
utilize classes e métodos da API de datas do Java para facilitar o cálculo do
tempo de estaciomanento.**
- Solução: 
  - Uso da nova API de datas introduzidas no java 8.
    - Classe `java.time.LocalDateTime` para armazenamento dos horários de entrada
e saída do estacionamento.
    - Classe `java.time.Duration` para calcular as medidas de tempo do estacionamento,
como horas, minutos e segundos.

**2. Implemente uma estrutura de persistência de dados eficiente. Utilize um banco
de dados (pode ser em memória ou físico, SQL ou NoSQL), para armazenar as informações
sobre os veículos estacionados. Isso permitirá um acesso rápido e confiável dos dados.**
- Solução:
  - Utilização de banco de dados Postgresql, acessado na API através de Spring Data.  