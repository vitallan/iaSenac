/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linhaQuatro.jogadores;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author adao.rmoura
 */
public class JogadorARM2 implements Jogador{
    int minhaCor;
    int oponente;
    int[][] tabuleiro;
    /* Construtor da classe */
    public JogadorARM2(){
    }
    
    /* Método que calcula a proxima jogda baseada no método minimax */
    
	public int jogada(int[][] tabuleiro, int corDaMinhaBola) {
            if (corDaMinhaBola == 1) {
                this.minhaCor = corDaMinhaBola;
                this.oponente = 2;
            }else{
                this.minhaCor = corDaMinhaBola;
                this.oponente = 1;
            }

            this.tabuleiro = tabuleiro;
            Vector<Integer> jogadas_possiveis = AnalisaPossiveisColunas(tabuleiro);
            int jogada = AvaliaMelhor(jogadas_possiveis);
            if (jogadas_possiveis.contains(jogada)) {
                return jogada;
            }
            return jogadas_possiveis.get(new Random().nextInt(jogadas_possiveis.size()));
    }
	
	public String getNome() {
		return "Jogador ARM";
	}

    private Vector<Integer> AnalisaPossiveisColunas(int[][] tabuleiro) {
        Vector<Integer> possiveis = new Vector<Integer>();

        for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
            if (tabuleiro[0][coluna]==0) {
                possiveis.add(coluna);
                //System.out.println(coluna);
            } 
            
        }/*
         for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
             int linha = 6;
             while (linha >=0 && tabuleiro[linha][coluna] == 0) {                 
                 linha--;
             }
             
         }*/
        
         return possiveis;
        
    }

    private int AvaliaDano(int linha, int coluna) {
        int danoMaior;
        int colunatemp = coluna;
        int linhatemp = linha;
        int danoD  =1;
        while (++linhatemp < 7 && --colunatemp >=0) // Sudoeste
        {
            if (tabuleiro[linhatemp][colunatemp]==oponente) {
                danoD++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        while (--linhatemp >= 0 && ++colunatemp <7) // Nordeste
        {
            if (tabuleiro[linhatemp][colunatemp]==oponente) {
                danoD++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        int danoV = 1;
        while (++linhatemp <= 6) // Sul
        {
            if (tabuleiro[linhatemp][colunatemp]==oponente) {
                danoV++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        while (--linhatemp >= 0) // Norte
        {
            if (tabuleiro[linhatemp][colunatemp]==oponente) {
                danoV++;
            }
            else
            {
                break;
            }
        }
            //
        danoMaior = Math.max(danoV, danoD);
        colunatemp = coluna;
        linhatemp = linha;
        int danoH=1;
        while (++colunatemp <7 && colunatemp >=0) // Leste
        {
            if (tabuleiro[linhatemp][colunatemp]==oponente) {
                danoH++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        //int danoH=1;
        while (--colunatemp >= 0) // Oeste
        {
            if (tabuleiro[linhatemp][colunatemp]==oponente) {
                danoH++;
            }
            else
            {
                break;
            }
        }
        danoMaior = Math.max(danoMaior, danoH);
        
        colunatemp = coluna;
        linhatemp = linha;
        int danoE=1;
        while (--linhatemp >= 0 && --colunatemp >=0) // Noroeste
        {
            if (tabuleiro[linhatemp][colunatemp]==oponente) {
                danoE++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        while (++linhatemp < 7 && ++colunatemp <7) // Sudoeste
        {
            if (tabuleiro[linhatemp][colunatemp]==oponente) {
                danoE++;
            }
            else
            {
                break;
            }
        }
        danoMaior = Math.max(danoMaior, danoE);

        return danoMaior;
}

    private int AvaliaGanho(int linha, int coluna) {
        int ganhoMaior;
        int colunatemp = coluna;
        int linhatemp = linha;
        int ganhoD  =1;
        while (++linhatemp < 7 && --colunatemp >=0) // Sudoeste
        {
            if (tabuleiro[linhatemp][colunatemp]==minhaCor) {
                ganhoD++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        while (--linhatemp >= 0 && ++colunatemp <7) // Nordeste
        {
            if (tabuleiro[linhatemp][colunatemp]==minhaCor) {
                ganhoD++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        int ganhoV = 1;
        while (++linhatemp <= 6) // Sul
        {
            if (tabuleiro[linhatemp][colunatemp]==minhaCor) {
                ganhoV++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        while (--linhatemp >= 0) // Norte
        {
            if (tabuleiro[linhatemp][colunatemp]==minhaCor) {
                ganhoV++;
            }
            else
            {
                break;
            }
        }
            //
        ganhoMaior = Math.max(ganhoD, ganhoV);
        colunatemp = coluna;
        linhatemp = linha;
        int ganhoH=1;
        while (++colunatemp <7 && colunatemp>=0) // Leste
        {
            if (tabuleiro[linhatemp][colunatemp]==minhaCor) {
                ganhoH++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        //int danoH=1;
        while (--colunatemp >= 0) // Oeste
        {
            if (tabuleiro[linhatemp][colunatemp]==minhaCor) {
                ganhoH++;
            }
            else
            {
                break;
            }
        }
        ganhoMaior = Math.max(ganhoMaior, ganhoH);
        
        colunatemp = coluna;
        linhatemp = linha;
        int ganhoE=1;
        while (--linhatemp >= 0 && --colunatemp >=0) // Noroeste
        {
            if (tabuleiro[linhatemp][colunatemp]==minhaCor) {
                ganhoE++;
            }
            else
            {
                break;
            }
        }
        colunatemp = coluna;
        linhatemp = linha;
        while (++linhatemp < 7 && ++colunatemp <7) // Sudoeste
        {
            if (tabuleiro[linhatemp][colunatemp]==minhaCor) {
                ganhoE++;
            }
            else
            {
                break;
            }
        }
        ganhoMaior = Math.max(ganhoMaior, ganhoE);

        return ganhoMaior;
    }

    private int AvaliaMelhor(Vector<Integer> jogadas_possiveis) {
        int q = new Random().nextInt(jogadas_possiveis.size());
        int maiorGanho = 0;
        int columaGanho =q;
        int maiorDano = 0;
        int colunaDano= q;
        for (Integer coluna : jogadas_possiveis) {
            for (int linha = 0; linha < tabuleiro.length; linha++) {
                if (tabuleiro[linha][coluna]==minhaCor) {
                    int ganho = AvaliaGanho(linha, coluna);
                    if (ganho > maiorGanho) {
                        maiorGanho = ganho;
                        columaGanho = coluna;
                    }
                }else
                {
                    if (tabuleiro[linha][coluna]==oponente) {
                        int dano = AvaliaDano(linha,coluna);
                        if (dano > maiorDano) {
                            maiorDano = dano;
                            colunaDano = coluna;
                        }
                    }
                }
            }
        }
        if(maiorDano >= 3)
        {
            //System.out.println("Dano: "+maiorDano);
            return colunaDano;
        }
        else
        {
            //System.out.println("Ganho: "+maiorGanho);
            return columaGanho;
        }/*
        if(maiorGanho >= 3)
        {

        }
        if (maiorGanho < maiorDano)
        {
            System.out.println("Dano: "+maiorDano+ " Ganho: "+maiorGanho);
            return colunaDano;
        }*/
        //return columaGanho;
    }
}


