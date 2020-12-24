package filesprocessing;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectoryProcessor {


	public static void main(String[] args) {
		File dir = new File(args[0]);
		int lineCount = 1;
		List<File> files = new LinkedList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(args[1]));
			String line = reader.readLine();// the filter
			while (line != null) {
				if(!line.equals("FILTER")){
					System.err.println("Invalid file");
				}
				lineCount ++;
				line = reader.readLine(); // the filter parm
				List<String> parms = Utils.getFilterParameters(line);
				try {
					IFilter<String> filter = FilterFactory.createFilter(parms.get(0));
					List<File> tmp = filter.filterFiles(dir, parms.subList(1, parms.size()));
					if (tmp != null){
						files.addAll(tmp);
					}



				}
				catch (Warning ex){
					System.err.printf("Warning in line %s%n", lineCount);
					if(dir.listFiles() == null) {
						for (File f : dir.listFiles()) {
							if (f.isFile()) {
								files.add(f);
							}
						}
					}
					//todo add next the hendel of all
				}
				// read next line
				lineCount ++;
				line = reader.readLine(); // the Order line
				lineCount ++;
				line = reader.readLine(); // the order parmeters
				//TODO add here the part of the order
				lineCount ++;
				line = reader.readLine(); // The filter line
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(File f:files){
			System.out.println(f.getName());
		}

	}
}
