package poi.procesos;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager implements Runnable {
	List<AbstractDataProcess> processes = new ArrayList<AbstractDataProcess>();

	public List<AbstractDataProcess> getProcesses() {
		return processes;
	}

	public void addProcess(AbstractDataProcess aProcess) {
		this.getProcesses().add(aProcess);
	}

	@Override
	public void run() {
		//ejecuto cada uno de mis sub procesos
		for (AbstractDataProcess aProcess : this.getProcesses()) {
			Thread thread = new Thread(aProcess);
			thread.start();
		}
		
	}
}
