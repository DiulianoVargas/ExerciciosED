package domain;

import java.util.List;

public class Pedido {

    private Cliente cliente;

    private Companhia companhia;

    private List<ItemPedido> listaItemPedido;

    private Double valorTotal;

    public Pedido(Cliente cliente, Companhia companhia, List<ItemPedido> listaItemPedido) {
        this.cliente = cliente;
        this.companhia = companhia;
        this.listaItemPedido = listaItemPedido;
    }

    public Pedido() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Companhia getVendedor() {
        return companhia;
    }

    public void setVendedor(Companhia companhia) {
        this.companhia = companhia;
    }

    public List<ItemPedido> getListaItemPedido() {
        return listaItemPedido;
    }

    public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
        this.listaItemPedido = listaItemPedido;
    }
}
