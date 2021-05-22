package domain;

public class Produto {

    private double valor;

    private String marca;

    private String nome;

    public Produto() {
    }

    public Produto(double valor, String marca, String nome) {
        this.valor = valor;
        this.marca = marca;
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
