package linhaQuatro.jogadores;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Robo implements Estado{

	static int rodadas;
	int[][] tabuleiro ;
	int corDaMinhaBola;
	int profundidade;
	public int melhorValor;
	public Robo melhorSucessor;
	public int coluna;
	public int linha;
	public int pontuacao;
	public int pontuacao1;
	public int pontuacao2;
	List<Robo>sucessores ;
	List<Ameaca> vertical = new LinkedList<Ameaca>();
	List<Ameaca> horizontal = new LinkedList<Ameaca>(); 
	List<Ameaca> diagonalesq = new LinkedList<Ameaca>();
	List<Ameaca> diagonaldir = new LinkedList<Ameaca>(); 


	public Robo(int[][] tabuleiro, int corDaMinhaBola) {
		this.corDaMinhaBola = corDaMinhaBola;
		this.tabuleiro = tabuleiro;
		this.profundidade = 0;
	}

	public Robo(int[][] tabuleiro, int linha, int coluna, int corDaMinhaBola, int profundidade) {
		this.tabuleiro = tabuleiro;
		this.profundidade = profundidade + 1;
		this.corDaMinhaBola = corDaMinhaBola;
		this.coluna = coluna;
		this.linha = linha;
		this.vertical = clone(vertical);
		this.horizontal = clone(horizontal);

		if (rodadas == 8)
			System.out.println("/n");

		calculapontuacao();
//		printTabuleiro();

//		if ((0  <  this.profundidade) && (this.profundidade <  8)){
//		this.sucessores = sucessores();

//		}
		//printTabuleiro();	

		// TODO Auto-generated constructor stub
	}

	public Robo() {

		// TODO Auto-generated constructor stub
	}

	private List<Ameaca> clone (List<Ameaca> vertical2) {
		LinkedList<Ameaca> clone = new LinkedList<Ameaca>();
		for (Ameaca a: vertical2){
			if (a!= null){
				Ameaca temp = new Ameaca();
				temp.faltam = a.faltam;
				temp.ameaca = a.ameaca;
				temp.grau = a.grau;
				temp.cor = a.cor;
				clone.add(temp);}

		}

		return clone;
	}

	private void calculapontuacao() {
		checavertical();
		checahorizontal();
		checaveresq();
		checavedir();



	}

	private void checahorizontal() {

		for(int i = 0; i < 7; i++){

			int j = 0;
			int k;
			while (j<6){
				int folgaesq = 0;
				int folgadir = 0;
				while ((j<6) && (tabuleiro[i][j] == 0)){
					if ((i==6 || (tabuleiro[i+1][j] != 0))){
						folgaesq++;
					}
					j++;
				}
				if (j<6) {
					j++;
					Ameaca nova = new Ameaca();
					nova.grau=1;
					nova.cor = tabuleiro[i][j-1];
					while (j<6 && (tabuleiro[i][j-1] == tabuleiro[i][j])){
						j++;
						//if ((i == 6) || (tabuleiro[i+1][j]!=0))
							nova.grau++;								
					}
					k = j;
					while (k<=6 && (tabuleiro[i][k])==0 && (i==6 || (tabuleiro[i+1][k] != 0))){
						k++;
						folgadir++;
					}
					if ((folgaesq + folgadir + nova.grau) >= 4){ 
						if ((nova.grau==2) && (j < 6 ) && (j>0) && (folgaesq + folgadir >= 3) && (folgaesq>=1) &&(folgadir>=1)){
							nova.grau++;
						}						
						horizontal.add(nova);
					}
				}

			}
		}

	}
	private void checaveresq() {

		for(int i = 0; i < 7; i++){
			int j = 0;
			int k;
			int z;
			while (j<6){
				int folgaesqinf = 0;
				int folgadirsup = 0;
				while ((j<6) && (tabuleiro[i][j] == 0)){
					j++;
				}
				if (j<6) {

					Ameaca nova = new Ameaca();
					nova.grau=1;
					nova.cor = tabuleiro[i][j];

					k = i + 1;
					z = j - 1;
					while ((z >= 0) &&  (k <= 6) && (k<=i+4) && ((tabuleiro [k][z] == tabuleiro[i][j] || tabuleiro [k][z] == 0))){
						if (tabuleiro [k][z] == 0){
							if ((k==6)||(tabuleiro [k+1][z] != 0)){
								folgaesqinf++;}
						}
						else{
							nova.grau++;
						}
						k++;
						z--;
					}

					k = i - 1;
					z = j + 1;
					while ((z <= 6) &&  (k >= 0) && (z<=j+4) && ((tabuleiro [k][z] == tabuleiro[i][j] || tabuleiro [k][z] == 0))){
						if (tabuleiro [k][z] == 0){
							if (tabuleiro [k+1][z] != 0){
								folgadirsup++;}
						}
						else {
							nova.grau++;
						}
						k--;
						z++;
					}
					j++;
					if(folgadirsup + folgaesqinf + nova.grau >= 4){
						diagonalesq.add(nova);
					}

				}
			}
		}
	}

	private void checavedir() {

		for(int i = 0; i < 7; i++){
			int j = 0;
			int k;
			int z;
			while (j<6){
				int folgaesqsup = 0;
				int folgadirinf = 0;
				while ((j<6) && (tabuleiro[i][j] == 0)){
					j++;
				}
				if (j<6) {
					
					Ameaca nova = new Ameaca();
					nova.grau=1;
					nova.cor = tabuleiro[i][j];

					k = i - 1;
					z = j - 1;
					while ((z >= 0) &&  (k >= 0) && (k>=i-4) && ((tabuleiro [k][z] == tabuleiro[i][j] || tabuleiro [k][z] == 0))){
						if ((tabuleiro [k][z] == 0)){
							if ((k==6)||(tabuleiro [k+1][z] != 0)){
							folgaesqsup++;}
						}else{
							nova.grau++;
						}
						k--;
						z--;
					}

					k = i + 1;
					z = j + 1;
					while ((z <= 6) &&  (k <= 6) && (k<=i+4) && ((tabuleiro [k][z] == tabuleiro[i][j] || tabuleiro [k][z] == 0))){
						if ((tabuleiro [k][z] == 0)){
							if ((k==6) || (tabuleiro [k+1][z] != 0)){
							folgadirinf++;
							}
						}else{
							nova.grau++;
						}
						k++;
						z++;
					}
					j++;
					if(folgadirinf + folgaesqsup + nova.grau >= 4){
						diagonaldir.add(nova);
					}
					
				}
			}
		}
	}


	private void checavertical() {
		for(int i = 0; i < 7; i++){
			int j = 0;
			while ((j<6) && (tabuleiro[j+1][i] == 0 )){
				j++;}
			if (j<6){
				j++;

				Ameaca nova = new Ameaca();
				nova.grau=1;
				nova.cor = tabuleiro[j][i];
				while (j<6 && (tabuleiro[j+1][i] == tabuleiro[j][i])){
					j++;
					nova.grau++;								
				}  
				if (j > 2){
					vertical.add(nova);
				}
			}
		}
		return ;
	}


	public int custo() {
		
		pontuacao1 = 0;
		pontuacao2 = 0;
		for (Ameaca a : diagonalesq){
			if (a != null){
				if (a.cor == 2){
					if (a.grau == 1)
						pontuacao2 += a.grau ;
					if (a.grau == 2)
						pontuacao2 ++ ;
					if (a.grau == 3)
						pontuacao2 += 30 ;
					if (a.grau == 4)
						pontuacao2 +=250;
				}
				else{
					if (a.grau == 1)
						pontuacao1 += a.grau ;
					if (a.grau == 2)
						pontuacao1 ++;
					if (a.grau == 3)
						pontuacao1 += 30;
					if (a.grau == 4)
						pontuacao1 +=250;}

			}
		}
		for (Ameaca a : diagonaldir){
			if (a != null){
				if (a.cor == 2){
					if (a.grau == 1)
						pontuacao2 += a.grau ;
					if (a.grau == 2)
						pontuacao2 ++;
					if (a.grau == 3)
						pontuacao2 += 30 ;
					if (a.grau == 4)
						pontuacao2 += 250;
				}
				else{
					if (a.grau == 1)
						pontuacao1 += a.grau ;
					if (a.grau == 2)
						pontuacao1 ++ ;
					if (a.grau == 3)
						pontuacao1 += 30;
					if (a.grau == 4)
						pontuacao1 +=250;}

			}
		} 
		for (Ameaca a : horizontal){
			if (a != null){
				if (a.cor == 2){
					if (a.grau == 1)
						pontuacao2 += 3 ;
					if (a.grau == 2)
						pontuacao2 += 9;
					if (a.grau == 3)
						pontuacao2 += 90 ;
					if (a.grau == 4)
						pontuacao2 += 1000;
				}
				else{
					if (a.grau == 1)
						pontuacao1 += 3 ;
					if (a.grau == 2)
						pontuacao1 += 9;
					if (a.grau == 3)
						pontuacao1 += 90;
					if (a.grau == 4)
						pontuacao1 +=1000;}

			}
		}
		for (Ameaca a : vertical){
			if (a.cor == 2){
				if (a.grau == 1)
					pontuacao2 += 3 ;
				if (a.grau == 2)
					pontuacao2 += 9;
				if (a.grau == 27)
					pontuacao2 += 90 ;
				if (a.grau == 4)
					pontuacao2 +=1000;
			}
			else{
				if (a.grau == 1)
					pontuacao1 += 3 ;
				if (a.grau == 2)
					pontuacao1 += 9;
				if (a.grau == 3)
					pontuacao1 += 90;
				if (a.grau == 4)
					pontuacao1 += 1000;}
		}
		//	System.out.println("PONTUACAO".concat(Integer.toString(pontuacao1-pontuacao2)));
		return pontuacao1 - pontuacao2;
	}

	public boolean ehMeta() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getDescricao() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Robo> sucessores() {
		sucessores = new LinkedList<Robo>();

		if((tabuleiro[0][0] != 0)||(tabuleiro[0][1] != 0)||(tabuleiro[0][2] != 0)||(tabuleiro[0][3] != 0)||(tabuleiro[0][4] != 0)||(tabuleiro[0][5] != 0)||(tabuleiro[0][6] != 0)){
//			System.out.println("achei");
		}
		if (tabuleiro[0][0] == 0){
			int i = 0;
			while ((i<7) && (tabuleiro[i][0]==0)){
				i++;
			}
			int[][] temp = new int[7][7];
			for(int j = 0; j < 7; j++){
				System.arraycopy(tabuleiro[j],0,temp[j],0,7);
			}
			if (i != 0){
				temp[i-1][0] = corDaMinhaBola;
				sucessores.add(new Robo(temp ,i-1, 0,(corDaMinhaBola)%2+1, profundidade));
			}
		}

		if (tabuleiro[0][1] == 0){
			int i = 0;
			while ((i<7) && (tabuleiro[i][1]==0)){
				i++;
			}
			int[][] temp = new int[7][7];

			for(int j = 0; j < 7; j++){
				System.arraycopy(tabuleiro[j],0,temp[j],0,7);
			}
			if (i != 0){
				temp[i-1][1] = corDaMinhaBola;
				sucessores.add(new Robo(temp ,i-1,1,(corDaMinhaBola)%2+1, profundidade));
			}
		}

		if (tabuleiro[0][2] == 0){
			int i = 0;
			while ((i<7) && (tabuleiro[i][2]==0)){
				i++;
			}
			int[][] temp = new int[7][7];
			for(int j = 0; j < 7; j++){
				System.arraycopy(tabuleiro[j],0,temp[j],0,7);
			}
			if (i != 0){
				temp[i-1][2] = corDaMinhaBola;
				sucessores.add(new Robo(  temp , i-1,2,(corDaMinhaBola)%2+1, profundidade));
			}}

		if (tabuleiro[0][3] == 0){
			int i = 0;
			while ((i<7) && (tabuleiro[i][3]==0)){
				i++;
			}
			int[][] temp = new int[7][7];
			for(int j = 0; j < 7; j++){
				System.arraycopy(tabuleiro[j],0,temp[j],0,7);
			}
			if (i != 0){
				temp[i-1][3] = corDaMinhaBola;
				sucessores.add(new Robo( temp , i-1,3,(corDaMinhaBola)%2+1, profundidade));
			}
		}

		if (tabuleiro[0][4] == 0){
			int i = 0;
			while ((i<7) && (tabuleiro[i][4]==0)){
				i++;
			}
			int[][] temp = new int[7][7];
			for(int j = 0; j < 7; j++){
				System.arraycopy(tabuleiro[j],0,temp[j],0,7);
			}
			if (i != 0){
				temp[i-1][4] = corDaMinhaBola;
				sucessores.add(new Robo(  temp , i-1,4,(corDaMinhaBola)%2+1, profundidade));
			}}

		if (tabuleiro[0][5] == 0){
			int i = 0;
			while ((i<7) && (tabuleiro[i][5]==0)){
				i++;
			}
			int[][] temp = new int[7][7];
			for(int j = 0; j < 7; j++){
				System.arraycopy(tabuleiro[j],0,temp[j],0,7);
			}
			if (i != 0){
				temp[i-1][5] = corDaMinhaBola;
				sucessores.add(new Robo(  temp , i-1,5,(corDaMinhaBola)%2+1, profundidade));
			}}

		if (tabuleiro[0][6] == 0){
			int i = 0;
			while ((i<7) && (tabuleiro[i][6]==0)){
				i++;
			}
			int[][] temp = new int[7][7];
			for(int j = 0; j < 7; j++){
				System.arraycopy(tabuleiro[j],0,temp[j],0,7);
			}
			if (i != 0){
				temp[i-1][6] = corDaMinhaBola;
				sucessores.add(new Robo( temp , i-1,6,(corDaMinhaBola)%2+1, profundidade));
			}
		}
		return sucessores;
	}

	public void printTabuleiro(){
		rodadas ++;
		System.out.println("rodadas:".concat(Integer.toString(rodadas)));
//		System.out.println("profundidade".concat(Integer.toString(this.profundidade)));
//		System.out.println("coluna jogada".concat(Integer.toString(this.coluna)));
		System.out.println("custo: ".concat(Integer.toString(this.custo())));


		for (int i = 0; i < 7; i++) {
			System.out.println("");
			for (int j = 0; j < 7; j++) {
				System.out.print (tabuleiro[i][j]);
			}

		}

		System.out.println("Ameacas:");
		for (Ameaca a: vertical)
			System.out.println("vertgrau:".concat(Integer.toString(a.grau)));
		System.out.println( "\n\n\n" );

		for (Ameaca a: horizontal){
			System.out.println("horgrau:".concat(Integer.toString(a.grau)));
			System.out.println( "\n\n\n" );
		}
		for (Ameaca a: diagonalesq){
			System.out.println("esqgrau:".concat(Integer.toString(a.grau)));
			System.out.println( "\n\n\n" );
		}
		for (Ameaca a: diagonaldir){
			System.out.println("dirgrau:".concat(Integer.toString(a.grau)));
			System.out.println( "\n\n\n" );
		}
	}


	public boolean naoFinal() {
	    int a = this.custo();
		if (a<900)
		return true;
		else
			return false;
	}

}
