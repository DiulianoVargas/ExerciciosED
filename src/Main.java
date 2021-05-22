import domain.*;

import java.io.IOException;
import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Cliente> listaClientes = new ArrayList<>();
    private static Companhia companhia = new Companhia("UsGuri", "9999999", "ADMIN", "ADMIN");

    private static int menuPageController = 0;
    private static int clientePageController = 0;
    private static int adminPageController = 0;
    private static int pedidoClientePageController = 0;
    private static int produtoPedidoClientePageController = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        while (menuPageController != 4) {
            menuInicial();
            menuPageController = scanner.nextInt();

            switch (menuPageController) {
                case 1: cadastrarCliente();
                    break;
                case 2: loginAdmin();
                    break;
                case 3: loginCliente();
                    break;
                case 4: System.out.println("Obrigado por usar nosso programa 😍🥰");
                    break;
                default: System.out.println("Selecione um menu existente 😀");
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
    }

    private static void loginAdmin() throws IOException {
       Companhia companhia = adminLogado();
       if (Objects.nonNull(companhia)) {
           gerenciarAdmin(companhia);
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

    private static void gerenciarAdmin(Companhia companhia) throws IOException {
        while (adminPageController != 3) {
            menuAdmin();
            adminPageController = scanner.nextInt();
            switch (adminPageController) {
                case 1: visualizarPedidosCompanhia();
                    break;
                case 2: finalizarPedidoMaisRecente();
                    break;
                case 3: System.out.println("Obrigado por usar nosso programa 😍🥰");
                    break;
                default: System.out.println("Selecione um menu existente 😀");
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
            for (int i = 1; i <= listaPedido.size(); i++) {
                Cliente cliente = listaPedido.get(i).getCliente();
                System.out.println("|-------------------------------------|");
                System.out.println("|Pedido: " + i);
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
            while(clientePageController != 3) {
                menuDoCliente();
                clientePageController = scanner.nextInt();
                switch (clientePageController) {
                    case 1: realizarPedido(clienteLogado.get());
                        break;
                    case 2: imprimirPedidos(clienteLogado.get());
                        break;
                    case 3: System.out.println("Volte sempre!!");
                        break;
                    default: System.out.println("Selecione um menu existente 😀");
                        break;
                }
            }
        } else {
            System.out.println("Email/Senha está errado!");
        }
    }

    private static Optional<Cliente> clienteLogado() {
        System.out.println("---------------------------------------");
        System.out.println("            Logar em Cliente           ");
        System.out.println("\n\nE-mail: ");
        String email = scanner.next();
        System.out.println("Senha: ");
        String senha = scanner.next();

        return listaClientes.stream()
                .filter(cliente -> cliente.getEmail().equals(email) && cliente.getSenha().equals(senha))
                .findFirst();
    }

    private static void listaDePedidos() {

    }

    private static void menuDoCliente() {
        System.out.println("---------------------------------------");
        System.out.println("     Bem Vindo ao menu do Cliente      ");
        System.out.println("\n\n1 - Fazer um Pedido");
        System.out.println("2 - Seus pedidos");
        System.out.println("3 - Sair");
    }

    private static void realizarPedido(Cliente cliente) {
        while(pedidoClientePageController != 2) {
            menuRealizarPedido();
            pedidoClientePageController = scanner.nextInt();
            switch (pedidoClientePageController) {
                case 1: selecionarProdutosPedido(cliente);
                    break;
                case 2: System.out.println("Volte sempre!!");
                    break;
                default: System.out.println("Selecione um menu existente 😀");
                    break;
            }
        }
    }

    private static void selecionarProdutosPedido(Cliente cliente) {
        listarProdutosDisponiveis();
    }

    private static void listarProdutosDisponiveis() {
        Produto notebook = new Produto(1000, "DELL", "Notebook");
        Produto tv = new Produto(5000, "LG", "Smart TV");

        List<Produto> listaProdutos = new ArrayList<>();

        listaProdutos.add(notebook);
        listaProdutos.add(tv);

        for (int i = 1; i <= listaProdutos.size(); i++) {
            System.out.println(i + " - " + listaProdutos.get(i).getNome());
            System.out.println("Marca: " + listaProdutos.get(i).getMarca());
            System.out.println("Valor: " + listaProdutos.get(i).getValor());
        }

    }

    private static void menuRealizarPedido() {
        System.out.println("---------------------------------------");
        System.out.println("     Bem Vindo ao menu de Pedidos     ");
        System.out.println("\n\n1 - Selecionar Produtos");
        System.out.println("2 - Sair");
    }

    private static void imprimirPedidos(Cliente cliente) {
        if (cliente.getListaPedidoCliente().isEmpty()) {
            System.out.println("Você ainda não possui pedidos!");
        } else {
            System.out.println(cliente.getListaPedidoCliente());
        }
    }
}
