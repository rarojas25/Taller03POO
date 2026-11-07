package Taller03;

/*
 * Minijuego Sobrevivir al Amanecer.
 * Premia la habilidad Regenerarse.
 */
public class SobrevivirAmanecer implements Minijuego{

	//Devuelve el nombre del minijuego.
	@Override
	public String getNombre() {
		return "Sobrevivir al Amanecer";
	}

	// Devuelve la descripción del minijuego.
	@Override
	public String getDescripcion() {
		return "aguanta en pie cuando salgan los primeros rayos de sol";
	}

	//Devuelve la habilidad que da multiplicador x1.5.
	@Override
	public String getHabilidadPremiada() {
		return "Regenerarse";
	}

	//Calcula el potencial del monstruo usando este minijuego como visitor. 
	@Override
	public double calcularPotencial(Monstruo monstruo) {
		return monstruo.aceptar(this);
	}
	
	//Fórmula del potencial del zombi.
	@Override
	public double visitar(Zombi zombi) {
		return zombi.getResistencia() * zombi.getVelocidad() / 100.0;
	}

	//Fórmula del potencial del vampiro.
	@Override
	public double visitar(Vampiro vampiro) {
		return vampiro.getResistencia() * vampiro.getResistenciaAlSol() / 100.0;
	}

	// Fórmula del potencial de la momia.
	@Override
	public double visitar(Momia momia) {
		return (momia.getResistencia() + momia.getVida()) / 2.0 * (1 + momia.getLogitudVendajes() / 300.0);
	}

	//Fórmula del potencial del hombre lobo.
	@Override
	public double visitar(HombreLobo hombreLobo) {
		return (hombreLobo.getResistencia() + hombreLobo.getVida()) / 2.0 * (5 - hombreLobo.getFaseLunar()) / 4.0;
	}

}
