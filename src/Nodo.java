// Classe Nodo representa cada nó da árvore binária Morse
public class Nodo {
    String caractere; // caractere armazenado no nó
    Nodo esquerda;    // filho esquerdo (ponto .)
    Nodo direita;     // filho direito (traço -)

    // Construtor inicializa o nó com o caractere fornecido
    public Nodo(String caractere) {
        this.caractere = caractere;
        this.esquerda = null;
        this.direita = null;
    }
}
