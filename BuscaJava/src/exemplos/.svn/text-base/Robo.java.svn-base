package exemplos;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import busca.AEstrela;
import busca.Estado;
import busca.Heuristica;
import busca.Nodo;

/**
 * Classe Robo com a implementação das
 * Interfaces Estado e Heurítica
 *
 * Representação do Mundo
 *
 * @charset utf-8
 */
public class Robo implements Estado, Heuristica{
	private int[] posicaoMeta;
	private int[] posicaoCorrente;
	private String op;
	private LinkedList<Comando> comandos;
	private int custo;

	//
	private int orientacaoCorrente;

	public Robo(int[] posicaoMeta, int posicaoCorrente[], int orientacao, String op, LinkedList<Comando> comandos){
		this.posicaoMeta = posicaoMeta;
		this.posicaoCorrente = posicaoCorrente;
		this.orientacaoCorrente = orientacao % 360;
		this.op = op;
		this.comandos = comandos;
		this.custo = 1;
	}

	public void autualizarCusto( int novoCusto ){
		this.custo = novoCusto;
	}
	
	public int custo() {
		return custo;
	}

	public boolean ehMeta() {
		return (posicaoMeta[0] == posicaoCorrente[0] &&
				posicaoMeta[1] == posicaoCorrente[1]);
	}


	public String getDescricao() {
		return "Exercício programa 2";
	}

	/**
	 * Retorna todos os sucessores
	 */
	public List<Estado> sucessores() {
		List<Estado> sucessores = new LinkedList<Estado>();
		Robo r;

		// Robo virando para esquerda
		r = new Robo( posicaoMeta,
					  posicaoCorrente,
					  orientacaoCorrente - 90,
					  "Virando para esquerda",
					  comandos);
		r.autualizarCusto( custo + 2);
		sucessores.add( r );

		// Robo virando para a direita
		r = new Robo( posicaoMeta,
					  posicaoCorrente,
					  orientacaoCorrente + 90,
					  "Virando para direita",
					  comandos);
		r.autualizarCusto( custo + 2);
		sucessores.add( r );

		// Robo indo para frente
		int[] novaPosicao = new int[2];
		novaPosicao[0] = posicaoCorrente[0] + (int)Math.cos(Math.toRadians(orientacaoCorrente));
		novaPosicao[1] = posicaoCorrente[1] + (int)Math.sin(Math.toRadians(orientacaoCorrente));
		// Estado só entra como sucessor se estiver dentro do mundo
		if( Mundo.estaNoMundo(novaPosicao) && (!Mundo.ehObstaculo(novaPosicao))  ){
			r = new Robo( posicaoMeta,
					  novaPosicao,
					  orientacaoCorrente,
					  "Caminhando para ("+novaPosicao[0]+","+novaPosicao[1]+")",
					  comandos);
			r.autualizarCusto( custo + 1);
			sucessores.add( r );
		}

		return sucessores;
	}

	/* Heurística by Pitagoras */
	public int h() {
		 double hypot = Math.pow(posicaoMeta[0] - posicaoCorrente[0],2) + Math.pow(posicaoMeta[1] - posicaoCorrente[1],2);
		 hypot = Math.sqrt(hypot);
		 hypot = (hypot%2>0)?hypot+1:hypot;
		 return (int)hypot;
	}

	public String toString(){
		comandos.add( new Comando(op,orientacaoCorrente,posicaoCorrente) );
		return op + " custo: "+custo+"; heurística: "+h()+"\n";
	}

