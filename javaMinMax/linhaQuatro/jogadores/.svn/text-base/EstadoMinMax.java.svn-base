package linhaQuatro.jogadores;

import java.util.ArrayList;

public class EstadoMinMax implements EstadoUtilidade{

	private int[][] tabuleiro;
	private int corDaMinhaBola;
	private int jogada;
	
	/**
	 * fica alternando entre 1 e 2
	 * Serve para verificar quem jogou por ultimo.
	 */
	private int jogadorAnterior;
	
	public EstadoMinMax(int[][] tabuleiro, int corDaMinhaBola, int jogada, int jogadorAnterior){
		this.tabuleiro = tabuleiro;
		this.corDaMinhaBola = corDaMinhaBola;
		this.jogada = jogada;
		this.jogadorAnterior = jogadorAnterior;
	}
	
	@Override
	public double eval() {
		int vitoria = 500;
		int derrota = -1500;
		/*
		 * abrangencia pecas
		 */
		int p1 = 1;
		/*
		 * vitoria com duas pecas
		 */
		int p2 = 15;
		/*
		 * vitoria com uma peca
		 */
		int p3 = 50;
		
		return 
			(vitoria * euGanho())+
			(derrota * oponenteGanha())+
			(p1 * areaAbrangenciaMinhasPecas())+
			(p2 * possibilidadeVitoriaMinhaComDuasPecas())+
			(-1 * p2 * possibilidadeVitoriaOponenteComDuasPecas())+
			(p3 * possibilidadeVitoriaMinhaComUmaPeca())+
			(-1 * p3 * possibilidadeVitoriaOponenteComUmaPeca());
		
		// apenas para testes
		//double retorno = 0;
		//for(int i=0; i<7; i++){
		//	for(int j=0; j<7; j++){
		//		if(this.tabuleiro[i][j]==this.corDaMinhaBola){
		//			retorno = retorno + i + j;
		//		}
		//	}
		//}
		//return retorno;
	}
	
