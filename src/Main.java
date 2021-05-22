import domain.*;

import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Cliente> listaClientes = new ArrayList<>();
    private static Companhia companhia = new Companhia("UsGuri", "9999999", "ADMIN", "ADMIN");

    private static int menuPageController = 0;
    private static int clientePageController = 0;
    private static int pedidoClientePageController = 0;
    private static int produtoPedidoClientePageController = 0;

    public static void main(String[] args) {

        while (menuPageController != 4) {
            menuInicial();
            menuPageController = scanner.nextInt();

            switch (menuPageController) {
                case 1 -> cadastrarCliente();
                case 2 -> loginCliente();
                case 3 -> System.out.println("Obrigado por usar nosso programa 😍🥰");
                default -> System.out.println("Selecione um menu existente 😀");
            }
        }
    }

    private static void cadastrarCliente() {
        System.out.println("---------------------------------------");
        System.out.println("         Cadastrar Cliente             ");
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("E-mail: ");
        String email = scanner.next();
        System.out.print("Senha: ");
        String senha = scanner.next();

        listaClientes.add(new Cliente(nome, email, senha));
    }

    private static void loginCliente() {
        Optional<Cliente> clienteLogado = clienteLogado();

        if (clienteLogado.isPresent()) {
            while(clientePageController != 3) {
                menuDoCliente();
                clientePageController = scanner.nextInt();
                switch (clientePageController) {
                    case 1 -> realizarPedido(clienteLogado.get());
                    case 2 -> imprimirPedidos(clienteLogado.get());
                    case 3 -> System.out.println("Volte sempre!!");
                    default -> System.out.println("Selecione um menu existente 😀");
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

    private static void menuInicial() {
        System.out.println("---------------------------------------");
        System.out.println("      Bem Vindo a Amazon dos Guri      ");
        System.out.println("\n\n1 - Para cadastrar cliente");
        System.out.println("2 - Para logar com cliente");
        System.out.println("3 - Para finalizar o programa");
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
                case 1 -> selecionarProdutosPedido(cliente);
                case 2 -> System.out.println("Volte sempre!!");
                default -> System.out.println("Selecione um menu existente 😀");
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
