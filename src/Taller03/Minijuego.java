package Taller03;

public interface Minijuego extends VisitorMinijuego{
	String getNombre();
	String getDescripcion();
	String getHabilidPremiada();
	double calcularPotencial(Monstruo monstruo);
}
