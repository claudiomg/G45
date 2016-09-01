package poi.procesos;
import poi.procesos.ProcesoActualizacionLocales;
public class ProcesoMultiple {

	
	
	public void correrProcecoMultiple (boolean actualizacionLocales, boolean bajaPOIs,
			boolean agregarAccionesAUsuarios) throws Exception{
	
			if (actualizacionLocales){
				ProcesoActualizacionLocales proceso1 =new ProcesoActualizacionLocales();
				proceso1.correrProceso();
			}
			if(bajaPOIs){
			ProcesoDarDeBajaPois proceso2 =new ProcesoDarDeBajaPois();
			proceso2.correrProceso();
			}
			if (agregarAccionesAUsuarios){
			ProcesoAgregarAccionesAUsuarios proceso3 =new ProcesoAgregarAccionesAUsuarios();
			proceso3.correrProceso(null, false, false, false);
			//TODO hacer esta parte mas prolija 
			}
		
	}
	
}
