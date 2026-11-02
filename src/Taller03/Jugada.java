package Taller03;

public class Jugada {
	private String minijuego;
	private String monstruo;
	private String habilidad;
	private int puntaje;
	
	public Jugada(String minijuego, String monstruo, String habilidad, int puntaje) {
		super();
		this.minijuego = minijuego;
		this.monstruo = monstruo;
		this.habilidad = habilidad;
		this.puntaje = puntaje;
	}

	public String getMinijuego() {
		return minijuego;
	}

	public String getMonstruo() {
		return monstruo;
	}

	public String getHabilidad() {
		return habilidad;
	}

	public int getPuntaje() {
		return puntaje;
	}
}
