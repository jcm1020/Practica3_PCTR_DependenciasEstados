# Practica3_PCTR_DependenciasEstados

Plantilla para la realización de la tercera práctica de la asignatura de Programación Concurrente y Tiempo Real del Grado en Ingeniería Informática de la Universidad de Burgos.

TABLA DE DEPENDENCIAS DE ESTADO

Estado	  Condicion	        entrarAlParque()	salirDelParque()

Superior  getValor() == MAX         NO            SI

Medio     MIN < getValor() <MAX     SI            SI
                                
Inferior  getValor() == MIN         SI            NO
                                
                           
