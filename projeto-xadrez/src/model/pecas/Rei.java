package model.pecas;

import model.Peca;
import view.Cores;
import model.Posicao;

import javax.swing.*;

public class Rei extends Peca {

    public Rei(Posicao posicao, Cores cor) {
        super(posicao, cor);
    }

    public Rei(Cores cor) {
        super(cor);
    }

    @Override
    public String toString(){
        return "R";
    }
    @Override
    public ImageIcon getIcon() {
        // Define o caminho para os ícones dentro da pasta de recursos
        String caminhoIcone;

        if (getCor() == Cores.BRANCA) {
            caminhoIcone = "images/white-king.png";
        } else {
            caminhoIcone = "images/black-king.png";
        }

        return carregaIcone(caminhoIcone);
    }
    @Override
    public boolean verificaMov(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro) {
        // O Rei pode apenas se mover 1 unidade, porém para qualquer direção

        int linhaInicial = posIni.getLinha();
        int colunaInicial = posIni.getColuna();
        int linhaFinal = posFinal.getLinha();
        int colunaFinal = posFinal.getColuna();

        int difLinha = Math.abs(linhaFinal - linhaInicial);
        int difColuna = Math.abs(colunaFinal - colunaInicial);

        if ((difColuna <= 1 && difLinha <= 1) && (difColuna + difLinha != 0)) //a difcoluna e a diflinha podem ser 1 ou 0, não podendo só ser as duas 0.
            return true;
        return false;
    }
}   
    