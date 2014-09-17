package linhaQuatro.jogadores; 

/**
 * 
 * @author Alexandre e Caio.
 *
 */
public class Timao implements Jogador { 

	int tabuleiro_virtual[][] = new int[7][7]; 
	int minhaCor; 
	int corOponente; 
	int melhorQueNada = -1;

	@Override 

	public String getNome() { 
		//TODO Auto-generated method stub 
		return "Timão!"; 
	}
	@Override 
	public int jogada(int[][] tabuleiro, int corDaMinhaBola) { 
		//TODO Auto-generated method stub 

		int flag_PodeJogar = 0; 
		int vetExclusao[] = new int[7];
		int escolha_final = 0; 
		for (int k = 0; k< 7;k++) 
			for (int l=0;l<7;l++) tabuleiro_virtual[k][l] = tabuleiro[k][l]; 

		minhaCor = corDaMinhaBola; 

		if (minhaCor == 1) corOponente = 2; 
		else corOponente = 1; 

		if (funcaoUtilidade_euGanhoemUma(tabuleiro,minhaCor) != -1) 
			return funcaoUtilidade_euGanhoemUma(tabuleiro,minhaCor); 
		else 
		{
			flag_PodeJogar = 0;
			if (funcaoUtilidade_euFacoDuplaAmeaca(tabuleiro,minhaCor) != -1)
			{
				escolha_final = funcaoUtilidade_euFacoDuplaAmeaca(tabuleiro,minhaCor);
				flag_PodeJogar = 1;
			}
						
			while (flag_PodeJogar == 0) 
			{
				int col_prev = funcaoUtilidade_abrangencia(0,tabuleiro,vetExclusao); 
				int lin = 6; 
				escolha_final = col_prev;
				if (vetExclusao[col_prev] == 1) break;
				while ((lin >= 0) && (tabuleiro_virtual[lin][col_prev] != 0)) lin--;
				if (lin < 0) break;
				tabuleiro_virtual[lin][col_prev] = minhaCor; 
	

				if ((funcaoUtilidade_euGanhoemUma(tabuleiro_virtual,corOponente) != -1)||
						(funcaoUtilidade_euFacoDuplaAmeaca(tabuleiro_virtual,corOponente) != -1))
				{
					if ((funcaoUtilidade_euFacoDuplaAmeaca(tabuleiro_virtual,corOponente) != -1)&&
							(funcaoUtilidade_euGanhoemUma(tabuleiro_virtual,corOponente) == -1))
					{
						melhorQueNada = col_prev;
					}
					for (int k = 0; k< 7;k++) 
						for (int l=0;l<7;l++) tabuleiro_virtual[k][l] = tabuleiro[k][l]; 

					vetExclusao[col_prev] = 1;
					flag_PodeJogar = 1;
					for (int c = 0; c < 7; c++)
					{
						if (vetExclusao[c] == 0)
						{
							flag_PodeJogar =0;
							for (int k = 0; k< 7;k++) 
								for (int l=0;l<7;l++) tabuleiro_virtual[k][l] = tabuleiro[k][l]; 
							break;
						}
					}
					if ((flag_PodeJogar == 1)&&(melhorQueNada != -1)) escolha_final = melhorQueNada;
				}
				else 
				{
					for (int k = 0; k< 7;k++) 
						for (int l=0;l<7;l++) tabuleiro_virtual[k][l] = tabuleiro[k][l]; 
					flag_PodeJogar = 1; 
				}
			}
		}
		return escolha_final; 
	}

