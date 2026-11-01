package Taller03;

public interface Minijuego extends VisitorMinijuego{
	String getNombre();
	String getDescripcion();
	String getHabilidadPremiada();
	double calcularPotencial(Monstruo monstruo);
}
