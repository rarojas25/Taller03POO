package Taller03;

/*
 * Resultado de un minijuego jugado.
 * Se guarda en el historial de la sesión.
 */
public class Jugada {
	private String minijuego;
	private String monstruo;
	private String habilidad;
	private int puntaje;
	
	/*Crea una jugada con el minijuego, el monstruo, 
	 * la habilidad usada y el puntaje. */
	public Jugada(String minijuego, String monstruo, String habilidad, int puntaje) {
		super();
		this.minijuego = minijuego;
		this.monstruo = monstruo;
		this.habilidad = habilidad;
		this.puntaje = puntaje;
	}

	///** Devuelve el nombre del minijuego jugado. */
	public String getMinijuego() {
		return minijuego;
	}

	//Devuelve el tipo de monstruo usado.
	public String getMonstruo() {
		return monstruo;
	}
	
	//Devuelve la habilidad usada (o "Ninguna").
	public String getHabilidad() {
		return habilidad;
	}

	// Devuelve el puntaje obtenido.
	public int getPuntaje() {
		return puntaje;
	}
}
