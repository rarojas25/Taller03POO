package Taller03;

/*
 * Monstruo Hombre Lobo.
 * Tiene las habilidades Morder y Enfurecerse.
 */
public class HombreLobo extends Monstruo implements Mordedor, Furioso{
	private int ferocidad;
	private int faseLunar;
	
	/*Crea un hombre lobo con sus atributos base,
	su ferocidad y la fase lunar (1 a 4).*/	
	public HombreLobo(int vida, int velocidad, int fuerza, int resistencia, int ferocidad,
			int faseLunar) {
		super("HombreLobo", vida, velocidad, fuerza, resistencia);
		this.ferocidad = ferocidad;
		this.faseLunar = faseLunar;
	}
	
	//Devuelve la ferocidad.
	public int getFerocidad() {
		return ferocidad;
	}

	// Devuelve la fase lunar (1 = luna nueva, 4 = luna llena).
	public int getFaseLunar() {
		return faseLunar;
	}
	
	//Entrega el hombre lobo al visitor para calcular su potencial.
	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	//Devuelve los atributos propios del hombre lobo como texto.
	@Override
	public String getAtributosPropios() {
		return " | Ferocidad " + ferocidad + " | Luna " + faseLunar;
	}
	
	//Mensaje de la habilidad Morder.
	@Override 
	public String morder() {
		return "El hombre Lobo muerde con sus colmillos afilados.";
	}
	
	// Mensaje de la habilidad Enfurecerse.
	@Override
	public String enfurecerse() {
		return "El hombre Lobo aulla a la luna y se enfurece.";
	}
}
