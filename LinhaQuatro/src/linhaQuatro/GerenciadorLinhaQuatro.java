package linhaQuatro;

import java.util.ArrayList;

import linhaQuatro.jogadores.Jogador;
import linhaQuatro.jogadores.JogadorManual;
import linhaQuatro.jogadores.JogadorPaquiderme1;

/**
 * Classe responsavel por gerenciar os jogos.
 * 
 * @author Fabricio J. Barth
 * @version 06, junho, 2008
 *
 */
public class GerenciadorLinhaQuatro {
	
	public GerenciadorLinhaQuatro(){
		/**
		 * Jogadores que irao participar
		 * da competicao
		 * 
		 * Para inserir um novo jogador, insira aqui.
		 * 
		 * Jogadores da competicao 2008/2
		 */
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		jogadores.add(new JogadorManual());
		jogadores.add(new JogadorPaquiderme1());
		/*
		 * Jogos
		 */
		ArrayList<JogoLinhaQuatro> jogos = new ArrayList<JogoLinhaQuatro>();
		for(int i=0; i<jogadores.size(); i++){
			for(int j=i+1; j<jogadores.size(); j++){
				System.out.println("\nNovo jogo entre "+jogadores.get(i).getNome()+"  "+jogadores.get(j).getNome());
				jogos.add(new JogoLinhaQuatro(jogadores.get(i),jogadores.get(j)));
				System.out.println("\nNovo jogo entre "+jogadores.get(j).getNome()+"  "+jogadores.get(i).getNome());
				jogos.add(new JogoLinhaQuatro(jogadores.get(j),jogadores.get(i)));
			}
		}
		
		
		/**
		 * Resultados
		 */
		System.out.println("");
		System.out.println("Resultados da competicao");
		for(int i=0; i<jogos.size(); i++){
			System.out.println(jogos.get(i).resultado());
		}
		
		/*
		 * Imprimindo apenas o nome dos vencedores
		 */
		System.out.println("");
		System.out.println("Imprimindo apenas o nome dos vencedores");
		for(int i=0; i<jogos.size(); i++){
			System.out.println(jogos.get(i).vencedor());
		}
	}
	
	public static void main(String args[]){
		new GerenciadorLinhaQuatro();
	}

}
