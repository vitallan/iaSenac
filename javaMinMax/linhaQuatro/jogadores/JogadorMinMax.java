package linhaQuatro.jogadores;

public class JogadorMinMax implements Jogador {

	@Override
	public int jogada(int[][] tabuleiro, int corDaMinhaBola) {
		try{
			EstadoMinMax estado;
			if(corDaMinhaBola==1)
				estado = new EstadoMinMax(tabuleiro,corDaMinhaBola,0,2);
			else
				estado = new EstadoMinMax(tabuleiro,corDaMinhaBola,0,1);
			//MinMax minMax = new MinMax(estado,2);
			return MinMax.getJogada(estado,6);
			//return MinMaxAlphaBeta.getJogada(estado,2);
		}catch(Exception e){
			System.out.println("Erro: "+e);
			return 0;
		}
	}

	@Override
	public String getNome() {
		return "MinMax Fabrício";
	}

}
