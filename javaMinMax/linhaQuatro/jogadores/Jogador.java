package linhaQuatro.jogadores;

/**
 * Interface responsável por definir os métodos que
 * devem ser implementados pelos jogadores.
 * 
 * @author Fabrício J. Barth
 * @version 05, novembro, 2007
 *
 */
public interface Jogador {
	
	public String getNome();
	
	public int jogada(int[][] tabuleiro, int corDaMinhaBola);

}
