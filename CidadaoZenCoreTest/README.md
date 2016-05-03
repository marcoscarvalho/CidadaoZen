# CidadaoZenCore

 * Trabalho de conclusão de curso de Análise e Desenvolvimento de Sistemas - 1º semestre de 2015
 * Aplicação CORE com o objetivo de ser implantado na nuvem e responder as solicitações do aplicativo WEB e das aplicações mobile.
 
## Pendências
 * Quando salva qualquer tipo de avaliação, ele está consultando mais uma vez determinada arvore. O problema é o número de requisições. Está muito alto.
 * Não está subindo corretamente no beanstalk. Validar porque está ocorrendo isso.
 * Não está logando da maneira correta. Arrumar os logs da aplicação.
 * Fazer cache da consulta de arvores a cada 6hs.
 
## Versões

### 2.7.2.1
 * Adição de GET all para endereco
 * Ajuste de data.sql
 
### 2.7.2.2
 * Ajuste base no application.properties
 
### 2.7.3.0
 * Ajuste Objeto Endereço
 * Retirou logradouro e numero
 * Ajustou processo de cache que será usado
 
### 2.7.4.0
 * Adição de novo relatorio.
 
### 2.7.5.0
 * Ajuste objeto log
 
### 2.7.6.0
 * Ajuste consulta log
 
### 2.7.7.0
 * Passar o id do Endereço para consultar relatórios
 * Ajuste na forma de fazer a contagem.
 
### 2.7.7.1
 * Ajuste da Arvore e logs
 
### 2.7.7.2
 * Retirando Guava por ser muito pesado
 * 