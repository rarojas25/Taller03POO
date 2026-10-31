package Taller03;

public abstract class Monstruo {
	private String tipo;
	private int vida;
	private int velocidad;
	private int fuerza;
	private int resistencia;
	private boolean usado;
	
	public Monstruo(String tipo, int vida, int velocidad, int fuerza, int resistencia, boolean usado) {
		super();
		this.tipo = tipo;
		this.vida = vida;
		this.velocidad = velocidad;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
		this.usado = usado;
	}
	
	public abstract double aceptar(VisitorMinijuego visitor);
	public abstract String getAtributosPropios();
	public String getDescripcion() {
		return String.format("%-10s | Vida %d | Vel %d | Fue %d | Res %d" , tipo, vida, velocidad, fuerza, resistencia) + getAtributosPropios();
	}
	
	public String getTipo() {
		return tipo;
	}
	public int getVida() {
		return vida;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public int getFuerza() {
		return fuerza;
	}
	public int getResistencia() {
		return resistencia;
	}
	public boolean isUsado() {
		return usado;
	}
	public void setUsado(boolean usado) {
		this.usado = usado;
	}
	
}
