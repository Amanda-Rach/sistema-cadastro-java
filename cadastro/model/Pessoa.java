

package cadastro.model;

public class Pessoa {
    
    private String nome;
    private String cpf;
    private int idade;

    public Pessoa(String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getCpf(){
        return cpf;
    }
    
    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        if(idade >= 0){
            this.idade = idade;
        }
    }
    public void exibirDados(){
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Idade: " + idade);
        System.out.println("-----------------");
    }
}
