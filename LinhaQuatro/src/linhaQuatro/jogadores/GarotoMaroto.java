package linhaQuatro.jogadores;

public class GarotoMaroto implements Jogador{

	@Override
	public String getNome() {
		return "GarotoMaroto ";
	}

	@Override
	public int jogada(int[][] tabuleiro, int corDaMinhaBola) {
		int jogada = obviousNextPlay(tabuleiro);
		if (jogada != -1) {
			jogada = 0;
		} 
		return jogada;
	}

	// Do I have an obvious next play? (win or lose in the next turn)
	private int obviousNextPlay(int[][] tabuleiro) {
		int jogada = winnerPlay(tabuleiro);
		if (jogada == -1) {
			jogada = avoidDefeatPlay(tabuleiro);
		}
		return jogada;
	}

	private int avoidDefeatPlay(int[][] tabuleiro) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int winnerPlay(int[][] tabuleiro) {
		// TODO Auto-generated method stub
		return 0;
	}

}
