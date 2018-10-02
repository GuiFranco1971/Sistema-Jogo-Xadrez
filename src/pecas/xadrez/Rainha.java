package pecas.xadrez;

import tabuleiro.Board;
import tabuleiro.Position;
import xadrez.ChessPiece;
import xadrez.Color;

public class Rainha extends ChessPiece {

	// Esta classe � uma especializa��o da ChessPiece com as regras da RAINHA
	
	public Rainha(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		// Casas Livres acima da pe�a na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Casas Livres acima/direita da pe�a na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Casas Livres acima/esquerda da pe�a na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Casas Livres abaixo da pe�a na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Casas Livres abaixo/direita da pe�a na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Casas Livres abaixo/esquerda da pe�a na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Casas Livres esquerda da pe�a na matriz
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Casas Livres direita da pe�a na matriz
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}

}
