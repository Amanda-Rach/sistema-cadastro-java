## Sistema de Cadastro em Java
 Este é um projeto desenvolvido para praticar os conceitos de Programação Orientada a Objetos, evoluído para incluir persistência de dados e segurança de entrada.

## Funcionalidades
⚬ Cadastrar pessoa: Com validação de CPF (evita duplicatas).

⚬ Listar pessoas cadastradas: Exibe todos os dados na tela.

⚬ Buscar pessoa pelo CPF: Busca rápida por registro.

⚬ Remover pessoa: Exclui o cadastro da lista e do arquivo.

⚬ Persistência: Salva e carrega os dados automaticamente em um arquivo dados.txt.

## Tecnologias utilizadas
⚬ Java

⚬ Scanner (com tratamento de exceções)

⚬ ArrayList

⚬ BufferedWriter / BufferedReader (Manipulação de arquivos)

## Conceitos aplicados
⚬ Classes e objetos

⚬ Encapsulamento

⚬ Pacotes

⚬ Estruturas de repetição e decisão

⚬ Tratamento de erros (Try-Catch)

⚬ Persistência de dados (I/O)
## Como executar

1. Compile o projeto:
   ```bash
   javac cadastro/model/Pessoa.java cadastro/Main.java

2. Execute o sistema:
   ```bash
   java cadastro.Main