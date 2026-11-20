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
Comecei o desenvolvimento pelas classes de modelagem, de acordo com a descrição da atividade, a questão de diferenciar as multas "normais" das multas de avaria eu peguei de referência de um diagrama de biblioteca das antigas aulas do Maicon em db1, Com base nas classes de modelagem foi desenvolvido as interfaces de cada classe pro Repository que depois foram aplicadas nos repositorys que estão em memory na pasta repository/memory. Dos repositorys em memoria, parti para os services e controllers das rotas, desenvolvendo em conjuntos as validações de entrada das rotas, os DTO's para saída/entrada de dados (onde ocorre as validações de entrada) e os filtros de pesquisa junto de seus Enum váriaveis, que adequam a pesquisa ao enum mapeado passado. Dentro de exeptions foi desenvolvido os erros específicos esperados. O Mapper inicialmente, eu estava utilizando pra retornar do service pro controller, mas discutindo com colegas sobre isso aderi a sugestão de mudar para retornar as classes de domínio ao controller e ele transformar usando .toResponse para retornar ao usuário. Foi feito também as políticas de empréstimo e as regras, usando o strategy, como o enunciado sugeriu, para remover if/else e ter escalabilidade de políticas. As regras são com base nas regras passadas que estão no md no diretório, de email único, isbn único etc...

Próximos passos: 
    Implementar JPA, que foi apresentado nos slides de hoje (19/11), planejo usar o mesmo diretório aqui do Git, so mudar pra outra branch.