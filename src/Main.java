import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        boolean sair = false;

        while (!sair) {
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
            sc.nextLine(); //limpa buffer

            switch (opcao) {
                case 1: //insere automaticamente
                    inicializarArvore(arvore);
                    System.out.println("Inserção automática concluída!");
                    break;

                case 2: //insere manualmente
                    System.out.print("Digite o caractere (A-Z ou 0-9): ");
                    String c = sc.nextLine().toUpperCase();
                    System.out.print("Digite o código Morse (ex: .-): ");
                    String codigo = sc.nextLine();
                    arvore.inserir(codigo, c);
                    System.out.println("Caractere inserido com sucesso!");
                    break;

                case 3: //busca caracter
                    System.out.print("Digite o código Morse: ");
                    String cod = sc.nextLine();
                    String resultado = arvore.buscar(cod);
                    if (resultado.equals("")) System.out.println("Código não encontrado!");
                    else System.out.println("Resultado: " + resultado);
                    break;

                case 4: //busca morse
                    System.out.print("Digite a letra: ");
                    String letra = sc.nextLine().toUpperCase();
                    String codigoLetra = arvore.buscarCodigo(letra);
                    if (codigoLetra.equals("")) System.out.println("Letra não encontrada!");
                    else System.out.println("Código Morse de " + letra + " é: " + codigoLetra);
                    break;

                case 5: //mostra arvore
                    System.out.println("\n=== ÁRVORE BINÁRIA DO CÓDIGO MORSE ===");
                    arvore.exibirArvore();
                    break;

                case 6: //transforma de morse para alfabeto
                    System.out.print("Digite uma mensagem em código Morse (separe por espaços): ");
                    String msg = sc.nextLine();
                    System.out.println("Tradução: " + arvore.buscarMensagem(msg));
                    break;

                case 7:
                    System.out.print("Digite uma mensagem para converter em código Morse: ");
                    String mensagem = sc.nextLine().toUpperCase();
                    String morse = arvore.traduzirMensagemParaMorse(mensagem);
                    System.out.println("Mensagem em código Morse:");
                    System.out.println(morse);
                    break;

                case 8: //remover caracter
                    System.out.print("Digite o caractere (A-Z ou 0-9) a remover: ");
                    String caractereRemover = sc.nextLine().toUpperCase();

                    String codigoRemover = arvore.buscarCodigo(caractereRemover);
                    if (codigoRemover.equals("")) {
                        System.out.println("Caractere não encontrado na árvore!");
                    } else {
                        arvore.remover(codigoRemover);
                        System.out.println("Caractere removido com sucesso!");
                    }
                    break;

                case 0:  //sair
                    sair = true;
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }

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
        for (int i = 0; i < morse.length; i++) {
            arvore.inserir(morse[i][1], morse[i][0]);
        }
    }
}
