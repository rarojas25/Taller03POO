package Taller03;

/*
 * Minijuego Lanzamiento de Lápidas.
 * Premia la habilidad Enfurecerse.
 */
public class LanzamientoLapidas implements Minijuego {

	//Devuelve el nombre del minijuego. 
	@Override
	public String getNombre() {
		return "Lanzamiento de Lápidas";
	}
	
	//Devuelve la descripción del minijuego.
	@Override
	public String getDescripcion() {
		return "lanza una lápida lo más lejos posible.";
	}
	
	//Devuelve la habilidad que da multiplicador x1.5.
	@Override
	public String getHabilidadPremiada() {
		return "Enfurecerse";
	}
	
	//Calcula el potencial del monstruo usando este minijuego como visitor.
	@Override
	public double calcularPotencial(Monstruo monstruo) {
		return monstruo.aceptar(this);
	}
	
	//Fórmula del potencial del zombi.
	@Override 
	public double visitar(Zombi zombi) {
		return zombi.getFuerza() * zombi.getResistencia() / 100.0;
	}
	
	//Fórmula del potencial del vampiro.
	@Override
	public double visitar(Vampiro vampiro) {
		return vampiro.getFuerza() + vampiro.getVelocidadDeVuelo() / 4.0;
	}
	
	//Fórmula del potencial de la momia.
	@Override
	public double visitar(Momia momia) {
		return momia.getFuerza() + momia.getLogitudVendajes() / 10.0;
	}	
	
	//Fórmula del potencial del hombre lobo.
	@Override
	public double visitar(HombreLobo hombreLobo) {
		return hombreLobo.getFuerza() + hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 8.0;
	}
}