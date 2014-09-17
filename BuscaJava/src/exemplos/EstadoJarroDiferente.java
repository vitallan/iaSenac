package exemplos;

import java.util.LinkedList;
import java.util.List;

import busca.Estado;

public class EstadoJarroDiferente implements Estado{

	private int jarro8 = 8;
	
	private int jarro5 = 0;
	
	private int jarro3 = 0;
	
	private String op;
	
	public EstadoJarroDiferente(int jarro8, int jarro5, int jarro3, String op){
		this.jarro8 = jarro8;
		this.jarro5 = jarro5;
		this.jarro3 = jarro3;
		this.op = op;
	}
	
	public String toString(){
		return "-> "+this.op;
	}

	public int custo() {
		return 1;
	}

	public boolean ehMeta() {
		if(this.jarro8==4 && this.jarro5==4 && this.jarro3==0)
			return true;
		else
			return false;
	}

	public String getDescricao() {
		return null;
	}

	public List<Estado> sucessores() {
		List<Estado> retorno = new LinkedList<Estado>();
		if(this.jarro8>0){
			//jarro8 para jarro5
			if(this.jarro5<5){
				int valor = this.jarro5 - 5;
				retorno.add(new EstadoJarroDiferente(this.jarro8+valor,this.jarro5+(valor*-1),this.jarro3,"jarro8 p/ jarro5"));
			}
			//jarro8 para jarro3
			if(this.jarro3<3){
				int valor = this.jarro3 - 3;
				retorno.add(new EstadoJarroDiferente(this.jarro8+valor,this.jarro5,this.jarro3+(valor*-1),"jarro8 p/ jarro3"));
			}
		}
		if(this.jarro5>0){
			//jarro5 para jarro8
			if(this.jarro8<8){
				
			}
			//jarro5 para jarro3
			if(this.jarro3<3){
				
			}
		}
		if(this.jarro3>0){
			//jarro3 para jarro8
			if(this.jarro8<8){
				
			}
			//jarro3 para jarro5
			if(this.jarro5<5){
				
			}
		}
		return retorno;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
