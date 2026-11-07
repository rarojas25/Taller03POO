package Taller03;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Lógica y datos del programa
 * Crea y válida los objetos, maneja la sesión, calcula los puntajes y ordena el ranking.
 * No usa Scanner, no lee archivos ni imprime: entrega todo con valores de retorno.
 */

public class Sistema {
	
	private ArrayList<Monstruo> monstruos;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Minijuego> minijuegos;
	
	private String nombreActual;
	private int puntajeSesion;
	private int recordAnterior;
	private int[] puntajesMinijuegos;
	private LinkedList<Jugada> historial;
	
	private Minijuego minijuegoActual;
	private Monstruo monstruoActual;
	
	/*
	 * Crea las listas vacías y agrega los 4 minijuegos. 
	 */
	public Sistema() {
		monstruos = new ArrayList<Monstruo>();
		jugadores = new ArrayList<Jugador>();
		minijuegos = new ArrayList<Minijuego>();
		minijuegos.add(new CarreraCementerio());
		minijuegos.add(new CazaCalabazas());
		minijuegos.add(new LanzamientoLapidas());
		minijuegos.add(new SobrevivirAmanecer());
		historial = new LinkedList<Jugada>();
		puntajesMinijuegos = new int[minijuegos.size()];
		nombreActual = null;
	}
	
	/*
	 * Convierte un texto a entero. Si no es un número devuelve -1.
	 */
	private int convertirEntero(String texto) {
		try {
			return Integer.parseInt(texto.trim());
		}catch (NumberFormatException e) {
			return -1;
		}
	}
	
	/*
	 * Indica si un valor está dentro del rango (incluye los extremos).
	 */
	private boolean enRango(int valor, int min, int max) {
		return valor >= min && valor <= max;
	}
	
	/*
	 * Crea un monstruo con los campos de una línea de Monstruos.txt. 
	 * Devuelve false si la línea no es válida.
	 */
	public boolean agregarMonstruo(String[] campos) {
		String tipo = campos[0].trim();
		int cantCampos;
		if(tipo.equals("Zombi") || tipo.equals("Momia")) {
			cantCampos = 6;
		}else if(tipo.equals("Vampiro") || tipo.equals("HombreLobo")) {
			cantCampos = 7;
		}else {
			return false;
		}
		if(campos.length != cantCampos) {
			return false;
		}
		//valores[0] no se usa(es el tipo), los atributos parten desde el 1.
		
		int[] valores = new int[cantCampos];
		for(int i = 1; i < cantCampos; i++) {
			valores[i] = convertirEntero(campos[i]);
			if(valores[i] == -1) {
				return false;
			}
		}
		
		//atributos base: vida, velocidad, fuerza y resistencia.
		
		if(!enRango(valores[1], 1, 200) || !enRango(valores[2], 1, 100) || !enRango(valores[3], 1, 100) || !enRango(valores[4], 1, 100)) {
			return false;
		}
		
		if(tipo.equals("Zombi")) {
			if(!enRango(valores[5], 1, 100)) {
				return false;
			}
			monstruos.add(new Zombi(valores[1], valores[2], valores[3], valores[4] , valores[5]));
		
		}else if(tipo.equals("Vampiro")){
			if(!enRango(valores[5], 1, 100) || !enRango(valores[6], 1, 100)) {
				return false;
			}
			monstruos.add(new Vampiro(valores[1], valores[2], valores[3], valores[4], valores[5], valores[6]));
		
		}else if(tipo.equals("Momia")) {
			if(!enRango(valores[5], 1, 300)) {
				return false;
			}
			monstruos.add(new Momia(valores[1], valores[2], valores[3], valores[4], valores[5]));
		
		}else {
			if(!enRango(valores[5], 1, 100) || !enRango(valores[6], 1, 4)) {
				return false;
			}
			monstruos.add(new HombreLobo(valores[1], valores[2], valores[3], valores[4], valores[5], valores[6]));
		}
		return true;
	}
	
