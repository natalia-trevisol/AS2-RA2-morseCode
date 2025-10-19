public class Nodo {
    String caractere;
    Nodo esquerda; // ponto (.)
    Nodo direita;  // traço (-)

    public Nodo(String caractere) {
        this.caractere = caractere;
        this.esquerda = null;
        this.direita = null;
    }
}
