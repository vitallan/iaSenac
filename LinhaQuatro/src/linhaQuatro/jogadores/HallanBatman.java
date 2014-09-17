package linhaQuatro.jogadores;

import java.util.ArrayList;

/**
 * Classe responsavel pela nossa nota alta em IA \o/
 * 
 * @author Leonardo Antonio Goncalves Baptista
 *         Rafael Almeida Silva
 *         Vitor Navarro
 * @version 28, nov, 2008
 */
 	public class HallanBatman implements Jogador{

            static int nroJogada = 0;
            static ArrayList bloqueadasLinhas;// = new ArrayList();
            static ArrayList bloqueadasColunas;// = new ArrayList();
            static int minhaBola;
            
	        @Override
 	        public String getNome() {
 	                return "Hallan Batman";
 	        }
                


 	        @Override
 	        public int jogada(int[][] tabuleiro, int corDaMinhaBola) {
                    EstadoMinMaxHallanBatman estado;
                    bloqueadasLinhas = new ArrayList();
                    bloqueadasColunas = new ArrayList();
                    minhaBola = corDaMinhaBola;

                    nroJogada++;
                    //Verifica se nao precisa fazer jogada forcada para nao perder...
                    int jog = verJogadasForcadas(tabuleiro,corDaMinhaBola);
                    //printJogadasBloqueadas();
                    if (jog < 7)
                        return jog;
                    //System.out.println("Entrando no minmax");
                    //Se não tem jogadas imediatas vamos ao minimax...
                    estado = new EstadoMinMaxHallanBatman(tabuleiro,corDaMinhaBola,0,corDaMinhaBola==1 ? 2 : 1);
                    
                    int profundidade =6;
                    int jogada = MinMaxHallanBatman.getJogada(estado,profundidade);
                    
                    if (jogada < 7)
                        if (!isFull(tabuleiro,jogada))
                            return jogada;


                    //Se o minimax so tiver opcoes de jogadas bloqueadas vamos perder...
                    //Mas pode ser a ultima coluna, vamos na sorte.
                    jogada = primeiraColunaVazia(tabuleiro,0);
                    
                    while (jogada != 7 && isBlocked(tabuleiro, jogada)){
                        jogada = primeiraColunaVazia(tabuleiro,jogada+1);
                    }
                    if (jogada < 7)
                        return jogada;
                    return primeiraColunaVazia(tabuleiro,0);

                }
                
                int qtdColunasAtivas(int[][] tabuleiro){
                    int colunas = 7;
                    for(int j=0;j < 7;j++){
                        if (tabuleiro[0][j] != 0)
                            colunas--;
                    }
                    return colunas;
                }

                //DEBUG
                void printJogadasBloqueadas(){
                    for(int i=0; i< bloqueadasColunas.size(); i++){
                        System.out.println("Jogada Bloqueada: (" +bloqueadasColunas.get(i) + "," +bloqueadasLinhas.get(i)+")"  );
                    }
                }
                

                boolean isFull(int[][] tabuleiro, int jogada){
                    return tabuleiro[0][jogada] != 0;
                }

                int primeiraColunaVazia(int[][] tabuleiro, int index){
                    for(int j=index;j < 7;j++){
                        if (tabuleiro[0][j] == 0)
                            return j;
                    }
                    return 7; //ueh ?? Acabou o tabuleiro ?!?!
                }

                //Mega funcao que nao deixa o oponente ganhar XD
                int analizaPeca(int i,int j, int[][] tabuleiro){
                    int corInimigo = tabuleiro[i][j];
                    int jogadaPric1 = 7; //Jogada prioridade 1
                    int jogadaPric2 = 7; //Jogada prioridade 2 -> Desativada por baguncar o minimax..
                    
                    int soma = 0;
                    
                    //Analisa na horizontal... ida
                    soma = 0;
                    if (j < 4) {
                        int num = 0;
                        int jogada = 7;
                        int nJogadas = 0;
                        while (num < 4){
                            if (tabuleiro[i][j+num] == corInimigo)
                                soma++;
                            if (tabuleiro[i][j+num] == 0  ){
                                //Garante que a jogada sera grudada na peca do adversario.
                                if (nJogadas == 0)
                                    jogada = j+num;
                                nJogadas++;
                            }
                            num++;
                        }
                        if (jogada < 7){
                            if (soma == 3 && tabuleiro[i][jogada] == 0 && (i==6 || tabuleiro[i+1][jogada] != 0))
                                jogadaPric1 = jogada;
                            //Nao fazer besteira, jogando onde nao pode...
                            else if (soma == 3 && tabuleiro[i][jogada] == 0 && (i<6 && tabuleiro[i+1][jogada] == 0)){
                                    bloqueadasColunas.add(jogada);
                                    bloqueadasLinhas.add(i+1);
                            }
                            else if (soma == 2 && j>0 && j<6 && nJogadas == 2 && tabuleiro[i][jogada] == 0 && (i==6 || tabuleiro[i+1][jogada] != 0)){
                                //Verificando se a jogada realmente me oferece risco, ex: 0 0 x 0 0 x 0 -> nao oferece
                                if ( !(tabuleiro[i][j] == corInimigo && tabuleiro[i][j+1] == 0 && tabuleiro[i][j+2] == 0) )
                                    jogadaPric2 =jogada;
                            }
                        }
                        
                    }
                    //volta..
                    soma = 0;
                    if (j > 2) {
                        int num = 0;
                        int jogada = 7;
                        int nJogadas = 0;
                        while (num < 4){
                            if (tabuleiro[i][j-num] == corInimigo)
                                soma++;
                            if (tabuleiro[i][j-num] == 0 ){
                                //Garante que a jogada sera grudada.
                                if (nJogadas == 0)
                                    jogada = j-num;
                                nJogadas++;
                            }
                            num++;
                        }
                        //toma as decisoes..
                        if (jogada < 7){
                            if (soma == 3 && tabuleiro[i][jogada] == 0 && (i==6 || tabuleiro[i+1][jogada] != 0))
                                jogadaPric1 = jogada;
                            //Nao fazer besteira, jogando onde nao pode...
                            else if (soma == 3 && tabuleiro[i][jogada] == 0 && (i<6 && tabuleiro[i+1][jogada] == 0)){
                                    bloqueadasColunas.add(jogada);
                                    bloqueadasLinhas.add(i+1);
                            }
                            else if (soma == 2 && j>0 && j<6 && nJogadas == 2 && tabuleiro[i][jogada] == 0 && (i==6 || tabuleiro[i+1][jogada] != 0)){
                                //Verificando se a jogada realmente me oferece risco, ex: 0 0 x 0 0 x 0 -> nao oferece
                                if ( !(tabuleiro[i][j] == corInimigo && tabuleiro[i][j-1] == 0 && tabuleiro[i][j-2] == 0) ){
                                    jogadaPric2 = jogada;
                                }
                            }
                        }
                    }
                    soma = 0;
                    //Analisa as diagonais...
                    //para direitaaaaa ----->
                    if (j < 4 && i > 2){
                        //System.out.println("Vendo diagonais ->(" + i + "," + j + ")");
                        int num = 0;
                        int jogadaJ = 7;
                        int jogadaI = 7;
                        int nJogadas = 0;
                        while (num < 4){
                            if (tabuleiro[i-num][j+num] == corInimigo)
                                soma++;
                            if (tabuleiro[i-num][j+num] == minhaBola)
                                soma =0;
                            if (tabuleiro[i-num][j+num] == 0 ){
                                //Garante que a jogada sera grudada.
                                if (nJogadas == 0){
                                    jogadaJ = j+num;
                                    jogadaI = i-num;
                                }
                                nJogadas++;
                            }
                            num++;
                        }
                        //Pegando um caso especifico, de bloquear a jogada na ultima peca em baixo.
                        if (nJogadas == 0 &&i < 6 && j > 0 && soma == 3 && tabuleiro[i+1][j-1] == 0){
                            if (i == 5 || tabuleiro[i+2][j-1] != 0)
                                jogadaPric1 = j-1;
                            else if (i < 5 && tabuleiro[i+2][j-1] == 0){
                                bloqueadasColunas.add(j-1);
                                bloqueadasLinhas.add(i+2);
                            }
                        }
                        //Vamos as decisoes denovo...
                        if (jogadaJ < 7){
                            //pegando jogada de cima...
                            if (soma == 3 && tabuleiro[jogadaI][jogadaJ] == 0 ){
                                if (jogadaI == 6 || tabuleiro[jogadaI+1][jogadaJ] != 0)
                                    jogadaPric1 = jogadaJ;
                                else if (jogadaI < 6 && tabuleiro[jogadaI+1][jogadaJ] == 0){
                                    bloqueadasColunas.add(jogadaJ);
                                    bloqueadasLinhas.add(jogadaI+1);
                                }
                            }
                            //pegando jogada de baixo...
                            if (soma == 3 && j > 0 && i < 6 && tabuleiro[i+1][j-1] == 0 ){
                                if (i == 5 || tabuleiro[i+2][j-1] != 0)
                                    jogadaPric1 = j-1;
                                else if (i < 5 && tabuleiro[i+2][j-1] == 0){
                                    bloqueadasColunas.add(j-1);
                                    bloqueadasLinhas.add(i+2);
                                }
                            }
                        }
                    }
                    soma =0;
                    //voltando para esquerdaaaaaaaaaaa <------------
                    if (j > 2 && i > 2){
                        //System.out.println("Vendo diagonais <- (" + i + "," + j + ")");
                        int num = 0;
                        int jogadaJ = 7;
                        int jogadaI = 7;
                        int nJogadas = 0;
                        while (num < 4){
                            if (tabuleiro[i-num][j-num] == corInimigo)
                                soma++;
                            if (tabuleiro[i-num][j-num] == minhaBola)
                                soma =0;
                            if (tabuleiro[i-num][j-num] == 0 ){
                                //Garante que a jogada sera grudada.
                                if (nJogadas == 0){
                                    jogadaJ = j-num;
                                    jogadaI = i-num;
                                }
                                nJogadas++;
                            }
                            num++;
                        }
                        //Pegando um caso especifico, de bloquear a jogada na ultima peca em baixo.
                        if (nJogadas == 0 && i < 6 && j < 6 && soma == 3 && tabuleiro[i+1][j+1] == 0){
                            if (i == 5 || tabuleiro[i+2][j+1] != 0)
                                jogadaPric1 = j+1;
                            else if (i < 5 && tabuleiro[i+2][j+1] == 0){
                                bloqueadasColunas.add(j+1);
                                bloqueadasLinhas.add(i+2);
                            }
                        }

                        //Vamos as decisoes denovo...
                        if (jogadaJ < 7){
                            if (soma == 3 && tabuleiro[jogadaI][jogadaJ] == 0 ){
                                if (jogadaI == 6 || tabuleiro[jogadaI+1][jogadaJ] != 0)
                                    jogadaPric1 = jogadaJ;
                                else if (jogadaI < 6 && tabuleiro[jogadaI+1][jogadaJ] == 0){
                                    bloqueadasColunas.add(jogadaJ);
                                    bloqueadasLinhas.add(jogadaI+1);
                                }
                            }
                            if (soma == 3 && j < 6 && i < 6 && tabuleiro[i+1][j+1] == 0 ){
                                if (i == 5 || tabuleiro[i+2][j+1] != 0)
                                    jogadaPric1 = j+1;
                                else if (i < 5 && tabuleiro[i+2][j+1] == 0){
                                    bloqueadasColunas.add(j+1);
                                    bloqueadasLinhas.add(i+2);
                                }
                            }
                        }
                    }
                    
                    //Analisa na vertical...
                    if (i > 2) {
                        if (tabuleiro[i-1][j] == corInimigo && tabuleiro[i-2][j] == corInimigo && tabuleiro[i-3][j] == 0)
                            jogadaPric1 = j;
                    }

                    //Desativada por baguncar minimax.
                    /*if (jogadaPric1 == 7)
                        if (! isBlocked(tabuleiro, jogadaPric2))
                            jogadaPric1 = jogadaPric2;*/
                     
                    return jogadaPric1;
                }

                //Sera que a jogada esta bloqueada ?
                boolean isBlocked(int[][] tabuleiro,int j){
                    for (int x = 0;x< bloqueadasColunas.size();x++) {
                        if (bloqueadasColunas.get(x).equals(j) ){
                            for(int y = 6; y > -1;y--){
                                if (tabuleiro[y][x] == 0){
                                    if (bloqueadasLinhas.get(x).equals(y))
                                        return true;
                                }
                            }
                        }
                    }
                    return false;
                }

                //Chama a mega funcao para todas as pecas inimigas...
                int verJogadasForcadas(int[][] tabuleiro,int corDaMinhaBola){
                    int corInimigo = 1;
                    if (corDaMinhaBola == 1)
                            corInimigo = 2;

                    for (int i=0;i < 7;i++){
                        for (int j=0;j < 7;j++){
                            if (tabuleiro[i][j] == corInimigo){
                                int teste = analizaPeca(i,j,tabuleiro);
                                if (teste < 7){
                                    return teste;
                                }
                            }
                        }
                    }
                        
                    return 7;
                }
 	}
        
        
