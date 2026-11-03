package Taller03;

public class Zombi extends Monstruo implements Mordedor, Regenerable {
	private int fuerzaDeMordida;

	public Zombi(int vida, int velocidad, int fuerza, int resistencia, int fuerzaDeMordida) {
		super("Zombi", vida, velocidad, fuerza, resistencia);
		this.fuerzaDeMordida = fuerzaDeMordida;
	}
	
	public int getFuerzaDeMordida() {
		return fuerzaDeMordida;
	}
	
	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	@Override
	public String getAtributosPropios() {
		return " | Mordida " + fuerzaDeMordida;  
	}
	
	@Override
	public String morder() {
		return "El Zombi clava los dientes con una mordida de fuerza " + fuerzaDeMordida + "."; 
	}
	
	@Override
	public String regenerarse() {
		return "El Zombi regenera su carne podrida.";
	}

}
