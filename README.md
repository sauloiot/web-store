# WEB STORE API V1.0
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/sauloiot/web-store/blob/main/LICENSE)

## Sobre o projeto
  A Web Store Api é um desafio de desenvolvimento para a construção de um pequeno sistema de compras on-line. Nesse sistema é possível cadastrar Administrador e Cliente, incluir categorias, produtos, contas, pedidos e cupons de desconto para serem utilizados no pedido.
  A conta cadastrada pode ser do tipo ADMIN ou CLIENTE, ADMIN é responsável por alimentar a base de dados da API criando e alterando as categorias, produtos e cupons. É possível editar todos os itens, é possível adicionar desconto a um produto que será calculado no momento do pedido, também é possível criar cupons de desconto do tipo valor fixo ou porcentagem. Ao realizar um pedido, é possível escolher entre pagamento com boleto e pagamento com cartão, ao gerar o pedido, será disponibilizado seu resumo contendo todos os itens, seus preços, descontos, cupons, valor total, valor com 10% de desconto e etc.
 
 ## Links
 ### - [Postamn contendo todas as requisiçoes da API](https://documenter.getpostman.com/view/5414747/TzK2aDyU)
 
 ![Modelo Conceitual](https://github.com/sauloiot/assets/blob/main/web-store-api-v1/modelo%20banco%20conceitual.png)
 
 # Tecnologias Utilizadas
 Java 11 e Spring Boot 2.4
 
 # Como Executar 
 ### Requisitos:
 -Java 11, Spring boot 2.4 e Maven
 
 ```sh
git clone https://github.com/sauloiot/web-store.git 
cd web-store
mvnw spring-boot:run
```
 Ou pode abrir o projeto em uma IDE e executar o arquivo "WebStoreApplication" em src/main/java/com/saulo/webstore/WebStoreApplication.java.
 
#### O projeto possui 3 perfis: test, prod e dev. 
Por padrão o perfil inicial é o dev, para alternar é necessario alterar a linha
 ```sh
spring.profiles.active

```
do arquivo 
 ```sh
application.properties
```
Para executar os testes é necessario alterar 
 ```sh
spring.profiles.active=dev
```
para
 ```sh
spring.profiles.active=test
```
e executar os testes na pasta src/test/java. 
### Para executar o projeto em produção
É necessario acessar o arquivo:
 ```sh
application-prod.properties
```
e alterar a url do banco e a senha do banco
 ```sh
spring.datasource.url=jdbc:postgresql://URL_DO_BANCO
spring.datasource.password=SENHA_DO_BANCO
spring.datasource.username=postgres
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
```
 
 
 # Autor
 Saulo Ivo Oliveira Tavares
 
