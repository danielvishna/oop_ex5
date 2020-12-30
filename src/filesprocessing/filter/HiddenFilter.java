package filesprocessing.filter;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class HiddenFilter implements IFilter<String>{
	@Override
	public List <File> filterFiles(File dir, List<String> parameters) throws FilterWarning {
		if(parameters.size() < 1 || parameters.size() > 2){
			throw new FilterWarning();
		}
		int size = 0;
		String isNot;
		File [] files = dir.listFiles();
		String nameFilter = parameters.get(0);
		if (!nameFilter.equals("YES") && !nameFilter.equals("NO")){
			throw new FilterWarning();
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
			}
			else {
				throw new FilterWarning();
			}
		}
		if (files == null)
			return fileLinkedList;
		if(nameFilter.equals("YES")){
			for(File f: files) {
				if (f.isFile() && f.isHidden()) {
					fileLinkedList.add(f);
				}
			}
		}
		else{
			for(File f: files) {
				if (f.isFile() && !f.isHidden()) {
					fileLinkedList.add(f);
				}
			}
		}
		return fileLinkedList;
	}
}