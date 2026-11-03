package Taller03;

public class Vampiro extends Monstruo implements Mordedor, Volador{
	private int velocidadDeVuelo;
	private int resistenciaAlSol;
	
	public Vampiro(int vida, int velocidad, int fuerza, int resistencia,
			int velocidadDeVuelo, int resistenciaAlSol) {
		super("Vampiro", vida, velocidad, fuerza, resistencia);
		this.velocidadDeVuelo = velocidadDeVuelo;
		this.resistenciaAlSol = resistenciaAlSol;
	}

	public int getVelocidadDeVuelo() {
		return velocidadDeVuelo;
	}

	public int getResistenciaAlSol() {
		return resistenciaAlSol;
	}
	
	@Override 
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	@Override
	public String getAtributosPropios() {
		return " | Vuelo " + velocidadDeVuelo + " | Sol " + resistenciaAlSol;
	}
	
	@Override 
	public String morder() {
		return "El Vampiro muestra los colmillos y muerde.";
	}
	
	@Override 
	public String volar() {
		return "El Vampiro se transforma en murcielago y vuela a velocidad " + velocidadDeVuelo + ".";	
	}
}
