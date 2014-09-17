package linhaQuatro;

import linhaQuatro.jogadores.*;

/**
 * Classe responsável por gerenciar os jogos.
 * 
 * @author Fabrício J. Barth
 * @version 09, novembro, 2007
 *
 */
public class GerenciadorLinhaQuatro {
	
	public GerenciadorLinhaQuatro(){
		/**
		 * Jogadores que irao participar
		 * da competicao
		 * 
		 * Para inserir um novo jogador, insira aqui.
		 */
		JogadorAleatorio aleatorio = new JogadorAleatorio();
		//JogadorManual manual = new JogadorManual();
		JogadorMinMax minmax = new JogadorMinMax();
		//JogadorAleatorioFocado aleatorioFocado = new JogadorAleatorioFocado();
		
		/**
		 * Sequencia de jogos
		 */
		//System.out.println("\nNovo jogo.");
		//JogoLinhaQuatro j1 = new JogoLinhaQuatro(aleatorio,manual);
		//System.out.println("\nNovo jogo.");
		//JogoLinhaQuatro j2 = new JogoLinhaQuatro(manual,aleatorio);
		
		//System.out.println("\nNovo jogo.");
		//JogoLinhaQuatro j3 = new JogoLinhaQuatro(aleatorioFocado,aleatorio);
		//System.out.println("\nNovo jogo.");
		//JogoLinhaQuatro j4 = new JogoLinhaQuatro(aleatorio,aleatorioFocado);
		
		//System.out.println("\nNovo jogo.");
		//JogoLinhaQuatro j5 = new JogoLinhaQuatro(aleatorioFocado,manual);
		//System.out.println("\nNovo jogo.");
		//JogoLinhaQuatro j6 = new JogoLinhaQuatro(manual,aleatorioFocado);
		
		System.out.println("\nNovo jogo.");
		JogoLinhaQuatro j1 = new JogoLinhaQuatro(aleatorio,minmax);
		//JogoLinhaQuatro j1 = new JogoLinhaQuatro(manual,minmax);
		System.out.println("\nNovo jogo.");
		JogoLinhaQuatro j2 = new JogoLinhaQuatro(minmax,aleatorio);
		//JogoLinhaQuatro j2 = new JogoLinhaQuatro(minmax,manual);
		
		/**
		 * Ao inserir um novo jogador, deve-se inserir os jogos correspondentes
		 */
		
		/**
		 * Resultados
		 */
		System.out.println("");
		System.out.println("Resultados da competição");
		System.out.println(j1.resultado());
		System.out.println(j2.resultado());
		//System.out.println(j3.resultado());
		//System.out.println(j4.resultado());
		//System.out.println(j5.resultado());
		//System.out.println(j6.resultado());
	}
	
	public static void main(String args[]){
		new GerenciadorLinhaQuatro();
	}

}
