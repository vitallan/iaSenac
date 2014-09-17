/*
 * JogadorPaquiderme.java
 *
 * Created on 3 de Novembro de 2008, 11:03
 */

package linhaQuatro.jogadores;

/**
 *
 * @author Allan, Robson e Thiago
 */
public class JogadorPaquiderme1 implements Jogador {
    int corBolaInimigo;
    public int jogada(int[][] tabuleiro, int corDaMinhaBola) {
        
        int play = 3;
        //Define the color of the enemy ball
        if(corDaMinhaBola==1){
            corBolaInimigo=2;
        } else{
            corBolaInimigo=1;
        }
        
        //If i could loose in next play, i try to prevent enemy victory
        if(looseInNextTurn(tabuleiro, corDaMinhaBola, corBolaInimigo)!=-1){
            play = looseInNextTurn(tabuleiro, corDaMinhaBola, corBolaInimigo);
            return play;
        }
        
        //If i could win in next play, i try to do this
        if(winInNextTurn(tabuleiro, corDaMinhaBola, corBolaInimigo)!=-1){
            play = winInNextTurn(tabuleiro, corDaMinhaBola, corBolaInimigo);
        }
        
        //If i could loose in next play at diagonal position, i try to prevent enemy victory
        if(dontLooseInDiagonal(tabuleiro, corDaMinhaBola, corBolaInimigo)!=-1){
            play = dontLooseInDiagonal(tabuleiro, corDaMinhaBola, corBolaInimigo);
        }
        
        if(dontLooseInHorizontalWithSpace(tabuleiro, corDaMinhaBola, corBolaInimigo)!=-1){
            play = dontLooseInHorizontalWithSpace(tabuleiro, corDaMinhaBola, corBolaInimigo);
        }
        
        
        //Verifies if the right side is all populated, so, start to play in left side
        if(tabuleiro[0][3]!=0 && tabuleiro[0][4]!=0 && tabuleiro[0][5]!=0 && tabuleiro[0][6]!=0 ){
            play=0;
        }
        
        //Search for a possible play
        while(verifyColumn(tabuleiro, play)==false){
            if(play<6){
                play++;
            } else{
                play=0;
            }
        }
        
        return play;
    }
    
    public String getNome() {
        return "Paquiderme1";
    }
    
    //This method verifies if is possible to play in the chosen column
    public boolean verifyColumn(int[][] tabuleiro, int nextPlace){
        //If player can play in this column(there's space to place the ball), return true.
        if(nextPlace>6){
            verifyColumn(tabuleiro, nextPlace--);
        }
        if(nextPlace<0){
            verifyColumn(tabuleiro, nextPlace++);
        }
        if (tabuleiro[0][nextPlace]==0){
            return true;
        } else{
            return false;
        }
    }
    
