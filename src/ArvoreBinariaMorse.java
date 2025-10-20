// Classe que representa a árvore binária do código Morse
public class ArvoreBinariaMorse {
    Nodo raiz; // Nó raiz da árvore

    // Construtor inicializa a árvore com um nó raiz vazio
    public ArvoreBinariaMorse() {
        raiz = new Nodo(""); // raiz sem caractere definido
    }

    // Inserção de um caractere na árvore usando seu código Morse
    public void inserir(String codigo, String caractere) {
        Nodo atual = raiz;

        // Percorre cada símbolo do código Morse
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') { // ponto representa filho esquerdo
                if (atual.esquerda == null) { // se não existe, cria
                    atual.esquerda = new Nodo("");
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') { // traço representa filho direito
                if (atual.direita == null) { // se não existe, cria
                    atual.direita = new Nodo("");
                }
                atual = atual.direita;
            }
        }

        // Atribui o caractere ao nó final
        atual.caractere = caractere;
    }

    // Busca um caractere a partir do código Morse
    public String buscar(String codigo) {
        Nodo atual = raiz;

        // Percorre a árvore conforme os símbolos
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') atual = atual.esquerda;
            else if (simbolo == '-') atual = atual.direita;

            // Se nó não existe, código não encontrado
            if (atual == null) return "";
        }

        return atual.caractere; // retorna o caractere encontrado
    }

    // Busca mensagem completa em Morse e traduz para texto
    public String buscarMensagem(String mensagem) {
        // Divisão manual por espaços
        String[] partes = new String[1000]; // tamanho máximo arbitrário
        int count = 0; // número de elementos válidos
        String temp = "";
        int i = 0;
        while (i < mensagem.length()) {
            char c = mensagem.charAt(i);
            if (c != ' ') {
                temp = temp + c;
            } else {
                partes[count] = temp;
                count++;
                temp = "";
            }
            i++;
        }
        partes[count] = temp;
        count++;

        // Monta resultado sem usar length de array
        String resultado = "";
        int idx = 0;
        while (idx < count) {
            if (compararStrings(partes[idx], "") != 0) { // evita códigos vazios
                resultado = resultado + buscar(partes[idx]); // busca cada código
            } else {
                resultado = resultado + " "; // espaço entre palavras
            }
            idx++;
        }

        return resultado;
    }

    // Remove um caractere da árvore
    public void remover(String codigo) {
        removerRec(raiz, codigo, 0);
    }

    // Função recursiva para remoção de nó
    private Nodo removerRec(Nodo atual, String codigo, int i) {
        if (atual == null) return null;

        // Quando chega no nó final, limpa o caractere
        if (i == codigo.length()) {
            atual.caractere = "";
            return atual;
        }

        char simbolo = codigo.charAt(i);
        if (simbolo == '.') atual.esquerda = removerRec(atual.esquerda, codigo, i + 1);
        else if (simbolo == '-') atual.direita = removerRec(atual.direita, codigo, i + 1);

        return atual;
    }

    // Exibe a árvore horizontalmente em console
    public void exibirArvore() {
        int h = altura(raiz); // altura da árvore
        int cols = pow2(h);   // largura da matriz baseada na altura

        // Inicializa matriz manualmente sem length
        String[][] mat = new String[h][cols];
        int i = 0;
        while (i < h) {
            int j = 0;
            while (j < cols) {
                mat[i][j] = "   ";
                j++;
            }
            i++;
        }

        // Preenche a matriz com os valores da árvore
        preencherMatriz(raiz, mat, 0, cols / 2, cols / 4);

        // Imprime a matriz linha por linha
        i = 0;
        while (i < h) {
            int j = 0;
            while (j < cols) {
                System.out.print(mat[i][j]);
                j++;
            }
            System.out.println();
            i++;
        }
    }

    // Preenche a matriz recursivamente com os nós
    private void preencherMatriz(Nodo n, String[][] mat, int linha, int col, int offset) {
        if (n == null) return;

        // Coloca o caractere no formato (X) ou espaço se vazio
        if (compararStrings(n.caractere, "") == 0) mat[linha][col] = "( )";
        else mat[linha][col] = "(" + n.caractere + ")";

        // Chamada recursiva para os filhos
        // Percorre linha + 1 apenas se não ultrapassar altura máxima conhecida
        int alturaMax = 7; // valor máximo de linhas da matriz (coloquei 7 por garantia, mas são 6)
        if (linha + 1 < alturaMax) {
            if (n.esquerda != null)
                preencherMatriz(n.esquerda, mat, linha + 1, col - offset, offset / 2);
            if (n.direita != null)
                preencherMatriz(n.direita, mat, linha + 1, col + offset, offset / 2);
        }
    }

    // Calcula a altura da árvore recursivamente
    private int altura(Nodo n) {
        if (n == null) return 0;
        int e = altura(n.esquerda);
        int d = altura(n.direita);
        return 1 + (e > d ? e : d);
    }

    // Calcula potência de 2 (2^n) sem usar bibliotecas externas
    private int pow2(int n) {
        int r = 1;
        int i = 0;
        while (i < n) {
            r = r * 2;
            i++;
        }
        return r;
    }

    // Busca código Morse a partir de uma letra
    public String buscarCodigo(String letra) {
        return buscarCodigoRec(raiz, letra, "");
    }

    // Função recursiva para encontrar caminho da letra
    private String buscarCodigoRec(Nodo atual, String letra, String caminho) {
        if (atual == null) return "";
        if (compararStrings(atual.caractere, letra) == 0) return caminho; // letra encontrada

        // Busca no filho esquerdo (.)
        String esq = buscarCodigoRec(atual.esquerda, letra, caminho + ".");
        if (compararStrings(esq, "") != 0) return esq;

        // Busca no filho direito (-)
        String dir = buscarCodigoRec(atual.direita, letra, caminho + "-");
        if (compararStrings(dir, "") != 0) return dir;

        return ""; // não encontrou
    }

    // Traduz mensagem de texto para código Morse
    public String traduzirMensagemParaMorse(String mensagem) {
        String resultado = "";

        int i = 0;
        while (i < mensagem.length()) {
            char c = mensagem.charAt(i);

            if (c == ' ') {
                resultado = resultado + " / "; // separador de palavras
            } else {
                String codigo = buscarCodigo("" + c); // busca código do caractere
                if (compararStrings(codigo, "") != 0) resultado = resultado + codigo + " ";
                else resultado = resultado + "? "; // caractere não encontrado
            }
            i++;
        }

        // Remove o último espaço extra manualmente
        String ajustado = "";
        i = 0;
        while (i < resultado.length()) {
            if (!(i == resultado.length() - 1 && resultado.charAt(i) == ' ')) {
                ajustado = ajustado + resultado.charAt(i);
            }
            i++;
        }

        return ajustado;
    }

    // Função auxiliar de comparação manual de strings (0 = iguais, 1 = diferentes)
    public static int compararStrings(String a, String b) {
        if (a == null && b == null) return 0;
        if (a == null || b == null) return 1;

        if (a.length() != b.length()) return 1;

        int i = 0;
        while (i < a.length()) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);

            // Converte minúsculas para maiúsculas manualmente
            if (ca >= 'a' && ca <= 'z') ca = (char)(ca - 32);
            if (cb >= 'a' && cb <= 'z') cb = (char)(cb - 32);

            if (ca != cb) return 1;
            i++;
        }
        return 0;
    }
}
