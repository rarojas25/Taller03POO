package Taller03;

/*
 * Minijuego Carrera del Cementerio.
 * Premia la habilidad Volar.
 */
public class CarreraCementerio implements Minijuego{
	
	//Devuelve el nombre del minijuego.
	@Override
	public String getNombre() {
		return "Carrera del Cementerio";
	}
	
	//Devuelve la descripción del minijuego.
	@Override
	public String getDescripcion() {
		return "recorre el cementerio de punta a punta antes de que el sepulturero te vea.";
	}
	
	//Devuelve la habilidad que da multiplicador x1.5.
	@Override
	public String getHabilidadPremiada() {
		return "Volar";
	}
	
	//Calcula el potencial del monstruo usando este minijuego como visitor.
	@Override
	public double calcularPotencial(Monstruo monstruo) {
		return monstruo.aceptar(this);
	}
	
	//Fórmula del potencial del zombi.
	@Override
	public double visitar(Zombi zombi) {
		return zombi.getVelocidad() * zombi.getResistencia() / 100.0;
	}
	
	//Fórmula del potencial del vampiro.
	@Override
	public double visitar(Vampiro vampiro) {
		return (vampiro.getVelocidad() + 2.0 * vampiro.getVelocidadDeVuelo()) / 2.0;
	}
	
	//Fórmula del potencial de la momia.
	@Override
	public double visitar(Momia momia) {
		return momia.getVelocidad() * 100.0 / (100 + momia.getLogitudVendajes());
	}
	
	//Fórmula del potencial del hombre lobo.
	@Override
	public double visitar(HombreLobo hombreLobo) {
		return (hombreLobo.getVelocidad() + hombreLobo.getFerocidad() / 5.0) * hombreLobo.getFaseLunar() / 4.0;
	}
}