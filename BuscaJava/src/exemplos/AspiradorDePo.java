package exemplos;

import java.util.LinkedList;
import java.util.List;

import busca.BuscaIterativo;
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.Nodo;

public class AspiradorDePo implements Estado{
	
	/**
	 * Atributo auxiliar que armazena informações
	 * sobre a operação realizada para chegar até
	 * este estado.
	 */
	private String op;
	
	/**
	 * Representa a situação do lado esquerdo
	 * da casa.
	 */
	private int ladoEsquerdo;
	
	/**
	 * Representa a situação do lado direito
	 * da casa.
	 */
	private int ladoDireito;

	/**
	 * Representa a posição onde o robô está.
	 */
	private int posicao;
	
	/**
	 * O custo neste caso é uniforme.
	 */
	public int custo() {
		return 1;
	}

	/**
	 * Se todos os quartos da casa estão 
	 * limpos, então o agente alcançou o
	 * seu objetivo
	 */
	public boolean ehMeta() {
		if(this.ladoEsquerdo==Quarto.LIMPO && this.ladoDireito==Quarto.LIMPO)
			return true;
		else
			return false;
	}

	public String getDescricao() {
		return "Problema clássico do aspirador de pó.";
	}

	/**
	 * Define como são gerados os sucessores
	 * do estado atual. Deve aplicar todos os operadores
	 * que o agente pode executar.
	 */
	public List<Estado> sucessores() {
		List<Estado> sucessores = new LinkedList<Estado>();
		
		//mover para a esquerda
		sucessores.add(new AspiradorDePo(Quarto.ESQUERDA,this.ladoDireito,this.ladoEsquerdo, "Para a esquerda"));
		//mover para a direita
		sucessores.add(new AspiradorDePo(Quarto.DIREITA,this.ladoDireito,this.ladoEsquerdo, "Para a direita"));
		//limpar
		if(this.posicao==Quarto.DIREITA)
			sucessores.add(new AspiradorDePo(this.posicao,Quarto.LIMPO,this.ladoEsquerdo, "Limpar o quarto da direita"));
		else
			sucessores.add(new AspiradorDePo(this.posicao,this.ladoDireito,Quarto.LIMPO, "Limpar o quarto da esquerda"));
		
		return sucessores;
	}
	
	public AspiradorDePo(int posicao, int situacaoDireita, int situacaoEsquerda, String op){
		this.posicao = posicao;
		this.ladoDireito = situacaoDireita;
		this.ladoEsquerdo = situacaoEsquerda;
		this.op = op;
	}
	
	/**
	 * Necessário para imprimir a solução
	 * encontrada.
	 */
	public String toString(){
		return op + " -> ";
	}
	
	public static void main(String args[]){
		AspiradorDePo inicial = new AspiradorDePo(Quarto.DIREITA,Quarto.SUJO,Quarto.SUJO,"");
		System.out.println("busca em largura");
		Nodo n = new BuscaLargura().busca(inicial);
		if (n == null) {
			System.out.println("sem solucao!");
		} else {
			System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
		}
		
		AspiradorDePo inicial2 = new AspiradorDePo(Quarto.DIREITA,Quarto.SUJO,Quarto.SUJO,"");
		System.out.println("busca em profundidade");
		Nodo n2 = new BuscaProfundidade().busca(inicial2);
		if (n2 == null) {
			System.out.println("sem solucao!");
		} else {
			System.out.println("solucao:\n" + n2.montaCaminho() + "\n\n");
		}
		
		AspiradorDePo inicial3 = new AspiradorDePo(Quarto.DIREITA,Quarto.SUJO,Quarto.SUJO,"");
		System.out.println("busca iterativa");
		Nodo n3 = new BuscaIterativo().busca(inicial3);
		if (n3 == null) {
			System.out.println("sem solucao!");
		} else {
			System.out.println("solucao:\n" + n3.montaCaminho() + "\n\n");
		}
	}

}

class Quarto{
	public static int SUJO = 1;
	public static int LIMPO = 0;
	
	public static int DIREITA = 1;
	public static int ESQUERDA = 0;
}