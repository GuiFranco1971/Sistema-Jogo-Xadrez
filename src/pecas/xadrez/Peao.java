package pecas.xadrez;

import tabuleiro.Board;
import tabuleiro.Position;
import xadrez.ChessMatch;
import xadrez.ChessPiece;
import xadrez.Color;

public class Peao extends ChessPiece {

	// Esta classe é uma especialização da ChessPiece com as regras do PEAO
	
	private ChessMatch partida;
	
	public Peao(Board tabuleiro, Color cor, ChessMatch partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Position p = new Position(0, 0);
		
		if (getCor() == Color.WHITE) {
			// Casas Livres acima da peça na matriz (apenas uma ou 2 se for primeiro movimento)
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() );
			if (getTabuleiro().existePosicao(p)  &&  !getTabuleiro().temPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;	
				p.setValues(posicao.getLinha() - 2, posicao.getColuna() );
			    if (getTabuleiro().existePosicao(p)  &&  !getTabuleiro().temPeca(p)  &&  getContMovto() == 0) 
			    	matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			// Casa com peça adversária na diagonal esquerda
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			// Casa com peça adversária na diagonal direita
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			// Movimento Especial EN PASSANT A ESQUERDA e A DIREITA BRANCAS
			// Significa que o peao PRETO andou 2 casas no primeiro movimento e não fez outro movimento
			// e o peao BRANCO está a seu lado, obrigatoriamente na linha 3 da matriz
			if (posicao.getLinha() == 3) {
				Position esquerda = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().existePosicao(esquerda) && temPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partida.getEnPassantVulneravel())
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				Position direita = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().existePosicao(direita) && temPecaAdversaria(direita) && getTabuleiro().peca(direita) == partida.getEnPassantVulneravel())
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
			}
		} else {
			// Casas Livres abaixo da peça na matriz (apenas uma ou 2 se for primeiro movimento)
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() );
			if (getTabuleiro().existePosicao(p)  &&  !getTabuleiro().temPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;	
				p.setValues(posicao.getLinha() + 2, posicao.getColuna() );
			    if (getTabuleiro().existePosicao(p)  &&  !getTabuleiro().temPeca(p)  &&  getContMovto() == 0) 
			    	matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			// Casa com peça adversária na diagonal esquerda
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			// Casa com peça adversária na diagonal direita
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			// Movimento Especial EN PASSANT A ESQUERDA e A DIREITA PRETAS
			// Significa que o peao BRANCO andou 2 casas no primeiro movimento e não fez outro movimento
			// e o peao PRETO está a seu lado, obrigatoriamente na linha 4 da matriz
			if (posicao.getLinha() == 4) {
				Position esquerda = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().existePosicao(esquerda) && temPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partida.getEnPassantVulneravel())
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				Position direita = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().existePosicao(direita) && temPecaAdversaria(direita) && getTabuleiro().peca(direita) == partida.getEnPassantVulneravel())
					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
			}
		}
		return matriz;
	}
	
}
