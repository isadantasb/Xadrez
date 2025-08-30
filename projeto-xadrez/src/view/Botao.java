package view;

import javax.swing.*;
import java.awt.*;

public class Botao extends JButton {
    private int linha;
    private int coluna;
    
    public Botao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        setFundo();
    }
    public int getLinha() { 
        return linha; 
    }
    public int getColuna() { 
        return coluna; 
    }
    public void setFundo() {
        Color corClara = Color.decode("#B0C69C");
        Color corEscura = Color.decode("#4F8C65");
        if ((linha + coluna) % 2 == 0) {
            this.setBackground(corClara);
        } else {
            this.setBackground(corEscura);
        }
    }
}
