package Taller03;

/*
 * **
 * Monstruo Zombi.
 * Tiene las habilidades Morder y Regenerarse.
 */
public class Zombi extends Monstruo implements Mordedor, Regenerable {
	private int fuerzaDeMordida;

	// Crea un zombi con sus atributos base y su fuerza de mordida.
	public Zombi(int vida, int velocidad, int fuerza, int resistencia, int fuerzaDeMordida) {
		super("Zombi", vida, velocidad, fuerza, resistencia);
		this.fuerzaDeMordida = fuerzaDeMordida;
	}
	
	// Devuelve la fuerza de mordida.
	public int getFuerzaDeMordida() {
		return fuerzaDeMordida;
	}
	
	//Entrega el zombi al visitor para calcular su potencial.
	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	// Devuelve el atributo propio del zombi como texto.
	@Override
	public String getAtributosPropios() {
		return " | Mordida " + fuerzaDeMordida;  
	}
	
	//Mensaje de la habilidad Morder.
	@Override
	public String morder() {
		return "El Zombi clava los dientes con una mordida de fuerza " + fuerzaDeMordida + "."; 
	}
	
	//Mensaje de la habilidad Regenerarse.
	@Override
	public String regenerarse() {
		return "El Zombi regenera su carne podrida.";
	}

}
