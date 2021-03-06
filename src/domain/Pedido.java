package domain;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Cliente cliente;

    private List<ItemPedido> listaItemPedido;

    private Double valorTotal;

    public Pedido(Cliente cliente, List<ItemPedido> listaItemPedido, Double valorTotal) {
        this.cliente = cliente;
        this.listaItemPedido = listaItemPedido;
        this.valorTotal = valorTotal;
    }

    public Pedido() {
        this.listaItemPedido = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getListaItemPedido() {
        return listaItemPedido;
    }

    public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
        this.listaItemPedido = listaItemPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }


    public void setValorTotal() {
        this.valorTotal = this.listaItemPedido
                .stream()
                .mapToDouble(itemPedido ->
                        itemPedido.getQuantidade() * itemPedido.getProduto().getValor())
                .sum();
    }
}
