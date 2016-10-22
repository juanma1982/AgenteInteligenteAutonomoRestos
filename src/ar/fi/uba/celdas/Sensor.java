package ar.fi.uba.celdas;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sensor {
	
	
	private FileInputStream fstream = null;
	private BufferedReader br = null;
	private String percepcion;
	public static final String FILE_NAME = "casosRestaurante.csv"; 
	
	public void openSensor() throws IOException{
		fstream = new FileInputStream(FILE_NAME);
		br = new BufferedReader(new InputStreamReader(fstream));
		br.readLine(); //ignoramos la primer linea, tiene los encabezados
	}
	
	public Estado sensarEntorno() throws IOException{
		percepcion = br.readLine();
		Estado e= null;
		if(percepcion!=null){
			e=new Estado(percepcion);
		}		
		return e;
	}

	public void closeSensor(){
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("No se pudo cerrar el archivo: "+FILE_NAME+". Error: "+e.getMessage());
			
		}		
	}


	public static <T extends Enum<T>> T valueOfIgnoreCase(Class<T> enumeration, String name) {
	    for(T enumValue : enumeration.getEnumConstants()) {
	        if (enumValue.toString().equalsIgnoreCase(name)) {
	            return enumValue;
	        }
	    }
	    throw new IllegalArgumentException("There is no value with name '" + name + " in Enum " + enumeration.getClass().getName());        
	}


}
