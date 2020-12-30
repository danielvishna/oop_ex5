package filesprocessing.filter;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Filter all the files that are hidden or unhidden
 */
public class HiddenFilter implements IFilter<String>{
	static final int BAD_BIG_SIZE_PARM = 2;
	static final int BAD_SMALL_SIZE_PARM = 1;
	static final int SIZE_PARM_WITH_NOT = 2;
	static final int FIRST_PARM = 0;
	static final int SEC_PARM = 1;
	@Override
	public List <File> filterFiles(File dir, List<String> parameters) throws FilterWarning {
		if(parameters.size() < BAD_SMALL_SIZE_PARM || parameters.size() > BAD_BIG_SIZE_PARM){
			throw new FilterWarning();
		}
		String isNot;
		File [] files = dir.listFiles();
		String nameFilter = parameters.get(FIRST_PARM);
		if (!nameFilter.equals("YES") && !nameFilter.equals("NO")){
			throw new FilterWarning();
		}
		LinkedList<File> fileLinkedList = new LinkedList<>();
		if (parameters.size() == SIZE_PARM_WITH_NOT) {
			isNot = parameters.get(SEC_PARM);
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
