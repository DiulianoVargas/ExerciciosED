package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Companhia {

    private String nome;

    private String cnpj;

    private String login;

    private String senha;

    private LinkedList<Pedido> listaPedidosCompanhia;

    private List<Produto> listaProdutosDisponiveis;

    public Companhia() {
    }

    public Companhia(String nome, String cnpj, String login, String senha) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.login = login;
        this.senha = senha;
        this.listaPedidosCompanhia = new LinkedList<>();
        this.listaProdutosDisponiveis = listarProdutosDisponiveis();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LinkedList<Pedido> getListaPedidosCompanhia() {
        return listaPedidosCompanhia;
    }

    public void setListaPedidosCompanhia(LinkedList<Pedido> listaPedidosCompanhia) {
        this.listaPedidosCompanhia = listaPedidosCompanhia;
    }

    public List<Produto> getListaProdutosDisponiveis() {
        return listaProdutosDisponiveis;
    }

    private List<Produto> listarProdutosDisponiveis() {
        Produto notebook = new Produto(1000, "DELL", "Notebook");
        Produto tv = new Produto(5000, "LG", "Smart TV");

        List<Produto> listaProdutos = new ArrayList<>();

        listaProdutos.add(notebook);
        listaProdutos.add(tv);

        return listaProdutos;
    }
}
