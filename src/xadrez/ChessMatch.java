package xadrez;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pecas.xadrez.Bispo;
import pecas.xadrez.Cavalo;
import pecas.xadrez.Peao;
import pecas.xadrez.Rainha;
import pecas.xadrez.Rei;
import pecas.xadrez.Torre;
import tabuleiro.Board;
import tabuleiro.Piece;
import tabuleiro.Position;

public class ChessMatch {

	// Esta classe é a que se relaciona com a interface de usuário e tem as
	// posições do tabuleiro

	private Board tabuleiro;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulneravel;
	private ChessPiece peaoPromovido;

	private List<Piece> pecasNoTabuleiro = new ArrayList<>();
	private List<Piece> pecasCapturadas = new ArrayList<>();

	public ChessMatch() {
		// cria uma nova partida
		this.tabuleiro = new Board(8, 8);
		this.turn = 1;
		this.currentPlayer = Color.WHITE;
		this.check = false;
		this.checkMate = false;
		initialSetup();
	}

	public Board getTabuleiro() {
		return tabuleiro;
	}

	private void initialSetup() {
		// Inicia um Tabuleiro Zerado
		novaPeca('a', 1, new Torre(tabuleiro, Color.WHITE));
		novaPeca('b', 1, new Cavalo(tabuleiro, Color.WHITE));
		novaPeca('c', 1, new Bispo(tabuleiro, Color.WHITE));
		novaPeca('d', 1, new Rainha(tabuleiro, Color.WHITE));
		novaPeca('e', 1, new Rei(tabuleiro, Color.WHITE, this));
		novaPeca('f', 1, new Bispo(tabuleiro, Color.WHITE));
		novaPeca('g', 1, new Cavalo(tabuleiro, Color.WHITE));
		novaPeca('h', 1, new Torre(tabuleiro, Color.WHITE));
		novaPeca('a', 2, new Peao(tabuleiro, Color.WHITE, this));
		novaPeca('b', 2, new Peao(tabuleiro, Color.WHITE, this));
		novaPeca('c', 2, new Peao(tabuleiro, Color.WHITE, this));
		novaPeca('d', 2, new Peao(tabuleiro, Color.WHITE, this));
		novaPeca('e', 2, new Peao(tabuleiro, Color.WHITE, this));
		novaPeca('f', 2, new Peao(tabuleiro, Color.WHITE, this));
		novaPeca('g', 2, new Peao(tabuleiro, Color.WHITE, this));
		novaPeca('h', 2, new Peao(tabuleiro, Color.WHITE, this));

		novaPeca('a', 8, new Torre(tabuleiro, Color.BLACK));
		novaPeca('b', 8, new Cavalo(tabuleiro, Color.BLACK));
		novaPeca('c', 8, new Bispo(tabuleiro, Color.BLACK));
		novaPeca('d', 8, new Rainha(tabuleiro, Color.BLACK));
		novaPeca('e', 8, new Rei(tabuleiro, Color.BLACK, this));
		novaPeca('f', 8, new Bispo(tabuleiro, Color.BLACK));
		novaPeca('g', 8, new Cavalo(tabuleiro, Color.BLACK));
		novaPeca('h', 8, new Torre(tabuleiro, Color.BLACK));
		novaPeca('a', 7, new Peao(tabuleiro, Color.BLACK, this));
		novaPeca('b', 7, new Peao(tabuleiro, Color.BLACK, this));
		novaPeca('c', 7, new Peao(tabuleiro, Color.BLACK, this));
		novaPeca('d', 7, new Peao(tabuleiro, Color.BLACK, this));
		novaPeca('e', 7, new Peao(tabuleiro, Color.BLACK, this));
		novaPeca('f', 7, new Peao(tabuleiro, Color.BLACK, this));
		novaPeca('g', 7, new Peao(tabuleiro, Color.BLACK, this));
		novaPeca('h', 7, new Peao(tabuleiro, Color.BLACK, this));
	}

