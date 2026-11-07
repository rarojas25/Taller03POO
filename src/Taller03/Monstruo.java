package Taller03;

/*
 * Clase base de todos los monstruos.
 * Guarda los atributos que comparten y 
 * obliga a las subclases a implementar aceptar() (patrón Visitor).
 */
public abstract class Monstruo {
	private String tipo;
	private int vida;
	private int velocidad;
	private int fuerza;
	private int resistencia;
	private boolean usado;
	
	//Crea un monstruo con sus atributos base. Al inicio queda sin usar.
	public Monstruo(String tipo, int vida, int velocidad, int fuerza, int resistencia) {
		super();
		this.tipo = tipo;
		this.vida = vida;
		this.velocidad = velocidad;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
		this.usado = false;
	}
	
	//Recibe el visitor de un minijuego y devuelve el potencial calculado.
	public abstract double aceptar(VisitorMinijuego visitor);
	//Devuelve el texto con los atributos propios de cada tipo de monstruo.
	public abstract String getAtributosPropios();
	//Devuelve el tipo y todos los atributos (base y propios) en una línea.
	public String getDescripcion() {
		return String.format("%-10s | Vida %d | Vel %d | Fue %d | Res %d" , tipo, vida, velocidad, fuerza, resistencia) + getAtributosPropios();
	}
	
	//Devuelve el tipo del monstruo.
	public String getTipo() {
		return tipo;
	}
	
	// Devuelve la vida del monstruo
	public int getVida() {
		return vida;
	}
	
	// Devuelve la velocidad del monstruo.
	public int getVelocidad() {
		return velocidad;
	}
	
	// Devuelve la fuerza del monstruo.
	public int getFuerza() {
		return fuerza;
	}
	
	// Devuelve la resistencia del monstruo.
	public int getResistencia() {
		return resistencia;
	}
	
	//Indica si el monstruo ya se usó en la sesión actual.
	public boolean isUsado() {
		return usado;
	}
	
	// Marca el monstruo como usado o como disponible.
	public void setUsado(boolean usado) {
		this.usado = usado;
	}
	
}