	/*
	 * Crea un jugador con los campos de una linea de Registros.txt.
	 * Devuelve false si la línea no es válida.
	 */
	public boolean agregarJugador(String[] campos) {
		if(campos.length != 2) {
			return false;
		}
		String nombre = campos[0].trim();
		int puntaje = convertirEntero(campos[1]);
		
		if(!validarNombre(nombre).isEmpty() || puntaje < 0) {
			return false;
		}
		if(buscarJugador(nombre) != null) {
			return false;
		}
		jugadores.add(new Jugador(nombre, puntaje));
		return true;
	}
	
	//Devuelve la cantidad de monstruos cargados.
	public int cantMonstruos() {
		return monstruos.size();
	}
	
	//Devuleve la cantidad de jugadores cargados.
	public int cantJugadores() {
		return jugadores.size();
	}
	
	/*
	 * Revisa el nombre de usuario. Devuelve un texto vacío si es válido o el mensaje de error.
	 */
	public String validarNombre(String nombre) {
		
		if(nombre.isEmpty()){
			return "El nombre no puede estar vacío.";
		}
		if(nombre.contains(";")) {
			return "El nombre no puede contener el carácter ; ";
		}
		return "";
	}
	
	//Busca un jugador por su nombre. Devuelve null si no existe.
	
	private Jugador buscarJugador(String nombre) {
		for(int i = 0; i < jugadores.size(); i++) {
			if(jugadores.get(i).getNombre().equals(nombre)) {
				return jugadores.get(i);
			}
		}
		return null;
	}
	
	//Devuelve el récord del jugador, o -1 si no está registrado.
	public int buscarRecord(String nombre) {
		Jugador jugador = buscarJugador(nombre);
		if(jugador == null) {
			return -1;
		}
		return jugador.getPuntajeMax();
	}
	
	/*
	 * Empieza una sesión nueva: minijuegos pendientes, monstruos disponibles y puntaje en 0.
	 */
	public void iniciarSesion(String nombre) {
		nombreActual = nombre;
		puntajeSesion = 0;
		recordAnterior = 0;
		historial = new LinkedList<Jugada>();
		for(int i = 0; i < puntajesMinijuegos.length; i++) {
			puntajesMinijuegos[i] = -1;
		}
		for(int i = 0; i < monstruos.size(); i++) {
			monstruos.get(i).setUsado(false);
		}
		minijuegoActual = null;
		monstruoActual = null;
	}
	
	//Indica si hay un jugador con la sesión iniciada.
	public boolean haySesionActiva() {
		return nombreActual != null;
	}
	
	//Devuelve el nombre del jugador de la sesión actual.
	public String getNombreActual() {
		return nombreActual;
	}
	
	/*
	 * Devuelve una línea por minijuego indicado si está pendiente o jugado.
	 */
	public ArrayList<String> obtenerEstadoMinijuegos(){
		ArrayList<String> lineas = new ArrayList<String>();
		for(int i = 0; i < minijuegos.size(); i++) {
			String estado;
			if(puntajesMinijuegos[i] == -1) {
				estado = "[PENDIENTE]";
			}else {
				estado = "[JUGADO - " + puntajesMinijuegos[i] + " pts]";
			}
			lineas.add(String.format("%-24s %s", minijuegos.get(i).getNombre(), estado));
		}
		return lineas;
	}
	
	//Devuelve el nombre del minijuego en esa posición. 
	public String getNombreMinijuego(int idx) {
		return minijuegos.get(idx).getNombre();
	}
	/*
	 * Elige un minijuego. Devuelve false si el índice es inválido o si ya se jugó.
	 */
	public boolean seleccionarMinijuego(int idx) {
		if(idx < 0 || idx >= minijuegos.size()) {
			return false;
		}
		if(puntajesMinijuegos[idx] != -1) {
			return false;
		}
		minijuegoActual = minijuegos.get(idx);
		return true;
	}
	
	//Devuelve el nombre y la descripción del minijuego elegido.
	public String getDescripcionMinijuegoActual() {
		return minijuegoActual.getNombre() + ": " + minijuegoActual.getDescripcion();
	}
	
	//Devuelve los monstruos que aún no se usan en la sesión.
	private ArrayList<Monstruo> obtenerDisponibles(){
		ArrayList<Monstruo> disponibles = new ArrayList<Monstruo>();
		for(int i = 0; i < monstruos.size(); i++) {
			if(!monstruos.get(i).isUsado()) {
				disponibles.add(monstruos.get(i));
			}
		}
		return disponibles;
	}
	
