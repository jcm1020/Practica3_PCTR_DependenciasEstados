package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parque implements IParque{


	// TODO 
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	
	private long tinicial;
	private double tmedio;
	
	private Random generadorAleatorios = new Random();
	
	static final long MIN = 0; // mínimo valor permitido
	static final long MAX = 50; // máximo valor permitido
	protected long valor = MIN;
	
	/**
	 * @param contadorPersonasTotales
	 * @param contadoresPersonasPuerta
	 * @param tinicial
	 * @param tmedio
	 */
	public Parque() {
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		tinicial = System.currentTimeMillis();
		tmedio = 0;
	}


	@Override
	public synchronized void entrarAlParque(String puerta) throws InterruptedException{		
		// TODO		
		//esperarSalirEstadoSuperior();
		comprobarAntesDeEntrar();
		setValor(valor + 1);
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
					contadoresPersonasPuerta.put(puerta, 0);
		}	
		
		/*contadorPersonasTotales =contadorPersonasTotales + 1;*/
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		try {
			TimeUnit.MILLISECONDS.sleep(generadorAleatorios.nextInt(3000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Logger.getGlobal().log(Level.INFO,"Interrupcion de hilos que utiliza el objeto parque " + puerta);	
		}
		
			
		
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		
		// TODO
		checkInvariante();

		
		// TODO
		Integer contPu = contadoresPersonasPuerta.get(puerta);
		contPu = contPu+1;
		contadoresPersonasPuerta.put(puerta,contPu);
		
		long tactual = System.currentTimeMillis();
		tmedio =(tmedio+(tactual-tinicial))/2;
		
			
		
	}
	
	// 
	// TODO Método salirDelParque
	//
	@Override
	public synchronized void salirDelParque(String puerta) throws InterruptedException{
	// TODO	
		//esperarSalirEstadoInferior();
		comprobarAntesDeSalir();
		setValor(valor - 1);
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
							contadoresPersonasPuerta.put(puerta, 0);
		}
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales--;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
				
		try {
					TimeUnit.MILLISECONDS.sleep(generadorAleatorios.nextInt(3000));
		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Logger.getGlobal().log(Level.INFO,"Interrupcion de hilos que utiliza el objeto parque " + puerta);	
		}				
						
				
				
				
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
				
		// TODO
		checkInvariante();

				
		// TODO
		Integer contPu = contadoresPersonasPuerta.get(puerta);
		contPu = contPu-1;
		contadoresPersonasPuerta.put(puerta,contPu);
				
		long tactual = System.currentTimeMillis();
		tmedio =(tmedio+(tactual-tinicial))/2;
		
	}
	
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	/*private String obtenerTmedio() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		// TODO 
		// TODO
		
		
		
	}

	protected void comprobarAntesDeEntrar() throws InterruptedException{
		//
		// TODO
		//
		while (valor == MAX)
			wait();
	}

	protected void comprobarAntesDeSalir() throws InterruptedException{
		//
		// TODO
		//
		while (valor == MIN)
			wait();
	}
	
	public synchronized long getValor() {
		return valor;
	}
	
	protected void setValor(long nuevoValor) { // PRE: bloqueo adquirido
		valor = nuevoValor;
		notifyAll(); // Despierta a todos los hilos que dependen del valor
	}


}
