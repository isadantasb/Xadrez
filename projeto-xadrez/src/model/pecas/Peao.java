package model.pecas;

import javax.swing.*;

import model.Peca;
import view.Cores;
import model.Posicao;

public class Peao extends Peca {
    private boolean primeiroPasso;
    private boolean vulneravel;


    public Peao(Posicao posicao, Cores cor) {
       super(posicao, cor);
       this.vulneravel = false;
       this.primeiroPasso = true;
   }

    public Peao(Cores cor) {
        super(cor);
    }

    public boolean getVulneravel(){
        return this.vulneravel;
    }
    
    public boolean getPrimeiroPasso(){
        return primeiroPasso;
    }

    public void setVulneravel(boolean estado){
        this.vulneravel = estado;
    }

    public void setPrimeiroPasso(boolean estado){
        this.primeiroPasso = estado;
    }

    @Override
    public String toString() {
        return "P";
    }
    @Override
    public ImageIcon getIcon() {
        // Define o caminho para os ícones dentro da pasta de recursos
        String caminhoIcone;

        if (getCor() == Cores.BRANCA) {
            caminhoIcone = "images/white-pawn.png";
        } else {
            caminhoIcone = "images/black-pawn.png";
        }

        return carregaIcone(caminhoIcone);
    }


    // Logica para a movimentacao do peao, levando em consideracao a movimentacao dupla e a normal
    // O codigo visa cobrir as situacaoes onde o movimento é permitido, retornando true em todos os if statements,
    // Caso todos esses casos não sejam cumpridos, o codigo retorna false
    @Override
    public boolean verificaMov(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro) {
        int linhaInicial = posIni.getLinha() ;
        int colunaInicial = posIni.getColuna() ;
        int linhaFinal = posFinal.getLinha() ;
        int colunaFinal = posFinal.getColuna() ;
        
        // Direcao q a peca se move, pra fzr a verificacao da validade do movimento
        int direcao = (getCor() == Cores.BRANCA) ? -1 : 1;

        // Move 1 unidade em caminho livre
        if (colunaFinal == colunaInicial) {
            if (linhaFinal == linhaInicial + direcao &&
             tabuleiro[linhaFinal][colunaFinal] == null) {
                if (this.getVulneravel() == true) {
                    setVulneravel(false);
                    return true;
                }
                else return true;
            }
        } 


        // Movimentacao dupla
        if(movimentoDuplo(posIni, posFinal, tabuleiro)) return true;
        
        // Captura diagonal
        if(captura(posIni, posFinal, tabuleiro)) {
            if (this.getVulneravel() == true) {
                setVulneravel(false);
                return true;
            }
            else return true;
        }

        // En passant - faltou fazer a logica da remoção de peça.
        /*
        if(enPassant(posIni, posFinal, tabuleiro)) {
            if (this.getVulneravel() == true) {
                setVulneravel(false);
                return true;
            }
            else return true;
        }*/
            
        return false;
    }

    /*
    public boolean enPassant(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro){
        int linhaInicial = posIni.getLinha();
        int colunaInicial = posIni.getColuna();
        int linhaFinal = posFinal.getLinha();
        int colunaFinal = posFinal.getColuna();
        int direcao = (getCor() == Cores.BRANCA) ? -1 : 1;

        if (Math.abs(colunaFinal - colunaInicial) == 1 &&
            linhaFinal == linhaInicial + direcao && // 
            tabuleiro[linhaFinal][colunaFinal] == null &&
            tabuleiro[linhaInicial][colunaFinal] != null &&
            tabuleiro[linhaInicial][colunaFinal].getClass() == Peao.class &&
            tabuleiro[linhaInicial][colunaFinal].getCor() != this.cor &&
            tabuleiro[linhaInicial][colunaFinal].getVulneravel() == true)
                return true;
        else return false;

    }*/


    public boolean captura(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro){
        int linhaInicial = posIni.getLinha();
        int colunaInicial = posIni.getColuna();
        int linhaFinal = posFinal.getLinha();
        int colunaFinal = posFinal.getColuna();
        int direcao = (getCor() == Cores.BRANCA) ? -1 : 1;

        if (Math.abs(colunaFinal - colunaInicial) == 1 &&
            linhaFinal == linhaInicial + direcao && // 
            tabuleiro[linhaFinal][colunaFinal] != null &&
            tabuleiro[linhaFinal][colunaFinal].getCor() != this.cor)
                return true;
        else return false;
    }


    public boolean movimentoDuplo(Posicao posIni, Posicao posFinal, Peca[][] tabuleiro){
        int linhaInicial = posIni.getLinha();
        int linhaFinal = posFinal.getLinha();
        int colunaFinal = posFinal.getColuna();
        int direcao = (getCor() == Cores.BRANCA) ? -1 : 1;

        int linhaInicialEsperada = (cor == Cores.BRANCA) ? 6 : 1;

        if (this.primeiroPasso == true){
            if (linhaInicial == linhaInicialEsperada &&                   // Verifica se a peca esta no lugar correto
                linhaFinal == linhaInicial + 2 * direcao &&               // Verifica se o movimento esta correto
                tabuleiro[linhaInicial + direcao][colunaFinal] == null && // Faz a verificacao se o caminho ta livre
                tabuleiro[linhaFinal][colunaFinal] == null){               // Destino livre
                    setPrimeiroPasso(false);                        // Faz com que o peao nao possa mais executar o salto duplo
                    setVulneravel(true);                            // Altera o estado dele para 'vulneravel' -> faz com que outro peao possa aplicar  um en passant nele
                    return true;
                } else return false;
        } else setVulneravel(false); // Caso ja tenha dado seu primeiro passo, nesse movimento ele deixara de estar vulneravel
        return false;
    }
}


