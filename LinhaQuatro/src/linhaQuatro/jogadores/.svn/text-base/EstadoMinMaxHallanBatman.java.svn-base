package linhaQuatro.jogadores;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Estados da classe Hallan Batman.
 * 
 * @author Leonardo Antonio Goncalves Baptista
 *         Rafael Almeida Silva
 *         Vitor Navarro
 * @version 28, nov, 2008
 */
public class EstadoMinMaxHallanBatman implements EstadoUtilidade{

	private int[][] tabuleiro;
	private int corDaMinhaBola;
	private int jogada;
        private Vector podas;
	/**
	 * fica alternando entre 1 e 2
	 * Serve para verificar quem jogou por ultimo.
	 */
	private int jogadorAnterior;
        
	public EstadoMinMaxHallanBatman(int[][] tabuleiro, int corDaMinhaBola, int jogada, int jogadorAnterior){
		this.tabuleiro = tabuleiro;
		this.corDaMinhaBola = corDaMinhaBola;
		this.jogada = jogada;
		this.jogadorAnterior = jogadorAnterior;
	}
	
	@Override
        //Nao adianta melhorar a funcao utilidade pois funciona bem pra 7 e demora 10s pra profundidade 8 com return 0;
	public double eval() {
            //return 0;
            return possibilidadesDoTabuleiro();
	}

        /*
         * Funcao utilidade do MiniMax
         * Funciona da seguinte forma:
         *  Verifica quais as maneiras de obter 4 em linha.
         *  Para cada maneira temos:
         *      1 ponto se tiver uma peca.
         *      20 pontos se tiver duas pecas
         *      50 pontos se tiver tres pecas
         *      200 pontos se tiver as quatro pecas (vitoria)
         *  Somam-se os pontos de cada jogador
         *  Subtrai do adversario.
         *  Temos nossa funcao utilidade :D 
         */

        int possibilidadesDoTabuleiro(){
            int cont=0;
            int pontosJogador=0;
            int pontosAdversario=0;
            final int CONST_SOMA = 1;
            final int P1 = 1;
            final int P2 = 20;
            final int P3 = 50;
            final int P4 = 200;
            //horizontal
            for (int i = 0; i < 7;i++){
                for (int j =0; j < 4;j++){
                    boolean viuBola1 = false;
                    boolean viuBola2 = false;
                    int soma = 0;
                    for (int vai = 0; vai < 4 && ! (viuBola1 && viuBola2 ); vai++){
                        if (tabuleiro[i][j+vai] == 1)
                            viuBola1 = true;
                        else if (tabuleiro[i][j+vai] == 2)
                            viuBola2 = true;
                        soma += tabuleiro[i][j+vai] != 0 ? CONST_SOMA : 0;
                    }
                    if (soma == 1)
                        soma = P1;
                    else if (soma == 2)
                        soma = P2;
                    else if (soma == 3)
                        soma = P3;
                    else if (soma == 4)
                        soma = P4;
                        
                    if (! (viuBola1 && viuBola2)){
                        if (corDaMinhaBola == 1){
                            pontosJogador += viuBola1 ? soma : 0;
                            pontosAdversario += viuBola2 ? soma : 0;
                        } else {
                            pontosJogador += viuBola2 ? soma : 0;
                            pontosAdversario += viuBola1 ? soma : 0;
                        }
                    }
                    //tabuleiroArrumado[cont][0] = tabuleiro[i][j];
                    //tabuleiroArrumado[cont][1] = tabuleiro[i][j+1];
                    //tabuleiroArrumado[cont][2] = tabuleiro[i][j+2];
                    //tabuleiroArrumado[cont][3] = tabuleiro[i][j+3];
                    cont++;
                }
            }
            //vertical...
            for (int j = 0; j < 7;j++){
                for (int i =0; i < 4;i++){
                    boolean viuBola1 = false;
                    boolean viuBola2 = false;
                    int soma = 0;
                    for (int vai = 0; vai < 4 && ! (viuBola1 && viuBola2 ); vai++){
                        if (tabuleiro[i+vai][j] == 1)
                            viuBola1 = true;
                        else if (tabuleiro[i+vai][j] == 2)
                            viuBola2 = true;
                        soma += tabuleiro[i+vai][j] != 0 ? CONST_SOMA : 0;
                    }
                    if (soma == 1)
                        soma = P1;
                    else if (soma == 2)
                        soma = P2;
                    else if (soma == 3)
                        soma = P3;
                    else if (soma == 4)
                        soma = P4;
                    if (! (viuBola1 && viuBola2)){
                        if (corDaMinhaBola == 1){
                            pontosJogador += viuBola1 ? soma : 0;
                            pontosAdversario += viuBola2 ? soma : 0;
                        } else {
                            pontosJogador += viuBola2 ? soma : 0;
                            pontosAdversario += viuBola1 ? soma : 0;
                        }
                    }
                    //tabuleiroArrumado[cont][0] = tabuleiro[i][j];
                    //tabuleiroArrumado[cont][1] = tabuleiro[i+1][j];
                    //tabuleiroArrumado[cont][2] = tabuleiro[i+2][j];
                    //tabuleiroArrumado[cont][3] = tabuleiro[i+3][j];
                    cont++;
                }
            }
            //diagonal direita
            for (int i = 0; i < 4;i++){
                for (int j =0; j < 4;j++){
                    boolean viuBola1 = false;
                    boolean viuBola2 = false;
                    int soma = 0;
                    for (int vai = 0; vai < 4 && ! (viuBola1 && viuBola2 ); vai++){
                        if (tabuleiro[i+vai][j+vai] == 1)
                            viuBola1 = true;
                        else if (tabuleiro[i+vai][j+vai] == 2)
                            viuBola2 = true;
                        soma += tabuleiro[i+vai][j+vai] != 0 ? CONST_SOMA : 0;
                    }
                    if (soma == 1)
                        soma = P1;
                    else if (soma == 2)
                        soma = P2;
                    else if (soma == 3)
                        soma = P3;
                    else if (soma == 4)
                        soma = P4;
                    if (! (viuBola1 && viuBola2)){
                        if (corDaMinhaBola == 1){
                            pontosJogador += viuBola1 ? soma : 0;
                            pontosAdversario += viuBola2 ? soma : 0;
                        } else {
                            pontosJogador += viuBola2 ? soma : 0;
                            pontosAdversario += viuBola1 ? soma : 0;
                        }
                    }
                    
                    //tabuleiroArrumado[cont][0] = tabuleiro[i][j];
                    //tabuleiroArrumado[cont][1] = tabuleiro[i+1][j+1];
                    //tabuleiroArrumado[cont][2] = tabuleiro[i+2][j+2];
                    //tabuleiroArrumado[cont][3] = tabuleiro[i+3][j+3];
                    cont++;
                }
            }
            for (int i = 0; i < 4;i++){
                for (int j =6; j > 2;j--){
                    boolean viuBola1 = false;
                    boolean viuBola2 = false;
                    int soma = 0;
                    for (int vai = 0; vai < 4 && ! (viuBola1 && viuBola2 ); vai++){
                        if (tabuleiro[i+vai][j-vai] == 1)
                            viuBola1 = true;
                        else if (tabuleiro[i+vai][j-vai] == 2)
                            viuBola2 = true;
                        soma += tabuleiro[i+vai][j-vai] != 0 ? CONST_SOMA : 0;
                    }
                    if (soma == 1)
                        soma = P1;
                    else if (soma == 2)
                        soma = P2;
                    else if (soma == 3)
                        soma = P3;
                    else if (soma == 4)
                        soma = P4;
                    if (! (viuBola1 && viuBola2)){
                        if (corDaMinhaBola == 1){
                            pontosJogador += viuBola1 ? soma : 0;
                            pontosAdversario += viuBola2 ? soma : 0;
                        } else {
                            pontosJogador += viuBola2 ? soma : 0;
                            pontosAdversario += viuBola1 ? soma : 0;
                        }
                    }
                    //tabuleiroArrumado[cont][0] = tabuleiro[i][j];
                    //tabuleiroArrumado[cont][1] = tabuleiro[i+1][j-1];
                    //tabuleiroArrumado[cont][2] = tabuleiro[i+2][j-2];
                    //tabuleiroArrumado[cont][3] = tabuleiro[i+3][j-3];
                    cont++;
                }
            }
            return pontosJogador - pontosAdversario;
        }
	@Override
	public int getJogada() {
		return this.jogada;
	}

