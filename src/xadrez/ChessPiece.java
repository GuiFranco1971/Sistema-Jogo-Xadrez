package xadrez;

import tabuleiro.Board;
import tabuleiro.Piece;
import tabuleiro.Position;

public abstract class ChessPiece extends Piece {

	// Esta classe é uma especialização da Piece com a cor da peça
	
	private Color cor;
	private int contMovto;
	
	public ChessPiece(Board tabuleiro, Color cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
	}
	
	public int getContMovto() {
		return contMovto;
	}

	protected void somaContMovto() {
		this.contMovto++;
	}

	protected void subtraiContMovto() {
		this.contMovto--;
	}

	// Verifica se a posição tem uma peça advserária, ou seja, de cor contrária
	protected boolean temPecaAdversaria(Position posicao) {
		ChessPiece p = (ChessPiece) getTabuleiro().peca(posicao);
		return (p != null && p.getCor() != this.cor);
	}
	
	// Obter a posição de linha coluna da Tela a partir da posição da peça na matriz
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosicao(posicao);
	}
}
