package linhaQuatro.jogadores;

import java.util.List;


public class JogadorMDF implements Jogador{

	

	int nivel = 4;
	


	public String getNome() {

		return "MDF";
	}

	public int jogada(int[][] tabuleiro, int corDaMinhaBola) {
		Robo.rodadas = 0;
	

			Robo inicial  = new Robo();
			inicial.tabuleiro = tabuleiro;			
			inicial.corDaMinhaBola = corDaMinhaBola;			
			inicial.profundidade = 0;
			minimax(inicial,  nivel);
			inicial = inicial.melhorSucessor;
			return inicial.coluna;

	}


	private boolean compara(int[][] tabuleiro, int[][] tabuleiro2) {
		for (int i = 0; i < 7; i++){
			for (int j = 0;j<6;j++){
				if (tabuleiro[i][j] != tabuleiro2[i][j])
					return false;
			}
		
		}
		return true;
	}

	private int minimax(Robo inicial,  int nivel) {
		if (inicial.naoFinal() && nivel > 0){
			inicial.melhorValor=0;
			inicial.melhorSucessor=null;
			int valor;
			List<Robo> lista = inicial.sucessores();
			for (Robo e : lista){
				valor = minimax(e,  nivel-1);
				if ((e.corDaMinhaBola == 1) && (valor < inicial.melhorValor)
						|| ((e.corDaMinhaBola ==2) && (valor > inicial.melhorValor))
						|| (inicial.melhorSucessor == null)){
					inicial.melhorValor = valor;
					inicial.melhorSucessor = e;
					
					
					;
				}


				// TODO Auto-generated method stub
			}
			
			return inicial.melhorValor;
		}
		else{
			inicial.melhorValor = inicial.custo();
			inicial.melhorSucessor=inicial;
			return inicial.melhorValor;}

	}
}







