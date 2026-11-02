package Taller03;

public class Jugador {
	private String nombre;
	private int puntajeMax;
	
	public Jugador(String nombre, int puntajeMax) {
		super();
		this.nombre = nombre;
		this.puntajeMax = puntajeMax;
	}

	public int getPuntajeMax() {
		return puntajeMax;
	}

	public void setPuntajeMax(int puntajeMax) {
		this.puntajeMax = puntajeMax;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
