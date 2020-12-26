package filesprocessing;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectoryProcessor {
	//todo check the case of #NOT/#REVERS

	public static void main(String[] args) {
		try {
			if (args.length != 2) // not sadisite args
			{
				throw new UsageError();
			}


			File dir = new File(args[0]);
			int lineCount = 1;
			List<File> files = new LinkedList<>();
			List<File> filesOut = new LinkedList<>();
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(args[1]));
				String line = reader.readLine();// the filter
				while (line != null) {
					if (!line.equals("FILTER")) {
						throw new SubsectionError();// todo check if this is the curect excipen
					}
					lineCount++;
					line = reader.readLine(); // the filter parm
					List<String> parms = Utils.getFilterParameters(line);
					//					try {
					IFilter<String> Ifilter = FilterFactory.createFilter(parms.get(0));
					Filter filter = new Filter(Ifilter);
					List<File> tmp = filter.filterFiles(dir, parms.subList(1, parms.size()), lineCount);
					if (tmp != null) {
						files.addAll(tmp);
					}


					//					} catch (Warning ex) {
					//						System.err.printf("Warning in line %s%n", lineCount);
					//						if (dir.listFiles() != null) {
					//							for (File f : dir.listFiles()) {
					//								if (f.isFile()) {
					//									files.add(f);
					//								}
					//							}
					//						}
					//					}
					// read next line
					lineCount++;
					line = reader.readLine(); // the Order line
					if (!line.equals("ORDER")) {
						throw new SubsectionError();
					}
					lineCount++;
					line = reader.readLine(); // the order parmeters / FILTER
					if (line != null && !line.equals("FILTER")) {
						parms = Utils.getFilterParameters(line);

						FileOrder order = new FileOrder();
						ICompere compere = OrderFactory.createOrder(parms.get(0));
						File[] arrayFiles1 = files.toArray(new File[0]);
						order.sort(arrayFiles1, 0, arrayFiles1.length - 1, compere,
								   parms.subList(1, parms.size()), lineCount);
						for (File f : arrayFiles1) {
							System.out.println(f.getName());
						}


						if (line != null) {
							filesOut.addAll(files);
							files = new LinkedList<File>();
							lineCount++;
							line = reader.readLine(); // The filter line
						}
					}
					else {
						parms = Utils.getFilterParameters("abs");

						FileOrder order = new FileOrder();
						ICompere compere = OrderFactory.createOrder(parms.get(0));
						File[] arrayFiles1 = files.toArray(new File[0]);
						order.sort(arrayFiles1, 0, arrayFiles1.length - 1, compere,
								   parms.subList(1, parms.size()), lineCount);
						for (File f : arrayFiles1) {
							System.out.println(f.getName());
						}
					}
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (File f : files) {
				System.out.println(f.getName());
			}
		} catch (Errors e) {
			System.err.println(e.getMessage());

		}
	}
}
