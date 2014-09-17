package linhaQuatro;

import linhaQuatro.jogadores.*;

/**
 * 
 * @author Fabrício J. Barth
 * @version 05, November, 2007
 *
 */
public class JogoLinhaQuatro {
	
	private int tabuleiro[][] = new int[7][7];
	private Jogador jogadores[] = new Jogador[2];
	private int jogadorDaVez;
	private int vencedor = 0; //0 significa empate.
	
	private int ultimaPossicaoAlterada_X;
	private int ultimaPossicaoAlterada_Y;
	
	private long tempInicial;
	private long tempFinal;
	private long duracaoJogada;
	
	public JogoLinhaQuatro(Jogador j0, Jogador j1){
		this.inicializaTabuleiro();
		this.inicializaJogadores(j0,j1);
	
		while(true){
			
			int jogada;
			boolean condicao;
			
			this.jogadorDaVez = 0;
			do{
				tempInicial = System.currentTimeMillis();
				jogada = this.jogadores[this.jogadorDaVez].jogada(this.tabuleiro,this.jogadorDaVez+1);
				tempFinal = System.currentTimeMillis();
				duracaoJogada = (tempFinal - tempInicial)/1000;
				System.out.println("");
				System.out.println(this.jogadores[this.jogadorDaVez].getNome()+" jogou.");
				System.out.println("Duração da jogada = "+duracaoJogada+" s");
				if(duracaoJogada>=10)
					System.out.println("JOGADA INVALIDA!");
				System.out.println("");
				condicao = this.alteraTabuleiro(jogada);
			}while(!condicao);
			System.out.println(this.print());
			if(this.ehFim()){
				this.vencedor = this.jogadorDaVez+1;
				System.out.println(this.resultado());
				return;
			}
			if(this.ehEmpate()){
				this.vencedor = 0; //empate
				System.out.println("O jogo empatou!");
				return;
			}
			
			this.jogadorDaVez = 1;
			do{
				tempInicial = System.currentTimeMillis();
				jogada = this.jogadores[this.jogadorDaVez].jogada(this.tabuleiro,this.jogadorDaVez+1);
				tempFinal = System.currentTimeMillis();
				duracaoJogada = (tempFinal - tempInicial)/1000;
				System.out.println("");
				System.out.println(this.jogadores[this.jogadorDaVez].getNome()+" jogou.");
				System.out.println("Duração da jogada = "+duracaoJogada+" s");
				if(duracaoJogada>=10)
					System.out.println("JOGADA INVALIDA!");
				System.out.println("");
				condicao = this.alteraTabuleiro(jogada);
			}while(!condicao);
			System.out.println(this.print());
			if(this.ehFim()){
				this.vencedor = this.jogadorDaVez+1;
				System.out.println(this.resultado());
				return;
			}
			if(this.ehEmpate()){
				this.vencedor = 0; //empate
				System.out.println("O jogo empatou!");
				return;
			}
		}
	}
	
	/**
	 * Quando um novo jogador eh adicionado a competicao
	 * deve-se alterar este metodo.
	 */
	private void inicializaJogadores(Jogador j0, Jogador j1){
		this.jogadores[0] = j0;
		this.jogadores[1] = j1;
	}
	
