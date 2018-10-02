package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.ChessMatch;
import xadrez.ChessPiece;
import xadrez.ChessPosition;
import xadrez.Color;

public class UI {

	// determina as cores a serem utilizadas
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void limparTela() {
		// comando para limpar a tela
		// https://stackoverflow.com/questions/2979383/java-clear-the-console
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void exibePartida(ChessMatch partida, List<ChessPiece> capturadas) {
		exibeTabuleiro(partida.getPieces());
		System.out.println();
		exibePecasCapturadas(capturadas);
		System.out.println();
		System.out.println("TURN: " + partida.getTurn());
		if (!partida.getCheckMate()) {
			System.out.println("Aguardando o jogador: " + partida.getCurrentPlayert());
			if (partida.getCheck())
				System.out.println("C H E Q U E   ! ! !");
		} else {
			System.out.println("C H E Q U E   M A T E  ! ! ! ");
			System.out.println("Vencedor: " + partida.getCurrentPlayert());
		}
	}

	public static void exibeTabuleiro(ChessPiece[][] pecas) {
		// Exibe o tabuleiro com as peças
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(ANSI_PURPLE + (8 - i) + " " + ANSI_RESET);
			for (int j = 0; j < pecas.length; j++) {
				exibePeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println(ANSI_PURPLE + "  a b c d e f g h " + ANSI_RESET);
	}

	public static void exibeTabuleiro(ChessPiece[][] pecas, boolean[][] possibleMoves) {
		// Exibe o tabuleiro com as peças
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(ANSI_PURPLE + (8 - i) + " " + ANSI_RESET);
			for (int j = 0; j < pecas.length; j++) {
				exibePeca(pecas[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println(ANSI_PURPLE + "  a b c d e f g h " + ANSI_RESET);
	}

	private static void exibePeca(ChessPiece peca, boolean background) {
		// Exibe cada peça do tabuleiro
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else if (peca.getCor() == Color.WHITE) {
			System.out.print(ANSI_WHITE + peca + ANSI_RESET);
		} else {
			System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
		}
		System.out.print(" ");
	}

	public static ChessPosition lerChessPosition(Scanner sc) {
		// faz a leitura do comando dado pelo usuário e retorna a posição digitada na
		// tela
		try {
			String s = sc.nextLine().trim().toLowerCase();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1, 2));
			return new ChessPosition(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro na leitura da posicao da peca. Valores validos de <a1> ate <h8>");

		}
	}

	private static void exibePecasCapturadas(List<ChessPiece> capturadas) {
		// recebe uma lista de peças capturadas e faz o filtro de peças Brancas e Pretas
		List<ChessPiece> brancas = capturadas.stream().filter(x -> x.getCor() == Color.WHITE)
				.collect(Collectors.toList());
		List<ChessPiece> pretas = capturadas.stream().filter(x -> x.getCor() == Color.BLACK)
				.collect(Collectors.toList());
		System.out.println("Pecas capturadas: ");
		System.out.println("BRANCAS: " + ANSI_WHITE + Arrays.toString(brancas.toArray()) + ANSI_RESET);
		System.out.println("PRETAS : " + ANSI_YELLOW + Arrays.toString(pretas.toArray()) + ANSI_RESET);
	}

}
