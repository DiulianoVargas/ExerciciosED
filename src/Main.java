import domain.*;

import java.sql.SQLOutput;
import java.util.*;

public class Main {

    private static List<Cliente> listaDeClientes;

    static {
        listaDeClientes = new ArrayList<>();
    }

    public static void main(String[] args) {

        Scanner tc = new Scanner(System.in);

        int pageController = 0;
        int clienteController = 0;

        Vendedor vendedor = new Vendedor();
        vendedor.setNome("nome vendedor");

        Cliente cliente = new Cliente();
        cliente.setNome("nome cliente");
        cliente.setEmail("emailcliente@email.com");
        cliente.setSenha("1234455");

        Produto computador = new Produto();
        computador.setMarca("DELL");
        computador.setNome("Computador topzera");
        computador.setValor(1000);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(computador);
        itemPedido.setQuantidade(10);

        List<ItemPedido> listaItemPedido = new ArrayList<>();
        listaItemPedido.add(itemPedido);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setVendedor(vendedor);
        pedido.setListaItemPedido(listaItemPedido);


        while (pageController != 4) {
            menuInicial();
            pageController = tc.nextInt();

            switch (pageController) {
                case 1:
                    listaDeClientes.add(menuCadastrarCliente(tc));
                    break;
                case 2:
                    Optional<Cliente> clienteLogado = menuLogarCliente(tc);
                    if (clienteLogado.isPresent()) {
                        while(clienteController != 3) {
                            menuDoCliente();
                            clienteController = tc.nextInt();
                            switch (clienteController) {
                                case 1:
                                    break;
                                case 2:

                                    break;
                                case 3:
                                    System.out.println("Volte sempre!!");
                                    break;
                                default:
                                    System.out.println("Selecione um menu existente 😀");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Cliente não existe");
                    }
                    break;
                case 3:
                    System.out.println("Obrigado por usar nosso programa 😍🥰");
                    break;
                default:
                    System.out.println("Selecione um menu existente 😀");
                    break;
            }
        }
        // CADASTRAR CLIENTE


        // LOGAR COM O CLIENTE

        // REALIZAR UM PEDIDO NA AMAZON DOS GURI
        // ESCOLHER UM VENDEDOR DISPONÍVEL PARA AUXILIAR A COMPRA
        // ADICIONAR ITENS NO CARRINHO
        // REMOVER ITENS DO CARRINHO
        // ATUALIZAR ITENS DO CARRINHO

        // FINALIZAR COMPRA

        // PEDIDOS REALIZADOS

        // LOGAR COM VENDEDOR
        // FINALIZAR PEDIDOS
    }

    static Cliente menuCadastrarCliente(Scanner tc) {
        System.out.println("---------------------------------------");
        System.out.println("         Cadastrar Cliente             ");
        System.out.print("Nome: ");
        String nome = tc.next();
        System.out.print("E-mail: ");
        String email = tc.next();
        System.out.print("Senha: ");
        String senha = tc.next();

        return new Cliente(nome, email, senha);
    }

    static Optional<Cliente> menuLogarCliente(Scanner tc) {
        System.out.println("---------------------------------------");
        System.out.println("            Logar em Cliente           ");
        System.out.println("\n\nE-mail: ");
        String email = tc.next();
        System.out.println("Senha: ");
        String senha = tc.next();

        return listaDeClientes.stream()
                .filter(cliente -> cliente.getEmail().equals(email) && cliente.getSenha().equals(senha))
                .findFirst();
    }
    static void listaDePedidos() {

    }

    static void menuInicial() {
        System.out.println("---------------------------------------");
        System.out.println("      Bem Vindo a Amazon dos Guri      ");
        System.out.println("\n\n1 - Para cadastrar cliente");
        System.out.println("2 - Para logar com cliente");
        System.out.println("3 - Para finalizar o programa");
    }

    static void menuDoCliente() {
        System.out.println("---------------------------------------");
        System.out.println("     Bem Vindo ao menu do Cliente      ");
        System.out.println("\n\n1 - Fazer um Pedido");
        System.out.println("2 - Seus pedidos");
        System.out.println("3 - Sair");
    }
}
