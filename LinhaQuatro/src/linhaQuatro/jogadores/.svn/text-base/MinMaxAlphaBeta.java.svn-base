package linhaQuatro.jogadores;

import java.util.ArrayList;

public class MinMaxAlphaBeta {

	private static EstadoUtilidade maiorEstado;
		
	/**
	 * 
	 * 
	 * @param estado
	 * @param alpha -1000
	 * @param beta +1000
	 * @param profundidade
	 * @return
	 */
	private static double maxValue(EstadoUtilidade estado, double alpha, double beta, int profundidade){
		if(profundidade==0){
			System.out.println("");
			System.out.println("");
			//System.out.println("Tabuleiro = "+estado.printTabuleiro());
			System.out.println("Profundidade = "+profundidade);
			System.out.println("Jogada = "+estado.getJogada());
			System.out.println("Avaliação = "+estado.eval());
			return estado.eval();
		}
		//double v = -10000; //infinito negativo
		profundidade = profundidade - 1;
		ArrayList<EstadoUtilidade> s = estado.sucessors();
		for(int i=0; i<s.size(); i++){
			double valor = minValue(s.get(i),alpha,beta,profundidade);
			if(valor > alpha){
				alpha = valor;
				maiorEstado = s.get(i);
			}
			if(alpha>=beta)
				return alpha; //cutoff
		}
		return alpha;
	}
	
	private static double minValue(EstadoUtilidade estado, double alpha, double beta, int profundidade){
		if(profundidade==0){
			System.out.println("");
			System.out.println("");
			//System.out.println("Tabuleiro = "+estado.printTabuleiro());
			System.out.println("Profundidade = "+profundidade);
			System.out.println("Jogada = "+estado.getJogada());
			System.out.println("Avaliação = "+estado.eval());
			return estado.eval();
		}
		//double v = 10000; //infinito positivo
		profundidade = profundidade - 1;
		ArrayList<EstadoUtilidade> s = estado.sucessors();
		for(int i=0; i<s.size(); i++){
			beta = Math.min(beta, maxValue(s.get(i),alpha,beta,profundidade));
			if(beta<=alpha)
				return beta; //cutoff
		}
		return beta;
	}
	
	/**
	 * A função deste método é retornar a operação
	 * que o jogador deve efetuar.
	 * 
	 * @return
	 */
	public static int getJogada(EstadoUtilidade estado, int profundidade){
		maxValue(estado,-1000,1000,profundidade);
		return maiorEstado.getJogada();
	}
	
}
