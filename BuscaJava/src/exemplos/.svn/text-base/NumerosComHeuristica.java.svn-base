package exemplos;

import java.util.LinkedList;
import java.util.List;

import busca.AEstrela;
import busca.Estado;
import busca.Heuristica;
import busca.Nodo;

/**
 * 
 * @author Fabricio J. Barth (fabricio.jbarth@sp.senac.br)
 * @version 13/09/2007.
 *
 */

public class NumerosComHeuristica implements Estado, Heuristica{

	private int numero;
	private int meta;
	private String op; // operacao que gerou o estado
	private int profundidade;
	
	public NumerosComHeuristica(int nr, int meta, String op, int p){
		this.numero = nr;
		this.meta = meta;
		this.op = op;
		this.profundidade = p;
	}
	
	public int custo() {
		return 1;
	}

	public boolean ehMeta() {
		if(this.numero==this.meta)
			return true;
		else
			return false;
	}

	public String getDescricao() {
		return "Considerando o seguinte problema " +
				"de busca (extremamente simples): " +
				"o estado inicial é o número 1, o " +
				"estado meta é o número 10. Existem " +
				"duas operações de geração de sucessores: " +
				"adicionar 1 ao número do estado atual; " +
				"adicionar 2 ao número do estado atual.";
	}

	public List<Estado> sucessores() {
		List<Estado> retorno = new LinkedList<Estado>();
		retorno.add(new NumerosComHeuristica(this.numero+1,this.meta," 1 ",this.profundidade+1));
		retorno.add(new NumerosComHeuristica(this.numero+2,this.meta," 2 ",this.profundidade+1));
		return retorno;
	}
	
	public String toString(){
		return this.op;
	}
	
	public static void main(String args[]){
		NumerosComHeuristica inicial = new NumerosComHeuristica(1,1000,"",0);
		System.out.println("busca em a*");
		Nodo n = new AEstrela().busca(inicial);
		if (n == null) {
			System.out.println("sem solucao!");
		} else {
			System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
		}
	}

	/**
	 * Minha heurística para o problema:
	 * 
	 * 
	 */
	public int h() {
		if((this.numero%2)==0) //o numero eh par
			if((this.meta%2)==0) //a meta tambem eh par
				return (this.meta/2) - this.profundidade;
			else
				return 1 + ((this.meta/2) - this.profundidade);
		else // o numero eh impar
			if((this.meta%2)!=0) //a meta tambem eh impar
				return (this.meta/2) - this.profundidade;
			else
				return 1 + ((this.meta/2) - this.profundidade);
	}

}
