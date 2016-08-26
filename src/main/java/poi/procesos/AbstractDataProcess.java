package poi.procesos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractDataProcess implements Runnable {

	boolean hasError = false;
	List<HashMap<String,String>> inputRecords = new ArrayList<HashMap<String,String>>();
	
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	
	public boolean getHasError() {
		return this.getHasError();
	}

	@Override
	public void run() {
		//maneja errores del proceso, si no puede leer datos de entrada o errores generales
		try {
			this.loadInputRecords();
			this.processInputRecords();
	    } catch (Exception e) {
	        this.setHasError(true);
	    }
		
	}

	public List<HashMap<String, String>> getInputRecords() {
		return inputRecords;
	}

	public void setInputRecords(List<HashMap<String, String>> inputRecords) {
		this.inputRecords = inputRecords;
	}

	private void processInputRecords(){
		//manejo errores por cada registro procesado >> se puede agregar un archivo de logs
		for (HashMap<String,String> aRecord : this.getInputRecords()){
			try {
				this.processRecord(aRecord);
		    } catch (Exception e) {
		        this.setHasError(true);
		    }
		}
	}

	abstract void loadInputRecords();
	
	abstract void processRecord(HashMap<String, String> aRecord);

}
