package ar.fi.uba.celdas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AgenteAutonomo {
			
	private List<Estado> estadosHistoricos = null;
	public Set<Teoria> teorias = null;	
	private Estado estadoActual = null;	
	private Sensor sensor = null;
	
	public AgenteAutonomo(){
		this.estadosHistoricos = new ArrayList<Estado>();
		this.sensor  = new Sensor();
		this.teorias = new HashSet<Teoria>();		
	}
	
	public void correr() throws IOException, CloneNotSupportedException{
		sensor.openSensor();
		List<Teoria> teoriasIguales=null;
		List<Teoria> teoriasSimilares=null;
		List<Teoria> teoriasErroneas=null;

		while(true){
			estadoActual = sensor.sensarEntorno();  //Obtenemos un nuevo dato del entorno
			if(estadoActual==null) break;   //Si no hay más datos termina la ejecución
			agregarEstadoAcualAHistoricos(estadoActual); //Vamos registrando todos los estados
			Teoria teoriaLocal = armarTeoriaLocal(estadoActual);  //Armamos una teoria local
			
			teoriasIguales = Teoria.returnIguales(teoriaLocal, teorias); //verificamos si existe una teoria igual
			if(!teoriasIguales.isEmpty()){
				for (Teoria ti : teoriasIguales) { //Sí existen teorias iguales las ponderamos
					ti.incExitos();
					ti.incUsos();					
				}
				
			}else{ //Si no hay teorias iguales
				teoriasSimilares = Teoria.returnSimilares(teoriaLocal, teorias); //obtenemos las teorias similares
				if(!teoriasSimilares.isEmpty()){
					for (Teoria ts : teoriasSimilares) {
						Teoria teoriaMutante = ts.exclusion(teoriaLocal); //para cada teoría similar aplicamos el algoritmo de exclusión
						teoriaMutante.copyExitosUsos(ts);						
						if(!this.teorias.contains(teoriaMutante)){ //sí no existe la agregamos a la lista
							this.teorias.add(teoriaMutante);
							this.teorias.remove(ts); //olvidamos la teoria anterior
						}
					}
				}
				//Ponderamos y agregamos la teoria local
				if(!this.teorias.contains(teoriaLocal)){
					teoriaLocal.incExitos();
					teoriaLocal.incUsos();
					this.teorias.add(teoriaLocal);
				}
			}			 
			//Ajustamos todas las teorias que son erroneas
			teoriasErroneas = Teoria.returnErroneas(teoriaLocal, teorias);
			for (Teoria te : teoriasErroneas) {
				te.incUsos();
			}
		}
		sensor.closeSensor();
	}
		
	private Teoria armarTeoriaLocal(Estado estadoActual) {
		Teoria teoriaLocal = new Teoria();
		teoriaLocal.condicionesSupuestas = estadoActual.situacionActual;
		teoriaLocal.efectosPredichos = estadoActual.efectosObservados;
		return teoriaLocal;
		
	}

	private void agregarEstadoAcualAHistoricos(Estado S) {
		this.estadosHistoricos.add(S);		
	}	
}
