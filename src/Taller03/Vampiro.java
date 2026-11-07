package Taller03;

/*
 * Monstruo Vampiro.
 * Tiene las habilidades Morder y Volar.
 */
public class Vampiro extends Monstruo implements Mordedor, Volador{
	private int velocidadDeVuelo;
	private int resistenciaAlSol;
	
	/*
	 * Crea un vampiro con sus atributos base, 
	 * su velocidad de vuelo y su resistencia al sol. 
	 */
	public Vampiro(int vida, int velocidad, int fuerza, int resistencia,
			int velocidadDeVuelo, int resistenciaAlSol) {
		super("Vampiro", vida, velocidad, fuerza, resistencia);
		this.velocidadDeVuelo = velocidadDeVuelo;
		this.resistenciaAlSol = resistenciaAlSol;
	}

	//Devuelve la velocidad de vuelo. 
	public int getVelocidadDeVuelo() {
		return velocidadDeVuelo;
	}

	//Devuelve la resistencia al sol. 
	public int getResistenciaAlSol() {
		return resistenciaAlSol;
	}
	
	//Entrega el vampiro al visitor para calcular su potencial. 
	@Override 
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	//Devuelve los atributos propios del vampiro como texto.
	@Override
	public String getAtributosPropios() {
		return " | Vuelo " + velocidadDeVuelo + " | Sol " + resistenciaAlSol;
	}
	
	//Mensaje de la habilidad Morder. 
	@Override 
	public String morder() {
		return "El Vampiro muestra los colmillos y muerde.";
	}
	
	//Mensaje de la habilidad Volar.
	@Override 
	public String volar() {
		return "El Vampiro se transforma en murcielago y vuela a velocidad " + velocidadDeVuelo + ".";	
	}
}
