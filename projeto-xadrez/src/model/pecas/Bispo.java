package model.pecas;

import model.Peca;
import view.Cores;
import model.Posicao;

import javax.swing.*;

public class Bispo extends Peca {
    public Bispo(Posicao posicao, Cores cor) {
       super(posicao, cor);
   }
    public Bispo(Cores cor) {
        super(cor);
    }

    @Override
    public ImageIcon getIcon() {
        // Define o caminho para os ícones dentro da pasta de recursos
        String caminhoIcone;

        if (getCor() == Cores.BRANCA) {
            caminhoIcone = "images/white-bishop.png";
        } else {
            caminhoIcone = "images/black-bishop.png";
        }

        return carregaIcone(caminhoIcone);
    }

    @Override
    public String toString() {
        return (getCor() == Cores.BRANCA)
                ? "\u2657"
                : "\u265D";
    }

    // Logica para a movimentacao do Bispo, levando em conta sua movimentacao diagonal
    // O bispo se mexe apenas nas diagonais, sem limite de unidades que pode se mexer, alem do claro, o limite do tabuleiro.
    @Override
    public boolean verificaMov(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro) {
        int linhaInicial = posIni.getLinha() ;
        int colunaInicial = posIni.getColuna() ;
        int linhaFinal = posFinal.getLinha() ;
        int colunaFinal = posFinal.getColuna()  ;

        int moduloLinha = Math.abs(linhaFinal - linhaInicial);
        int moduloColuna = Math.abs(colunaFinal - colunaInicial);

        if (moduloLinha != moduloColuna) return false; //verifica se realmente anda na diagonal

        boolean caminhoLivre = verificaCaminhoDiagonal(posIni, posFinal, tabuleiro);

        return caminhoLivre;
    }
}
