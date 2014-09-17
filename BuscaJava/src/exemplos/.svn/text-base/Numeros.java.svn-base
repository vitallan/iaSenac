package exemplos;

import java.util.LinkedList;
import java.util.List;

import busca.BuscaIterativo;
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.Nodo;

/**
 * 
 * @author Fabricio J. Barth (fabricio.jbarth@sp.senac.br)
 * @version 31/08/2007.
 *
 */

public class Numeros implements Estado{

	private int numero;
	private int meta;
	final String op; // operacao que gerou o estado
	
	public Numeros(int nr, int meta, String op){
		this.numero = nr;
		this.meta = meta;
		this.op = op;
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
		retorno.add(new Numeros(this.numero+1,this.meta," 1 "));
		retorno.add(new Numeros(this.numero+2,this.meta," 2 "));
		return retorno;
	}
	
	public String toString(){
		return this.op;
	}
	
	public static void main(String args[]){
		Numeros inicial = new Numeros(1,10,"");
		System.out.println("busca em largura");
		Nodo n = new BuscaLargura().busca(inicial);
		if (n == null) {
			System.out.println("sem solucao!");
		} else {
			System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
		}
		
		Numeros inicial2 = new Numeros(1,10,"");
		System.out.println("busca em profundidade");
		Nodo n2 = new BuscaProfundidade().busca(inicial2);
		if (n2 == null) {
			System.out.println("sem solucao!");
		} else {
			System.out.println("solucao:\n" + n2.montaCaminho() + "\n\n");
		}
		
		Numeros inicial3 = new Numeros(1,10,"");
		System.out.println("busca iterativa");
		Nodo n3 = new BuscaIterativo().busca(inicial3);
		if (n3 == null) {
			System.out.println("sem solucao!");
		} else {
			System.out.println("solucao:\n" + n3.montaCaminho() + "\n\n");
		}
		
	}
}
