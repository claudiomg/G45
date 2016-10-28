package poi.dataImport;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.utilidades.Direccion;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Posicion;
import poi.utilidades.TimeRange;

public class LibraryImportService extends DataImportService {
	
	DisponibilidadHoraria lunes = new DisponibilidadHoraria(DayOfWeek.MONDAY);
	DisponibilidadHoraria martes = new DisponibilidadHoraria(DayOfWeek.TUESDAY);
	DisponibilidadHoraria miercoles = new DisponibilidadHoraria(DayOfWeek.WEDNESDAY);
	DisponibilidadHoraria jueves = new DisponibilidadHoraria (DayOfWeek.THURSDAY);	
	DisponibilidadHoraria viernes = new DisponibilidadHoraria (DayOfWeek.FRIDAY);
	DisponibilidadHoraria sabado = new DisponibilidadHoraria (DayOfWeek.SATURDAY);
	TimeRange rangoInferiorDeLaSemana = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
	TimeRange rangoSuperiorDeLaSemana = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
	TimeRange rangoDelSabado = new TimeRange (LocalTime.of(8, 30, 0),LocalTime.of(16, 0, 0));
	List<DisponibilidadHoraria> dias = Arrays.asList(lunes,martes,miercoles,jueves,viernes,sabado);

	public LibraryImportService() {
		lunes.agregarNuevoRango(rangoInferiorDeLaSemana);
		lunes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		martes.agregarNuevoRango(rangoInferiorDeLaSemana);
		martes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		miercoles.agregarNuevoRango(rangoInferiorDeLaSemana);
		miercoles.agregarNuevoRango(rangoSuperiorDeLaSemana);
		jueves.agregarNuevoRango(rangoSuperiorDeLaSemana);
		jueves.agregarNuevoRango(rangoInferiorDeLaSemana);
		viernes.agregarNuevoRango(rangoInferiorDeLaSemana);
		viernes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		sabado.agregarNuevoRango(rangoDelSabado);
	}
	
	@Override
	String getRadarSearchUri() {
		return "https://maps.googleapis.com/maps/api/place/radarsearch/json?" +
				"location=-34.659603,-58.468883&" +
				"radius=5000&" +
				"types=library&" +
				"key=" + this.keyGoogleApi();
	}

	@Override
	void processData(JSONObject responseJson) throws JSONException {
		try {
			JSONObject locationJson = responseJson.getJSONObject("geometry").getJSONObject("location");
			JSONObject poiJson = this.getPlaceData(responseJson.get("place_id").toString());
			JSONArray address = poiJson.getJSONArray("address_components");
			String name = poiJson.getString("name");
			
			Posicion posicion = new Posicion(locationJson);
			Direccion direccion = new Direccion(address);
	    	LocalComercial libreria = new LocalComercial(name, posicion, direccion, RadioCercania.LibreriaEscolar);
	    	this.setAditionalAttributes(libreria);
	    	this.persistPoi(libreria);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setAditionalAttributes(LocalComercial libreria) {
		Random rnd= new Random();
		for (DisponibilidadHoraria dia: dias){
			if (rnd.nextInt(10) % 2 == 0){
				libreria.addDisponibilidadDeAtencion(dia);
			}
		}
	}

}
