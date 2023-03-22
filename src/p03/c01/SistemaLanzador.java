package src.p03.c01;

public class SistemaLanzador {
	public static void main(String[] args) {
		
		//String numeroDePuertas=args[0];
		
		//En esta practica se limitan a 5 puertas
		
		String numeroDePuertas = "5";
		
		IParque parque = new Parque(); // TODO
		char letra_puerta = 'A';
		
		System.out.println("¡Parque abierto!");
		
		//for (int i = 0; i < 5; i++) {
		for (int i = 0; i < Integer.parseInt(numeroDePuertas); i++) {
		
			
			String puerta = ""+((char) (letra_puerta++));
			
			// Creación de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread (entradas).start();
			
			// 
			// TODO
			//
			
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread (salidas).start();
			
			
		}
	}	
}