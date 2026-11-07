package Taller03;

/*
 * Minijuego Caza de Calabazas.
 * Premia la habilidad Morder.
 */
public class CazaCalabazas implements Minijuego {
	
	//Devuelve el nombre del minijuego.
	@Override 
	public String getNombre() {
		return "Caza de Calabazas";
	}
	
	//Devuelve la descripción del minijuego.
	@Override
	public String getDescripcion() {
		return "muerde la mayor cantidad de calabazas del huerto en 60 segundos.";
	}
	
	//Devuelve la habilidad que da multiplicador x1.5. 
	@Override
	public String getHabilidadPremiada() {
		return "Moder";
	}
	
	//Calcula el potencial del monstruo usando este minijuego como visitor.
	@Override
	public double calcularPotencial(Monstruo monstruo) {
		return monstruo.aceptar(this);
	}
	
	//Fórmula del potencial del zombi.
	@Override
	public double visitar(Zombi zombi) {
		return zombi.getFuerzaDeMordida() + zombi.getVida() / 100.0 + zombi.getResistencia() / 2.0; 
	}
	
	//Fórmula del potencial del vampiro.
	@Override
	public double visitar(Vampiro vampiro) {
		return (vampiro.getFuerza() + vampiro.getVelocidad()) / 3.0;
	}
	
	//Fórmula del potencial de la momia.
	@Override 
	public double visitar(Momia momia) {
		return momia.getLogitudVendajes() * momia.getFuerza() / 200.0;
	}
	
	//Fórmula del potencial del hombre lobo.
	@Override
	public double visitar(HombreLobo hombreLobo) {
		return hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 4.0 + hombreLobo.getVelocidad() / 4.0;
	}
}
