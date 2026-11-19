--- API biblioteca Spring ---

A API conta com registro de livros, leitores (professores e alunos) e multas (de atraso e degradação do livro)
Para popular a API ao executar, adicione um .env com a seguinte anotação --> CARGA_INICIAL=true caso queira iniciar a API vazia, inicie passando um false.

Para testes da API está disponibilizado a collection do postman para rodar os endpoints que estão na pasta ./postman



lista de endpoints: 

| Método | URI | Descrição | Sucesso |
|---|---|---|---|
| POST | `/livros` | cadastra um livro | 201 + Location |
| GET | `/livros` | lista livros; filtros opcionais `?titulo=`, `?autor=`, `?ano=` e `?disponivel=true\|false` | 200 |
| GET | `/livros/{id}` | busca um livro | 200 |
| GET | `/livros/isbn/{isbn}` | busca um livro pelo ISBN | 200 |
| PUT | `/livros/{id}` | atualiza um livro | 200 |
| DELETE | `/livros/{id}` | remove um livro | 204 |
| POST | `/leitores` | cadastra um leitor | 201 + Location |
| GET | `/leitores` | lista leitores; filtro opcional `?tipo=ALUNO\|PROFESSOR` | 200 |
| GET | `/leitores/{id}` | busca um leitor | 200 |
| PUT | `/leitores/{id}` | atualiza um leitor | 200 |
| DELETE | `/leitores/{id}` | remove um leitor | 204 |
| POST | `/emprestimos` | realiza um empréstimo | 201 + Location |
| GET | `/emprestimos` | lista empréstimos; filtros opcionais `?leitorId=`, `?livroId=`, `?data=AAAA-MM-DD` e `?ativos=true\|false` | 200 |
| GET | `/emprestimos/{id}` | busca um empréstimo | 200 |
| POST | `/emprestimos/{id}/devolucao` | registra a devolução e calcula a multa | 200 |
| GET | `/emprestimos/atrasados` | lista os empréstimos ativos com prazo vencido | 200 |
| GET | `/multas` | lista multas; filtros opcionais `?emprestimoId=` e `?leitorId=` | 200 |
| GET | `/multas/{id}` | busca uma multa | 200 |
| POST | `/multas/{id}/pagamento` | registra o pagamento de uma multa | 200 |



Decisões de projeto: