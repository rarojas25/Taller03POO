package Taller03;

public interface VisitorMinijuego {
	double visitar(Zombi zombi);
	double visitar(Vampiro vampiro);
	double visitar(Momia momia);
	double visitar(HombreLobo hombreLobo);
}
