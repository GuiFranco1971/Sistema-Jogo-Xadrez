package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ChessException;
import xadrez.ChessMatch;
import xadrez.ChessPiece;
import xadrez.ChessPosition;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch partida = new ChessMatch();
		List<ChessPiece> listaPecasCapturadas = new ArrayList<>();

		while (!partida.getCheckMate()) {
			try {

				UI.limparTela();
				UI.exibePartida(partida, listaPecasCapturadas);

				System.out.println();
				System.out.print("Origem: ");
				ChessPosition origem = UI.lerChessPosition(sc);
				
				boolean[][] possibleMoves = partida.movimentosPossiveis(origem);
				UI.limparTela();
				UI.exibeTabuleiro(partida.getPieces(), possibleMoves);

				System.out.println();
				System.out.print("Destino: ");
				ChessPosition destino = UI.lerChessPosition(sc);

				ChessPiece pecaCapturada = partida.moverPeca(origem, destino);
				if (pecaCapturada != null) {
					listaPecasCapturadas.add(pecaCapturada);
				}
				
				if (partida.getPeaoPromovido() != null) {
					System.out.print("Digite a peca a ser promovida (Q/T/B/C): ");
					String tipo = sc.nextLine().trim().toUpperCase();
					partida.efetuarPromocaoPeao(tipo);
				}
			}
			catch (ChessException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.print(e.getMessage());
				sc.nextLine();

			}
		}
		UI.limparTela();
		UI.exibePartida(partida, listaPecasCapturadas);
	}

}
