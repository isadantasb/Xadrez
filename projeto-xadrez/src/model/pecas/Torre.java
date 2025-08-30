
package model.pecas;

import model.Peca;
import view.Cores;
import model.Posicao;

import javax.swing.*;

public class Torre extends Peca {
   public Torre(Posicao posicao, Cores cor) {
       super(posicao, cor);
   }

    public Torre(Cores cor) {
        super(cor);
    }

    // A torre se movimenta em linha reta (vertical ou horizontalmente) quantas posições quiser
    // Para testar tire a camada dos peões
    @Override
    public boolean verificaMov(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro) {
        int linhaInicial = posIni.getLinha();
        int colunaInicial = posIni.getColuna();
        int linhaFinal = posFinal.getLinha();
        int colunaFinal = posFinal.getColuna();

        // Verifica se a torre deseja se movimentar diagonalmente
        if (linhaInicial != linhaFinal && colunaInicial != colunaFinal) {return false;}

        // verifica se há peças no caminho reto
        boolean caminhoLivre = verificaCaminhoReto(posIni, posFinal, tabuleiro);

            return caminhoLivre;
    }


    @Override
    public String toString() {
        return "T";
    }
    @Override
    public ImageIcon getIcon() {
        // Define o caminho para os ícones dentro da pasta de recursos
        String caminhoIcone;

        if (getCor() == Cores.BRANCA) {
            caminhoIcone = "images/white-rook.png";
        } else {
            caminhoIcone = "images/black-rook.png";
        }

        return carregaIcone(caminhoIcone);
    }
}