	//Devuelve cuántos monstruos quedan disponibles.
	public int cantDisponibles() {
		return obtenerDisponibles().size();
	}
	
	//Devuelve la descripción de cada monstruo disponible.
	public ArrayList<String> listarMonstruosDisponibles(){
		ArrayList<String> lineas = new ArrayList<String>();
		ArrayList<Monstruo> disponibles = obtenerDisponibles();
		for(int i = 0; i < disponibles.size(); i++) {
			lineas.add(disponibles.get(i).getDescripcion());
		}
		return lineas;
	}
	
	/*
	 * Elige un monstruo de los disponibles. 
	 * Devuelve false si el índice es inválido.
	 */
	public boolean seleccionarMonstruo(int idx) {
		ArrayList<Monstruo> disponibles = obtenerDisponibles();
		if(idx < 0 || idx >= disponibles.size()) {
			return false;
		}
		monstruoActual = disponibles.get(idx);
		return true;
	}
	
	// Calcula el potencial del monstruo elegido en el minijuego elegido.
	public double calcularPotencial() {
		return minijuegoActual.calcularPotencial(monstruoActual);
	}
	
	//Devuelve los nombres de las habilidades del monstruo elegido.	
	public ArrayList<String> obtenerHabilidades() {
		return obtenerNombresHabilidades(monstruoActual);
	}
	//Detecta con instanceof qué habilidades tiene un monstruo.
	private ArrayList<String> obtenerNombresHabilidades(Monstruo monstruo){
		
		ArrayList<String> habilidades = new ArrayList<String>();
		if(monstruo instanceof Mordedor) {
			habilidades.add("Morder");
		}
		if(monstruo instanceof Volador) {
			habilidades.add("Volar");
		}
		if(monstruo instanceof Regenerable) {
			habilidades.add("Regenerarse");
		}
		if(monstruo instanceof Furioso) {
			habilidades.add("Enfurecerse");
		}
		return habilidades;
	}
	
	//Hace el cast a la interfaz de la habilidad y devuelve su mensaje.
	public String ejecutarHabilidad(String habilidad) {
		
		if(habilidad.equals("Morder")) {
			return ((Mordedor) monstruoActual).morder();
		}
		if(habilidad.equals("Volar")) {
			return ((Volador) monstruoActual).volar();
		}
		if(habilidad.equals("Regenerarse")) {
			return ((Regenerable) monstruoActual).regenerarse();
		}
		if(habilidad.equals("Enfurecerse")) {
			return ((Furioso) monstruoActual).enfurecerse();
		}
		return "";
	}
	
	//Indica si la habilidad usada es la premiada por el minijuego.
	public boolean habilidadEfectiva(String habilidad) {
		return habilidad.equals(minijuegoActual.getHabilidadPremiada());
	}
	
	/*
	 * Calcula el puntaje, lo guarda en el historial y
	 *  marca el monstruo y el minijuego como usados. 
	 *  Si no se usó habilidad se envía "Ninguna".
	 */
	public int registrarJugada(String habilidad) {
		double potencial = minijuegoActual.calcularPotencial(monstruoActual);
	
	//el multiplicador sube a 1.5 solo si la habilidad es la premiada
	double multiplicador = 1.0;
	if(habilidadEfectiva(habilidad)) {
		multiplicador = 1.5;
	}
	int puntaje = (int) Math.round(potencial * 10 * multiplicador);
	
	int idxMinijuego = minijuegos.indexOf(minijuegoActual);
	puntajesMinijuegos[idxMinijuego] = puntaje;
	monstruoActual.setUsado(true);
	historial.add(new Jugada(minijuegoActual.getNombre(), monstruoActual.getTipo(), habilidad, puntaje));
	puntajeSesion += puntaje;
	
	minijuegoActual = null;
	monstruoActual = null;
	return puntaje;
	
	}
	
