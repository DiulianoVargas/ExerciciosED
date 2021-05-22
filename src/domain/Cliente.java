package domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;

    private String email;

    private String senha;

    private List<Pedido> listaPedidoCliente;

    public Cliente() {
    }

    public Cliente(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.listaPedidoCliente = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Pedido> getListaPedidoCliente() {
        return listaPedidoCliente;
    }

    public void setListaPedidoCliente(List<Pedido> listaPedidoCliente) {
        this.listaPedidoCliente = listaPedidoCliente;
    }
}