	public int funcaoUtilidade_euFacoDuplaAmeaca(int[][] tab, int cor_atual)
	{
		int col_esc = -1;
		int lin;
		int [][] tab_aux = new int[7][7];
		int cor_op;
		
		if (cor_atual == 1) cor_op = 2;
		else cor_op = 1;
		
		
		for (int k = 0; k< 7;k++) 
			for (int l=0;l<7;l++) tab_aux[k][l] = tab[k][l];
		
		for (int col = 0; col < 7; col++) 
		{
			lin = 6;
			while (tab_aux[lin][col] != 0) 
			{
				lin--;
				if (lin < 0) break; 
			}
			if (lin >= 0)
			{
				tab_aux[lin][col] = cor_atual;
				if (funcaoUtilidade_euGanhoemUma(tab_aux,cor_op)  == -1) //Oponente nao ganha na proxima entao posso olhar mais adiante
				{
						if(funcaoUtilidade_euGanhoemUma(tab_aux, cor_atual) != -1) //Eu tenho uma possibilidade de vencer
						{
							int col_bloq = funcaoUtilidade_euGanhoemUma(tab_aux,cor_atual);
							int lin_bloq = 6;
							while (tab_aux[lin_bloq][col_bloq] != 0) 
							{
								lin_bloq--;
								if (lin < 0) break; 
							}
							tab_aux[lin_bloq][col_bloq] = cor_op;		//Julgando que o oponente vai bloquear essa ameaca
							if (funcaoUtilidade_euGanhoemUma(tab_aux,cor_atual) != -1) // Mesmo com um bloqueio ainda ganho
								return col; //Retorna jogada de dupla ameaca
						}
				}
			}
			for (int k = 0; k< 7;k++) //Zerando tab_aux para outra interacao
				for (int l=0;l<7;l++) tab_aux[k][l] = tab[k][l];
		}
		
		return col_esc; //Caso nao tenha encontrado nenhuma dupla_ameac
	}
	
	public int funcaoUtilidade_euGanhoemUma(int[][] tab, int cor_atual) 
	{
		double abra_parc_hor = 0; 
		double abra_parc_vert = 0; 
		double abra_parc_de = 0; // (\) 
		double abra_parc_dd = 0; // (/) 
		double abra_esc = -1; 

		int col_esc = -1; 
		int lin; 
		int cont3; 

		double peso_peca_minha = 10; 

		for (int col = 0; col < 7; col++) 
		{
			lin = 6;
			while (tab[lin][col] != 0) 
			{
				lin--;
				if (lin < 0) break; 
			}
			if (lin >= 0) 
			{
//				SUL 
				cont3 = 1;
				while ((lin + cont3 < 7)&&(cont3 < 4)) 
				{
					if (tab[lin + cont3][col] == cor_atual) abra_parc_vert += peso_peca_minha;
					else cont3 = 4;
					cont3++;
				}
//				SUDOESTE 
				cont3 = 1;
				while ((lin + cont3 < 7)&&(col - cont3 >= 0)&&(cont3 < 4)) 
				{
					if (tab[lin + cont3][col - cont3] == cor_atual) abra_parc_dd += peso_peca_minha;
					else cont3 = 4;
					cont3++;
				}
//				OESTE 
				cont3 = 1;
				while ((col - cont3 >= 0)&&(cont3 < 4)) 
				{
					if (tab[lin][col - cont3] == cor_atual) abra_parc_hor += peso_peca_minha; 
					else cont3 = 4;
					cont3++;
				}
//				NOROESTE 
				cont3 = 1;
				while ((lin - cont3 >= 0)&&(col - cont3 >= 0)&&(cont3 < 4)) 
				{
					if (tab[lin - cont3][col - cont3] == cor_atual) abra_parc_de += peso_peca_minha;
					else cont3 = 4;
					cont3++;
				}
//				NORDESTE 
				cont3 = 1;
				while ((lin - cont3 >= 0)&&(col + cont3 < 7)&&(cont3 < 4)) 
				{
					if (tab[lin - cont3][col + cont3] == cor_atual) abra_parc_dd += peso_peca_minha;
					else cont3 = 4;
					cont3++;
				}
//				LESTE 
				cont3 = 1;
				while ((col + cont3 < 7)&&(cont3 < 4)) 
				{
					if (tab[lin][col + cont3] == cor_atual) abra_parc_hor += peso_peca_minha;
					else cont3 = 4;
					cont3++;
				}
//				SUDESTE 
				cont3 = 1;
				while ((lin + cont3 < 7)&&(col + cont3 < 7)&&(cont3 < 4)) 
				{
					if (tab[lin + cont3][col + cont3] == cor_atual) abra_parc_de += peso_peca_minha;
					else cont3 = 4;
					cont3++;
				}
				
				if ((abra_parc_hor >= 30)||(abra_parc_vert >= 30)||(abra_parc_de >= 30)||(abra_parc_dd >= 30)) 
				{
					abra_esc = 30;
					col_esc = col;
					break;
				}

				abra_parc_hor = 0;
				abra_parc_vert = 0;
				abra_parc_de = 0;
				abra_parc_dd = 0;
			}
		}

		if (abra_esc == -1) col_esc = -1; 
		return col_esc; 
	}

