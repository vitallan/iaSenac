package linhaQuatro.jogadores;

import java.util.ArrayList;

public class MinMax {
	
	////private EstadoUtilidade estado;
	//private EstadoMinMax estado;
	
	//private int profundidade;
	
	private static EstadoUtilidade maiorEstado;
	
	/**
	 * 
	 * @param estado
	 * @param profundidade
	 */
	//public MinMax(EstadoUtilidade estado, int profundidade){
	//public MinMax(EstadoMinMax estado, int profundidade){
	//	this.estado = estado;
	//	this.profundidade = profundidade;
	//}
	
	private static double maxValue(EstadoUtilidade estado, int profundidade){
		if(profundidade==0){
			//System.out.println("");
			//System.out.println("");
			//System.out.println("Tabuleiro = "+estado.printTabuleiro());
			//System.out.println("Jogada = "+estado.getJogada());
			//System.out.println("Avaliação = "+estado.eval());
			return estado.eval();
		}
		double v = -10000; //infinito negativo
		profundidade = profundidade - 1;
		ArrayList<EstadoUtilidade> s = estado.sucessors();
		for(int i=0; i<s.size(); i++){
			double valor = minValue(s.get(i),profundidade);
			if(valor > v){
				v = valor;
				maiorEstado = s.get(i);
			}
			//v = Math.max(v, minValue(s.get(i),profundidade--));
		}
		return v;
	}
	
	private static double minValue(EstadoUtilidade estado, int profundidade){
		if(profundidade==0){
			//System.out.println("");
			//System.out.println("");
			//System.out.println("Tabuleiro = "+estado.printTabuleiro());
			//System.out.println("Jogada = "+estado.getJogada());
			//System.out.println("Avaliação = "+estado.eval());
			return estado.eval();
		}
		double v = 10000; //infinito positivo
		profundidade = profundidade - 1;
		ArrayList<EstadoUtilidade> s = estado.sucessors();
		for(int i=0; i<s.size(); i++){
			v = Math.min(v, maxValue(s.get(i),profundidade));
		}
		return v;
	}
	
	/**
	 * A função deste método é retornar a operação
	 * que o jogador deve efetuar.
	 * 
	 * @return
	 */
	public static int getJogada(EstadoUtilidade estado, int profundidade){
		//double valor = maxValue(estado,profundidade);
		//System.out.println("Valor escolhido "+valor);
		maxValue(estado,profundidade);
		return maiorEstado.getJogada();
	}

}
