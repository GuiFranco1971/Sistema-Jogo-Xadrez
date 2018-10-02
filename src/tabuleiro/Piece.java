package tabuleiro;

public abstract class Piece {

	protected Position posicao;
	private Board tabuleiro;
	
	// Esta classe ABSTRATA tem todas as pe�as do tabuleiro
	
	public Piece(Board tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.posicao = null;
	}

	protected Board getTabuleiro() {
		return tabuleiro;
	}

	// Neste m�todo da subclasse ser�o retornada os poss�veis movimentos de uma pe�a
	// Est� implementado nas subclasses: BISPO, CAVALO, etc..
	public abstract boolean[][] movimentosPossiveis();

	// Este � m�todo PUBLIC chamado pelo ChessMatch para obter os movimentos poss�veis da pe�a 
	public boolean possivelMovimento(Position posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	// Verifica se a pe�a est� bloqueada, ou seja, se n�o houver um movimento poss�vel 
	public boolean pecaEstaBloqueada() {
		boolean matriz[][] = movimentosPossiveis();
		for (int i=0 ; i<matriz.length; i++) {
			for (int j=0 ; j<matriz.length; j++) {
				if (matriz[i][j])
					return false;
			}
		}
		return true;
	}
	
}