	public int funcaoUtilidade_abrangencia(double a,int[][] tab,int[]vetE) { 

		double abra_parc = 0; 
		double abra_esc = 0; 
		int col_esc = -1; 
		int lin; 
		int cont3; 

		double peso_peca_minha = 100; 
		double peso_peca_oponente = 90; 
		double peso_espaco_vazio = 1; 

		for (int col = 0; col < 7; col++) 
		{
			if (vetE[col] == 0) 
			{
				lin = 6;
				while (tab[lin][col] != 0) 
				{
					lin--;
					if (lin < 0) break; 
				}

				if (lin >= 0) 
				{
//					SUL 
					cont3 = 1;
					while ((lin + cont3 < 7)&&(cont3 < 4)) 
					{
						if (tab[lin + cont3][col] == minhaCor) abra_parc += peso_peca_minha; 
						else abra_parc += peso_peca_oponente; 
						cont3++;
					}
//					SUDOESTE 
					cont3 = 1;
					while ((lin + cont3 < 7)&&(col - cont3 >= 0)&&(cont3 < 4)) 
					{
						if (tab[lin + cont3][col - cont3] == 0)abra_parc += peso_espaco_vazio; 
						else 
							if (tab[lin + cont3][col - cont3] == minhaCor) abra_parc += peso_peca_minha; 
							else abra_parc += peso_peca_oponente; 
						cont3++;
					}
//					OESTE 
					cont3 = 1;
					while ((col - cont3 >= 0)&&(cont3 < 4)) 
					{
						if (tab[lin][col - cont3] == 0)abra_parc += peso_espaco_vazio; 
						else 
							if (tab[lin][col - cont3] == minhaCor) abra_parc += peso_peca_minha; 
							else abra_parc += peso_peca_oponente; 
						cont3++;
					}
//					NOROESTE 
					cont3 = 1;
					while ((lin - cont3 >= 0)&&(col - cont3 >= 0)&&(cont3 < 4)) 
					{
						if (tab[lin - cont3][col - cont3] == 0)abra_parc += peso_espaco_vazio; 
						else 
							if (tab[lin - cont3][col - cont3] == minhaCor) abra_parc += peso_peca_minha; 
							else abra_parc += peso_peca_oponente; 
						cont3++;
					}
//					NORTE 
					cont3 = 1;
					while ((lin - cont3 >= 0)&&(cont3 < 4)) 
					{
						abra_parc += peso_espaco_vazio;
						cont3++;
					}
//					NORDESTE 
					cont3 = 1;
					while ((lin - cont3 > 0)&&(col + cont3 < 7)&&(cont3 < 4)) 
					{
						if (tab[lin - cont3][col + cont3] == 0)abra_parc += peso_espaco_vazio; 
						else 
							if (tab[lin - cont3][col + cont3] == minhaCor) abra_parc += peso_peca_minha; 
							else abra_parc += peso_peca_oponente; 
						cont3++;
					}
//					LESTE 
					cont3 = 1;
					while ((col + cont3 < 7)&&(cont3 < 4)) 
					{
						if (tab[lin][col + cont3] == 0)abra_parc += peso_espaco_vazio; 
						else 
							if (tab[lin][col + cont3] == minhaCor) abra_parc += peso_peca_minha; 
							else abra_parc += peso_peca_oponente; 
						cont3++;
					}
//					SUDESTE 
					cont3 = 1;
					while ((lin + cont3 < 7)&&(col + cont3 < 7)&&(cont3 < 4)) 
					{
						if (tab[lin + cont3][col + cont3] == 0)abra_parc += peso_espaco_vazio; 
						else 
							if (tab[lin + cont3][col + cont3] == minhaCor) abra_parc += peso_peca_minha; 
							else abra_parc += peso_peca_oponente; 
						cont3++;
					}

					if ((abra_parc > abra_esc) && (tab[0][col] == 0 )) 
					{
						abra_esc = abra_parc;
						col_esc = col;
					}
					abra_parc = 1;
				}
			}
		}
		if (col_esc == -1) for (int i = 0; i<7;i++) if (tab[0][i]==0) col_esc = i; 
		return col_esc; 
	}
}

