package Taller03;

import java.util.ArrayList;
import java.util.LinkedList;

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
	
	private int convertirEntero(String texto) {
		try {
			return Integer.parseInt(texto.trim());
		}catch (NumberFormatException e) {
			return -1;
		}
	}
	
	private boolean enRango(int valor, int min, int max) {
		return valor >= min && valor <= max;
	}
	
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
		
		int[] valores = new int[cantCampos];
		for(int i = 1; i < cantCampos; i++) {
			valores[i] = convertirEntero(campos[i]);
			if(valores[i] == -1) {
				return false;
			}
		}
		
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
	
	public int cantMonstruos() {
		return monstruos.size();
	}
	
	public int cantJugadores() {
		return jugadores.size();
	}
	
	public String validarNombre(String nombre) {
		
		if(nombre.isEmpty()){
			return "El nombre no puede estar vacío.";
		}
		if(nombre.contains(";")) {
			return "El nombre no puede contener el carácter ; ";
		}
		return "";
	}
	
	private Jugador buscarJugador(String nombre) {
		for(int i = 0; i < jugadores.size(); i++) {
			if(jugadores.get(i).getNombre().equals(nombre)) {
				return jugadores.get(i);
			}
		}
		return null;
	}
	
	public int buscarRecord(String nombre) {
		Jugador jugador = buscarJugador(nombre);
		if(jugador == null) {
			return -1;
		}
		return jugador.getPuntajeMax();
	}
	
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
	
	public boolean haySesionActiva() {
		return nombreActual != null;
	}
	
	public String getNombreActual() {
		return nombreActual;
	}
	
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
	
	public String getNombreMinijuego(int idx) {
		return minijuegos.get(idx).getNombre();
	}
	
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
	
	public String getDescripcionMinijuegoActual() {
		return minijuegoActual.getNombre() + ": " + minijuegoActual.getDescripcion();
	}
	
	private ArrayList<Monstruo> obtenerDisponibles(){
		ArrayList<Monstruo> disponibles = new ArrayList<Monstruo>();
		for(int i = 0; i < monstruos.size(); i++) {
			if(!monstruos.get(i).isUsado()) {
				disponibles.add(monstruos.get(i));
			}
		}
		return disponibles;
	}
	
	public int cantDisponibles() {
		return obtenerDisponibles().size();
	}
	
	public ArrayList<String> listarMonstruosDisponibles(){
		ArrayList<String> lineas = new ArrayList<String>();
		ArrayList<Monstruo> disponibles = obtenerDisponibles();
		for(int i = 0; i < disponibles.size(); i++) {
			lineas.add(disponibles.get(i).getDescripcion());
		}
		return lineas;
	}
	
	public boolean seleccionarMonstruo(int idx) {
		ArrayList<Monstruo> disponibles = obtenerDisponibles();
		if(idx < 0 || idx >= disponibles.size()) {
			return false;
		}
		monstruoActual = disponibles.get(idx);
		return true;
	}
	
	public double calcularPotencial() {
		return minijuegoActual.calcularPotencial(monstruoActual);
	}
	
	public ArrayList<String> obtenerHabilidades() {
		return obtenerNombresHabilidades(monstruoActual);
	}
	
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
	
	public boolean habilidadEfectiva(String habilidad) {
		return habilidad.equals(minijuegoActual.getHabilidadPremiada());
	}
	
	public int registrarJugada(String habilidad) {
		double potencial = minijuegoActual.calcularPotencial(monstruoActual);
	
	
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
	
	public ArrayList<String> obtenerHistorial(){
		ArrayList<String> lineas = new ArrayList<String>();
		int num = 1;
		for(Jugada jugada : historial) {
			lineas.add(String.format("%d. %-24s | %-10s | Habilidad: %-11s | %d pts", num, jugada.getMinijuego(), jugada.getMonstruo(), jugada.getHabilidad(), jugada.getPuntaje()));
			num++;
		}
		return lineas;
	}
	
	public int getPuntajeSesion() {
		return puntajeSesion;
	}
	
	public boolean hayJugadas() {
		return !historial.isEmpty();
	}
	
	private boolean vaAntes(Jugador a, Jugador b) {
		if(a.getPuntajeMax() != b.getPuntajeMax()) {
			return a.getPuntajeMax() > b.getPuntajeMax();
		}
		return a.getNombre().compareToIgnoreCase(b.getNombre()) < 0;
	}
	
	public ArrayList<String> obtenerRanking(){
		
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
	
	public int getRecordAnterior() {
		return recordAnterior;
	}
	
	public ArrayList<String> obtenerRegistros(){
		ArrayList<String> lineas = new ArrayList<String>();
		for(int i = 0; i < jugadores.size(); i++) {
			lineas.add(jugadores.get(i).getNombre() + ";" + jugadores.get(i).getPuntajeMax());
		}
		return lineas;
	}

}