        /* Gera os sucessores
         * Tomando cuidado para nao jogar nas jogadas bloqueadas pela classe HallanBatman!
         */
	@Override
	public ArrayList<EstadoUtilidade> sucessors() {
		ArrayList<EstadoUtilidade> sucessores = new ArrayList<EstadoUtilidade>();
		for(int opcoesDejogada=0; opcoesDejogada<=6; opcoesDejogada++){
			for(int i=6; i>=0; i--){
				if(this.tabuleiro[i][opcoesDejogada]==0){
                                    boolean l_entra = true;
                                    
                                    for (int x = 0;x < HallanBatman.bloqueadasColunas.size();x++)
                                    {
                                        if (HallanBatman.bloqueadasColunas.get(x).equals(opcoesDejogada) && HallanBatman.bloqueadasLinhas.get(x).equals(i) && this.corDaMinhaBola == HallanBatman.minhaBola) {
                                            l_entra = false;
                                        }
                                    }
                                    if (!l_entra){
                                        break;
                                    }
                                    int[][] tabuleiroTemp;
                                    tabuleiroTemp = transfereValores(this.tabuleiro);
                                    tabuleiroTemp[i][opcoesDejogada]=this.corDaMinhaBola;
                                    if(this.jogadorAnterior==1){
                                            tabuleiroTemp[i][opcoesDejogada]=2;
                                            //System.out.println(this.printTabuleiro(tabuleiroTemp));
                                            sucessores.add(new EstadoMinMaxHallanBatman(tabuleiroTemp,this.corDaMinhaBola,opcoesDejogada,2));
                                    }else{
                                            tabuleiroTemp[i][opcoesDejogada]=1;
                                            //System.out.println(this.printTabuleiro(tabuleiroTemp));
                                            sucessores.add(new EstadoMinMaxHallanBatman(tabuleiroTemp,this.corDaMinhaBola,opcoesDejogada,1));
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
	
}
