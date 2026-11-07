package Taller03;

/*
 * Monstruo Momia.
 * Tiene las habilidades Regenerarse y Enfurecerse.
 */
public class Momia extends Monstruo implements Regenerable, Furioso{
	private int longitudVendajes;

	//Crea una momia con sus atributos base y la longitud de sus vendajes (en metros).
	public Momia(int vida, int velocidad, int fuerza, int resistencia,
			int logitudVendajes) {
		super("Momia", vida, velocidad, fuerza, resistencia);
		this.longitudVendajes = logitudVendajes;
	}

	//Devuelve la longitud de los vendajes.
	public int getLogitudVendajes() {
		return longitudVendajes;
	}
	
	//Entrega la momia al visitor para calcular su potencial.
	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	// Devuelve el atributo propio de la momia como texto.
	@Override 
	public String getAtributosPropios() {
		return " | Vendajes " + longitudVendajes;
	}
	
	//Mensaje de la habilidad Regenerarse.
	@Override
	public String regenerarse() {
		return "La Momia se vuelve a enrrollar los vendajes y recupera fuerzas.";
	}
	
	// Mensaje de la habilidad Enfurecerse.
	@Override
	public String enfurecerse() {
		return "La Momia suelta un rugido y se enfurese";
	}
}
