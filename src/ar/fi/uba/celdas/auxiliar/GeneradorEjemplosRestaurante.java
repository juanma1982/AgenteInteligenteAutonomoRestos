package ar.fi.uba.celdas.auxiliar;

import ar.fi.uba.celdas.CondicionesRestaurantes;
import ar.fi.uba.celdas.CondicionesRestaurantes.Clientes;
import ar.fi.uba.celdas.CondicionesRestaurantes.TiempoEspera;

public class GeneradorEjemplosRestaurante {
	
	
	public static boolean tengoQueEsperar(CondicionesRestaurantes cr){
		
		if(cr.clientes == Clientes.NINGUNO){
			return false;
		}else if(cr.clientes == Clientes.ALGUNOS){
			return true;
		}else{
			if(cr.tiempo == TiempoEspera.CERODIEZ){
				return true;
			}else if(cr.tiempo == TiempoEspera.DIEZTREINTA){
				return flujoHambriento(cr);
			}else if(cr.tiempo == TiempoEspera.TREINTASESENTA){				
				return flujoAlternativa(cr);
			}else{
				return false;
			}			
		}
	}

	private static boolean flujoHambriento(CondicionesRestaurantes cr) {
		if(!cr.hambriento){
			return true;
		}
		if(!cr.alternativa) return true;
		return cr.lloviendo;
	}

	//lleno y espera de 10 a 30 minutos
	private static boolean flujoAlternativa(CondicionesRestaurantes cr) {
		if(cr.alternativa){
			return cr.viernesSabado;
		}else{
			if(cr.reserva){
				return true;
			}else{
				return cr.bar;
			}
		}
	}
	
	public static void main(String[] arg){
		
		for(int i=0;i<20000;i++){
			CondicionesRestaurantes cr = new CondicionesRestaurantes();
			System.out.print(cr.toString());
			System.out.print(",");
			System.out.println(String.valueOf(tengoQueEsperar(cr)));
		}
	}

}
