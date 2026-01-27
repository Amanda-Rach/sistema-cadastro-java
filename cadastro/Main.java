package cadastro;

import cadastro.model.Pessoa;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Permite entradas pelo usuario/teclado
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        // Tenta carregar dados existentes logo ao iniciar o programa
        carregarDados(pessoas);
        int opcao;

        do {
            System.out.println("===== SISTEMA DE CADASTRO =====");
            System.out.println("1 - Cadastrar pessoa");
            System.out.println("2 - Listar pessoas");
            System.out.println("3 - Buscar pessoa por CPF");
            System.out.println("4 - Remover pessoa");
            System.out.println("0 - Sair");

            try {
                System.out.println("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine(); // para limpar o buffer

                switch (opcao) { // Serve como menu
                    case 1:

                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();

                        System.out.print("Idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); // limpa o buffer

                        // Validacao de cpf para cadastramento, evitando duplo cadastramento de um mesmo
                        // cpf
                        boolean jaExiste = false;
                        for (Pessoa pessoa : pessoas) {
                            if (pessoa.getCpf().equals(cpf)) {
                                jaExiste = true;
                                break;
                            }
                        }

                        // caso ja exista, nao permite o cadastramento
                        if (!jaExiste) {
                            pessoas.add(new Pessoa(nome, cpf, idade));
                            salvarDados(pessoas); // Salva dados alterados
                            System.out.println("Pessoa cadastrada com sucesso.\n");
                        } else {
                            System.out.println("Erro: CPF ja cadastrado.\n");
                        }

                        break;

                    case 2:
                        if (pessoas.isEmpty()) {
                            System.out.println("Nenhuma pessoa cadastrada.\n");
                        } else {
                            for (Pessoa pessoa : pessoas) {
                                pessoa.exibirDados();
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Digite o CPF para busca: ");
                        String cpfBusca = scanner.nextLine();

                        boolean encontrada = false;

                        for (Pessoa pessoa : pessoas) {
                            if (pessoa.getCpf().equals(cpfBusca)) {
                                pessoa.exibirDados();
                                encontrada = true;
                                break;
                            }
                        }
                        if (!encontrada) {
                            System.out.println("Pessoa nao encontrada.\n");
                        }
                        break;

                    case 4:
                        System.out.print("Digite o CPF da pessoa que deseja remover: ");
                        String cpfRemover = scanner.nextLine();
                        boolean removido = false;

                        // Uso do for para evitar erro de concorrencia ao remover
                        for (int i = 0; i < pessoas.size(); i++) {
                            if (pessoas.get(i).getCpf().equals(cpfRemover)) {
                                pessoas.remove(i); // percorre todas pessoas cadastradas e remove quando encontrado o
                                                   // CPF
                                removido = true;
                                salvarDados(pessoas); // Salva os dados do .txt
                                System.out.println("Pessoa removida com sucesso!\n");
                                break;
                            }
                        }
                        if (!removido) {
                            System.out.println("Erro: CPF nao encontrado.\n");
                        }
                        break;

                    case 0:
                        salvarDados(pessoas); // Salva dados antes de encerrar o programa
                        System.out.println("Saindo do sistema...");
                        break;

                    default:
                        System.out.println("Opcao invalida!\n");
                }

            } catch (InputMismatchException e) {
                // Trata erros de digitacao
                System.out.println("\n---- ERRO: Voce digitou uma letra! Digite apenas numeros de 0 a 4. ----\n");
                scanner.nextLine(); // Limpa o lixo, como "abc..."
                opcao = -1; // Faz com que o loop continue
            }

        } while (opcao != 0);

        scanner.close();
    }

    // metodo para salvar lista de pessoas no arquivo dados.txt
    public static void salvarDados(ArrayList<Pessoa> pessoas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dados.txt"))) {
            for (Pessoa p : pessoas) {
                bw.write(p.toLinha()); // toLinha() na classe Pessoa, salva no formato CSV (ponto e virgula)
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    // metodo para le o arquivo dados.txt e reconstruir a lista de objetos na
    // memoria
    public static void carregarDados(ArrayList<Pessoa> pessoas) {
        File arquivo = new File("dados.txt");
        if (!arquivo.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    // Transforma string de volta em objeto Pessoa
                    pessoas.add(new Pessoa(dados[0], dados[1], Integer.parseInt(dados[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

    }
}
