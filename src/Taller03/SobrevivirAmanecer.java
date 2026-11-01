package Taller03;

public class SobrevivirAmanecer implements Minijuego{

	@Override
	public String getNombre() {
		return "Sobrevivir al Amanecer";
	}

	@Override
	public String getDescripcion() {
		return "aguanta en pie cuando salgan los primeros rayos de sol";
	}

	@Override
	public String getHabilidadPremiada() {
		return "Regenerarse";
	}

	@Override
	public double calcularPotencial(Monstruo monstruo) {
		return monstruo.aceptar(this);
	}
	
	@Override
	public double visitar(Zombi zombi) {
		return zombi.getResistencia() * zombi.getVelocidad() / 100.0;
	}

	@Override
	public double visitar(Vampiro vampiro) {
		return vampiro.getResistencia() * vampiro.getResistenciaAlSol() / 100.0;
	}

	@Override
	public double visitar(Momia momia) {
		return (momia.getResistencia() + momia.getVida()) / 2.0 * (1 + momia.getLogitudVendajes() / 300.0);
	}

	@Override
	public double visitar(HombreLobo hombreLobo) {
		return (hombreLobo.getResistencia() + hombreLobo.getVida()) / 2.0 * (5 - hombreLobo.getFaseLunar()) / 4.0;
	}

}
