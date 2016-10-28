package poi.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ReportByTerminalAndDateTransformer {
	
	
	private List<HashMap<String, Object>> nativeResults;
	private List<HashMap<String, Object>> results = new ArrayList<>();
	private List<HashMap<String, String>> terminals = new ArrayList<>();

	public ReportByTerminalAndDateTransformer(List<HashMap<String, Object>> results) {
		this.setNativeResults(results);
		this.createResults();
	}

	private void createResults() {
		//debo transformar la lista nativa a una lista con el siguiente formato
		//Lista de hashMap
			// key: "Fecha" value: "fecha"
			// key: "Terminales" value: Lista de hashMap
											//Key: "Terminal" value: "terminal"
											//key: "Cantidad" value: "cantidad"
		for (HashMap<String, Object> record : this.getNativeResults()){
			String terminal = (String) record.get("Terminal");
			String fecha = (String) record.get("Fecha");
			String cantidad = (String) record.get("Cantidad");

			this.addTerminalResult(terminal);
			this.addTerminalAndDateResult(fecha,terminal,cantidad);
		}
		
	}

	@SuppressWarnings("unchecked")
	private void addTerminalAndDateResult(String fecha, String terminal, String cantidad) {
		HashMap<String, Object> dateMapper;
		List<HashMap<String, String>> terminales;
		HashMap<String, String> terminalMapper;
		dateMapper = this.getRelatedDateMapper(fecha);
		terminales = (List<HashMap<String, String>>) dateMapper.get("Terminales");
		terminalMapper = new HashMap<String,String>();
		terminalMapper.put("Terminal", terminal);
		terminalMapper.put("Cantidad", cantidad);
		terminales.add(terminalMapper);
	}

	private HashMap<String, Object> getRelatedDateMapper(String fecha) {
		//devuelvo el hash que contiene las llaves de fecha y terminales
		HashMap<String, Object> hash;
		boolean includes = this.getResults().stream()
				.anyMatch( dateMap -> dateMap.get("Fecha").equals(fecha));
		if (!includes){
			hash = new	HashMap<String, Object>();
			hash.put("Fecha", fecha);
			hash.put("Terminales", new ArrayList<HashMap<String, String>>());
			this.addDateMapper(hash);
		}else{
			hash = this.getResults().stream()
				.filter( dateMap -> dateMap.get("Fecha").equals(fecha))
				.collect(Collectors.toList())
				.get(0);
		}
		return hash;
	}

	private void addDateMapper(HashMap<String, Object> hash) {
		this.results.add(hash);		
	}

	private void addTerminalResult(String terminal) {
		boolean includes = this.getTerminals()
			.stream()
			.anyMatch( hash -> hash.get("Terminal").equals(terminal) );
		if (!includes){
			HashMap<String, String> newHash = new HashMap<>();
			newHash.put("Terminal", terminal);
			this.terminals.add(newHash);
		}
	}

	public List<HashMap<String, Object>> getResults() {
		return results;
	}

	public void setResults(List<HashMap<String, Object>> results) {
		this.results = results;
	}

	public List<HashMap<String, String>> getTerminals() {
		return terminals;
	}

	public void setTerminals(List<HashMap<String, String>> terminals) {
		this.terminals = terminals;
	}

	public List<HashMap<String, Object>> getNativeResults() {
		return nativeResults;
	}

	public void setNativeResults(List<HashMap<String, Object>> nativeResults) {
		this.nativeResults = nativeResults;
	}
}
