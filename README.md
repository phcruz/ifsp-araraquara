# ifsp-araraquara
Repositorio destinado a conteúdos compartilhados em cursos/aulas do FISP - Araraquara


<h4>:heavy_check_mark: Crawler - brasileirao-api</h4>
Desenvolvimento de um Bot automatizado de raspagem de dados web, capturando informações sobre partidas na página principal do Google, tratando e armazenando os dados no bando de dados H2 e disponibilizando as informações da partida em tempo real em uma API REST  

<h3>Projeto de raspagem web, desenvolvido com a utilização de conceitos e técnicas de Web Scraping</h3>

Para executar o projeto no terminal, digite o seguinte comando:
```
- mvn spring-boot:run
```

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a documentação do projeto:

* Para consultar a documentação da API acesse:
```
- http://localhost:8080/swagger-ui/index.html
```

* Para acessar o banco de dados:
```
- http://localhost:8080/h2-console/
```

São necessários os seguintes pré-requisitos para a execução do projeto:

* Java 11 ou versões superiores.
* Maven 3.6.3 ou versões superiores.
* STS, Intellj IDEA Community Edition ou sua IDE favorita.
* Controle de versão GIT.


<h3>API desenvolvida utilizando:</h3>

:ballot_box_with_check: Java 11

:ballot_box_with_check: JSOUP

:ballot_box_with_check: Spring Boot

:ballot_box_with_check: Spring Data JPA

:ballot_box_with_check: Lombok

:ballot_box_with_check: Swagger

:ballot_box_with_check: Hikari

:ballot_box_with_check: Banco de dados H2

:ballot_box_with_check: ModelMapper

:ballot_box_with_check: Modelo arquitetural REST
