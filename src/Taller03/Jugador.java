package Taller03;

//Jugador registrado con su puntaje máximo (récord)
public class Jugador {
	private String nombre;
	private int puntajeMax;
	
	//Crea un jugador con su nombre y su récord.
	public Jugador(String nombre, int puntajeMax) {
		super();
		this.nombre = nombre;
		this.puntajeMax = puntajeMax;
	}

	//Devuelve el récord del jugador.
	public int getPuntajeMax() {
		return puntajeMax;
	}

	//Actualiza el récord del jugador.
	public void setPuntajeMax(int puntajeMax) {
		this.puntajeMax = puntajeMax;
	}

	//Devuelve el nombre del jugador.
	public String getNombre() {
		return nombre;
	}
	
	
}
