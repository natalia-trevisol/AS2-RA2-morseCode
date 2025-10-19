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
        String[] partes = mensagem.split(" "); // separa cada código Morse
        String resultado = "";

        for (int i = 0; i < partes.length; i++) {
            if (!partes[i].equals("")) { // evita códigos vazios
                resultado += buscar(partes[i]); // busca cada código
            } else {
                resultado += " "; // espaço entre palavras
            }
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
        String[][] mat = new String[h][cols];

        // Inicializa matriz com espaços
        for (int i = 0; i < h; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = "   ";

        // Preenche a matriz com os valores da árvore
        preencherMatriz(raiz, mat, 0, cols / 2, cols / 4);

        // Imprime a matriz linha por linha
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
    }

    // Preenche a matriz recursivamente com os nós
    private void preencherMatriz(Nodo n, String[][] mat, int linha, int col, int offset) {
        if (n == null) return;

        // Coloca o caractere no formato (X) ou espaço se vazio
        mat[linha][col] = "(" + (n.caractere.equals("") ? " " : n.caractere) + ")";

        // Chamada recursiva para os filhos, ajustando posição horizontal
        if (linha + 1 < mat.length) {
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
        for (int i = 0; i < n; i++) r *= 2;
        return r;
    }

    // Busca código Morse a partir de uma letra
    public String buscarCodigo(String letra) {
        return buscarCodigoRec(raiz, letra, "");
    }

    // Função recursiva para encontrar caminho da letra
    private String buscarCodigoRec(Nodo atual, String letra, String caminho) {
        if (atual == null) return "";
        if (atual.caractere.equals(letra)) return caminho; // letra encontrada

        // Busca no filho esquerdo (.)
        String esq = buscarCodigoRec(atual.esquerda, letra, caminho + ".");
        if (!esq.equals("")) return esq;

        // Busca no filho direito (-)
        String dir = buscarCodigoRec(atual.direita, letra, caminho + "-");
        if (!dir.equals("")) return dir;

        return ""; // não encontrou
    }

    // Traduz mensagem de texto para código Morse
    public String traduzirMensagemParaMorse(String mensagem) {
        String resultado = "";

        for (int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);

            if (c == ' ') {
                resultado = resultado + " / "; // separador de palavras
            } else {
                String codigo = buscarCodigo("" + c); // busca código do caractere
                if (codigo != null) {
                    resultado = resultado + codigo + " ";
                } else {
                    resultado = resultado + "? "; // caractere não encontrado
                }
            }
        }

        // Remove o último espaço extra manualmente
        String ajustado = "";
        for (int i = 0; i < resultado.length(); i++) {
            if (!(i == resultado.length() - 1 && resultado.charAt(i) == ' ')) {
                ajustado = ajustado + resultado.charAt(i);
            }
        }

        return ajustado;
    }
}
