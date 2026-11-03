package Taller03;

public class HombreLobo extends Monstruo implements Mordedor, Furioso{
	private int ferocidad;
	private int faseLunar;
	
	public HombreLobo(int vida, int velocidad, int fuerza, int resistencia, int ferocidad,
			int faseLunar) {
		super("HombreLobo", vida, velocidad, fuerza, resistencia);
		this.ferocidad = ferocidad;
		this.faseLunar = faseLunar;
	}

	public int getFerocidad() {
		return ferocidad;
	}

	public int getFaseLunar() {
		return faseLunar;
	}
	
	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	@Override
	public String getAtributosPropios() {
		return " | Ferocidad " + ferocidad + " | Luna " + faseLunar;
	}
	
	@Override 
	public String morder() {
		return "El hombre Lobo muerde con sus colmillos afilados.";
	}
	
	@Override
	public String enfurecerse() {
		return "El hombre Lobo aulla a la luna y se enfurece.";
	}
}
