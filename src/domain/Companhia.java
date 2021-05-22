package domain;

import java.util.List;

public class Companhia {

    private String nome;

    private String cnpj;

    private String login;

    private String senha;

    private List<Pedido> listaPedidosCompanhia;

    public Companhia() {
    }

    public Companhia(String nome, String cnpj, String login, String senha) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.login = login;
        this.senha = senha;
        this.listaPedidosCompanhia = listaPedidosCompanhia;
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

    public List<Pedido> getListaPedidosCompanhia() {
        return listaPedidosCompanhia;
    }

    public void setListaPedidosCompanhia(List<Pedido> listaPedidosCompanhia) {
        this.listaPedidosCompanhia = listaPedidosCompanhia;
    }
}
