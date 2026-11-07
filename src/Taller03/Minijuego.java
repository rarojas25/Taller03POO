package Taller03;

/*
 * Interfaz de los minijuegos.
 * Extiende el visitor porque cada minijuego contiene las fórmulas de los 4 monstruos.
 */
public interface Minijuego extends VisitorMinijuego{
	//Devuelve el nombre del minijuego. 
	String getNombre();
	//Devuelve la descripción del minijuego.
	String getDescripcion();
	//Devuelve la habilidad que da multiplicador x1.5 en este minijuego. */
	String getHabilidadPremiada();
	//Calcula el potencial de un monstruo en este minijuego. 
	double calcularPotencial(Monstruo monstruo);
}
