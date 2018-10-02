package xadrez;

import tabuleiro.Board;
import tabuleiro.Piece;
import tabuleiro.Position;

public abstract class ChessPiece extends Piece {

	// Esta classe � uma especializa��o da Piece com a cor da pe�a
	
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

	// Verifica se a posi��o tem uma pe�a advser�ria, ou seja, de cor contr�ria
	protected boolean temPecaAdversaria(Position posicao) {
		ChessPiece p = (ChessPiece) getTabuleiro().peca(posicao);
		return (p != null && p.getCor() != this.cor);
	}
	
	// Obter a posi��o de linha coluna da Tela a partir da posi��o da pe�a na matriz
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosicao(posicao);
	}
}
