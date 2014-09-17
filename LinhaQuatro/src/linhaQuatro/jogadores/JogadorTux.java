package linhaQuatro.jogadores;

public class JogadorTux implements Jogador {

	String dados;
	int corDaMinhaBola;
	int corDaBolaAdv;
	int jogadas;
	
	public JogadorTux(){
		Serialize database = new Serialize();
		this.dados = database.loadSerialized();
	}
	
	public String getNome() {
		return "Tux";
	}

	public int jogada(int[][] tabuleiro, int corDaMinhaBola) {
		this.corDaMinhaBola = corDaMinhaBola;
		if (corDaMinhaBola == 1){
			this.corDaBolaAdv = 2;
		} else {
			this.corDaBolaAdv = 1;
		}
		
		int[][] novoTabuleiro = new int[7][7];
		for (int i = 0; i < tabuleiro.length; i++){
			for (int j = 0; j < tabuleiro[i].length; j++){
				novoTabuleiro[j][6-i] = tabuleiro[i][j];
			}
		}
		tabuleiro = novoTabuleiro;
		
		int[] vazio = new int[7];
		int jogadas = 0;
		int jogadaDerrota = -1;
		for (int i = 0; i < tabuleiro.length; i++){
			for (int j = 0; j < tabuleiro[i].length; j++){
				if (tabuleiro[i][j] == 0){
					vazio[i] = j;
					jogadas += j;
					int[] jogadaForcada = verificaFinal(tabuleiro, i, j);
					//jogadaForcada[1] = 1 => Vitoria
					//jogadaForcada[1] = 0 => Derrota
					//jogadaForcada[1] = -1 => Nao ha
					if (jogadaForcada[1] == 1){
						return jogadaForcada[0];
					} else if (jogadaForcada[1] == 0){
						jogadaDerrota = jogadaForcada[0];
					}
					break;
				} else if (j+1 == tabuleiro[i].length){
					vazio[i] = j+1;
					jogadas += j+1;
				}
			}
		}
		if (jogadaDerrota >= 0){
			return jogadaDerrota;
		}
		this.jogadas = jogadas;
		
		if (jogadas == 1){
			return 4;
		}
		
		if (tabuleiro[3][1] == 0){
			return 3;
		} else if (tabuleiro[3][0] == this.corDaBolaAdv){
			if (tabuleiro[4][0] == 0){
				return 4;
			} else if (tabuleiro[2][0] == 0){
				return 2;
			}
		} else if (jogadas <= 5){
			return 3;
		}
		
		int[] pontuacao = new int[7];
		if ((this.corDaMinhaBola == 1 && this.jogadas == 6) ||
				this.corDaMinhaBola == 2 && this.jogadas == 7){
			pontuacao = consultaBase(tabuleiro, vazio);
		} else {
			pontuacao = minimax(tabuleiro, vazio, corDaMinhaBola, 5);
			//System.out.println(">>>minimax");
		}
		
		for (int i = 0; i < vazio.length; i++){
			if (vazio[i] == tabuleiro.length){
				pontuacao[i] = -999999;
			}
		}
		
		int maiorPontuacao = -99999;
		int indiceMaior = 0;
		for (int i = 0; i < pontuacao.length; i++){
			//System.out.println(pontuacao[i]);
			if (maiorPontuacao < pontuacao[i]){
				maiorPontuacao = pontuacao[i];
				indiceMaior = i;
			}
		}

		//System.out.println(indiceMaior);
		//System.out.println(maiorPontuacao);
		return indiceMaior;
	}

