package exemplos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;


public class Mundo extends JFrame{
	private static final long serialVersionUID = 4616559359426199280L;

	private int[] posRobo = {1,1};
	private int[] posMeta = {3,4};

	Graphics2D gBuffer;
	Image iBuffer;

	private static char[][] mundo = {
		{ ' ', ' ', ' ', ' ', '*', '*', '*' },
		{ ' ', 'O', ' ', ' ', ' ', ' ', ' ' },
		{ ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
		{ '*', '*', '*', '*', ' ', '*', ' ' },
		{ ' ', ' ', ' ', 'D', ' ', '*', ' ' },
		{ ' ', ' ', ' ', ' ', ' ', '*', ' ' },
		{ '*', '*', '*', '*', ' ', '*', ' ' }};

	int ajusteTela = 20;

	public Mundo(){
		this.setBounds(200, 200, mundo[0].length * 50, mundo.length * 50 + ajusteTela);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setOrigem(int[] pos){
		mundo[posRobo[1]][posRobo[0]] = ' ';
		mundo[pos[1]][pos[0]] = 'O';
		this.posRobo = pos;
		this.repaint();
	}

	public void setMeta(int[] pos){
		mundo[posMeta[1]][posMeta[0]] = ' ';
		mundo[pos[1]][pos[0]] = 'D';
		this.posMeta = pos;
		this.repaint();
	}

	public void paint(java.awt.Graphics g) {
		if(gBuffer == null){
			iBuffer = this.createImage(this.getWidth(), this.getHeight());
			gBuffer = (Graphics2D) iBuffer.getGraphics();
		}

		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());
		gBuffer.setColor(Color.BLACK);

		for( int i = 0; i < mundo.length; i++){
			gBuffer.drawLine( 0, 50 * i + ajusteTela, this.getWidth(), 50 * i + ajusteTela);
			for( int j = 0; j < mundo[0].length; j++){
				gBuffer.drawLine(j * 50, 0 + ajusteTela, j * 50, this.getHeight());
				switch( mundo[i][j]){
					case '*': gBuffer.fillRect(j * 50, i * 50 + ajusteTela, 50, 50);
							  break;
					case 'O': gBuffer.setColor(Color.BLUE);
							  gBuffer.fillRect(j * 50, i * 50 + ajusteTela, 50, 50);
							  gBuffer.setColor(Color.BLACK);
							  break;
					case 'D': gBuffer.setColor(Color.GREEN);
							  gBuffer.fillRect(j * 50, i * 50 + ajusteTela, 50, 50);
							  gBuffer.setColor(Color.BLACK);
							  break;
				}
			}
		}

		g.drawImage(iBuffer,0,0,this);
		gBuffer = null;
		iBuffer = null;
	}

	@Override
	public void repaint() {
		Graphics g = this.getGraphics();
		this.paint(g);
	}

	public static boolean ehObstaculo(int pos[]) {
		// System.out.println(pos[0]+","+pos[1]);
		boolean b = mundo[pos[1]][pos[0]] == '*';
		return (b);
	}

	public static boolean estaNoMundo(int pos[]) {
		return (pos[0] >= 0 &&
				pos[1] >= 0 &&
				pos[0] < mundo[0].length &&
				pos[1] < mundo.length);
	}
}
