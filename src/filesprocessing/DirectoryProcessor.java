package filesprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectoryProcessor {
	static final String const_filter = "FILTER";
	static final String const_order = "ORDER";
	//todo check the case of #NOT/#REVERS

	public static void main(String[] args) {
		try {
			if (args.length != 2) // not sadisite args
			{
				throw new UsageError();
			}
			Queue<Command> commands = new LinkedList<>();
			File dir = new File(args[0]);
			int lineCount = 1;
			BufferedReader reader;
			Command curntComend = new Command();
			reader = new BufferedReader(new FileReader(args[1]));
			String line = reader.readLine();// the filter
			if (line == null) {
				System.out.print("");
				return;
			}
			while (line != null) {
				if (!line.equals(const_filter)) {
					throw new SubsectionError();// todo check if this is the curect excipen
				}
				lineCount++;
				line = reader.readLine(); // the filter parm
				curntComend.setFilter(line);
				curntComend.setLineFilter(lineCount);

				lineCount++;
				line = reader.readLine(); // the Order line
				if (line == null) {
					String msg = String.format("ERROR: %s sub-section missing", const_order);
					throw new ErrorMissSubsection(msg);
				}
				if (!line.equals(const_order)) {
					throw new SubsectionError();
				}
				lineCount++;
				line = reader.readLine(); // the order parmeters / FILTER
				if (line != null && !line.equals("FILTER")) {
					curntComend.setOrder(line);
					curntComend.setLineOrder(lineCount);
					line = reader.readLine();
					lineCount++;
				} else {
					curntComend.setOrder(null);
					curntComend.setLineOrder(-1);
				}
				Command tmp_comend = new Command(curntComend);
				commands.add(tmp_comend);
			}
			reader.close();
			while (commands.size() > 0){
				Command command = commands.peek();
				List<String> filterParameters = Utils.getFilterParameters(command.getFilter());
				IFilter<String> Ifilter = FilterFactory.createFilter(filterParameters.get(0));
				Filter filter = new Filter(Ifilter);
				List<File> files = filter
						.filterFiles(dir, filterParameters.subList(1, filterParameters.size()),
									 command.getLineFilter());
				String orderLine = command.getOrder();
				if (orderLine == null) {
					orderLine = "abs";
				}
				List<String> paramsOrder = Utils.getFilterParameters(orderLine);
				FileOrder order = new FileOrder();
				ICompere compere = OrderFactory.createOrder(paramsOrder.get(0));
				File[] arrayFiles1 = files.toArray(new File[0]);
				order.sort(arrayFiles1, 0, arrayFiles1.length - 1, compere,
						   paramsOrder.subList(1, paramsOrder.size()), command.getLineOrder());
				for (File f : arrayFiles1) {
					System.out.println(f.getName());
				}
				commands.remove();


			}
		} catch (Errors e) {
			System.err.println(e.getMessage());

		} catch (IOException e) {
			System.err.println("ERROR: can't open this file");

		}


	}
}
