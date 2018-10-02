package tabuleiro;

public class Board {

	private int linhas;
	private int colunas;
	private Piece[][] pecas;

	public Board(int linhas, int colunas) {
		// Faz a criação do tabuleiro (LXC) com as peças NULL em cada posição da matriz
		if (linhas != 8 && colunas != 8) {
			throw new BoardException("ERRO CRIANDO TABULEIRO: o numero de linhas e colunas deve ser 8 x 8");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Piece[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Piece peca(int linha, int coluna) {
		// retorna a peca na posição linha x coluna da matriz
		if (!existePosicao(linha, coluna)) {
			throw new BoardException("Nao existe esta posicao no Tabuleiro: Linha " + linha + " - Coluna " + coluna);
		}
		return pecas[linha][coluna];
	}

	public Piece peca(Position posicao) {
		// retorna a peca na posição linha x coluna da Tela
		if (!existePosicao(posicao)) {
			throw new BoardException("Nao existe esta posicao no Tabuleiro: " + posicao);
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void colocarPeca(Piece peca, Position posicao) {
		// incluir a peca na posicao Linha x Coluna da matriz, após conversão da posição
		if (temPeca(posicao)) {
			throw new BoardException("Ja existe uma peca nesta posicao no Tabuleiro: " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

	public Piece removerPeca(Position posicao) {
		// Remover a peça na posicao Linha x Coluna da matriz, após conversão da posição
		if (!existePosicao(posicao)) {
			throw new BoardException("Nao existe esta posicao no Tabuleiro: " + posicao);
		}
		if (peca(posicao) == null) {
			return null;
		}
		Piece aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}

	public boolean existePosicao(int linha, int coluna) {
		// Verifica se existe a Linha x Coluna na matriz 
		if ((linha >= 0 && linha < this.linhas) && (coluna >= 0 && coluna < this.colunas))
			return true;
		else
			return false;
	}

	public boolean existePosicao(Position posicao) {
		// Verifica se existe a Linha x Coluna na matriz, passando a posição
		return existePosicao(posicao.getLinha(), posicao.getColuna());
	}

	public boolean temPeca(Position posicao) {
		// Verifica se existe uma peça na posição informada
		if (!existePosicao(posicao)) {
			throw new BoardException("Nao existe esta posicao no Tabuleiro: " + posicao);
		}
		if (peca(posicao) != null)
			return true;
		else
			return false;
	}
}
