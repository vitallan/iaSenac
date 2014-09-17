package linhaQuatro.jogadores;

import java.util.ArrayList;

public class MinMaxHallanBatman {
	
	private static EstadoUtilidade maiorEstado;
	
	//Max
	private static double maxValue(EstadoUtilidade estado, int profundidade){
		if(profundidade==0){
			return estado.eval();
		}
		double v = Double.NEGATIVE_INFINITY; //infinito negativo
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
	//Mini
	private static double minValue(EstadoUtilidade estado, int profundidade){
		if(profundidade==0){
			return estado.eval();
		}
		double v = Double.POSITIVE_INFINITY; //infinito positivo
		profundidade = profundidade - 1;
		ArrayList<EstadoUtilidade> s = estado.sucessors();
		for(int i=0; i<s.size(); i++){
			v = Math.min(v, maxValue(s.get(i),profundidade));
		}
		return v;
	}
	
	/**
	 * A funcao deste metodo eh retornar a operacao
	 * que o jogador deve efetuar.
	 * 
         * Se o minimax so achar jogadas bloqueadas, retorna 7 para a classe se virar...
         *  Caso isso aconteca, pode ser que perdemos.
         *
	 * @return
	 */
	public static int getJogada(EstadoUtilidade estado, int profundidade){
		//double valor = maxValue(estado,profundidade);
		//System.out.println("Valor escolhido "+valor);
		maxValue(estado,profundidade);
                //Se teve lugar pra jogar...
                if (maiorEstado != null)
                    return maiorEstado.getJogada();
                else
                    return 7;

	}

}
