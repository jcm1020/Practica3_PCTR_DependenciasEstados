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
	public void entrarAlParque(String puerta){		// TODO
		
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
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
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
	public void salirDelParque(String puerta){
	// TODO	
		
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
	
	private String obtenerTmedio() {
		// TODO Auto-generated method stub
		return null;
	}
	
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

	protected void comprobarAntesDeEntrar(){
		//
		// TODO
		//
	}

	protected void comprobarAntesDeSalir(){
		//
		// TODO
		//
	}


}
