package pecas.xadrez;

import tabuleiro.Board;
import tabuleiro.Position;
import xadrez.ChessPiece;
import xadrez.Color;

public class Bispo extends ChessPiece {

	// Esta classe é uma especialização da ChessPiece com as regras do BISPO
	
	public Bispo(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		// Casas Livres acima/direita da peça na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Casas Livres acima/esquerda da peça na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casas Livres abaixo/direita da peça na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Casas Livres abaixo/esquerda da peça na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}
}
