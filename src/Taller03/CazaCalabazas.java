package Taller03;

public class CazaCalabazas implements Minijuego {
	@Override 
	public String getNombre() {
		return "Caza de Calabazas";
	}
	
	@Override
	public String getDescripcion() {
		return "muerde la mayor cantidad de calabazas del huerto en 60 segundos.";
	}
	
	@Override
	public String getHabilidadPremiada() {
		return "Moder";
	}
	
	@Override
	public double calcularPotencial(Monstruo monstruo) {
		return monstruo.aceptar(this);
	}
	
	@Override
	public double visitar(Zombi zombi) {
		return zombi.getFuerzaDeMordida() + zombi.getVida() / 100.0 + zombi.getResistencia() / 2.0; 
	}
	
	@Override
	public double visitar(Vampiro vampiro) {
		return (vampiro.getFuerza() + vampiro.getVelocidad()) / 3.0;
	}
	
	@Override 
	public double visitar(Momia momia) {
		return momia.getLogitudVendajes() * momia.getFuerza() / 200.0;
	}
	
	@Override
	public double visitar(HombreLobo hombreLobo) {
		return hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 4.0 + hombreLobo.getVelocidad() / 4.0;
	}
}
