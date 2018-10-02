package pecas.xadrez;

import tabuleiro.Board;
import tabuleiro.Position;
import xadrez.ChessPiece;
import xadrez.Color;

public class Torre extends ChessPiece {

	// Esta classe é uma especialização da ChessPiece com as regras da TORRE
	
	public Torre(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);

	}

	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Position p = new Position(0, 0);
		
		// Casas Livres acima da peça na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() );
		while (getTabuleiro().existePosicao(p)  &&  !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casas Livres abaixo da peça na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() );
		while (getTabuleiro().existePosicao(p)  &&  !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
	
		// Casas Livres esquerda da peça na matriz
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().existePosicao(p)  &&  !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
	
		// Casas Livres direita da peça na matriz
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().existePosicao(p)  &&  !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		return matriz;
	}
	
}
