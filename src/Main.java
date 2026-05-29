import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ABB<Integer> abb = new ABB<>();
        Scanner scanner = new Scanner(System.in);
        int opcao, valor;

        do {
            System.out.println("\n===== MENU ABB =====");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Remover");
            System.out.println("3 - Pesquisar");
            System.out.println("4 - Caminhamento Em Ordem");
            System.out.println("5 - Quantidade de Nos");
            System.out.println("6 - Quantidade de Nos Folha");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Digite o valor a adicionar: ");
                    valor = scanner.nextInt();
                    try {
                        abb.adicionar(valor);
                        System.out.println("Valor " + valor + " adicionado com sucesso!");
                    } catch (RuntimeException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    System.out.println("\n--- Arvore em ordem apos adicionar ---");
                    try { abb.caminhamentoEmOrdem(); } catch (IllegalStateException e) { System.out.println("Arvore vazia."); }
                    break;

                case 2:
                    System.out.print("Digite o valor a remover: ");
                    valor = scanner.nextInt();
                    try {
                        abb.remover(valor);
                        System.out.println("Valor " + valor + " removido com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    System.out.println("\n--- Arvore em ordem apos remover ---");
                    try { abb.caminhamentoEmOrdem(); } catch (IllegalStateException e) { System.out.println("Arvore vazia."); }
                    break;

                case 3:
                    System.out.print("Digite o valor a pesquisar: ");
                    valor = scanner.nextInt();
                    try {
                        Integer resultado = abb.pesquisar(valor);
                        System.out.println("Valor encontrado: " + resultado);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("\n--- Caminhamento Em Ordem ---");
                    try { abb.caminhamentoEmOrdem(); } catch (IllegalStateException e) { System.out.println("Arvore vazia."); }
                    break;

                case 5:
                    System.out.println("Quantidade de nos: " + abb.quantidadeDeNos(null));
                    break;

                case 6:
                    if (abb.vazia()) {
                        System.out.println("Arvore vazia.");
                    } else {
                        System.out.println("Quantidade de nos folha: " + abb.quantidadeDeNosFolha(null));
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}