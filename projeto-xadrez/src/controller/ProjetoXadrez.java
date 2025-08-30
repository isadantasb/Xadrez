//Carlos Bravin, Isadora Dantas e Vinicius Okada
package controller;


import view.InterfaceGrafica;
import model.Tabuleiro;

public class ProjetoXadrez {


    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro();
        InterfaceGrafica interfaceGrafica = new InterfaceGrafica(tabuleiro);
    }
}
        

