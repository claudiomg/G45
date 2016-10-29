package poi.dataImport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public abstract class DataImport {
	
    String line;
    Direccion direccion;
	Posicion posicion;
    
    public void importData(String pathFile, String separator) {

        try (BufferedReader buffer = new BufferedReader(new FileReader(pathFile))) {
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(separator);
                this.processData(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    abstract void processData(String[] data);
}
