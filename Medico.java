
public class Medico {
	//// 1.Propiedades de la clase
	// Se pueden agrupar siempre que tengan el mismo modificador y tipo
	// Por ejemplo:
	// private int numColegiado, edad, aniosExperiencia;
	private int numColegiado;
	private String nombre, apellidos;
	private int edad;
	private String especialidad;
	private int aniosExperiencia;
	private String centroTrabajo;
	private boolean activo; // True si está activo o false si no lo est�

	//// 2.Constructores
	// Constructor por defecto. No recibe parámetros
	public Medico() {

		this.numColegiado = 0;
		this.nombre = ""; // Se introduce la cadena vacía
		this.apellidos = "";
		this.edad = 0;
		this.especialidad = "";
		this.aniosExperiencia = 0;
		this.centroTrabajo = "";
		this.activo = false;
	}

	// Constructor con parámetros. Recibe toda la información para inicializar un
	// objeto Médico
	public Medico(int numColegiado, String nombre, String apellidos, int edad, String especialidad,
			int aniosExperiencia, String centroTrabajo, boolean activo) {
		super();
		this.numColegiado = numColegiado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.especialidad = especialidad;
		this.aniosExperiencia = aniosExperiencia;
		this.centroTrabajo = centroTrabajo;
		this.activo = activo;
	}

	//// 3.Getters, setters y toString
	public int getNumColegiado() {
		return numColegiado;
	}

	public void setNumColegiado(int numColegiado) {
		this.numColegiado = numColegiado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	// toString también devuelve el sueldo del médico además de sus datos
	public String toString() {
		return "Medico [numColegiado=" + numColegiado + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad="
				+ edad + ", especialidad=" + especialidad + ", aniosExperiencia=" + aniosExperiencia
				+ ", centroTrabajo=" + centroTrabajo + ", activo=" + activo + ", sueldo=" + calcularSueldo() + "]";
	}

	//// 4.Resto de métodos
	// Método para calcular el sueldo
	// public para poder acceder a él desde fuera de la clase (desde el Main)
	// int porque va a devolver un número entero, el sueldo
	// () porque no recibe parámetros. Todo lo necesario para calcular el sueldo ya
	//// esta en la clase:años trabajados y especialidad
	// Sería un error MUY GRAVE hacer que recibiese esos datos por parámetro:
	// public float calcularSueldo(String especialidad, int experiencia)
	public int calcularSueldo() {
		// Declaro una variable local al método calcularSueldo. Inicializada al salario
		// base, 1600€
		// ERROR GRAVE: declarar una propiedad sueldo al inicio de la clase Médico
		// public class Medico {
		// private int sueldo;
		int sueldo = 1600;
		// Añado 50€ por año trabajado. Podría haberlo hecho en la propia declaración:
		// int sueldo=1600+50*aniosExperiencia;
		sueldo = sueldo + 50 * aniosExperiencia; // Mejor: sueldo+=50*aniosExperiencia;
		// 200€ más si es neurocirujano o investigador
		// Lo siguiente no funciona:
		// if (especialidad=="Neurocirujano" || especialidad=="Investigador") {
		// Para comparar dos Strings debemos hacerlo con equals:
		if (especialidad.equals("Neurocirujano") || especialidad.equals("Investigador")) {
			sueldo += 200;
		}
		// Una vez calculado el sueldo se devuelve el dato a quien llamó al método
		// calcularSueldo
		return sueldo;
	}

	// Método jubilación. No recibe parámetros, solo hay que comprobar que se
	// cumplen unas determinadas condiciones
	// de edad y/o antigüedad
	// Tiene que devolver información de si un médico se puede jubilar o no
	// Método 1.NO PERMITIDO. Mostrar un System.out en el método indicando si se
	// puede jubilar o no
	// Método 2. Devolver un boolean a la llamada. True si se puede jubilar, False
	// si no.
	public boolean jubilacion() {
		// Comprobamos que se puede jubilar
		// Primero solo se va a poder jubilar si está activo Y se cumple que
		// cumple el requisito de edad
		// o el de edad combinado con experiencia
		if (activo && (edad >= 65 || (edad >= 60 && aniosExperiencia >= 33))) {
			// Jubilar implica que el médico deja de estar activo
			activo = false;
			// Y no tiene centro de trabajo asignado
			centroTrabajo = "";
			// Indicamos a quien ha llamado al método que se ha hecho la jubilación
			return true;
		} else {
			// Se incumple algún requisito para la jubilación
			// El médico no se jubila y se devuelve la información al Main
			return false;
		}
	}

	// Sobrecargamos el m�todo jubilaci�n
	// Ahora recibe los par�metros con los requisitos para la jubilaci�n
	// Antes no era necesario porque los requisitos eran fijos: >=65 o >=60 con
	// experiencia>=33
	// Pero ahora no podemos saber esos datos por lo que hay que introducir esa
	// informaci�n en la clase
	// a trav�s de par�metros.

	// edadMaxJubilaci�n tendr�a la condici�n de la edad a la que se puede jubilar
	// un m�dico independientemente de la experiencia
	// edadMinJubilaci�n y experienciaMinima me va a decir a si el m�dico se puede
	// jubilar con una edad y una experiencia m�nimas
	public boolean jubilacion(int edadMaxJubilacion, int edadMinJubilacion, int experienciaMinima) {
		// Aqu� es donde est� el cambio respecto al m�todo anterior:
		if (activo && (edad >= edadMaxJubilacion
				|| (edad >= edadMinJubilacion && aniosExperiencia >= experienciaMinima))) {
			activo = false;
			centroTrabajo = "";
			return true;
		} else {
			return false;
		}
	}

}
