package xadrez;

import tabuleiro.Position;

public class ChessPosition {

	// Esta classe faz a convers�o da posi��o da Tela para Linha x Coluna da Matriz e vice-versa
	
	private char coluna;
	private int linha;

	public ChessPosition(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ChessException("Erro na inicializacao do ChessPosition. Posicao: " + coluna + linha);
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	protected Position toPosicao() {
		// Aqui est� convertendo a colunha/linha do tabuleiro Tela para linha/coluna da matriz
		// Exemplo: a1 no tabuleiro Tela -> posi��o 7, 0 na matriz
		//          c6 no tabuleiro Te�a -> posi��o 2, 2 na matriz
		return new Position(8 - linha, coluna - 'a');
	}

	protected static ChessPosition fromPosicao(Position posicao) {
		// Aqui est� convertendo a linha/coluna da matriz para a colunha/linha do tabuleiro Tela
		// Exemplo: posi��o 7, 0 na matriz -> a1 no tabuleiro Tela
		//          posi��o 2, 2 na matriz -> c6 no tabuleiro Tela
		return new ChessPosition((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}

	@Override
	public String toString() {
		return "" + coluna + linha;
	}

	
}
