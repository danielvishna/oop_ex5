package filesprocessing;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectoryProcessor {
	public enum FileOperationMode {
		WRITABLE {File[] eval(File dir, String isNot,String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				result= file.canWrite() == "YES".equals(value1);
				if(isNot.equals("NOT")){
					result = !result;
				}
				return result;
			});

		}},
		EXECUTABLE {File[] eval(File dir, String isNot,String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				result= file.canExecute() == "YES".equals(value1);
				if(isNot.equals("NOT")){
					result = !(result);
				}
				return result;
			});

		}},
		HIDDEN {File[] eval(File dir, String isNot,String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				result= file.isHidden() == "YES".equals(value1);
				if(isNot.equals("NOT")){
					result = !result;
				}
				return result;
			});

		}},
		GREATER_THAN {File[] eval(File dir, String isNot, String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				double size = Double.parseDouble(value1);
				result= file.length() >= size;
				if(isNot.equals("NOT")){
					result = !result;
				}
				return result;
			});

		}},
		BETWEEN {File[] eval(File dir, String isNot, String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				double low = Double.parseDouble(value1);
				double greater = Double.parseDouble(value2);
				result= file.length() >= low && file.length() <= greater;
				if(isNot.equals("NOT")){
					result = !result;
				}
				return result;
			});

		}},
		SMALLER_THAN {File[] eval(File dir, String isNot, String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				double low = Double.parseDouble(value1);
				result= file.length() >= low;
				if(isNot.equals("NOT")){
					result = !result;
				}
				return result;
			});

		}},
		FILE {File[] eval(File dir, String isNot,String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				result= file.getName().equals(value1);
				if(isNot.equals("NOT")){
					result = !result;
				}
				return result;
			});

		}},
		CONTAINS {File[] eval(File dir, String isNot,String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				result= file.canWrite() == "YES".equals(value1);
				if(isNot.equals("NOT")){
					result = !result;
				}
				return result;
			});

		}},
		PREFIX {File[] eval(File dir, String isNot,String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				result= file.canWrite() == "YES".equals(value1);
				if(isNot.equals("NOT")){
					result = !result;
				}
				return result;
			});

		}},
		SUFFIX {File[] eval(File dir, String isNot,String value1, String value2){
			//apply a filter
			return dir.listFiles((file, name) -> {
				boolean result;
				result= file.canWrite() == "YES".equals(value1);
				if(isNot.equals("NOT")){
					result = !result;
				}
				return result;
			});

		}},
		;
		abstract File[] eval(File dir, String isNot,String value1, String value2);
//		abstract File[] evalFileSize(int value, String isNot, File dir);
//		abstract File[] evalFileSizeBet(int greater, int low, String isNot, File dir);
	}
	public enum FileOperationSize{


	}


	public static void main(String[] args) {
		String sourcedir = args[0];
		String commandFile = args[1];
		File dir = new File(sourcedir);
//		Reader inFile = null;
		LinkedList<String > lines = new LinkedList<>();
//		try {
//			inFile = new FileReader(commandFile);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}

//		try(InputStream input = new FileInputStream(commandFile)) {
//			int result;
//			while ((result = input.read()) != -1) {
//				lines.add(input);
//			}
//		} catch (IOException ioe) {
//			System.err.println("Couldn’t copy file");
//		} // No need to close streams! (AutoCloseable interface rocks!)
//		Reader fileCommend = new Reader(sourcedir);
		int lineCount = 1;
		File [] files = null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(commandFile));
			String line = reader.readLine();
			while (line != null) {
				if(!line.equals("FILTER")){
					System.err.println("Invalid file");
				}
				lineCount ++;
				line = reader.readLine();
				List<String> parms = Utils.getFilterParameters(line);
				IFilter<String> filter = FilterFactory.createFilter(parms.get(0));
				try {
					files = filter.filterFiles(dir, parms.subList(1, parms.size()));
				}
				catch (Warning ex){
					System.err.println(String.format("Warning in line %s", lineCount));
					files = dir.listFiles();
					//todo add next the hendel of all
				}
				// read next line
				line = reader.readLine();
				lineCount ++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String tmp = "greater_than#0.1#NOT";
		List<String> parms = Utils.getFilterParameters(tmp);
		IFilter<String> filter = FilterFactory.createFilter(parms.get(0));
		for(File f:files){
			System.out.println(f.getName());
		}

	}
}
