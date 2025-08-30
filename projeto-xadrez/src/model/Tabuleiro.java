package model;

import model.pecas.*;
import view.Cores;
import model.exceptions.MovementNotAllowedException;

import static view.Cores.BRANCA;
import static view.Cores.PRETA;

public class Tabuleiro {
    private final Peca[][] pecas;
    private Cores turno;

    // se não tiver peça em determinado lugar tem que imprimir algum outro simbolo
    public Tabuleiro(){
        pecas = new Peca[8][8]; // define o tabuleiro como uma matriz 8x8 de peças genéricas
        turno = BRANCA;
        inicializaJogo(); // chama a função para inicializar o jogo com as peças nas posições corretas
    }

    // retorna a posição da peça
    public Peca posicaoPeca(Posicao posicao) {
        if (!posicaoVerifica(posicao)) { // verifica se a posição da peça é correta, se está dentro do tabuleiro
            throw new MovementNotAllowedException("Posição inválida.");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()]; // pega a linha em que a peça se encontra e a coluna
    }

    // verifica se a posição da peça está correta, linha vai de 0 a 7 e coluna vai de 0 a 7 também
    public boolean posicaoVerifica(Posicao posicao) {
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();
        return (linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8);
        }

    // verifica se tem peça em uma determinada posição, interessante usar na hora de movimentar as peças
    public boolean temPeca(Posicao posicao) {
        if (!posicaoVerifica(posicao)) {
            throw new MovementNotAllowedException("Posição inválida.");
        }
        return posicaoPeca(posicao) != null;
    }

    // coloca a peça em determinada posição, pode ser usada para implementar as peças, ir trocando a posição delas
    public void colocaPeca(Peca peca, Posicao posicao){
        if (temPeca(posicao)){ // vai verificar se já existe uma peça na posição que vc quer colocar alguma outra
            throw new MovementNotAllowedException("Já existe uma peça na posição: " + posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca; // insere a peça na posição da matriz indicada
        peca.posicao = posicao; // atualiza o campo da peça para se ter uma noção de onde ela está no tabuleiro

    }

    public Peca removePeca(Posicao posicao){
        if (!posicaoVerifica(posicao)) { // verifica se a posição da peça é correta, se está dentro do tabuleiro
            throw new MovementNotAllowedException("Posição inválida.");
        }
        if (posicaoPeca(posicao) == null) {
            return null;
        }
        Peca aux = posicaoPeca(posicao); // guarda a peça que vai ser removida
        aux.posicao = null; // atualiza a posição dessa peça, colocando ele em nulo (fora do tabuleiro)
        pecas[posicao.getLinha()][posicao.getColuna()] = null; //remove a peça do tabuleiro em si, ou seja, marca no tabuleiro null
        return aux; // retorna a peça removida
    }


    public boolean movePeca(Posicao posIni, Posicao posFinal){
        // verifica se a posicao que foi inserida é valida
        if (!posicaoVerifica(posIni) || !posicaoVerifica(posFinal)) {
            throw new MovementNotAllowedException("Posição inválida.");
        }

        // verifica se tem peça na posição inicial
        if(!temPeca(posIni)){
            throw new MovementNotAllowedException("Não existe peça nessa posição.");
        }
        Peca origem = posicaoPeca(posIni);
        Peca destino = posicaoPeca(posFinal);

        if(origem == destino){
            throw new MovementNotAllowedException("Tentativa de mover a peça para a mesma posição.");
        }

        // verifica o movimento das peças conforme as suas peculiaridades
        if(!origem.verificaMov(posIni, posFinal, pecas)) {
            throw new MovementNotAllowedException("Movimento inválido para essa peça.");
        }
        // verifica o turno do carinha
        if(origem.getCor() != turno)
            throw new MovementNotAllowedException("Não é o seu turno.");

        // verifica se a cor da peça da onde ele vai mover não é igual a que ele ta movendo
        if (destino != null && destino.getCor() == origem.getCor()) {
            throw new MovementNotAllowedException("Movimento inválido.");
        }

        removePeca(posIni); // remove a peça da posição inicial para não ocorrer da mesma peça ficar em duas posições
        Peca capturada = removePeca(posFinal);
        colocaPeca(origem, posFinal);

        // verifica se o rei foi capturado
        if (fimjogo(capturada)){
            return true;
        }

        turno = (turno == BRANCA) ? PRETA : BRANCA;

        return false;

    }
    //inicializa o jogo com a peça nas posições
    private void inicializaJogo(){
        Peca[] brancas = { // listas das peças brancas sem nenhuma posição ainda <- não foram colocadas no tabuleiro
                new Torre(null, BRANCA), new Cavalo(null, BRANCA), new Bispo(null, BRANCA),
                new Dama(null, BRANCA), new Rei(null, BRANCA), new Bispo(null, BRANCA),
                new Cavalo(null, BRANCA), new Torre(null, BRANCA)
        };
        Peca[] pretas = { // lista das peças pretas sem nenhuma posição <- não foram colocadas no tabuleiro
                new Torre(null, PRETA), new Cavalo(null, PRETA), new Bispo(null, PRETA),
                new Dama(null, PRETA), new Rei(null, PRETA), new Bispo(null, PRETA),
                new Cavalo(null, PRETA), new Torre(null, PRETA)
        };


        for (int col = 0; col < 8; col++){
            colocaPeca(new Peao(new Posicao(1, col), PRETA), new Posicao(1, col)); // coloca todos os peão preto
            colocaPeca(new Peao(new Posicao(6, col), BRANCA), new Posicao(6, col)); // coloca os os peão branco
            colocaPeca(pretas[col], new Posicao(0, col)); // coloca cada peça da lista das pretas na posição inicial correta
            colocaPeca(brancas[col], new Posicao(7, col));
        }
    }

    // finaliza o jogo quando a peça capturada for um rei. 
    public boolean fimjogo(Peca capturada) {

        if (capturada instanceof Rei) {
            return true; 
        }
    return false;
    }
    public Cores getTurno() {
        return turno; //Imprime no tabuleiro de quem é a vez.
    }
}

