package pecas.xadrez;

import tabuleiro.Board;
import tabuleiro.Position;
import xadrez.ChessMatch;
import xadrez.ChessPiece;
import xadrez.Color;

public class Rei extends ChessPiece {

	// Esta classe é uma especialização da ChessPiece com as regras do REI
	
	private ChessMatch partida;   // precisa de uma associação com a partida para verificar possibilidade de ROQUE 
	
	public Rei(Board tabuleiro, Color cor, ChessMatch partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Position p = new Position(0, 0);
		
		// Casa Livres acima da peça na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
				
		// Casa Livres acima/direita da peça na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casa Livres acima/esquerda da peça na matriz
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casa Livres direita da peça na matriz
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casas Livres esquerda da peça na matriz
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casa Livres abaixo/direita da peça na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() +1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casas Livres abaixo da peça na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() );
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Casas Livres abaixo/esquerda da peça na matriz
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() -1);
		if ( getTabuleiro().existePosicao(p)  &&  (!getTabuleiro().temPeca(p) || temPecaAdversaria(p)) ) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Movimento Especial - ROQUE 
		if (getContMovto() == 0   &&  !partida.getCheck()) {
			// ROQUE à direita do REI
			Position posTorreDir = new Position(posicao.getLinha(), posicao.getColuna() + 3);
			if (testarRoque(posTorreDir)) {
				Position p1 = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				Position p2 = new Position(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null  &&  getTabuleiro().peca(p2) == null)
					matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
			}
			// ROQUE à esquerda do REI
			Position posTorreEsq = new Position(posicao.getLinha(), posicao.getColuna() - 4);
			if (testarRoque(posTorreEsq)) {
				Position p1 = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				Position p2 = new Position(posicao.getLinha(), posicao.getColuna() - 2);
				Position p3 = new Position(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null  &&  getTabuleiro().peca(p2) == null  &&  getTabuleiro().peca(p3) == null)
					matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
			}
						
		}
		
		
		return matriz;
	}
	
	private boolean testarRoque(Position posicao) {
		ChessPiece p = (ChessPiece)getTabuleiro().peca(posicao);
		if (p != null  &&  p instanceof Torre  &&  p.getCor() == this.getCor()  &&  p.getContMovto() == 0)
			return true;
		else
			return false;
	}
	
}
