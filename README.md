# Teste Técnico

* Sistema CRUD de eventos, onde pode ser cadastrado eventos, definidos data de inicio e
fim de vigência. Quando estiver na data de vigência, o sistema automaticamente deve
setar como ativo o evento e desativar quando passar da data.



## Observações

* Em vez de utilizar Date para as datas, utilizei LocalTimeDate para que eu pudesse 
ver o funcionamento utilizando minutos como base.
* A principio iria utilizar delayed message do RabbitMQ como agendador de tarefas, mas tive problemas 
nos testes, porém tive problemas com a versão e plugin.
* Utilizei ScheduledExecutorService para agendar a troca de estados de ativos.
* Fiz validação no frontend de datas finais maiores que iniciais e de diferenças de datas
menores de 1 minuto.
* Fiz validação no backend visando verificar no banco se já existia evento na mesma data e da mesma instituição. 

## Para testar

* Baixe o pacote e abra na IDE da sua preferência.

### Para o frontend acesse a pasta:
    cd frontend/cresol-events/
### Rode:
### `npm start`

Abra [http://localhost:3000](http://localhost:3000) no navegador

### Rotas Backend CRUD:
    http://localhost:8080/event
    http://localhost:8080/institution

## O que falta?
* Validações adicionais para garantir que esteja livre de erros.
* Testes unitários
* Possibilidade de update e delete no frontend.
    