	public int getTurn() {
		return this.turn;
	}

	public Color getCurrentPlayert() {
		return this.currentPlayer;
	}

	public Boolean getCheck() {
		return this.check;
	}

	public Boolean getCheckMate() {
		return this.checkMate;
	}

	public ChessPiece getEnPassantVulneravel() {
		return this.enPassantVulneravel;
	}

	public ChessPiece getPeaoPromovido() {
		return this.peaoPromovido;
	}

	private void novaPeca(char coluna, int linha, ChessPiece peca) {
		// coloca a peca informada na coluna, linha informada e na Lista de peças no
		// Tabuleiro
		tabuleiro.colocarPeca(peca, new ChessPosition(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	public ChessPiece[][] getPieces() {
		// Aqui está sendo feita uma programação por camadas
		// O tabuleiro que está na classe Board não será visivel diretamente para o Jogo
		// de Xadrez
		// O tabuleiro está sendo retornado através da matriz auxiliar ChessPiece

		ChessPiece[][] matriz = new ChessPiece[tabuleiro.getLinhas()][tabuleiro.getColunas()];

		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (ChessPiece) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}

	public boolean[][] movimentosPossiveis(ChessPosition origem) {
		// Está validando a posição digitada pelo usuario na tela e
		// informando uma matriz com os movimentos possíveis da peça
		Position pos = origem.toPosicao();
		validarPosicaoOrigem(pos);
		return tabuleiro.peca(pos).movimentosPossiveis();
	}

	public ChessPiece moverPeca(ChessPosition posicaoOrigem, ChessPosition posicaoDestino) {
		// efetua a validação das posições origem e destino e faz a movimentação
		// retornando a peça capturada
		Position origem = posicaoOrigem.toPosicao();
		Position destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);

		Piece pecaCapturada = fazerMovimentacao(origem, destino);
		ChessPiece pecaMovida = (ChessPiece) tabuleiro.peca(destino);

		if (testarCheck(currentPlayer)) {
			desfazerMovimentacao(origem, destino, pecaCapturada);
			if (this.check) {
				throw new ChessException("Voce precisa sair da posicao de CHECK !!!");
			} else
				throw new ChessException("Voce nao pode se colocar em CHECK !!!");
		}

		// Efetuar tratamento de PROMOCAO caso o peão chegue no final do tabuleiro
		peaoPromovido = null;
		if (pecaMovida instanceof Peao) {
			if ((pecaMovida.getCor() == Color.WHITE && destino.getLinha() == 0)
					|| (pecaMovida.getCor() == Color.BLACK && destino.getLinha() == 7)) {
				peaoPromovido = pecaMovida;
				peaoPromovido = efetuarPromocaoPeao("Q");
			}
		}

		this.check = (testarCheck(adversario(currentPlayer))) ? true : false;
		if (testarCheckMate(adversario(currentPlayer)))
			this.checkMate = true;
		else
			nextTurn();

		// Movimento Especial - Verifica vulnerabilidade do Peão para En Passant
		// Testa se o Peao andou 2 casas no primeiro movimento

		if (pecaMovida instanceof Peao
				&& (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			this.enPassantVulneravel = pecaMovida;
		} else {
			this.enPassantVulneravel = null;
		}

		return (ChessPiece) pecaCapturada;
	}

	private void validarPosicaoOrigem(Position origem) {
		// Validar a peça origem e se ela está bloqueada
		if (!tabuleiro.temPeca(origem)) {
			throw new ChessException("Nao tem uma peca nesta posicao de origem");
		}
		if (this.currentPlayer != ((ChessPiece) tabuleiro.peca(origem)).getCor()) {
			throw new ChessException("A peca escolhida nao e sua !!!");
		}
		if (tabuleiro.peca(origem).pecaEstaBloqueada()) {
			throw new ChessException("A peca na posicao de origem nao pode ser movimentada");
		}
	}

	private void validarPosicaoDestino(Position origem, Position destino) {
		// Validar se a peça origem pode ser movimentada para o destino
		if (!tabuleiro.peca(origem).possivelMovimento(destino)) {
			throw new ChessException("A peca escolhida nao pode ser movimentada para posicao de destino");
		}
	}

	private Piece fazerMovimentacao(Position origem, Position destino) {
		// Remove a peça da origem e a coloca no destino, retornando a peça
		// capturada se houver. Se for capturada uma peça, exclui da lista do Tabuleiro
		ChessPiece p = (ChessPiece) tabuleiro.removerPeca(origem);
		p.somaContMovto();
		Piece pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		if (pecaCapturada != null) {
			pecasCapturadas.add(pecaCapturada);
			pecasNoTabuleiro.remove(pecaCapturada);
		}
		// Tratar Movimento Especial - ROQUE A DIREITA
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Position origemTorre = new Position(origem.getLinha(), origem.getColuna() + 3);
			Position destinoTorre = new Position(origem.getLinha(), origem.getColuna() + 1);
			ChessPiece torre = (ChessPiece) tabuleiro.removerPeca(origemTorre);
			tabuleiro.colocarPeca(torre, destinoTorre);
			torre.somaContMovto();
		}
		// Tratar Movimento Especial - ROQUE A ESQUERDA
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Position origemTorre = new Position(origem.getLinha(), origem.getColuna() - 4);
			Position destinoTorre = new Position(origem.getLinha(), origem.getColuna() - 1);
			ChessPiece torre = (ChessPiece) tabuleiro.removerPeca(origemTorre);
			tabuleiro.colocarPeca(torre, destinoTorre);
			torre.somaContMovto();
		}
		// Tratar Movimento Especial - EM PASSANT
		// Testa se o peao se moveu na diagonal e não capturou uma peça
		// Neste caso é um EN PASSANT
		if (p instanceof Peao) {
			if (destino.getColuna() != origem.getColuna() && pecaCapturada == null) {
				Position posPeaoEnPassantCapturado;
				if (p.getCor() == Color.WHITE)
					posPeaoEnPassantCapturado = new Position(destino.getLinha() + 1, destino.getColuna());
				else
					posPeaoEnPassantCapturado = new Position(destino.getLinha() - 1, destino.getColuna());
				pecaCapturada = tabuleiro.removerPeca(posPeaoEnPassantCapturado);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}
		return pecaCapturada;
	}

	private void desfazerMovimentacao(Position origem, Position destino, Piece pecaCapturada) {
		// Desfaz o movimento anterior, voltando as peça para a posição origem e
		// retornando
		// a peça capturada para o do Tabuleiro
		ChessPiece p = (ChessPiece) tabuleiro.removerPeca(destino);
		p.subtraiContMovto();
		tabuleiro.colocarPeca(p, origem);
		if (pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		// Desfazer Movimento Especial - ROQUE A DIREITA
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Position origemTorre = new Position(origem.getLinha(), origem.getColuna() + 3);
			Position destinoTorre = new Position(origem.getLinha(), origem.getColuna() + 1);
			ChessPiece torre = (ChessPiece) tabuleiro.removerPeca(destinoTorre);
			tabuleiro.colocarPeca(torre, origemTorre);
			torre.subtraiContMovto();
		}
		// Desfazer Movimento Especial - ROQUE A ESQUERDA
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Position origemTorre = new Position(origem.getLinha(), origem.getColuna() - 4);
			Position destinoTorre = new Position(origem.getLinha(), origem.getColuna() - 1);
			ChessPiece torre = (ChessPiece) tabuleiro.removerPeca(destinoTorre);
			tabuleiro.colocarPeca(torre, origemTorre);
			torre.subtraiContMovto();
		}
		// Desfazer Movimento Especial - EM PASSANT
		// O tratamento na linha acima da peca capturada recolocou o peao na posicao
		// destino
		// Para o EN PASSANT isto é errado e está corrigindo aqui
		if (p instanceof Peao) {
			if (destino.getColuna() != origem.getColuna() && pecaCapturada == enPassantVulneravel) {
				Position posPeaoEnPassantCapturado;
				if (p.getCor() == Color.WHITE)
					posPeaoEnPassantCapturado = new Position(destino.getLinha() + 1, destino.getColuna());
				else
					posPeaoEnPassantCapturado = new Position(destino.getLinha() - 1, destino.getColuna());
				tabuleiro.removerPeca(destino);
				tabuleiro.colocarPeca(pecaCapturada, posPeaoEnPassantCapturado);
			}
		}
	}

	private void nextTurn() {
		// Troca a vez entre jogador BRANCO e PRETO
		this.turn++;
		this.currentPlayer = (this.currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Color adversario(Color cor) {
		// retorna a cor do Adversário que quem está jogando
		return (cor == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece procurarRei(Color cor) {
		// Filtrar as peças do tabuleiro e criar uma lista com todas as peças da cor do
		// parâmetro
		// e depois procurar o REI nesta lista
		List<Piece> lista = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Piece p : lista) {
			if (p instanceof Rei) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("Não há um REI " + cor + " no tabuleiro.");
	}

	private boolean testarCheck(Color cor) {
		// Obter a posicao na matriz do Rei da cor do parâmetro
		// Criar uma lista de peças do adversário
		// Verificar se existe alguma peça do adversário que tenha um movimnento
		// possível
		// que coincida com a posição do REI. Se sim, está em CHECK
		Position posicaoRei = procurarRei(cor).getChessPosition().toPosicao();
		List<Piece> lista = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece) x).getCor() == adversario(cor))
				.collect(Collectors.toList());
		for (Piece p : lista) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testarCheckMate(Color cor) {
		// Se não está em CHEQUE, não está em CHEQUE-MATE
		// Criar uma lista de peças do jogador e verificar os movimnentos possíveis de
		// cada peça
		// Para cada movimento possível de cada peça, simular uma movimentação da peça e
		// testar se
		// continua em CHEQUE.
		// Se para todos os movimentos possíveis, continuar em CHEQUE é CHEQUE-MATE

		if (!testarCheck(cor)) {
			return false;
		}
		List<Piece> lista = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Piece p : lista) {
			boolean[][] matriz = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (matriz[i][j]) {
						Position origem = ((ChessPiece) p).getChessPosition().toPosicao();
						Position destino = new Position(i, j);
						Piece pecaCapturada = fazerMovimentacao(origem, destino);
						boolean testarCheque = testarCheck(cor);
						desfazerMovimentacao(origem, destino, pecaCapturada);
						if (!testarCheque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public ChessPiece efetuarPromocaoPeao(String tipo) {
		if (this.peaoPromovido == null) 
			throw new IllegalStateException("Nao ha pecas para serem promovidas.");
		if (!tipo.equals("C") && !tipo.equals("B") && !tipo.equals("T") && !tipo.equals("Q") )
			throw new InvalidParameterException("Peca selecionada para promocao invalida");
		Position pos = peaoPromovido.getChessPosition().toPosicao();
		Piece p = tabuleiro.removerPeca(pos);
		pecasNoTabuleiro.remove(p);
		ChessPiece novaPeca = pecaTrocada(tipo, peaoPromovido.getCor());
		tabuleiro.colocarPeca(novaPeca, pos);
		pecasNoTabuleiro.add(novaPeca);
		return novaPeca;
	}

	private ChessPiece pecaTrocada(String tipo, Color cor) {
		if (tipo.equals("B")) return new Bispo(tabuleiro, cor);
		if (tipo.equals("C")) return new Cavalo(tabuleiro, cor);
		if (tipo.equals("T")) return new Torre(tabuleiro, cor);
		return new Rainha(tabuleiro, cor);
	}
	
}
