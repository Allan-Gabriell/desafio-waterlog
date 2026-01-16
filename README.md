# 🌦️ Desafio Waterlog

Este repositório contém uma solução distribuída para monitoramento climático e envio de alertas via e-mail. O sistema é composto por microsserviços que coletam dados meteorológicos, processam essas informações através de mensageria e notificam os interessados.

## 🏗️ Arquitetura e Módulos

O projeto é dividido em três módulos principais:

### 1. `watter-collector` (Coletor)
*   **Responsabilidade:** Coletar dados climáticos em tempo real.
*   **Funcionamento:** Um agendador (`Scheduler`) é executado a cada 1 minuto para buscar informações da API [Open-Meteo](https://open-meteo.com/).
*   **Output:** Os dados processados são enviados para uma fila no **RabbitMQ** (`weather.queue`).
*   **Tecnologias:** Spring Boot, RabbitMQ Client, Open-Meteo SDK/Client.

### 2. `watter-worker` (Processador)
*   **Responsabilidade:** Consumir os dados climáticos e notificar.
*   **Funcionamento:** Escuta a fila `weather.queue` no RabbitMQ. Ao receber uma nova mensagem, processa os dados e envia um e-mail formatado com as condições atuais (Temperatura, Vento, Código do Clima).
*   **Tecnologias:** Spring Boot, RabbitMQ Listener, JavaMailSender.

### 3. `watter-api` (API Futura)
*   **Status:** *Em desenvolvimento / Planejamento*
*   **Objetivo:** Este módulo está reservado para ser a interface REST da aplicação, permitindo consultas futuras e gerenciamento de configurações via HTTP. Atualmente, serve como base para expansões futuras.

---

## 🚀 Tecnologias Utilizadas

*   **Linguagem:** Java 21
*   **Framework:** Spring Boot 4.0.1
*   **Mensageria:** RabbitMQ
*   **Containerização:** Docker & Docker Compose
*   **Fonte de Dados:** Open-Meteo
*   **Notificações:** Spring Mail (JavaMail)

---

## ⚙️ Como Executar

O projeto utiliza Docker Compose para orquestrar os serviços. É necessário que o serviço de mensageria (RabbitMQ) esteja ativo antes que os consumidores tentem se conectar.

### Pré-requisitos
*   Docker e Docker Compose instalados.
*   Java 21 (para execução local fora do Docker, se desejado).
*   Maven.

### Passo 1: Iniciar o Coletor e RabbitMQ
O `watter-collector` é responsável por subir a infraestrutura do RabbitMQ.

1.  Navegue até a pasta do coletor:
    ```bash
    cd watter-collector
    ```
2.  Suba os containers:
    ```bash
    docker-compose up -d --build
    ```
    Isso iniciará o container do **RabbitMQ** e do **Weather Collector**.

### Passo 2: Iniciar o Worker
O `watter-worker` se conecta à rede criada pelo coletor para acessar o RabbitMQ.

1.  Navegue até a pasta do worker:
    ```bash
    cd ../watter-worker
    ```
2.  Suba o container:
    ```bash
    docker-compose up -d --build
    ```

### Passo 3: Verificar os Logs
Para garantir que tudo está funcionando:
*   Verifique se o coletor está enviando mensagens:
    ```bash
    docker logs -f weather-collector
    ```
*   Verifique se o worker está recebendo e enviando e-mails:
    ```bash
    docker logs -f weather-consumer
    ```

---

## 📧 Configuração de E-mail

Atualmente, o serviço de e-mail está configurado para usar o remetente `allangabrieldev@gmail.com`. Para testar o envio real de e-mails, pode ser necessário configurar as credenciais SMTP no arquivo `application.properties` ou através de variáveis de ambiente no `docker-compose.yml` do `watter-worker`, dependendo de como você deseja autenticar (App Password do Google, por exemplo).

---

## 🔮 Próximos Passos (Roadmap)

*   [ ] Implementação completa da **watter-api** para expor dados históricos.
*   [ ] Parametrização dinâmica da cidade e intervalos de coleta via API.
*   [ ] Melhoria no template de e-mail (HTML).
*   [ ] Persistência dos dados climáticos em banco de dados.
