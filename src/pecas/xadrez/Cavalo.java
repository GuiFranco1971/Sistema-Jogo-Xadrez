package pecas.xadrez;

import tabuleiro.Board;
import tabuleiro.Position;
import xadrez.ChessPiece;
import xadrez.Color;

public class Cavalo extends ChessPiece {

	// Esta classe é uma especialização da ChessPiece com as regras do REI
	
	public Cavalo(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "C";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Position p = new Position(0, 0);
		
		// Casa Livres acima/esquerda da peça na matriz
		p.setValues(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
				
		// Casa Livres acima/direita da peça na matriz
		p.setValues(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casa Livres direita/acima da peça na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casa Livres direita/abaixo da peça na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casas Livres esquerda/acima da peça na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casa Livres esquerda/abaixo da peça na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casas Livres abaixo/esquerda da peça na matriz
		p.setValues(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casas Livres abaixo/direita da peça na matriz
		p.setValues(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		return matriz;
	}
	

}
