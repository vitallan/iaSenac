package example.health;

import example.health.Tipos.CADASTRO;
import example.health.Tipos.CARENCIA;
import example.health.Tipos.SINISTRO;

public class Pessoa {
	
	private String nome;
	private int idade;
	private boolean liberado = false;
	private Tipos.CADASTRO cadastro;
	private Tipos.CARENCIA carencia;
	private Tipos.SINISTRO sinistro;
	
	public Pessoa(String nome, int idade, CADASTRO cadastro, CARENCIA carencia, SINISTRO sinistro) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.cadastro = cadastro;
		this.carencia = carencia;
		this.sinistro = sinistro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Tipos.CADASTRO getCadastro() {
		return cadastro;
	}
	public void setCadastro(Tipos.CADASTRO cadastro) {
		this.cadastro = cadastro;
	}
	public Tipos.CARENCIA getCarencia() {
		return carencia;
	}
	public void setCarencia(Tipos.CARENCIA carencia) {
		this.carencia = carencia;
	}
	public Tipos.SINISTRO getSinistro() {
		return sinistro;
	}
	public void setSinistro(Tipos.SINISTRO sinistro) {
		this.sinistro = sinistro;
	}
	public boolean isLiberado() {
		return liberado;
	}
	public void setLiberado(boolean liberado) {
		this.liberado = liberado;
	}
}
