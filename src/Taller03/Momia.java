package Taller03;

public class Momia extends Monstruo implements Regenerable, Furioso{
	private int longitudVendajes;

	public Momia(String tipo, int vida, int velocidad, int fuerza, int resistencia, boolean usado,
			int logitudVendajes) {
		super("Momia", vida, velocidad, fuerza, resistencia, usado);
		this.longitudVendajes = logitudVendajes;
	}

	public int getLogitudVendajes() {
		return longitudVendajes;
	}
	
	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	@Override 
	public String getAtributosPropios() {
		return " | Vendajes " + longitudVendajes;
	}
	
	@Override
	public String regenerarse() {
		return "La Momia se vuelve a enrrollar los vendajes y recupera fuerzas.";
	}
	
	@Override
	public String enfurecerse() {
		return "La Momia suelta un rugido y se enfurese";
	}
}
