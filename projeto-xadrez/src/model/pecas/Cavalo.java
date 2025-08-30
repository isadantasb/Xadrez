/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pecas;

import model.Peca;
import view.Cores;
import model.Posicao;

import javax.swing.*;

public class Cavalo extends Peca {
    public Cavalo(Posicao posicao, Cores cor) {
        super(posicao, cor);
    }

    public Cavalo(Cores cor) {
        super(cor);
    }

    @Override
    public String toString() {
        return "C";
    }

    @Override
    public ImageIcon getIcon() {
        // Define o caminho para os ícones dentro da pasta de recursos
        String caminhoIcone;

        if (getCor() == Cores.BRANCA) {
            caminhoIcone = "images/white-knight.png";
        } else {
            caminhoIcone = "images/black-knight.png";
        }

        return carregaIcone(caminhoIcone);
    }
    @Override
    public boolean verificaMov(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro) {
        // O cavalo se move em forma de L, ou seja, 2 unidades em linha reta e 1 unidade para o lado
        // Alem disso, o cavalo pula peças
        int linhaInicial = posIni.getLinha();
        int colunaInicial = posIni.getColuna();
        int linhaFinal = posFinal.getLinha();
        int colunaFinal = posFinal.getColuna();

        int difLinha = Math.abs(linhaFinal - linhaInicial);
        int difColuna = Math.abs(colunaFinal - colunaInicial);

        if ((difColuna == 2 && difLinha == 1) || (difColuna == 1 && difLinha == 2)) {return true;}
        return false;
    }
}
