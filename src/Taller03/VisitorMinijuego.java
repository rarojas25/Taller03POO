package Taller03;

/*
 * Visitor con una fórmula de potencial por cada tipo de monstruo.
 * Cada minijuego lo implementa con sus propias fórmulas.
 */
public interface VisitorMinijuego {
	//Potencial de un zombi. 
	double visitar(Zombi zombi);
	//Potencial de un vampiro. 
	double visitar(Vampiro vampiro);
	//Potencial de una momia.
	double visitar(Momia momia);
	//Potencial de un hombre lobo.
	double visitar(HombreLobo hombreLobo);
}
