package cadastro;

import cadastro.model.Pessoa;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in); //Permite entradas pelo usuario/teclado
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        int opcao;

        do{ 
            System.out.println("===== SISTEMA DE CADASTRO =====");
            System.out.println("1 - Cadastrar pessoa");
            System.out.println("2 - Listar pessoas");
            System.out.println("3 - Buscar pessoa por CPF");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); //para limpar o buffer

            switch(opcao){ //Serve como menu
                case 1:
                    
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine(); //limpa o buffer
                    
                    //Validacao de cpf para cadastramento, evitando duplo cadastramento de um mesmo cpf
                    boolean jaExiste = false;
                    for(Pessoa pessoa : pessoas){
                        if(pessoa.getCpf().equals(cpf)){
                            jaExiste = true;
                            break;
                        }
                    }
                    //caso ja exista, nao permite o cadastramento
                    if(!jaExiste){
                        pessoas.add(new Pessoa(nome, cpf, idade));
                        System.out.println("Pessoa cadastrada com sucesso.\n");
                    } else {
                        System.out.println("Erro: CPF ja cadastrado.\n");
                    }
                    break;

                case 2:
                    if(pessoas.isEmpty()){
                        System.out.println("Nenhuma pessoa cadastrada.\n");
                    } else {
                        for(Pessoa pessoa : pessoas){
                            pessoa.exibirDados();
                        }
                    }
                    break;
                    
                case 3:
                    System.out.print("Digite o CPF para busca: ");
                    String cpfBusca = scanner.nextLine();

                    boolean encontrada = false;

                    for(Pessoa pessoa : pessoas){
                        if(pessoa.getCpf().equals(cpfBusca)){
                            pessoa.exibirDados();
                            encontrada = true;
                            break; 
                        }
                    }
                    if (!encontrada){
                        System.out.println("Pessoa nao encontrada.\n"); 
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                    
                default:
                    System.out.println("Opcao invalida!\n");    
            }
        } while(opcao != 0);

        scanner.close();
    }
}
