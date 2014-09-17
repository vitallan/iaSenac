package linhaQuatro.jogadores;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Classe que permite a interação de uma pessoa na competicao
 * 
 * @author Fabricio J. Barth
 *
 */
public class JogadorManual implements Jogador{

	@Override
	public int jogada(int[][] tabuleiro, int corDaMinhaBola) {
		try {
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Digite a sua jogada [0-6]: ");
			return new Integer(teclado.readLine()).intValue();
		} catch (Exception e) {
			System.out.println(e);
			return jogada(tabuleiro,corDaMinhaBola);
		}
	}

	@Override
	public String getNome() {
		return "Manual";
	}
}
