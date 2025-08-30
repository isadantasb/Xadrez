package model;

import model.exceptions.MovementNotAllowedException;

public class PosicaoXadrez {
    private char coluna;
    private int linha;


    public PosicaoXadrez(char coluna, int linha) throws MovementNotAllowedException {
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
            throw new MovementNotAllowedException("Erro de posição!");
        }
        this.coluna = coluna;
        this.linha = linha;
    }
    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    // converte de PosicaoXadrez para Posicao da matriz
    public Posicao paraPosicao(){
        int linhaXadrez = 8 - linha;
        int colunaXadrez = coluna - 'a';
        return new Posicao(linhaXadrez, colunaXadrez);
    }
}
