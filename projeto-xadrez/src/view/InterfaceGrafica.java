package view;

import model.Peca;
import model.Posicao;
import model.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class InterfaceGrafica {

    private final Tabuleiro tabuleiro;
    private final JButton[][] botoes = new JButton[8][8];
    private Posicao origem = null;

    public InterfaceGrafica(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        criaInterface(tabuleiro);
    }

    public void criaInterface(Tabuleiro tabuleiro){
    JFrame janela = new JFrame();

    janela.setTitle("Xadrez");
    janela.setSize(600, 600);
    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela.setLocationRelativeTo(null); // centraliza o xadrez na tela
    janela.setLayout(new GridLayout(8, 8)); // tabuleiro 8x8

    for (int linha = 0; linha < 8; linha++) {
        for (int col = 0; col < 8; col++) {
            Botao botao = new Botao(linha,col);

            botoes[linha][col] = botao;
            Peca peca = tabuleiro.posicaoPeca(new Posicao(linha, col));
            if (peca != null) {
                botao.setIcon(peca.getIcon());
            }

            TratadorClique clicou = new TratadorClique(linha, col); //
            botao.addActionListener(clicou); //
            janela.add(botao);
        }
    }

    janela.setVisible(true);
    }
    private class TratadorClique implements ActionListener{
        private int linha;
        private int coluna;

        public TratadorClique(int linha, int coluna) {
             this.linha = linha;
             this.coluna = coluna;
        }

        @Override
        public void actionPerformed(ActionEvent event){
            Posicao clicada = new Posicao(linha, coluna);

            if (origem == null) {
                origem = clicada;
            } else {
                try {
                    boolean fim = tabuleiro.movePeca(origem, clicada);

                    atualizarTabuleiro();

                    if (fim) {
                        JOptionPane.showMessageDialog(null, "FIM DE JOGO!");
                        System.exit(0);
                    }
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());

                }

                origem = null;
            }
        }
    }

    private void atualizarTabuleiro() {
        for (int linha = 0; linha < 8; linha++) {
            for (int col = 0; col < 8; col++) {
                Peca peca = tabuleiro.posicaoPeca(new Posicao(linha, col));
                if (peca != null) {
                    botoes[linha][col].setIcon(peca.getIcon());
                } else {
                    botoes[linha][col].setIcon(null);
                }
                botoes[linha][col].setText("");
            }
        }
    }
}