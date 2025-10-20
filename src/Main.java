import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner para leitura do teclado
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse(); // Cria a árvore Morse
        boolean sair = false; // Flag para controlar o loop do menu

        // Loop principal do menu
        while (!sair) {
            // Exibe o menu de opções
            System.out.println("\n===== MENU: ÁRVORE BINÁRIA CÓDIGO MORSE =====");
            System.out.println("(1) - Inserir caracteres automaticamente (A-Z, 0-9)");
            System.out.println("(2) - Inserir caractere manualmente");
            System.out.println("(3) - Buscar caractere pelo código Morse");
            System.out.println("(4) - Buscar código Morse de uma letra");
            System.out.println("(5) - Exibir árvore hierárquica");
            System.out.println("(6) - Traduzir mensagem (código Morse -> texto)");
            System.out.println("(7) - Traduzir mensagem (texto -> código Morse)");
            System.out.println("(8) - Remover caractere");
            System.out.println("(0) - Sair");
            System.out.print("Digite a opção desejada: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // limpa buffer do Scanner

            switch (opcao) {
                case 1: // Inserção automática de todos os caracteres
                    inicializarArvore(arvore);
                    System.out.println("Inserção automática concluída!");
                    break;

                case 2: // Inserção manual de um caractere
                    System.out.print("Digite o caractere (A-Z ou 0-9): ");
                    String c = sc.nextLine();
                    System.out.print("Digite o código Morse (ex: .-): ");
                    String codigo = sc.nextLine();
                    arvore.inserir(codigo, c); // insere na árvore
                    System.out.println("Caractere inserido com sucesso!");
                    break;

                case 3: // Busca de caractere a partir do código Morse
                    System.out.print("Digite o código Morse: ");
                    String cod = sc.nextLine();
                    String resultado = arvore.buscar(cod); // busca na árvore
                    if (compararStrings(resultado, "") == 0)
                        System.out.println("Código não encontrado!");
                    else
                        System.out.println("Resultado: " + resultado);
                    break;

                case 4: // Busca do código Morse a partir de uma letra
                    System.out.print("Digite a letra: ");
                    String letra = sc.nextLine();
                    String codigoLetra = arvore.buscarCodigo(letra);
                    if (compararStrings(codigoLetra, "") == 0)
                        System.out.println("Letra não encontrada!");
                    else
                        System.out.println("Código Morse de " + letra + " é: " + codigoLetra);
                    break;

                case 5: // Exibe a árvore hierárquica
                    System.out.println("\n=== ÁRVORE BINÁRIA DO CÓDIGO MORSE ===");
                    arvore.exibirArvore();
                    break;

                case 6: // Traduz mensagem de código Morse para texto
                    System.out.print("Digite uma mensagem em código Morse (separe por espaços): ");
                    String msg = sc.nextLine();
                    System.out.println("Tradução: " + arvore.buscarMensagem(msg));
                    break;

                case 7: // Traduz mensagem de texto para código Morse
                    System.out.print("Digite uma mensagem para converter em código Morse: ");
                    String mensagem = sc.nextLine();
                    String morse = arvore.traduzirMensagemParaMorse(mensagem);
                    System.out.println("Mensagem em código Morse:");
                    System.out.println(morse);
                    break;

                case 8: // Remoção de caractere
                    System.out.print("Digite o caractere (A-Z ou 0-9) a remover: ");
                    String caractereRemover = sc.nextLine();

                    // Busca o código Morse correspondente para remover
                    String codigoRemover = arvore.buscarCodigo(caractereRemover);
                    if (compararStrings(codigoRemover, "") == 0) {
                        System.out.println("Caractere não encontrado na árvore!");
                    } else {
                        arvore.remover(codigoRemover); // remove o caractere
                        System.out.println("Caractere removido com sucesso!");
                    }
                    break;

                case 0:  // Sair do programa
                    sair = true;
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close(); // Fecha o Scanner
    }

    // Inicializa a árvore com todos os caracteres alfanuméricos e seus códigos Morse
    public static void inicializarArvore(ArvoreBinariaMorse arvore) {
        String[][] morse = {
                {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, {"E", "."},
                {"F", "..-."}, {"G", "--."}, {"H", "...."}, {"I", ".."}, {"J", ".---"},
                {"K", "-.-"}, {"L", ".-.."}, {"M", "--"}, {"N", "-."}, {"O", "---"},
                {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
                {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"}, {"Y", "-.--"},
                {"Z", "--.."}, {"1", ".----"}, {"2", "..---"}, {"3", "...--"},
                {"4", "....-"}, {"5", "....."}, {"6", "-...."}, {"7", "--..."},
                {"8", "---.."}, {"9", "----."}, {"0", "-----"}
        };

        // Insere cada caractere na árvore conforme o código Morse
        int linhas = 0;
        for (String[] s : morse) linhas++; // contar linhas manualmente

        for (int i = 0; i < linhas; i++) {
            arvore.inserir(morse[i][1], morse[i][0]);
        }
    }

    // Função para comparar strings manualmente (0 = iguais, 1 = diferentes)
    public static int compararStrings(String a, String b) {
        if (a == null && b == null) return 0; // ambas nulas = iguais
        if (a == null || b == null) return 1; // apenas uma é nula = diferentes

        int lenA = a.length();
        int lenB = b.length();

        if (lenA != lenB) return 1; // tamanhos diferentes = diferentes

        for (int i = 0; i < lenA; i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);

            // Converte minúsculas para maiúsculas manualmente
            if (ca >= 'a' && ca <= 'z') ca = (char)(ca - 32);
            if (cb >= 'a' && cb <= 'z') cb = (char)(cb - 32);

            if (ca != cb) return 1; // caractere diferente
        }

        return 0; // todas iguais
    }
}