/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import view.Cores;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class Peca {
    protected Posicao posicao;
    protected Cores cor;
    protected boolean vulneravel;
    private static final int TAMANHO_ICONE = 68;

    public Peca(Posicao posicao, Cores cor) {
        this.posicao = posicao;
        this.cor = cor;
        this.vulneravel = false;

    }

    public Peca(Cores cor) {
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public boolean getVulneravel(){
        return vulneravel;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Cores getCor() {
        return cor;
    }

    public abstract boolean verificaMov(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro);

    public void setCor(Cores cor) {
        this.cor = cor;

    }

    // essa função tem por objetivo verificar se no caminho reto há peças -> Torre e Dama compartilham essa lógica
    protected boolean verificaCaminhoReto(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro) {
        int linhaInicial = posIni.getLinha();
        int colunaInicial = posIni.getColuna();
        int linhaFinal = posFinal.getLinha();
        int colunaFinal = posFinal.getColuna();

        // verifica movimento horizontal
        if (linhaInicial == linhaFinal) {
            int direcao = (colunaFinal > colunaInicial) ? 1 : -1; // ve a direção (direita +1, esquerda: -1)
            int coluna = colunaInicial + direcao; // inicializa verficador da próxima casa
            // vai verificando as peças da coluna a procura de alguma outra peça para impedir o movimento
            while (coluna != colunaFinal) {
                if (tabuleiro[linhaInicial][coluna] != null) {return false;}
                coluna += direcao; }
        }
        //verifica movimento vertical
        else {
            int direcao = (linhaFinal > linhaInicial) ? 1 : -1;
            int linha = linhaInicial + direcao;
            //vai verificando as peças da linha a procura de alguma outra peça para impedir o movimento
            while (linha != linhaFinal) {
                if (tabuleiro[linha][colunaInicial] != null) {return false;}
                linha += direcao; }
        }
        return true; // se tudo terminar o movimento é valido
    }

    // essa função tem por objetivo verificar se no caminho diagonal há peças -> Bispo e Dama compartilham essa lógica
    protected boolean verificaCaminhoDiagonal(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro) {
        int linhaInicial = posIni.getLinha();
        int colunaInicial = posIni.getColuna();
        int linhaFinal = posFinal.getLinha();
        int colunaFinal = posFinal.getColuna();

        int direcaoLinha = (linhaFinal > linhaInicial) ? 1 : -1;
        int direcaoColuna = (colunaFinal > colunaInicial) ? 1 : -1;

        int linha = linhaInicial + direcaoLinha;
        int coluna = colunaInicial + direcaoColuna;

        while (linha != linhaFinal && coluna != colunaFinal) {
            if (tabuleiro[linha][coluna] != null) {return false;}
            linha += direcaoLinha;
            coluna += direcaoColuna;
        }
        return true;

    }

    public abstract ImageIcon getIcon();

    protected ImageIcon carregaIcone(String caminhoIcone) {
        URL urlDoIcone = getClass().getResource(caminhoIcone); // localiza o caminho da peça

        if (urlDoIcone != null) { // se tiver um caminho
            ImageIcon iconeOriginal = new ImageIcon(urlDoIcone);
            Image imagemOriginal = iconeOriginal.getImage();
            Image imagemRedimensionada = imagemOriginal.getScaledInstance(TAMANHO_ICONE, TAMANHO_ICONE, Image.SCALE_SMOOTH); // redimensionamos a imagem passando largura, altura e instrução de qualidade
            return new ImageIcon(imagemRedimensionada);
        } else {
            System.out.println("erro: " + caminhoIcone);
            return null; // retorna null se a imagem não for encontrada
        }
    }
}