	private int[] minimax(int[][] tabuleiro, int[] vazio, int vez, int profundidade){
		int[] pontuacao = new int[7];
		int[] pontuacaoi = new int[7];
		int[] jogadaFinal;
		
		int novavez;
		if (vez == 1){
			novavez = 2;
		} else {
			novavez = 1;
		}
		
		for (int i = 0; i < tabuleiro.length; i++){
			if (vazio[i] != tabuleiro.length){
				jogadaFinal = verificaFinal(tabuleiro, i, vazio[i]);
				if (jogadaFinal[1] == 0){
					if (profundidade >= 3){
						pontuacao[i] = -999;
					} else {
						pontuacao[i] = -1;
					}
				} else if (jogadaFinal[1] == 1){
					if (profundidade >= 3){
						pontuacao[i] = 500;
					} else {
						pontuacao[i] = 1;
					}
				} else if (jogadaFinal[1] == -1){
					tabuleiro[i][vazio[i]] = vez;
					vazio[i] += 1;
					if (profundidade > 0){
						pontuacaoi = minimax(tabuleiro, vazio, novavez, profundidade-1);
					}
					vazio[i] -= 1;
					tabuleiro[i][vazio[i]] = 0;
					
					pontuacao[i] = 0;
					for (int j = 0; j < pontuacaoi.length; j++){
						pontuacao[i] += pontuacaoi[j];
					}
				}
			} else {
				pontuacao[i] = 0;
			}
		}
		return pontuacao;
	}
	
	private int[] consultaBase(int[][] tabuleiro, int[] vazio){
		int[] pontuacao = new int[7];
		for (int i = 0; i < tabuleiro.length; i++){
			if (vazio[i] != tabuleiro.length){
				tabuleiro[i][vazio[i]] = corDaMinhaBola;
				if (corDaMinhaBola == 1){
					vazio[i] += 1;
					for (int j = 0; j < tabuleiro.length; j++){
						if (vazio[j] != tabuleiro.length){
							tabuleiro[j][vazio[j]] = corDaBolaAdv;
							pontuacao[i] += pegaPontuacao(tabuleiro);
							tabuleiro[j][vazio[j]] = 0;
						}
					}
					vazio[i] -= 1;
				} else {
					pontuacao[i] = pegaPontuacao(tabuleiro);
				}
				tabuleiro[i][vazio[i]] = 0;
			} else {
				pontuacao[i] = -99;
			}
		}
		return pontuacao;
	}
	
