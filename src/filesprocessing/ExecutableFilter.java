package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ExecutableFilter implements IFilter<String>{
	@Override
	public List<File> filterFiles(File dir, List<String> parameters) throws Warning {
		String isNot;
		boolean result = false;
		File [] files = dir.listFiles();
		if(parameters.size() > 2 || parameters.size() == 0){
			throw  new Warning();
		}
		String nameFilter = parameters.get(0);
		if (!nameFilter.equals("YES") && !nameFilter.equals("NO")){
			throw new Warning();
		}
		LinkedList<File> fileLinkedList = new LinkedList<>();
		if (parameters.size() == 2) {
			isNot = parameters.get(1);
			if (isNot.equals("NOT")) {
				if(nameFilter.equals("YES")){
					nameFilter = "NO";
				}
				else {
					nameFilter = "YES";
				}
//				result = true;
			}
			else {
				throw new Warning();
			}
		}
		if (files == null)
			return fileLinkedList;
		if(nameFilter.equals("YES")){
			for(File f: files) {
				if (f.isFile() && f.canExecute()) {
					fileLinkedList.add(f);
				}
			}
		}
		else{
			for(File f: files) {
				if (f.isFile() && !f.canExecute()) {
					fileLinkedList.add(f);
				}
			}
		}
//		for(File f: files) {
//			if(nameFilter.equals("YES")){
//				if(f.isFile() && !f.canExecute() && result) {
//					fileLinkedList.add(f);
//				}
//				else if (f.isFile() && !result && f.canExecute()) {
//					fileLinkedList.add(f);
//				}
//			}
//			else {
//				if(f.isFile() && f.canExecute() && result) {
//					fileLinkedList.add(f);
//				}
//				else if (f.isFile() && !result && !f.canExecute()) {
//					fileLinkedList.add(f);
//				}
//			}
//
//
//		}
		return fileLinkedList;
	}
}