    //This method verifies if enemy could win in one play, so i will prevent the victory
    public int looseInNextTurn(int[][] tabuleiro, int corDaMinhaBola, int corBolaInimigo){
        int enemyWinFlag=0;
        int dangerLine=-1;
        //Verifies if enemy will win in any place in the next play.
        //Vertical
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(tabuleiro[j][i]==corBolaInimigo){
                    enemyWinFlag++;
                    if(enemyWinFlag==3){
                        if(j>=3&&tabuleiro[j-3][i]==0){
                            dangerLine=i;
                            return dangerLine;
                        } else{
                            if(j==2&&tabuleiro[j-2][i]==0){
                                dangerLine=i;
                                return dangerLine;
                            }
                        }
                    }
                } else{
                    enemyWinFlag=0;
                }
            }
        }
        //Horizontal
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(tabuleiro[i][j]==corBolaInimigo){
                    enemyWinFlag++;
                    if(enemyWinFlag==3){
                        if(j<6&&tabuleiro[i][j+1]==0){
                            dangerLine=j+1;
                            return dangerLine;
                        }
                        if(j>3&&tabuleiro[i][j-3]==0){
                            dangerLine=j-3;
                            return dangerLine;
                        }
                    }
                } else{
                    enemyWinFlag=0;
                }
            }
        }
        
        return dangerLine;
    }
    
    //This method verifies if i could win in one play, so i will place my ball in the winning position
    public int winInNextTurn(int[][] tabuleiro, int corDaMinhaBola, int corBolaInimigo){
        int iWinFlag=0;
        int victoryLine=-1;
        //Verifies if i will win in any place in the next play.
        //Vertical
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(tabuleiro[j][i]==corDaMinhaBola){
                    iWinFlag++;
                    if(iWinFlag==3){
                        if(j>=3&&tabuleiro[j-3][i]==0){
                            victoryLine=i;
                            return victoryLine;
                        } else{
                            if(j==2&&tabuleiro[j-2][i]==0){
                                victoryLine=i;
                                return victoryLine;
                            }
                        }
                    }
                } else{
                    iWinFlag=0;
                }
            }
        }
        //Horizontal
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(tabuleiro[i][j]==corDaMinhaBola){
                    iWinFlag++;
                    if(iWinFlag==3){
                        if(j<6&&tabuleiro[i][j+1]==0){
                            victoryLine=j+1;
                            return victoryLine;
                        }
                        if(j>3&&tabuleiro[i][j-3]==0){
                            victoryLine=j-3;
                            return victoryLine;
                        }
                    }
                } else{
                    iWinFlag=0;
                }
            }
        }
        return victoryLine;
    }
    
    public int dontLooseInDiagonal(int[][] tabuleiro, int corDaMinhaBola, int corBolaInimigo){
        int play=-1;
        try{
            //Verifies middle of diagonal
            for(int i=1;i<6;i++){
                for(int j=1;j<6;j++){
                    if(tabuleiro[i][j]==0){
                        if(tabuleiro[i-1][j-1]==corBolaInimigo && tabuleiro[i+1][j+1]==corBolaInimigo && tabuleiro[i+1][j]!=0 ){
                            if(i!=5&&j!=1){
                                play=j;
                                return play;
                            }
                        }
                        if(tabuleiro[i-1][j+1]==corBolaInimigo && tabuleiro[i+1][j-1]==corBolaInimigo && tabuleiro[i+1][j]!=0 ){
                            if(i!=5&&j!=5){
                                play=j;
                                return play;
                            }
                        }
                    }
                }
            }
            
            //Verifies diagonal in this form : \ .... at the top
            for(int i=6;i>2;i--){
                for(int j=6;j>2;j--){
                    if(tabuleiro[i][j]==corBolaInimigo && tabuleiro[i-1][j-1]==corBolaInimigo && tabuleiro[i-1][j-2]!=0){
                        if(tabuleiro[i-2][j-2]==0){
                            play=j-2;
                            return play;
                        }
                    }
                }
            }
            
            //Verifies diagonal in this form : / .... at the top
            for(int i=6;i>2;i--){
                for(int j=0;j<4;j++){
                    if(tabuleiro[i][j]==corBolaInimigo && tabuleiro[i-1][j+1]==corBolaInimigo && tabuleiro[i-1][j+2]!=0){
                        if(tabuleiro[i-2][j+2]==0){
                            play=j+2;
                            return play;
                        }
                    }
                }
            }
            
            
            //Verifies diagonal in this form : \ .... at the bottom
            for(int i=6;i>2;i--){
                for(int j=6;j>2;j--){
                    if(tabuleiro[i][j]==0 && tabuleiro[i-1][j-1]==corBolaInimigo && tabuleiro[i-2][j-2]==corBolaInimigo){
                        if(tabuleiro[i+1][j]!=0 && i+1<6){
                            play=j;
                            return play;
                        }
                        if(i==6)
                        {
                            play=j;
                            return play;
                        }
                    }
                }
            }
            
            //Verifies diagonal in this form : / .... at the bottom
            for(int i=6;i>2;i--){
                for(int j=0;j<4;j++){
                    if(tabuleiro[i][j]==0 && tabuleiro[i-1][j+1]==corBolaInimigo && tabuleiro[i-2][j+2]==corBolaInimigo){
                        if(i<6 && tabuleiro[i+1][j]!=0){
                            play=j;
                            return play;
                        }
                        if(i==6){
                         play=j;
                            return play;
                        }
                    }
                }
            }
        } catch(Exception e){
            
        }
        
        return play;
    }
 
    public int dontLooseInHorizontalWithSpace(int[][] tabuleiro, int corDaMinhaBola, int corBolaInimigo){
        int play=-1;
        //Dont loose in horizontal with space at the left side of middle
        for(int i=6;i>0;i--){
            if(tabuleiro[i][3]==corBolaInimigo){
                if(tabuleiro[i][0]==corBolaInimigo && tabuleiro[i][1]==corBolaInimigo && tabuleiro[i][2]==0){
                    if(i<6 && tabuleiro[i+1][2]!=0){
                        play=2;
                        return play;
                    }
                    else if(i==6){
                        play=2;
                        return play;
                    }
                }
           }
        }
        
        for(int i=6;i>0;i--){
            if(tabuleiro[i][3]==corBolaInimigo){
                if(tabuleiro[i][0]==corBolaInimigo && tabuleiro[i][1]==0 && tabuleiro[i][2]==corBolaInimigo){
                    if(i<6 && tabuleiro[i+1][1]!=0){
                        play=1;
                        return play;
                    }
                    else if(i==6){
                        play=1;
                        return play;
                    }
                }
           }
        }
        
        //Dont loose in horizontal with space at the right side of middle
        for(int i=6;i>0;i--){
            if(tabuleiro[i][3]==corBolaInimigo){
                if(tabuleiro[i][5]==corBolaInimigo && tabuleiro[i][6]==corBolaInimigo && tabuleiro[i][4]==0){
                    if(i<6 && tabuleiro[i+1][4]!=0){
                        play=4;
                        return play;
                    }
                    else if(i==6){
                        play=4;
                        return play;
                    }
                }
           }
        }
        
        for(int i=6;i>0;i--){
            if(tabuleiro[i][3]==corBolaInimigo){
                if(tabuleiro[i][4]==corBolaInimigo && tabuleiro[i][5]==0 && tabuleiro[i][6]==corBolaInimigo ){
                    if(i<6 && tabuleiro[i+1][5]!=0){
                        play=5;
                        return play;
                    }
                    else if(i==6){
                        play=5;
                        return play;
                    }
                }
           }
        }
        return play;
    }
}



