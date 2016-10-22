package ar.fi.uba.celdas;

public class Estado {
	
		public CondicionesRestaurantes situacionActual;
		public Boolean efectosObservados;
		
		public Estado(String percepcion){			
			String[] strCond = percepcion.split(",");
			situacionActual = new CondicionesRestaurantes(strCond);
			efectosObservados = Boolean.valueOf(strCond[strCond.length-1]);			
		}
}
