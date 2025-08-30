package model;

public class Posicao {
    public int linha;
    public int coluna;

    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    @Override
    public String toString() {
        return linha + ", " + coluna;
    }
}
