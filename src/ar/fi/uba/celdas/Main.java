package ar.fi.uba.celdas;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static final int EXITOS_MINIMOS = 100;
	public static void main(String[] args) {
		AgenteAutonomo au = new AgenteAutonomo();
		try {
			au.correr();
									
			List<Teoria> teoriasExitosas = Teoria.returnExitosas(au.teorias);
			Collections.sort(teoriasExitosas, new Comparator<Teoria>() {
			        public int compare(Teoria t1, Teoria t2) {
			           return -t1.getCantUsos()+t2.getCantUsos();
			        }
			}); 
			for (Teoria teo : teoriasExitosas) {
				if(teo.getCantExitos()>EXITOS_MINIMOS)
					System.out.println(teo.toString(false));
			}
			
			
			System.out.println("Finalización exitosa!");
			
		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
		} catch (CloneNotSupportedException e) {
			System.out.println("Error: "+e.getMessage());
			e.printStackTrace();
		}

	}

}
