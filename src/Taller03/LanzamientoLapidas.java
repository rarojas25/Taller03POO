package Taller03;

public class LanzamientoLapidas implements Minijuego {

	@Override
	public String getNombre() {
		return "Lanzamiento de Lápidas";
	}
	
	@Override
	public String getDescripcion() {
		return "lanza una lápida lo más lejos posible.";
	}
	
	@Override
	public String getHabilidadPremiada() {
		return "Enfurecerse";
	}
	
	@Override
	public double calcularPotencial(Monstruo monstruo) {
		return monstruo.aceptar(this);
	}
	
	@Override 
	public double visitar(Zombi zombi) {
		return zombi.getFuerza() * zombi.getResistencia() / 100.0;
	}
	
	@Override
	public double visitar(Vampiro vampiro) {
		return vampiro.getFuerza() + vampiro.getVelocidadDeVuelo() / 4.0;
	}
	
	@Override
	public double visitar(Momia momia) {
		return momia.getFuerza() + momia.getLogitudVendajes() / 10.0;
	}	
	
	@Override
	public double visitar(HombreLobo hombreLobo) {
		return hombreLobo.getFuerza() + hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 8.0;
	}
}