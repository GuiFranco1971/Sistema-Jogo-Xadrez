package tabuleiro;

public abstract class Piece {

	protected Position posicao;
	private Board tabuleiro;
	
	// Esta classe ABSTRATA tem todas as peças do tabuleiro
	
	public Piece(Board tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.posicao = null;
	}

	protected Board getTabuleiro() {
		return tabuleiro;
	}

	// Neste método da subclasse serão retornada os possíveis movimentos de uma peça
	// Está implementado nas subclasses: BISPO, CAVALO, etc..
	public abstract boolean[][] movimentosPossiveis();

	// Este é método PUBLIC chamado pelo ChessMatch para obter os movimentos possíveis da peça 
	public boolean possivelMovimento(Position posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	// Verifica se a peça está bloqueada, ou seja, se não houver um movimento possível 
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
