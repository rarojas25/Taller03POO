//Integrante 1: Rocío Azucena Rojas Robledo - 21.694.049-0 - rarojas25
//Link repositorio: https://github.com/rarojas25/Taller03POO



package Taller03;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
		private static Scanner teclado;
		private static Sistema sistema;
		
		public static void main(String[]args) {
			teclado = new Scanner(System.in);
			sistema = new Sistema();
			
			System.out.println("Cargando archivos...");
			cargarMonstruos();
			cargarRegistros();
			
			if(sistema.cantMonstruos() == 0) {
				System.out.println("[!] No hay monstruos válidos en Monstruos.txt El programa termina.");
				return;
			}
			System.out.println(" > " + sistema.cantMonstruos() + " monstruos cargados desde Monstruos.txt ");
			System.out.println(" > " + sistema.cantJugadores() + " jugadores cargados desde Registros.txt");
			
			System.out.println("\n=========================================");
			System.out.println("                   SPOOKY SEASON");
			System.out.println("===========================================");
			
			boolean salir = false;
			while(!salir) {
				iniciarSesion();
				salir = menuPrincipal();
			}
			System.out.println("¡Hasta la próxima!");
		}

		private static void cargarMonstruos() {
			try {
				Scanner lector = new Scanner(new File("Monstruos.txt"));
				while(lector.hasNextLine()) {
					String linea = lector.nextLine();
					String[] campos = linea.split(";", -1);
					if(!sistema.agregarMonstruo(campos)) {
						System.out.println("[!] Línea descartada en Monstruos.txt: " + linea);
					}
				}
				lector.close();
			}catch(FileNotFoundException e) {
				System.out.println("[!] No se encontró el archivo Monstruos.txt");
			}
		}

		private static void cargarRegistros() {
			try {
				Scanner lector = new Scanner(new File("Registros.txt"));
				while(lector.hasNextLine()) {
					String linea = lector.nextLine();
					String[] campos = linea.split(";", -1);
					if(!sistema.agregarJugador(campos)) {
						System.out.println("[!] Línea descartada en Registros.txt: " + linea);
					}
				}
				lector.close();
			}catch(FileNotFoundException e) {
				System.out.println("[!] No existe Registros.txt, se creará el guardar.");
			}
		}

		private static void iniciarSesion() {
			String nombre = "";
			boolean valido = false;
			while(!valido) {
				System.out.print("\n Ingrese su nombre de usuario: ");
				nombre = leerLinea();
				String error = sistema.validarNombre(nombre);
				if(error.isEmpty()) {
					valido = true;
				}else {
					System.out.println("[!] " + error);
				}
			}
			int record = sistema.buscarRecord(nombre);
			if(record == -1) {
				System.out.println("\n¡Bienvenido, " + nombre + "! Es tu primera vez jugando.");
			}else {
				System.out.println("\n¡Bienvenido de vuelta, " + nombre + "! Tu récord actual es de " + record + " puntos.");
			}
			sistema.iniciarSesion(nombre);
		}

		private static String leerLinea() {
			if(!teclado.hasNextLine()) {
				System.out.println("\nNo hay más entrada. Cerrando el programa.");
			if(sistema.haySesionActiva()) {
				terminarSesion();
			}
			System.exit(0);
			}
			return teclado.nextLine().trim();
		}
		
		private static boolean menuPrincipal() {
			
			while(true) {
				System.out.println("\n------ MENÚ PRINCIPAL (" + sistema.getNombreActual() + ") ------\n\r"
						+ "1) Jugar un minijuego\r\n"
						+ "2) Ver catálogo de monstruos\r\n"
						+ "3) Ver historial de la sesión\r\n"
						+ "4) Ver ranking de jugadores\r\n"
						+ "5) Terminar sesión\r\n"
						+ "6) Salir\n");
				
				int opcion = leerEntero(1, 6);
				
				switch (opcion){
					case 1:
						jugarMinijuego();	
						break;
					case 2:
						verCatalogo();
						break;
					case 3:
						verHistorial();
						break;
					case 4:
						verRanking();
						break;
					case 5:
						terminarSesion();
						return false;
					case 6:
						terminarSesion();
						return true;
				}
			}
		}

		private static void jugarMinijuego() {
			while(true) {
				
				System.out.println("\n------ MINIJUEGOS ------");
				ArrayList<String> estados = sistema.obtenerEstadoMinijuegos();
				
				for(int i = 0; i < estados.size(); i++) {
					System.out.println((i+1) + ") " + estados.get(i));
				}
				System.out.println("0) Volver");
				boolean elegido = false;
				
				while(!elegido) {
					
					int opcion = leerEntero(0, estados.size());
					if(opcion == 0) {
						return;
					}
					if(sistema.seleccionarMinijuego(opcion - 1)) {
						elegido = true;
					}else {
						System.out.println("[!] Ya jugaste " + sistema.getNombreMinijuego(opcion - 1) + " en esta sesión.");
					}
				}
				boolean volverAlMenu = elegirMonstruoYJugar();
				if(volverAlMenu) {
					return;
				}
			}
		}

		private static boolean elegirMonstruoYJugar() {
			System.out.println("\n" +  sistema.getDescripcionMinijuegoActual());
			
			if(sistema.cantDisponibles() == 0) {
				System.out.println("[!] No quedan monstruos disponibles en esta sesión.");
				return true;
			}
			System.out.println("\nElige el monstruo en el que te transformarás: ");
			ArrayList<String> monstruos = sistema.listarMonstruosDisponibles();
			for(int i = 0; i < monstruos.size(); i++) {
				System.out.println((i+1) + ") " + monstruos.get(i));
			}
			
			System.out.println("0) Volver");
			
			int opcionMonstruo = leerEntero(0, monstruos.size());
			if(opcionMonstruo == 0) {
				return false;
			}
			sistema.seleccionarMonstruo(opcionMonstruo - 1);
			
			System.out.println("\nTe comes un dulce de dudosa procedencia...");
			System.out.println("Potencial: " + String.format("%.2f", sistema.calcularPotencial()));
			
			ArrayList<String> habilidades = sistema.obtenerHabilidades();
			System.out.println("\n¿Quieres usar una habilidad?");
			for(int i = 0; i < habilidades.size(); i++) {
				System.out.println((i+1) + ") " + habilidades.get(i));
			}
			System.out.println("0) No usar habilidad");
			
			int opcionHabilidad = leerEntero(0, habilidades.size());
			String habilidadUsada = "Ninguna";
			
			if(opcionHabilidad != 0) {
				habilidadUsada = habilidades.get(opcionHabilidad - 1);
				System.out.println(sistema.ejecutarHabilidad(habilidadUsada));
				if(sistema.habilidadEfectiva(habilidadUsada)){
					System.out.println("¡La habilidad fue efectiva! Puntaje x1.5");
				}else {
					System.out.println("La habilidad no tuvo efecto en este minijuego");
				}
			}
			int puntaje = sistema.registrarJugada(habilidadUsada);
			System.out.println("\n>> Puntaje obtenido: " + puntaje + " puntos");
			return true;
		}

		private static void verCatalogo() {
			System.out.println("\n------ CATÁLOGO DE MONSTRUOS ------");
			ArrayList<String> catalogo = sistema.obtenerCatalogo();
			for(int i = 0; i < catalogo.size(); i++) {
				System.out.println((i + 1) + ") " + catalogo.get(i));
			}
		}

		private static void verHistorial() {
			System.out.println("\n------ HISTORIAL DE LA SESIÓN ------");
			ArrayList<String> historial = sistema.obtenerHistorial();
			if(historial.isEmpty()) {
				System.out.println("Aún no has jugado ningún minijuego.");
				return;
			}
			for(int i = 0; i < historial.size(); i++) {
				System.out.println(historial.get(i));
			}
			System.out.println("Puntaje acumulado: " + sistema.getPuntajeSesion() + " pts");
		}

		private static void verRanking() {
			System.out.println("\n------ RANKING ------");
			ArrayList<String> ranking = sistema.obtenerRanking();
			if(ranking.isEmpty()) {
				System.out.println("Todavía no hay jugadores registrados.");
				return;
			}
			for(int i = 0; i < ranking.size(); i++) {
				System.out.println(ranking.get(i));
			}
		}

		private static void terminarSesion() {
			int puntaje = sistema.getPuntajeSesion();
			System.out.println("\nSesión terminada. Puntaje total: " + puntaje + " puntos.");
			
			int resultado = sistema.terminarSesion();
			
			if(resultado == 0){
				System.out.println("No jugaste ningún minijuego, no se guardó registro.");
				return;
			}
			if(resultado == 1) {
				System.out.println("¡Quedaste registrado con " + puntaje + "puntos!");
			}else if(resultado == 2) {
				System.out.println("¡Felicitaciones! Superaste tu récord anterior (" + sistema.getRecordAnterior() + ").");
			}else {
				System.out.println("No superaste tu récord (" + sistema.getRecordAnterior() + "). ¡Suerte para la próxima!");
			}
			
			if(guardarRegistros()) {
				System.out.println("Registros guardados en Registros.txt.");
			}
		}

		private static boolean guardarRegistros() {
			try {
				BufferedWriter escritor = new BufferedWriter(new FileWriter("Registros.txt"));
				ArrayList<String> registros = sistema.obtenerRegistros();
				for(int i = 0; i < registros.size(); i++) {
					escritor.write(registros.get(i));
					escritor.newLine();
				}
				escritor.close();
				return true;
				
			}catch(IOException e) {
				System.out.println("[!] No se pudo guardar Registros.txt.");
				return false;
			}
		}

		private static int leerEntero(int min, int max) {
			while(true) {
				
				System.out.print("> ");
				String texto = leerLinea();
				
				try {
					int num = Integer.parseInt(texto);
					if(num < min || num > max) {
						System.out.println("[!] Opción fuera de rango. Intente nuevamente.");
					}else {
						return num;
					}
				}catch(NumberFormatException e) {
					System.out.println("[!] Debe ingresar un número. Intente nuevamente.");
				}
			}
		}
	}