	private int[] verificaFinal(int[][] tabuleiro, int x, int y){
		int[] jogadaForcada = new int[2];
		jogadaForcada[0] = -1;
		jogadaForcada[1] = -1;
		
		//VERIFICA VERTICAL
		if (y >= 3){
			if (tabuleiro[x][y-1] == tabuleiro[x][y-2] && 
					tabuleiro[x][y-2] == tabuleiro[x][y-3]){
				jogadaForcada[0] = x;
				if (tabuleiro[x][y-1] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x][y-1] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}
		
		//VERIFICA HORIZONTAL
		try{
			if (tabuleiro[x-1][y] == tabuleiro[x-2][y] && 
					tabuleiro[x-2][y] == tabuleiro[x-3][y]){
				jogadaForcada[0] = x;
				if (tabuleiro[x-1][y] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x-1][y] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		try{
			if (tabuleiro[x+1][y] == tabuleiro[x-1][y] && 
					tabuleiro[x-1][y] == tabuleiro[x-2][y]){
				jogadaForcada[0] = x;
				if (tabuleiro[x+1][y] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x+1][y] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		try{
			if (tabuleiro[x+2][y] == tabuleiro[x+1][y] && 
					tabuleiro[x+1][y] == tabuleiro[x-1][y]){
				jogadaForcada[0] = x;
				if (tabuleiro[x+2][y] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x+2][y] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		try{
			if (tabuleiro[x+3][y] == tabuleiro[x+2][y] && 
					tabuleiro[x+2][y] == tabuleiro[x+1][y]){
				jogadaForcada[0] = x;
				if (tabuleiro[x+3][y] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x+3][y] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		
		//VERIFICA DIAGONAL DIREITA
		try{
			if (tabuleiro[x-1][y-1] == tabuleiro[x-2][y-2] && 
					tabuleiro[x-2][y-2] == tabuleiro[x-3][y-3]){
				jogadaForcada[0] = x;
				if (tabuleiro[x-1][y-1] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x-1][y-1] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		try{
			if (tabuleiro[x+1][y+1] == tabuleiro[x-1][y-1] && 
					tabuleiro[x-1][y-1] == tabuleiro[x-2][y-2]){
				jogadaForcada[0] = x;
				if (tabuleiro[x+1][y+1] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x+1][y+1] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		try{
			if (tabuleiro[x+2][y+2] == tabuleiro[x+1][y+1] && 
					tabuleiro[x+1][y+1] == tabuleiro[x-1][y-1]){
				jogadaForcada[0] = x;
				if (tabuleiro[x+2][y+2] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x+2][y+2] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		try{
			if (tabuleiro[x+3][y+3] == tabuleiro[x+2][y+2] && 
					tabuleiro[x+2][y+2] == tabuleiro[x+1][y+1]){
				jogadaForcada[0] = x;
				if (tabuleiro[x+3][y+3] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x+3][y+3] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		
		//VERIFICA DIAGONAL ESQUERDA
		try{
			if (tabuleiro[x+1][y-1] == tabuleiro[x+2][y-2] && 
					tabuleiro[x+2][y-2] == tabuleiro[x+3][y-3]){
				jogadaForcada[0] = x;
				if (tabuleiro[x+1][y-1] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x+1][y-1] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		try{
			if (tabuleiro[x-1][y+1] == tabuleiro[x+1][y-1] && 
					tabuleiro[x+1][y-1] == tabuleiro[x+2][y-2]){
				jogadaForcada[0] = x;
				if (tabuleiro[x-1][y+1] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x-1][y+1] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		try{
			if (tabuleiro[x-2][y+2] == tabuleiro[x-1][y+1] && 
					tabuleiro[x-1][y+1] == tabuleiro[x+1][y-1]){
				jogadaForcada[0] = x;
				if (tabuleiro[x-2][y+2] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x-2][y+2] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		try{
			if (tabuleiro[x-3][y+3] == tabuleiro[x-2][y+2] && 
					tabuleiro[x-2][y+2] == tabuleiro[x-1][y+1]){
				jogadaForcada[0] = x;
				if (tabuleiro[x-3][y+3] == this.corDaMinhaBola){
					jogadaForcada[1] = 1;
				} else if (tabuleiro[x-3][y+3] == this.corDaBolaAdv){
					jogadaForcada[1] = 0;
				}
				return jogadaForcada;
			}
		}catch(Exception e){}
		
		return jogadaForcada;
	}
	
	private int pegaPontuacao(int[][] tabuleiro){
		String estado = implode(tabuleiro[0], ",");
		String estado2 = implode(tabuleiro[6], ",");
		for (int i = 1; i < tabuleiro.length; i++){
			estado += "," + implode(tabuleiro[i], ",");
			estado2 += "," + implode(tabuleiro[6 - i], ",");
		}
		estado = estado.replace('0', 'b');
		estado = estado.replace('1', 'x');
		estado = estado.replace('2', 'o');
		estado2 = estado2.replace('0', 'b');
		estado2 = estado2.replace('1', 'x');
		estado2 = estado2.replace('2', 'o');
		
		int pos = this.dados.indexOf(estado);
		int tam = estado.length();
		if (pos <= 0){
			pos = this.dados.indexOf(estado2);
			tam = estado2.length();
		}

		if (pos > 0){
			//System.out.println(">>>>>>>>>>>>>>>>>\n>>>>>>>>>>>>>\n>>>>>>>>>>>>>>>"+estado);
			String status = this.dados.substring(pos + tam + 1, pos + tam + 4);
			//System.out.println(status);
			if (this.corDaMinhaBola == 1){
				if (status.equals("win")){
					return 2;
				} else if (status.equals("dra")){
					return 1;
				} else if (status.equals("los")){
					return -2;
				}
			} else {
				if (status.equals("win")){
					return -2;
				} else if (status.equals("dra")){
					return 1;
				} else if (status.equals("los")){
					return 2;
				}
			}
		}
		return 0;
	}
	
	private String implode(int[] array, String separator){
		String aString = new Integer(array[0]).toString();
		for (int i = 1; i < 6; i++){
			aString += separator + new Integer(array[i]).toString();
		}
		return aString;
	}
}