	/**
	 * Retorna o numero de situacoes onde eh possivel a
	 * vitoria do oponente com a insercao de uma peca
	 * 
	 * @return numero de situacoes
	 */
	private int possibilidadeVitoriaOponenteComUmaPeca() {
		int cont = 0;
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				if(this.tabuleiro[i][j]!=this.corDaMinhaBola && this.tabuleiro[i][j]!=0){
					cont = 
						cont + 
						verificaColuna3(i,j) +
						verificaLateral3(i,j) +
						verificaDiagonalEsq3(i,j) + 
						verificaDiagonalDir3(i,j);
				}
			}
		}
		return cont;
	}

	/**
	 * Retorna o numero de situacoes onde eh possivel
	 * com a insercao de uma peca alcancar a vitoria
	 * 
	 * @return numero de situacoes
	 */
	private int possibilidadeVitoriaMinhaComUmaPeca() {
		int cont = 0;
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				if(this.tabuleiro[i][j]==this.corDaMinhaBola){
					cont = 
						cont + 
						verificaColuna3(i,j) +
						verificaLateral3(i,j) +
						verificaDiagonalEsq3(i,j) + 
						verificaDiagonalDir3(i,j);
				}
			}
		}
		return cont;
	}

	/**
	 * 
	 * TODO falta verificar se a diagonal eh 
	 * conectada com zeros
	 * 
	 * @see verificaDiagonalDir
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int verificaDiagonalDir3(int i, int j) {
		int numerosIguais = 1;
		int apontadorX = i;
		int apontadorY = j;
		while(apontadorX>=i-3 && apontadorX>=1 &&
				apontadorY<=j+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX-1][apontadorY+1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX--;
				apontadorY++;
			}else{
				break;
			}
		}
		
		apontadorX = i;
		apontadorY = j;
		while(apontadorX<=i+3 && apontadorX<=5 &&
				apontadorY>=j-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX+1][apontadorY-1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX++;
				apontadorY--;
			}else{
				break;
			}
		}
		
		if(numerosIguais>=3)
			return 1;
		else
			return 0;
	}

	/**
	 * TODO falta verificar se a diagonal eh 
	 * conectada com zeros
	 * 
	 * @see verificaDiagonalEsq
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int verificaDiagonalEsq3(int i, int j) {
		int numerosIguais = 1;
		int apontadorX = i;
		int apontadorY = j;
		while(apontadorX>=i-3 && apontadorX>=1 &&
				apontadorY>=j-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX-1][apontadorY-1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX--;
				apontadorY--;
			}else{
				break;
			}
		}
		
		apontadorX = i;
		apontadorY = j;
		while(apontadorX<=i+3 && apontadorX<=5 &&
				apontadorY<=j+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX+1][apontadorY+1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX++;
				apontadorY++;
			}else{
				break;
			}
		}
		
		if(numerosIguais>=3)
			return 1;
		else
			return 0;
	}

	/**
	 * TODO falta verificar se a diagonal eh 
	 * conectada com zeros
	 * 
	 * @see verificaLateral
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int verificaLateral3(int i, int j) {
		int numerosIguais = 1;
		int apontador = j;
		while(apontador<=j+3 && apontador<=5){
			if(this.tabuleiro[i][apontador+1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontador++;
			}else{
				break;
			}
		}
		
		apontador = j;
		while(apontador>=j-3 && apontador>=1){
			if(this.tabuleiro[i][apontador-1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontador--;
			}else{
				break;
			}
		}
		
		if(numerosIguais>=3)
			return 1;
		else
			return 0;
	}

	/**
	 * 
	 * TODO falta verificar se a diagonal eh 
	 * conectada com zeros
	 * 
	 * @see verificaColuna
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int verificaColuna3(int i, int j) {
		if(i<=3){
			if(this.tabuleiro[i][j]==
				this.tabuleiro[i+1][j] &&
				this.tabuleiro[i][j]==
					this.tabuleiro[i+2][j] &&
					this.tabuleiro[i][j]==
						this.tabuleiro[i+3][j]){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Retorna o numero de situacoes onde eh possivel a
	 * vitoria do oponente com a insercao de duas pecas
	 * 
	 * @return numero de situacoes
	 */
	private int possibilidadeVitoriaOponenteComDuasPecas() {
		int cont = 0;
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				if(this.tabuleiro[i][j]!=this.corDaMinhaBola && this.tabuleiro[i][j]!=0){
					cont = 
						cont + 
						verificaColuna2(i,j) +
						verificaLateral2(i,j) +
						verificaDiagonalEsq2(i,j) + 
						verificaDiagonalDir2(i,j);
				}
			}
		}
		return cont;
	}

	/**
	 * Retorna o numero de situacoes onde eh possivel
	 * com a insercao de duas pecas alcancar a vitoria
	 * 
	 * @return numero de situacoes
	 */
	private int possibilidadeVitoriaMinhaComDuasPecas() {
		int cont = 0;
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				if(this.tabuleiro[i][j]==this.corDaMinhaBola){
					cont = 
						cont + 
						verificaColuna2(i,j) +
						verificaLateral2(i,j) +
						verificaDiagonalEsq2(i,j) + 
						verificaDiagonalDir2(i,j);
				}
			}
		}
		return cont;
	}

	/**
	 * 
	 * TODO falta verificar se a diagonal eh 
	 * conectada com zeros
	 * 
	 * @see verificaDiagonalDir
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int verificaDiagonalDir2(int i, int j) {
		int numerosIguais = 1;
		int apontadorX = i;
		int apontadorY = j;
		while(apontadorX>=i-3 && apontadorX>=1 &&
				apontadorY<=j+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX-1][apontadorY+1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX--;
				apontadorY++;
			}else{
				break;
			}
		}
		
		apontadorX = i;
		apontadorY = j;
		while(apontadorX<=i+3 && apontadorX<=5 &&
				apontadorY>=j-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX+1][apontadorY-1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX++;
				apontadorY--;
			}else{
				break;
			}
		}
		
		if(numerosIguais>=2)
			return 1;
		else
			return 0;
	}

	/**
	 * 
	 * TODO falta verificar se a diagonal eh 
	 * conectada com zeros
	 * 
	 * @see verificaDiagonalEsq
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int verificaDiagonalEsq2(int i, int j) {
		int numerosIguais = 1;
		int apontadorX = i;
		int apontadorY = j;
		while(apontadorX>=i-3 && apontadorX>=1 &&
				apontadorY>=j-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX-1][apontadorY-1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX--;
				apontadorY--;
			}else{
				break;
			}
		}
		
		apontadorX = i;
		apontadorY = j;
		while(apontadorX<=i+3 && apontadorX<=5 &&
				apontadorY<=j+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX+1][apontadorY+1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX++;
				apontadorY++;
			}else{
				break;
			}
		}
		
		if(numerosIguais>=2)
			return 1;
		else
			return 0;
	}

	/**
	 * 
	 * TODO falta verificar se a diagonal eh 
	 * conectada com zeros
	 * 
	 * @see verificaLateral
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int verificaLateral2(int i, int j) {
		int numerosIguais = 1;
		int apontador = j;
		while(apontador<=j+3 && apontador<=5){
			if(this.tabuleiro[i][apontador+1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontador++;
			}else{
				break;
			}
		}
		
		apontador = j;
		while(apontador>=j-3 && apontador>=1){
			if(this.tabuleiro[i][apontador-1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontador--;
			}else{
				break;
			}
		}
		
		if(numerosIguais>=2)
			return 1;
		else
			return 0;
	}

	/**
	 * 
	 * TODO falta verificar se a diagonal eh 
	 * conectada com zeros
	 * 
	 * @see verificaColuna
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int verificaColuna2(int i, int j) {
		if(i<=3){
			if(this.tabuleiro[i][j]==
				this.tabuleiro[i+1][j] &&
				this.tabuleiro[i][j]==
					this.tabuleiro[i+2][j] &&
					this.tabuleiro[i][j]==
						this.tabuleiro[i+3][j]){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Retorna o numero de casas que podem ser preenchidas
	 * com pecas do meu jogador e conectadas com pecas (do meu
	 * jogador) jah existentes no tabuleiro.
	 * 
	 * @return numero de casas que podem ser preenchidas
	 */
	private int areaAbrangenciaMinhasPecas() {
		int cont = 0;
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				if(this.tabuleiro[i][j]==this.corDaMinhaBola){
					cont = 
						cont + 
						espacosVaziosColuna(i,j) + 
						espacosVaziosLateral(i,j) + 
						espacosVaziosDiagonalEsq(i,j) + 
						espacosVaziosDiagonalDir(i,j);
				}
			}
		}
		return cont;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int espacosVaziosDiagonalDir(int i, int j) {
		int numerosIguais = 1;
		int apontadorX = i;
		int apontadorY = j;
		while(apontadorX>=i-3 && apontadorX>=1 &&
				apontadorY<=j+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX-1][apontadorY+1]==0){
				numerosIguais++;
				apontadorX--;
				apontadorY++;
			}else{
				break;
			}
		}
		
		apontadorX = i;
		apontadorY = j;
		while(apontadorX<=i+3 && apontadorX<=5 &&
				apontadorY>=j-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX+1][apontadorY-1]==0){
				numerosIguais++;
				apontadorX++;
				apontadorY--;
			}else{
				break;
			}
		}
		return numerosIguais;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int espacosVaziosDiagonalEsq(int i, int j) {
		int numerosIguais = 1;
		int apontadorX = i;
		int apontadorY = j;
		while(apontadorX>=i-3 && apontadorX>=1 &&
				apontadorY>=j-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX-1][apontadorY-1]==0){
				numerosIguais++;
				apontadorX--;
				apontadorY--;
			}else{
				break;
			}
		}
		
		apontadorX = i;
		apontadorY = j;
		while(apontadorX<=i+3 && apontadorX<=5 &&
				apontadorY<=j+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX+1][apontadorY+1]==0){
				numerosIguais++;
				apontadorX++;
				apontadorY++;
			}else{
				break;
			}
		}
		
		return numerosIguais;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int espacosVaziosLateral(int i, int j) {
		int numerosIguais = 1;
		int apontador = j;
		while(apontador<=j+3 && apontador<=5){
			if(this.tabuleiro[i][apontador+1]==0){
				numerosIguais++;
				apontador++;
			}else{
				break;
			}
		}
		
		apontador = j;
		while(apontador>=j-3 && apontador>=1){
			if(this.tabuleiro[i][apontador-1]==0){
				numerosIguais++;
				apontador--;
			}else{
				break;
			}
		}
		return numerosIguais;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int espacosVaziosColuna(int i, int j) {
		int cont = 0;
		for(int k=i+1; k<7; k++){
			if(this.tabuleiro[k][j]==0)
				cont++;
			else
				break;
		}
		return cont;
	}

	/**
	 * Se identificada uma situacao onde eu ganho o
	 * jogo este metodo retorna 1, senao 0
	 * @return 1 ou 0
	 */
	private int euGanho() {
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				if(this.tabuleiro[i][j]==this.corDaMinhaBola){
					if(verificaColuna(i,j) || verificaLateral(i,j) ||	verificaDiagonalEsq(i,j) || verificaDiagonalDir(i,j))
						return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * Se identificada uma situacao onde o oponente ganha
	 * o jogo este método retorna 1, senao 0
	 * @return 1 ou 0
	 */
	private int oponenteGanha() {
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				if(this.tabuleiro[i][j]!=this.corDaMinhaBola && this.tabuleiro[i][j]!=0){
					if(verificaColuna(i,j) || verificaLateral(i,j) ||	verificaDiagonalEsq(i,j) || verificaDiagonalDir(i,j)){
						//System.out.println("Oponente ganha");
						return 1;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Verifica se encontra uma situacao final na diagonal (/)
	 * @return
	 */
	private boolean verificaDiagonalDir(int i, int j) {
		int numerosIguais = 1;
		int apontadorX = i;
		int apontadorY = j;
		while(apontadorX>=i-3 && apontadorX>=1 &&
				apontadorY<=j+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX-1][apontadorY+1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX--;
				apontadorY++;
			}else{
				break;
			}
		}
		
		apontadorX = i;
		apontadorY = j;
		while(apontadorX<=i+3 && apontadorX<=5 &&
				apontadorY>=j-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX+1][apontadorY-1]==
				this.tabuleiro[i][j]){
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
	 * Verificar se encontra uma situacao final na diagonal (\)
	 * 
	 * @return
	 */
	private boolean verificaDiagonalEsq(int i, int j) {
		int numerosIguais = 1;
		int apontadorX = i;
		int apontadorY = j;
		while(apontadorX>=i-3 && apontadorX>=1 &&
				apontadorY>=j-3 && apontadorY>=1){
			if(this.tabuleiro[apontadorX-1][apontadorY-1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontadorX--;
				apontadorY--;
			}else{
				break;
			}
		}
		
		apontadorX = i;
		apontadorY = j;
		while(apontadorX<=i+3 && apontadorX<=5 &&
				apontadorY<=j+3 && apontadorY<=5){
			if(this.tabuleiro[apontadorX+1][apontadorY+1]==
				this.tabuleiro[i][j]){
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
	 * Verifica se encontra uma situacao final na lateral
	 * @return
	 */
	private boolean verificaLateral(int i, int j) {
		int numerosIguais = 1;
		int apontador = j;
		while(apontador<=j+3 && apontador<=5){
			if(this.tabuleiro[i][apontador+1]==
				this.tabuleiro[i][j]){
				numerosIguais++;
				apontador++;
			}else{
				break;
			}
		}
		
		apontador = j;
		while(apontador>=j-3 && apontador>=1){
			if(this.tabuleiro[i][apontador-1]==
				this.tabuleiro[i][j]){
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
	 * Verifica se encontra uma situacao final na coluna
	 * @return
	 */
	private boolean verificaColuna(int i, int j) {
		if(i<=3){
			if(this.tabuleiro[i][j]==
				this.tabuleiro[i+1][j] &&
				this.tabuleiro[i][j]==
					this.tabuleiro[i+2][j] &&
					this.tabuleiro[i][j]==
						this.tabuleiro[i+3][j]){
				return true;
			}
		}
		return false;
	}

	@Override
	public int getJogada() {
		return this.jogada;
	}

	@Override
	public ArrayList<EstadoUtilidade> sucessors() {
		ArrayList<EstadoUtilidade> sucessores = new ArrayList<EstadoUtilidade>();
		for(int opcoesDejogada=0; opcoesDejogada<=6; opcoesDejogada++){
			for(int i=6; i>=0; i--){
				if(this.tabuleiro[i][opcoesDejogada]==0){
					int[][] tabuleiroTemp;
					tabuleiroTemp = transfereValores(this.tabuleiro);
					tabuleiroTemp[i][opcoesDejogada]=this.corDaMinhaBola;
					if(this.jogadorAnterior==1){
						tabuleiroTemp[i][opcoesDejogada]=2;
						//System.out.println(this.printTabuleiro(tabuleiroTemp));
						sucessores.add(new EstadoMinMax(tabuleiroTemp,this.corDaMinhaBola,opcoesDejogada,2));
					}else{
						tabuleiroTemp[i][opcoesDejogada]=1;
						//System.out.println(this.printTabuleiro(tabuleiroTemp));
						sucessores.add(new EstadoMinMax(tabuleiroTemp,this.corDaMinhaBola,opcoesDejogada,1));
					}
					break;
				}
			}
		}
		return sucessores;
	}
	
	private int[][] transfereValores(int[][] in){
		int[][] out = new int[7][7];
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				out[i][j] = in[i][j];
			}
		}
		return out;
	}

	/**
	 * Eh utilizado apenas para debug.
	 */
	public String printTabuleiro(){
		String resultado = "";
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				resultado = resultado + " | "+this.tabuleiro[i][j];
			}
			resultado = resultado + " | " + "\n";
		}
		return resultado;
	}
	
	/**
	 * Eh utilizado apenas para debug
	 * 
	 * @param tabuleiro
	 * @return
	 */
	//private String printTabuleiro(Integer[][] tab){
	//	String resultado = "";
	//	for(int i=0; i<7; i++){
	//		for(int j=0; j<7; j++){
	//			resultado = resultado + " | "+tab[i][j];
	//		}
	//		resultado = resultado + " | " + "\n";
	//	}
	//	return resultado;
	//}
	
}
