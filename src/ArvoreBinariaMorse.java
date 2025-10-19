public class ArvoreBinariaMorse {
    Nodo raiz;

    public ArvoreBinariaMorse() {
        raiz = new Nodo(""); // raiz vazia
    }

    // Inserção de caractere com base no código Morse
    public void inserir(String codigo, String caractere) {
        Nodo atual = raiz;
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new Nodo("");
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    atual.direita = new Nodo("");
                }
                atual = atual.direita;
            }
        }
        atual.caractere = caractere;
    }

    // Busca o caractere pelo código Morse
    public String buscar(String codigo) {
        Nodo atual = raiz;
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                atual = atual.direita;
            }
            if (atual == null) {
                return "";
            }
        }
        return atual.caractere;
    }

    // Busca mensagem completa
    public String buscarMensagem(String mensagem) {
        String[] partes = mensagem.split(" ");
        String resultado = "";
        for (int i = 0; i < partes.length; i++) {
            if (!partes[i].equals("")) {
                resultado += buscar(partes[i]);
            } else {
                resultado += " ";
            }
        }
        return resultado;
    }

    // Remover caractere
    public void remover(String codigo) {
        removerRec(raiz, codigo, 0);
    }

    private Nodo removerRec(Nodo atual, String codigo, int i) {
        if (atual == null) return null;

        if (i == codigo.length()) {
            atual.caractere = "";
            return atual;
        }

        char simbolo = codigo.charAt(i);
        if (simbolo == '.') {
            atual.esquerda = removerRec(atual.esquerda, codigo, i + 1);
        } else if (simbolo == '-') {
            atual.direita = removerRec(atual.direita, codigo, i + 1);
        }
        return atual;
    }

    // Exibir árvore hierarquicamente
    /* public void exibirArvore() {
        exibirArvoreRec(raiz, 0);
    }

    private void exibirArvoreRec(Nodo atual, int nivel) {
        if (atual == null) return;

        // Exibe primeiro o filho direito (para o lado)
        exibirArvoreRec(atual.direita, nivel + 1);

        // Espaços para mostrar hierarquia
        for (int i = 0; i < nivel; i++) {
            System.out.print("      ");
        }

        // Exibe o caractere do nó (somente se não for nulo)
        if (atual.caractere != null && !atual.caractere.equals("")) {
            System.out.println("(" + atual.caractere + ")");
        } else {
            System.out.println("( )"); // pode remover se quiser ocultar nós vazios
        }

        // Exibe o filho esquerdo
        exibirArvoreRec(atual.esquerda, nivel + 1);
    }

     */

    public void exibirArvore() {
        int h = altura(raiz);
        int cols = pow2(h) * 1; // 3 é o espaçamento do nó
        String[][] mat = new String[h][cols];

        // Inicializa a matriz com espaços
        for (int i = 0; i < h; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = "   ";

        preencherMatriz(raiz, mat, 0, cols / 2, cols / 4);

        // Imprime linha por linha
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
    }

    private void preencherMatriz(Nodo n, String[][] mat, int linha, int col, int offset) {
        if (n == null) return;
        mat[linha][col] = "(" + (n.caractere.equals("") ? " " : n.caractere) + ")";
        if (linha + 1 < mat.length) {
            if (n.esquerda != null)
                preencherMatriz(n.esquerda, mat, linha + 1, col - offset, offset / 2);
            if (n.direita != null)
                preencherMatriz(n.direita, mat, linha + 1, col + offset, offset / 2);
        }
    }

    private int altura(Nodo n) {
        if (n == null) return 0;
        int e = altura(n.esquerda);
        int d = altura(n.direita);
        return 1 + (e > d ? e : d);
    }

    private int pow2(int n) {
        int r = 1;
        for (int i = 0; i < n; i++) r *= 2;
        return r;
    }


    // Buscar código Morse a partir de uma letra
    public String buscarCodigo(String letra) {
        return buscarCodigoRec(raiz, letra, "");
    }

    private String buscarCodigoRec(Nodo atual, String letra, String caminho) {
        if (atual == null) return "";
        if (atual.caractere.equals(letra)) return caminho;

        String esq = buscarCodigoRec(atual.esquerda, letra, caminho + ".");
        if (!esq.equals("")) return esq;

        String dir = buscarCodigoRec(atual.direita, letra, caminho + "-");
        if (!dir.equals("")) return dir;

        return "";
    }

    public String traduzirMensagemParaMorse(String mensagem) {
        String resultado = "";
        for (int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);

            if (c == ' ') {
                // separa palavras
                resultado = resultado + " / ";
            } else {
                String codigo = buscarCodigo("" + c);
                if (codigo != null) {
                    resultado = resultado + codigo + " ";
                } else {
                    resultado = resultado + "? ";
                }
            }
        }

        // remove espaços extras no final manualmente
        String ajustado = "";
        for (int i = 0; i < resultado.length(); i++) {
            if (!(i == resultado.length() - 1 && resultado.charAt(i) == ' ')) {
                ajustado = ajustado + resultado.charAt(i);
            }
        }

        return ajustado;
    }

}
