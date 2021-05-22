package domain;

import java.util.List;

public class Pedido {

    private Cliente cliente;

    private Vendedor vendedor;

    private List<ItemPedido> listaItemPedido;

    public Pedido(Cliente cliente, Vendedor vendedor, List<ItemPedido> listaItemPedido) {
        this.cliente = cliente;
        this.vendedor = vendedor;
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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<ItemPedido> getListaItemPedido() {
        return listaItemPedido;
    }

    public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
        this.listaItemPedido = listaItemPedido;
    }
}
