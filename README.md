# Taller 3 POO - Spooky Season

## Descripción
Simulación por consola de minijuegos de Halloween. Cada jugador inicia sesión y se transforma en un monstruo distinto (Zombi, Vampiro, Momia u Hombre Lobo) para cada minijuego. Puede usar una habilidad por minijuego, y el puntaje depende del potencial del monstruo y de si la habilidad fue efectiva. Los récords de cada jugador se guardan en `Registros.txt`.

El proyecto usa herencia, interfaces, colecciones (`ArrayList` y `LinkedList`) y el patrón Visitor para calcular los potenciales. Incluye las dos bonificaciones: patrón Visitor y separación en las clases `Main` y `Sistema`.

## Integrantes
| Nombre completo | RUT | Usuario GitHub |
|-----------------|-----|----------------|
| Rocío Azucena Rojas Robledo | 21.694.049-0 | rarojas25 |

## Estructura del proyecto
Proyecto Eclipse en la raíz del repositorio. Las clases están en `src/Taller03`:

- `Main`: entrada y salida. Usa Scanner, lee los archivos, muestra los menús y escribe `Registros.txt`.
- `Sistema`: lógica y datos. Crea y valida los objetos, maneja la sesión, calcula puntajes y ordena el ranking.
- `Monstruo`: clase abstracta con los atributos base.
- `Zombi`, `Vampiro`, `Momia`, `HombreLobo`: subclases de `Monstruo`.
- `Mordedor`, `Volador`, `Regenerable`, `Furioso`: interfaces de las habilidades.
- `Minijuego`: interfaz de los minijuegos.
- `VisitorMinijuego`: interfaz con una fórmula por tipo de monstruo.
- `CarreraCementerio`, `CazaCalabazas`, `LanzamientoLapidas`, `SobrevivirAmanecer`: los 4 minijuegos.
- `Jugador`: nombre y puntaje máximo de un jugador.
- `Jugada`: resultado de un minijuego, que se guarda en el historial.

Archivos de datos, en la raíz del proyecto:
- `Monstruos.txt`: catálogo de monstruos.
- `Registros.txt`: récord de cada jugador.

## Instrucciones de ejecución
1. Clonar el repositorio:
   ```
   git clone https://github.com/rarojas25/Taller03POO
   ```
2. Abrir Eclipse e importar el proyecto: `File > Import > General > Existing Projects into Workspace`, y elegir la carpeta clonada.
3. Ejecutar la clase `Main` (`Run As > Java Application`).

## Cómo probarlo
1. Iniciar sesión con un jugador existente (por ejemplo `Clapt`) o con un nombre nuevo.
2. Elegir "Jugar un minijuego", escoger un monstruo y usar una habilidad.
3. Revisar el catálogo, el historial y el ranking desde el menú.
4. Elegir "Terminar sesión" y abrir `Registros.txt` para ver el récord actualizado.
5. Probar los errores: opciones inválidas, nombre vacío o con `;`, repetir un minijuego o un monstruo, y líneas mal escritas en los archivos.

## Diagrama de clases
El diagrama UML en PDF está en la raíz del repositorio.
