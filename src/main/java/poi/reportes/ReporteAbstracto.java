package poi.reportes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public abstract class ReporteAbstracto {

	public abstract JsonArray dumpReport();
	//este metodo debe ser llamado desde el controller para armar la grilla de resultados
	
	protected abstract JsonObject createResult();
	//por cada resultado se debe generar un JsonObject y se debe agregar al JsonArray de resultados

}