	private void inicializaTabuleiro(){
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				tabuleiro[i][j]=0;
			}
		}
	}
	
	/**
	 * O objetivo deste metodo eh alterar o tabuleiro
	 * com uma jogada.
	 * 
	 * Se a jogada seleciona nao for capaz de alterar
	 * o tabuleiro entao eh retornado false.
	 *
	 * @param jogada
	 */
	private boolean alteraTabuleiro(int jogada){
		for(int i=6; i>=0; i--){
			if(this.tabuleiro[i][jogada]==0){
				this.tabuleiro[i][jogada]=this.jogadorDaVez+1;
				this.ultimaPossicaoAlterada_X = i;
				this.ultimaPossicaoAlterada_Y = jogada;
				return true; 
			}
		}
		return false;
	}
	
	private String print(){
		String retorno = " | ";
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				retorno = retorno + tabuleiro[i][j]+" | ";
			}
			retorno = retorno + "\n" + " | ";
		}
		return retorno;
	}

	/**
	 * 
	 * @return
	 */
	private boolean ehFim(){	
		if(verificaColuna() || verificaLateral() || verificaDiagonalEsq() || verificaDiagonalDir())
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se encontra uma situacao final na coluna
	 * @return
	 */
	private boolean verificaColuna(){
		if(this.ultimaPossicaoAlterada_X<=3){
			if(this.tabuleiro[this.ultimaPossicaoAlterada_X][this.ultimaPossicaoAlterada_Y]==
				this.tabuleiro[this.ultimaPossicaoAlterada_X+1][this.ultimaPossicaoAlterada_Y] &&
				this.tabuleiro[this.ultimaPossicaoAlterada_X][this.ultimaPossicaoAlterada_Y]==
					this.tabuleiro[this.ultimaPossicaoAlterada_X+2][this.ultimaPossicaoAlterada_Y] &&
					this.tabuleiro[this.ultimaPossicaoAlterada_X][this.ultimaPossicaoAlterada_Y]==
						this.tabuleiro[this.ultimaPossicaoAlterada_X+3][this.ultimaPossicaoAlterada_Y]){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica se encontra uma situacao final na lateral
	 * @return
	 */
	private boolean verificaLateral(){
		int numerosIguais = 1;
		int apontador = this.ultimaPossicaoAlterada_Y;
		while(apontador<=this.ultimaPossicaoAlterada_Y+3 && apontador<=5){
			if(this.tabuleiro[this.ultimaPossicaoAlterada_X][apontador+1]==
				this.tabuleiro[this.ultimaPossicaoAlterada_X][this.ultimaPossicaoAlterada_Y]){
				numerosIguais++;
				apontador++;
			}else{
				break;
			}
		}
		
		apontador = this.ultimaPossicaoAlterada_Y;
		while(apontador>=this.ultimaPossicaoAlterada_Y-3 && apontador>=1){
			if(this.tabuleiro[this.ultimaPossicaoAlterada_X][apontador-1]==
				this.tabuleiro[this.ultimaPossicaoAlterada_X][this.ultimaPossicaoAlterada_Y]){
				numerosIguais++;
				apontador--;
			}else{
				break;
			}
		}
		
		if(numerosIguais>=4)
			return true;
		else
			return false;
	}
	
	/**
	 * Verificar se encontra uma situacao final na diagonal (\)
	 * 
	 * @return
	 */
	private boolean verificaDiagonalEsq(){
		int numerosIguais = 1;
		int apontadorX = this.ultimaPossicaoAlterada_X;
		int apontadorY = this.ultimaPossicaoAlterada_Y;
		while(apontadorX>=this.ultimaPossicaoAlterada_X-3 && apontadorX>=1 &&
				apontadorY>=this.ultimaPossicaoAlterada_Y-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX-1][apontadorY-1]==
				this.tabuleiro[this.ultimaPossicaoAlterada_X][this.ultimaPossicaoAlterada_Y]){
				numerosIguais++;
				apontadorX--;
				apontadorY--;
			}else{
				break;
			}
		}
		
		apontadorX = this.ultimaPossicaoAlterada_X;
		apontadorY = this.ultimaPossicaoAlterada_Y;
		while(apontadorX<=this.ultimaPossicaoAlterada_X+3 && apontadorX<=5 &&
				apontadorY<=this.ultimaPossicaoAlterada_Y+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX+1][apontadorY+1]==
				this.tabuleiro[this.ultimaPossicaoAlterada_X][this.ultimaPossicaoAlterada_Y]){
				numerosIguais++;
				apontadorX++;
				apontadorY++;
			}else{
				break;
			}
		}
		
		if(numerosIguais>=4)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se encontra uma situacao final na diagonal (/)
	 * @return
	 */
	public boolean verificaDiagonalDir(){
		int numerosIguais = 1;
		int apontadorX = this.ultimaPossicaoAlterada_X;
		int apontadorY = this.ultimaPossicaoAlterada_Y;
		while(apontadorX>=this.ultimaPossicaoAlterada_X-3 && apontadorX>=1 &&
				apontadorY<=this.ultimaPossicaoAlterada_Y+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX-1][apontadorY+1]==
				this.tabuleiro[this.ultimaPossicaoAlterada_X][this.ultimaPossicaoAlterada_Y]){
				numerosIguais++;
				apontadorX--;
				apontadorY++;
			}else{
				break;
			}
		}
		
		apontadorX = this.ultimaPossicaoAlterada_X;
		apontadorY = this.ultimaPossicaoAlterada_Y;
		while(apontadorX<=this.ultimaPossicaoAlterada_X+3 && apontadorX<=5 &&
				apontadorY>=this.ultimaPossicaoAlterada_Y-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX+1][apontadorY-1]==
				this.tabuleiro[this.ultimaPossicaoAlterada_X][this.ultimaPossicaoAlterada_Y]){
				numerosIguais++;
				apontadorX++;
				apontadorY--;
			}else{
				break;
			}
		}
		
		if(numerosIguais>=4)
			return true;
		else
			return false;
	}
	
	/**
	 * Se todas as casas do tabuleiro estiverem preenchidas, ou seja,
	 * todas as casas diferentes de zero entao trata-se de um empate
	 * 
	 * @return empate retorna true, caso contrario false.
	 */
	private boolean ehEmpate(){
		for(int i=0; i<7; i++)
			for(int j=0; j<7; j++)
				if(this.tabuleiro[i][j]==0)
					return false;
		return true;
	}
	
	/**
	 * Retorna o resultado do jogo
	 * @return venceu 1 ou 2 ou empate
	 */
	public String resultado(){
		if(this.vencedor==1)
			return this.jogadores[0].getNome()+ " venceu "+this.jogadores[1].getNome()+" perdeu";
		else if(this.vencedor==2)
			return this.jogadores[0].getNome()+ " perdeu "+this.jogadores[1].getNome()+" venceu";
		else
			return this.jogadores[0].getNome()+ " empatou "+this.jogadores[1].getNome()+" empatou";
	}
	
	public String vencedor(){
		if(this.vencedor==1)
			return this.jogadores[0].getNome();
		else if(this.vencedor==2)
			return this.jogadores[1].getNome();
		else
			return this.jogadores[0].getNome()+ " empatou "+this.jogadores[1].getNome()+" empatou";
	}
}