	/*
	 * Devuelve una línea por monstruo con sus atributos, 
	 * sus habilidades y si ya fue usado.
	 */
	public ArrayList<String> obtenerCatalogo(){
		ArrayList<String> lineas = new ArrayList<String>();
		for(int i = 0; i < monstruos.size(); i++) {
			Monstruo monstruo = monstruos.get(i);
			ArrayList<String> habilidades = obtenerNombresHabilidades(monstruo);
			
			String linea = monstruo.getDescripcion() + " | Habilidades: ";
			for(int j = 0; j < habilidades.size(); j++) {
				if(j > 0) {
					linea += ", ";
				}
				linea += habilidades.get(j);
			}
			if(monstruo.isUsado()) {
				linea += " [USADO]";
			}else {
				linea += " [DISPONIBLE]";
			}
			lineas.add(linea);
		}
		return lineas;
	}
	
	//Devuelve una línea por jugada, en el orden en que se jugaron.
	public ArrayList<String> obtenerHistorial(){
		ArrayList<String> lineas = new ArrayList<String>();
		int num = 1;
		for(Jugada jugada : historial) {
			lineas.add(String.format("%d. %-24s | %-10s | Habilidad: %-11s | %d pts", num, jugada.getMinijuego(), jugada.getMonstruo(), jugada.getHabilidad(), jugada.getPuntaje()));
			num++;
		}
		return lineas;
	}
	
	// Devuelve el puntaje acumulado de la sesión.
	public int getPuntajeSesion() {
		return puntajeSesion;
	}
	
	//Indica si ya se jugó al menos un minijuego en la sesión.
	public boolean hayJugadas() {
		return !historial.isEmpty();
	}
	
	/*
	 *  Indica
	 *   si el jugador a va antes que el b en el ranking 
	 *   (mayor puntaje y, si empatan, por nombre).
	 */
	private boolean vaAntes(Jugador a, Jugador b) {
		if(a.getPuntajeMax() != b.getPuntajeMax()) {
			return a.getPuntajeMax() > b.getPuntajeMax();
		}
		return a.getNombre().compareToIgnoreCase(b.getNombre()) < 0;
	}
	
	/*
	 *  Devuelve el ranking ordenado de mayor a menor puntaje
	 *   (ordenamiento propio).
	 */
	public ArrayList<String> obtenerRanking(){
		
		// se ordena una copia con selección directa (sin Collections.sort)
		ArrayList<Jugador> copia = new ArrayList<Jugador>(jugadores);
		for(int i = 0; i < copia.size() - 1; i++) {
			int mejor = i;
			for(int j = i + 1; j < copia.size(); j++) {
				if(vaAntes(copia.get(j), copia.get(mejor))) {
					mejor = j;
				}
			}
			Jugador aux = copia.get(i);
			copia.set(i, copia.get(mejor));
			copia.set(mejor, aux);
		}
		
		ArrayList<String> lineas = new ArrayList<String>();
		for(int i = 0; i < copia.size(); i++) {
			lineas.add(String.format("%d. %-15s %d", i + 1, copia.get(i).getNombre(), copia.get(i).getPuntajeMax()));
		}
		return lineas;
	}
	
	/*
	 * Termina la sesión y actualiza los récords. Devuelve 0 si no jugó, 1 si es nuevo,
	 *  2 si superó su récord y 3 si no lo superó. 
	 */
	public int terminarSesion() {
		int resultado;
		if(!hayJugadas()) {
			resultado = 0;
		}else {
			Jugador jugador = buscarJugador(nombreActual);
			if(jugador == null) {
				jugadores.add(new Jugador(nombreActual, puntajeSesion));
				resultado = 1;
			}else {
				recordAnterior = jugador.getPuntajeMax();
				if(puntajeSesion > recordAnterior) {
					jugador.setPuntajeMax(puntajeSesion);
					resultado = 2;
				}else {
					resultado = 3;
				}
			}
		}
		nombreActual = null;
		return resultado;
	}
	
	// Devuelve el récord que tenía el jugador antes de terminar la sesión.
	public int getRecordAnterior() {
		return recordAnterior;
	}
	
	/*
	 * Devuelve las líneas (Jugador;PuntajeMaximo) 
	 * que Main escribe en Registros.txt.
	 */
	public ArrayList<String> obtenerRegistros(){
		ArrayList<String> lineas = new ArrayList<String>();
		for(int i = 0; i < jugadores.size(); i++) {
			lineas.add(jugadores.get(i).getNombre() + ";" + jugadores.get(i).getPuntajeMax());
		}
		return lineas;
	}

}
