package model.pecas;

import model.Peca;
import view.Cores;
import model.Posicao;

import javax.swing.*;

public class Dama extends Peca {
    
    public Dama(Posicao posicao, Cores cor) {
        super(posicao, cor);
    }
    public Dama(Cores cor) {
        super(cor);
    }

    // a dama anda reto ou na diagonal quantas casas quiser
    @Override
    public boolean verificaMov(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro) {
        int linhaInicial = posIni.getLinha();
        int colunaInicial = posIni.getColuna();
        int linhaFinal = posFinal.getLinha();
        int colunaFinal = posFinal.getColuna();

        // ve se o movimento é reto
        boolean movimentoReto = (linhaInicial == linhaFinal || colunaInicial == colunaFinal);
        // ve se o movimento é diagonal
        boolean movimentoDiagonal = (Math.abs(linhaFinal - linhaInicial) == Math.abs(colunaFinal - colunaInicial));

        if (!movimentoReto && !movimentoDiagonal) {return false;}

        boolean caminhoLivre;
        // se o movimento for reto tem que verificar o caminho pela função verificaCaminhoReto
        if (movimentoReto) {
            caminhoLivre = verificaCaminhoReto(posIni, posFinal, tabuleiro);
        } // se o caminho for diagonal tem que verificar pela função verificaCaminhoDiagonal
        else {
            caminhoLivre = verificaCaminhoDiagonal(posIni, posFinal, tabuleiro);
        }

        return caminhoLivre;

    }
    @Override
    public String toString() {
        return "D";
    }

    @Override
    public ImageIcon getIcon() {
        // Define o caminho para os ícones dentro da pasta de recursos
        String caminhoIcone;

        if (getCor() == Cores.BRANCA) {
            caminhoIcone = "images/white-queen.png";
        } else {
            caminhoIcone = "images/black-queen.png";
        }

        return carregaIcone(caminhoIcone);
    }
}
