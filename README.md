# JWT Validator

## Resumo

A aplicação foi criada para validação de tokens JWT.

Um swagger foi disponibilizado no diretório ./docs/openapi contendo uma relação dos endpoints criados e seus parâmetros e retornos. Ele também está configurado para acesso na API via {HOST}/swagger-ui/index.html.

Além disso, uma collection do postman foi disponibilizada no diretório ./docs/postman. 

## Design

A aplicação foi desenvolvida seguindo uma arquitetura em camadas. No pacote application, as features foram separadas em subpacotes, cada um contendo:

- Controller: lida com as requisições e respostas, executa validações básicas e encaminha os dados para a camada de Service;
- Service: contém a lógica de negócio e faz a interface com as classes de decoding e validação;
- Dto: representa os POJOs utilizados para transporte de dados entre as camadas;
- Model: define as classes de domínio da aplicação.

Para a feature de JWT especificamente, foram criados subpacotes adicionais:

- Validation: concentra as classes responsáveis pelas regras de validação;
- Decoder: contém as classes de suporte para decodificação do JWT.

A estrutura de validação foi organizada em duas partes:

- Validators: implementam as regras de validação em si;
- Handlers: implementam um Chain of Responsibility, permitindo adicionar novas validações sem alterar o código já existente.

## Funcionalidades

- Validação de tokens JWT;
- Healthcheck no endpoint /health

## Cloud

A aplicação foi implantada no GCP Cloud Run. A escolha se deu pelo tier gratuito generoso e pela familiaridade com outros serviços já utilizados para estudo.

O serviço pode ser acessado através da URL: https://jwt-validator-159709509079.us-east1.run.app

Caso desejem simular o build via GitHub Actions, é necessário criar uma Service Account específica com as seguintes roles e adicioná-la na secret REGISTRY_SA_KEY:

- Artifact Registry Writer
- Cloud Run Admin
- Service Account Token Creator
- Service Account User

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Docker
- GCP Cloud Run

## Build e utilização do projeto

### Clonagem do repositório

```sh
git clone https://github.com/brunomsouz/jwt-validator.git
cd jwt-validator
```

### Docker
Para facilitar a execução, o projeto disponibiliza um Dockerfile e um docker-compose.yaml, que automatizam o build e o deploy da aplicação.

A partir da raíz do projeto, execute:

```sh
docker compose -f ./docker/docker-compose.yaml up
```

Caso prefira rodar sem utilização do docker-compose.yaml:

```sh
docker build -f ./docker/Dockerfile -t jwt-validator .
docker run -d -p 8080:8080 --name=jwt-validator --restart=unless-stopped jwt-validator
```