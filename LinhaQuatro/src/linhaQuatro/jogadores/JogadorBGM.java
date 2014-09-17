package linhaQuatro.jogadores;

import java.util.Collections;
import java.util.Vector;

/**
 * Implementacao do jogador BGM para competicao
 *
 * @author Marcelo Honorio
 * @author Gabriel Koji
 * @author Bruno Hererra
 * @version 08, dezembro, 2007
 *
 */
public class JogadorBGM implements Jogador{
	private int LARGURA, ALTURA, PLAYER, RECURSAO_MAX=7;
	private int[][] TABULEIRO;
	private int[][] COPIATABULEIRO;
	private Vector movimentos;
    private int[] colunaAltura;
    int alfa, beta;
    private static int recurDepth=0;
    
    /* Construtor da classe */
    public JogadorBGM(){
    	alfa = Integer.MAX_VALUE;
    	beta = Integer.MIN_VALUE;
    	movimentos = new Vector();
    	this.setLarguraAltura();
    }
    
    /* Método que calcula a proxima jogda baseada no método minimax */
    
	public int jogada(int[][] tabuleiro, int corDaMinhaBola) {
		this.TABULEIRO = tabuleiro;
		this.PLAYER = corDaMinhaBola;
		int coluna;		
		movimentos.removeAllElements();
		COPIATABULEIRO = this.CopiaMatriz(TABULEIRO);
		setColunaAltura();
		/*printGridReal();
		printVetor(colunaAltura);*/		
		coluna = 0;
        int prevValue = -RECURSAO_MAX - 1;
        for(int x=0; x < LARGURA; x++){
        	if(colunaAltura[x] >= ALTURA) continue;
        	int value = minmax(PLAYER, x);
        	if(value > prevValue){
        		movimentos.removeAllElements();
                prevValue=value;
            }
            if(value==prevValue) movimentos.add(new Integer(x));
        }
        if(movimentos.size()>0) {
            Collections.shuffle(movimentos);
            coluna=((Integer)movimentos.get(0)).intValue();
        }
        return coluna;
    }
	
	/* Método que calcula o minimax baseado em uma função utilidade */
	public int minmax(int player, int coluna) {
        int valor = 0;
        if(colunaAltura[coluna] >= ALTURA) return 0;
        recurDepth++;
        COPIATABULEIRO[coluna][colunaAltura[coluna]] = player;
        colunaAltura[coluna]++;        
        if(quatroEmLinha() > 0){
        	if(player == this.PLAYER)
                valor = RECURSAO_MAX + 1 - recurDepth;
            else valor = -RECURSAO_MAX - 1 + recurDepth;
        }
        if(recurDepth < RECURSAO_MAX && valor == 0) {
            valor = RECURSAO_MAX + 1;
            for(int x=0; x < LARGURA; x++){
            	if(colunaAltura[x] >= ALTURA) continue;
                int v = minmax(3-player, x);
                if(valor == (RECURSAO_MAX + 1)) valor = v;
                else if(player == this.PLAYER) { if(valor > v) valor = v; }
                else if(v > valor) valor = v;
            }
        }
        recurDepth--;
        colunaAltura[coluna]--;
        COPIATABULEIRO[coluna][colunaAltura[coluna]]=0;
        return valor;
    }
	
	/* Método que popula o vetor de alturas das colunas */
	private void setColunaAltura(){
		int aux = 0;
		for(int i=0; i < LARGURA; i++){
			for(int j=0; j < ALTURA; j++){
				if(TABULEIRO[j][i] == 0){
					aux++;
				}
			}
			colunaAltura[i] = ALTURA - aux;
			aux = 0;
		}
	}
	
	/* Método que copia a matriz e rotaciona */
	private int[][] CopiaMatriz(int[][] matriz){
		int[][] copia = new int[matriz.length][matriz[0].length];		
		int aux = 0;
        for (int i=matriz.length-1;i>=0;i--){
        	for (int j=0;j<matriz.length;j++){
        		copia[j][aux] = matriz[i][j];
        	}
        	aux++;
        }
		return copia;
	}
	
	/* Método que seta os valores da largura e altura */
	private void setLarguraAltura(){
		/*this.LARGURA = this.TABULEIRO.length;
		this.ALTURA = this.TABULEIRO[0].length;*/
		LARGURA = ALTURA = 7;
		this.colunaAltura = new int[LARGURA];
	}
	
	/* Função utilidade do minimax */
	int quatroEmLinha() {
        int num, player;

        for(int y=0; y<ALTURA; y++) {
            num=0; player=0;
            for(int x=0; x<LARGURA; x++) {
                if(COPIATABULEIRO[x][y]==player) num++;
                else { num=1; player = COPIATABULEIRO[x][y]; }
                if(num==4 && player>0) return player;
            }
        }

        for(int x=0; x<LARGURA; x++) {
            num=0; player=0;
            for(int y=0; y<ALTURA; y++) {
                if(COPIATABULEIRO[x][y]==player) num++;
                else { num=1; player=COPIATABULEIRO[x][y]; }
                if(num==4 && player>0) return player;
            }
        }

        for(int xStart=0, yStart=2; xStart<4; ) {
            num=0; player=0;
            for(int x=xStart, y=yStart; x<LARGURA && y<ALTURA; x++, y++) {
                if(COPIATABULEIRO[x][y]==player) num++;
                else { num=1; player=COPIATABULEIRO[x][y]; }
                if(num==4 && player>0) return player;
            }
            if(yStart==0) xStart++;
            else yStart--;
        }

        for(int xStart=0, yStart=3; xStart<4; ) {
            num=0; player=0;
            for(int x=xStart, y=yStart; x<LARGURA && y>=0; x++, y--) {
                if(COPIATABULEIRO[x][y]==player) num++;
                else { num=1; player=COPIATABULEIRO[x][y]; }
                if(num==4 && player>0) return player;
            }
            if(yStart==ALTURA-1) xStart++;
            else yStart++;
        }
        // Ninguem está ganhando
        return 0;
    }
	
	public String getNome() {
		return "Jogador BGM";
	}
	
	/* void printGridReal(){
	    	for(int i=0;i<LARGURA;i++){
	    		for(int j=0;j<ALTURA;j++){
	    			System.out.print(COPIATABULEIRO[i][j]+" ");
	    		}
	    		System.out.println();
	    	}
	    }
	    
	   void printVetor(int[] vetor){
	    	System.out.println("Colum altura: ");
	    	for(int i=0;i<vetor.length;i++){
	    		System.out.print(vetor[i] + " ");
	    	}
	    	System.out.println();
	    }*/
}