	public static void andar( Mundo mundo, LinkedList<Comando> comandos){
		int[] pos = { 0, 0};
		int ori = 0;
		int tempoC = 30;
		int[] cx = { -8, 17, -8 };
		int[] cy = { -15, 0, 15 };

		Graphics2D g2 = (Graphics2D) mundo.getGraphics();
		Polygon robo = new Polygon( cx, cy, 3);

		pos = comandos.peek().coordenada;
		ori = comandos.poll().orientacao;

		robo.translate(pos[0]*50 + 25, pos[1]*50 + 45);
		g2.fillPolygon(robo);

		Iterator<Comando> it = comandos.iterator();
		while(it.hasNext()){
			Comando c = it.next();
			if( c.op.startsWith("Virando")){
				while( ori < c.orientacao ){
					mundo.repaint();
					robo = new Polygon( cx, cy, 3);
					robo = virar(robo,-ori);
					robo.translate(pos[0]*50 + 25, pos[1]*50 + 45);
					g2.fillPolygon(robo);
					ori++;
					wait(tempoC);
				}
				while( ori > c.orientacao ){
					mundo.repaint();
					robo = new Polygon( cx, cy, 3);
					robo = virar(robo,-ori);
					robo.translate(pos[0]*50 + 25, pos[1]*50 + 45);
					g2.fillPolygon(robo);
					ori--;
					wait(tempoC);
				}
			}else if(c.op.startsWith("Caminhando")){
				while(pos[0] < c.coordenada[0]){
					for(int i = 0; i < 50; i++){
						mundo.repaint();
						robo.translate(1, 0);
						g2.fillPolygon(robo);
						wait(tempoC);
					}
					pos[0]++;
				}
				while( pos[1] < c.coordenada[1]){
					for(int i = 0; i < 50; i++){
						mundo.repaint();
						robo.translate(0, 1);
						g2.fillPolygon(robo);
						wait(tempoC);
					}
					pos[1]++;
				}
				while( pos[0] > c.coordenada[0]){
					for(int i = 0; i < 50; i++){
						mundo.repaint();
						robo.translate(-1, 0);
						g2.fillPolygon(robo);
						wait(tempoC);
					}
					pos[0]--;
				}
				while( pos[1] > c.coordenada[1]){
					for(int i = 0; i < 50; i++){
						mundo.repaint();
						robo.translate(0, -1);
						g2.fillPolygon(robo);
						wait(tempoC);
					}
					pos[1]--;
				}

			}
		}
	}

	public static Polygon virar(Polygon poly, int ang) {

		Polygon newPoly;
		int i;
		double x, y;
		double theta = Math.toRadians(ang);

		newPoly = new Polygon();
		for (i = 0; i < poly.npoints; i++) {
			x = poly.xpoints[i] * Math.cos(theta) + poly.ypoints[i] * Math.sin(theta);
			y = poly.ypoints[i] * Math.cos(theta) - poly.xpoints[i] * Math.sin(theta);
			newPoly.addPoint((int) x, (int) y);
		}

		return newPoly;
	}

	public static void wait(int t){
		try{Thread.sleep(t);}
		catch(Exception e){}
	}

	private static int[] leCoordenadas(){
		String[] aux;
		int[] coord;

		coord = new int[2];
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(is);

		try{
			aux = reader.readLine().split(",");
			aux[0] = aux[0].trim();
			aux[1] = aux[1].trim();

			coord[0] = Integer.parseInt(aux[0]);
			coord[1] = Integer.parseInt(aux[1]);

			return coord;
		}catch(IOException e){
			System.err.println("Erro de leitura");
			System.exit(-1);
		}
		return coord;
	}
	/**
	 *
	 * @param arg
	 */
	public static void main(String[] arg){
		System.out.print("Posicao incial do robo: ");
		int[] corrente = Robo.leCoordenadas();
		System.out.print("\nPosicao meta do robo: ");
		int[] meta = Robo.leCoordenadas();
		Mundo m = new Mundo();
		LinkedList<Comando> comandos = new LinkedList<Comando>();

		m.setMeta(meta);
		m.setOrigem(corrente);

		Robo robo = new Robo( meta,corrente, 0, "", comandos);

		System.out.println("Busca A*");
		long tempo = System.currentTimeMillis();
		Nodo n = new AEstrela().busca(robo);
		tempo = System.currentTimeMillis() - tempo;
		if( n == null )
			System.out.println("Sem solução");
		else{
			System.out.println("Tempo: " + tempo + " ms");
			System.out.println("Solução:\n" + n.montaCaminho() + "\n\n");
		}

		Robo.andar(m, comandos);
	}


	/* Representa os comandos passados para o robo */
	private class Comando{
		String op;
		int orientacao;
		int[] coordenada;

		public Comando( String op, int orientacao, int[] coordenada){
			this.op = op;
			this.orientacao = orientacao;
			this.coordenada = coordenada;
		}
	}
}
