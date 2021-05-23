import domain.*;

import java.io.IOException;
import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Cliente> listaClientes = new ArrayList<>();
    private static Companhia companhia = new Companhia("UsGuri", "9999999", "ADMIN", "ADMIN");
    private static Pedido pedidoMomentaneo = new Pedido();

    private static int menuPageController = 0;
    private static int clientePageController = 0;
    private static int adminPageController = 0;
    private static int pedidoClientePageController = 0;

    public static void main(String[] args) {
        while (menuPageController != 4) {
            menuInicial();
            menuPageController = scanner.nextInt();

            switch (menuPageController) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    loginAdmin();
                    break;
                case 3:
                    loginCliente();
                    break;
                case 4:
                    mensagemSair();
                    break;
                default:
                    mensagemOpcaoInvalida();
            }
        }
    }

    private static void menuInicial() {
        System.out.println("|-------------------------------------|");
        System.out.println("|     Bem Vindo a Amazon dos Guri     |");
        System.out.println("|-------------------------------------|");
        System.out.println("|     1 - Cadastrar                   |");
        System.out.println("|     2 - Área admin                  |");
        System.out.println("|     3 - Logar                       |");
        System.out.println("|     4 - Sair                        |");
        System.out.println("|-------------------------------------|");
        System.out.print("|valor: ");
    }

    private static void cadastrarCliente() {
        System.out.println("|-------------------------------------|");
        System.out.println("|          Cadastrar Cliente          |");
        System.out.println("|-------------------------------------|");

        System.out.print("|Nome: ");
        String nome = scanner.next();

        System.out.print("|E-mail: ");
        String email = scanner.next();
        System.out.print("|Senha: ");
        String senha = scanner.next();

        listaClientes.add(new Cliente(nome, email, senha));

        System.out.println();
        System.out.println("|-------------------------------------|");
        System.out.println("|         Cadastro Realizado          |");
        System.out.println("|-------------------------------------|");
        System.out.println();
    }

    private static void loginAdmin() {
        Companhia companhia = adminLogado();
        if (Objects.nonNull(companhia)) {
            gerenciarAdmin();
        } else {
            System.out.println("|-------------------------------------|");
            System.out.println("|      Não foí possível acessar!      |");
            System.out.println("|-------------------------------------|");
        }
    }

    private static Companhia adminLogado() {
        System.out.println("|-------------------------------------|");
        System.out.println("|          Área Admin (Logar)         |");
        System.out.println("|-------------------------------------|");
        System.out.print("|Login: ");
        String email = scanner.next();

        System.out.print("|Senha: ");
        String senha = scanner.next();

        if (companhia.getLogin().equals(email) && companhia.getSenha().equals(senha)) {
            return companhia;
        }
        return null;
    }

    private static void gerenciarAdmin() {
        while (adminPageController != 3) {
            menuAdmin();
            adminPageController = scanner.nextInt();
            switch (adminPageController) {
                case 1:
                    visualizarPedidosCompanhia();
                    break;
                case 2:
                    finalizarPedidoMaisRecente();
                    break;
                case 3:
                    mensagemSair();
                    break;
                default:
                    mensagemOpcaoInvalida();
            }
        }
    }

    private static void menuAdmin() {
        System.out.println("|-------------------------------------|");
        System.out.println("|             Menu Admin              |");
        System.out.println("|-------------------------------------|");
        System.out.println("|   1 - Visualizar Pedidos            |");
        System.out.println("|   2 - Finalizar Pedido Mais Recente |");
        System.out.println("|   3 - Sair                          |");
        System.out.println("|-------------------------------------|");
        System.out.print("|valor: ");
    }

    private static void visualizarPedidosCompanhia() {
        if (!companhia.getListaPedidosCompanhia().isEmpty()) {
            List<Pedido> listaPedido = companhia.getListaPedidosCompanhia();
            for (int i = 0; i < listaPedido.size(); i++) {
                Cliente cliente = listaPedido.get(i).getCliente();
                System.out.println("|-------------------------------------|");
                System.out.println("|Pedido: " + i + 1);
                System.out.println("|Cliente: " + cliente.getNome());
                System.out.println("|-------------------------------------|");
                List<ItemPedido> listaItemPedido = listaPedido.get(i).getListaItemPedido();
                for (int j = 0; j < listaItemPedido.size(); j++) {
                    ItemPedido itemPedido = listaItemPedido.get(j);
                    System.out.println("|-------------------------------------|");
                    System.out.println("|               Produtos              |");
                    System.out.println("|-------------------------------------|");
                    System.out.println("|Nome: " + itemPedido.getProduto().getNome());
                    System.out.println("|Marca: " + itemPedido.getProduto().getMarca());
                    System.out.println("|Quantidade: " + itemPedido.getQuantidade());
                }
                System.out.println("|-------------------------------------|");
                System.out.println("|            Valor Total              |");
                System.out.println("|-------------------------------------|");
                System.out.println("|Valor: R$" + listaPedido.get(i).getValorTotal());
            }

        } else {
            System.out.println("|-------------------------------------|");
            System.out.println("|       Sem pedidos no momento!       |");
            System.out.println("|-------------------------------------|");
        }
    }

    private static void finalizarPedidoMaisRecente() {
        if (!companhia.getListaPedidosCompanhia().isEmpty()) {
            companhia.getListaPedidosCompanhia().removeFirst();
            System.out.println("|-------------------------------------|");
            System.out.println("|          Pedido Finalizado          |");
            System.out.println("|-------------------------------------|");
        } else {
            System.out.println("|-------------------------------------|");
            System.out.println("|       Sem pedidos no momento!       |");
            System.out.println("|-------------------------------------|");
        }
    }

    private static void loginCliente() {
        Optional<Cliente> clienteLogado = clienteLogado();

        if (clienteLogado.isPresent()) {
            while (clientePageController != 3) {
                menuDoCliente(clienteLogado.get());
                clientePageController = scanner.nextInt();
                switch (clientePageController) {
                    case 1:
                        realizarPedido(clienteLogado.get());
                        break;
                    case 2:
                        imprimirPedidos(clienteLogado.get());
                        break;
                    case 3:
                        mensagemSair();
                        break;
                    default:
                        mensagemOpcaoInvalida();
                        break;
                }
            }
        } else {
            loginInvalido();
        }
        clientePageController = 0;
    }

    private static Optional<Cliente> clienteLogado() {
        System.out.println("|---------------------------------------|");
        System.out.println("|            Logar em Cliente           |");
        System.out.println("|---------------------------------------|");
        System.out.print("|E-mail: ");
        String email = scanner.next();
        System.out.print("|Senha: ");
        String senha = scanner.next();
        System.out.println("|---------------------------------------|");

        return listaClientes.stream()
                .filter(cliente -> cliente.getEmail().equals(email) && cliente.getSenha().equals(senha))
                .findFirst();
    }

    private static void menuDoCliente(Cliente cliente) {
        System.out.println("|---------------------------------------|");
        System.out.println("|             Bem Vindo "+ cliente.getNome() + "         |");
        System.out.println("|---------------------------------------|");
        System.out.println("|1 - Fazer um Pedido");
        System.out.println("|2 - Seus pedidos");
        System.out.println("|3 - Sair");
        System.out.println("|---------------------------------------|");
        System.out.print("|valor: ");
    }

    private static void realizarPedido(Cliente cliente) {
        while (pedidoClientePageController != 3) {
            menuRealizarPedido();
            pedidoClientePageController = scanner.nextInt();
            switch (pedidoClientePageController) {
                case 1:
                    selecionarProdutosPedido();
                    break;
                case 2:
                    finalizarPedidoByCliente(cliente);
                    break;
                case 3:
                    mensagemSair();
                    break;
                default:
                    mensagemOpcaoInvalida();
                    break;
            }
            if (pedidoClientePageController == 2)
                break;
        }
        pedidoClientePageController = 0;
    }

    private static void selecionarProdutosPedido() {
        List<Produto> listaProdutos = companhia.getListaProdutosDisponiveis();

        listarProdutosDisponiveis();
        System.out.println();
        System.out.print("|Código do produto: ");
        int codigo = scanner.nextInt();
        System.out.print("|Quantidade: ");
        int quantidade = scanner.nextInt();

        if (listaProdutos.size() >= codigo && codigo > 0 && quantidade > 0) {
            ItemPedido newItemPedido = new ItemPedido(listaProdutos.get(codigo - 1), quantidade);
            pedidoMomentaneo.getListaItemPedido().add(newItemPedido);
        } else {
            produtoInvalido();
        }
    }

    private static void listarProdutosDisponiveis() {
        List<Produto> listaProdutos = companhia.getListaProdutosDisponiveis();

        for (int i = 0; i < listaProdutos.size(); i++) {
            System.out.println("|--------------------------------------|");
            System.out.println("|" + (i + 1) + " - " + listaProdutos.get(i).getNome());
            System.out.println("|Marca: " + listaProdutos.get(i).getMarca());
            System.out.println("|Valor: " + listaProdutos.get(i).getValor());
        }
    }

    private static void finalizarPedidoByCliente(Cliente cliente) {
        if (!pedidoMomentaneo.getListaItemPedido().isEmpty()) {
            pedidoMomentaneo.setCliente(cliente);
            pedidoMomentaneo.setValorTotal();
            companhia.getListaPedidosCompanhia().addLast(pedidoMomentaneo);
            cliente.getListaPedidoCliente().add(pedidoMomentaneo);
            pedidoMomentaneo = new Pedido();
        } else {
            naoPossuiItensNoCarrinho();
            pedidoClientePageController = 0;
        }

    }

    private static void menuRealizarPedido() {
        System.out.println("|--------------------------------------|");
        System.out.println("|     Bem Vindo ao menu de Pedidos     |");
        System.out.println("|--------------------------------------|");
        System.out.println("|1 - Adicionar item ao pedido");
        System.out.println("|2 - Finalizar pedido");
        System.out.println("|3 - Sair");
        System.out.println("|--------------------------------------|");
        System.out.print("|valor: ");
    }

    private static void imprimirPedidos(Cliente cliente) {
        if (cliente.getListaPedidoCliente().isEmpty()) {
            semPedidos();
        } else {
            List<Pedido> listaPedido = cliente.getListaPedidoCliente();
            for (int i = 0; i < listaPedido.size(); i++) {
                System.out.println("|-------------------------------------|");
                System.out.println("|Pedido: " + i + 1);
                System.out.println("|-------------------------------------|");
                List<ItemPedido> listaItemPedido = listaPedido.get(i).getListaItemPedido();
                for (int j = 0; j < listaItemPedido.size(); j++) {
                    ItemPedido itemPedido = listaItemPedido.get(j);
                    System.out.println("|-------------------------------------|");
                    System.out.println("|               Produtos              |");
                    System.out.println("|-------------------------------------|");
                    System.out.println("|Nome: " + itemPedido.getProduto().getNome());
                    System.out.println("|Marca: " + itemPedido.getProduto().getMarca());
                    System.out.println("|Quantidade: " + itemPedido.getQuantidade());
                }
                System.out.println("|-------------------------------------|");
                System.out.println("|            Valor Total              |");
                System.out.println("|-------------------------------------|");
                System.out.println("|Valor: R$" + listaPedido.get(i).getValorTotal());
                System.out.println();
            }
        }
    }

    private static void mensagemSair() {
        System.out.println();
        System.out.println("|-------------------------------------|");
        System.out.println("|Obrigado por usar nosso programa 😍🥰|");
        System.out.println("|-------------------------------------|");
        System.out.println();
    }

    private static void mensagemOpcaoInvalida() {
        System.out.println();
        System.out.println("|-------------------------------------|");
        System.out.println("|    Selecione um menu existente 😀   |");
        System.out.println("|-------------------------------------|");
        System.out.println();
    }

    private static void loginInvalido() {
        System.out.println();
        System.out.println("|-------------------------------------|");
        System.out.println("|       Email/Senha está errado!      |");
        System.out.println("|-------------------------------------|");
        System.out.println();
    }

    private static void semPedidos() {
        System.out.println();
        System.out.println("|-------------------------------------|");
        System.out.println("|    Você ainda não possui pedidos!   |");
        System.out.println("|-------------------------------------|");
        System.out.println();
    }

    private static void produtoInvalido() {
        System.out.println("");
        System.out.println();
        System.out.println("|-------------------------------------------|");
        System.out.println("| Produto não existe ou quantidade inválida |");
        System.out.println("|-------------------------------------------|");
        System.out.println();
    }

    private static void naoPossuiItensNoCarrinho() {
        System.out.println();
        System.out.println("|-------------------------------------|");
        System.out.println("|   Você não possui itens no carrinho |");
        System.out.println("|-------------------------------------|");
        System.out.println();
    }
}
