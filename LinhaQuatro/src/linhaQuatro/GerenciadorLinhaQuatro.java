package linhaQuatro;

import java.util.ArrayList;

import linhaQuatro.jogadores.HallanBatman;
import linhaQuatro.jogadores.Jogador;
import linhaQuatro.jogadores.JogadorARM2;
import linhaQuatro.jogadores.JogadorAleatorio;
import linhaQuatro.jogadores.JogadorBGM;
import linhaQuatro.jogadores.JogadorGuru;
import linhaQuatro.jogadores.JogadorMDF;
import linhaQuatro.jogadores.JogadorMinMax;
import linhaQuatro.jogadores.JogadorPaquiderme1;
import linhaQuatro.jogadores.JogadorTux;
import linhaQuatro.jogadores.Timao;

/**
 * Classe responsável por gerenciar os jogos.
 * 
 * @author Fabrício J. Barth
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
		jogadores.add(new JogadorAleatorio());
		//jogadores.add(new JogadorManual());
		//jogadores.add(new JogadorAleatorioFocado());
		jogadores.add(new JogadorMDF());
		jogadores.add(new JogadorGuru());
		jogadores.add(new JogadorPaquiderme1());
		jogadores.add(new HallanBatman());
		jogadores.add(new JogadorMinMax());
		jogadores.add(new JogadorTux());
		jogadores.add(new JogadorBGM());
		jogadores.add(new JogadorARM2());
		/*
		 * Jogadores do 1o sem. de 2008
		 */
		jogadores.add(new Timao());
		//jogadores.add(new JogadorX());
		
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
		System.out.println("Resultados da competição");
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
