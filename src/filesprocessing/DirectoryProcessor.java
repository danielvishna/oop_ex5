package filesprocessing;

import filesprocessing.order.*;
import filesprocessing.filter.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The main class here I get the dir and I filter sort the files
 */
public class DirectoryProcessor {
	static final String CONST_FILTER = "FILTER";
	static final String CONST_ORDER = "ORDER";
	static final int INX_DIR = 0;
	static final int INX_COMMAND_FILE = 1;
	static final int NUM_OF_ARGS = 2;
	static final int SIZE_WITH_REVERS = 2;
	static final int FIRST_PARM = 2;
	static final String DEFAULT_ORDER = "abs";

	/**
	 * print list of files in given order
	 * @param command : commends of how to print the given list of files
	 * @param files : the list of the files to print
	 */
	private static void printInOrder(Command command, List<File> files) {
		String orderLine = command.getOrder();

		if (orderLine == null) {
			orderLine = DEFAULT_ORDER;
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
	}

	/**
	 * proses the list of the files in give dir and filter them by given commends
	 * @param commands : commends of how to filter the list of the files
	 * @param dir : the directory with the files to filter
	 */
	private static void prosesListFile(Queue<Command> commands, File dir) {
		while (commands.size() > 0) {
			Command command = commands.peek();
			List<String> filterParameters = Utils.getFilterParameters(command.getFilter());
			IFilter<String> Ifilter = FilterFactory.createFilter(filterParameters.get(0));
			Filter filter = new Filter(Ifilter);
			List<File> files = filter.filterFiles(dir, filterParameters.subList(1, filterParameters.size()),
												  command.getLineFilter());
			printInOrder(command, files);
			commands.remove();
		}
	}

	/**
	 * proses the commend in the commend file and throw exciton if there is problem in the file
	 * @param reader - the reader for the file
	 * @param commands - data stacker that contain all the commends in the file
	 * @param line - the courant line we start to work on
	 * @throws IOException - expiation of problem in the BufferedReader
	 * @throws Errors - problem in the file structure of the commends
	 */
	private static void prosesCommands(BufferedReader reader, Queue<Command> commands, String line)
			throws IOException, Errors {
		int lineCount = 1;
		Command currentCommend = new Command();
		while (line != null) {
			if (!line.equals(CONST_FILTER)) {
				throw new SubsectionError();
			}
			lineCount++;
			line = reader.readLine(); // the filter parm
			currentCommend.setFilter(line);
			currentCommend.setLineFilter(lineCount);

			lineCount++;
			line = reader.readLine(); // the Order line
			if (line == null) {
				String msg = String.format("ERROR: %s sub-section missing", CONST_ORDER);
				throw new ErrorMissSubsection(msg);
			}
			if (!line.equals(CONST_ORDER)) {
				throw new SubsectionError();
			}
			lineCount++;
			line = reader.readLine(); // the order parameters / FILTER
			if (line != null && !line.equals(CONST_FILTER)) {
				currentCommend.setOrder(line);
				currentCommend.setLineOrder(lineCount);
				line = reader.readLine();
				lineCount++;
			} else {
				currentCommend.setOrder(null);
				currentCommend.setLineOrder(-1);
			}
			Command tmpCommend = new Command(currentCommend);
			commands.add(tmpCommend);
		}
	}

	public static void main(String[] args) {
		try {
			if (args.length != NUM_OF_ARGS) // not sadistic args
			{
				throw new UsageError();
			}
			Queue<Command> commands = new LinkedList<>();
			File dir = new File(args[INX_DIR]);
			BufferedReader reader = new BufferedReader(new FileReader(args[INX_COMMAND_FILE]));
			String line = reader.readLine();// the filter
			if (line == null) {
				System.out.print("");
			} else {
				prosesCommands(reader, commands, line);
				reader.close();
				prosesListFile(commands, dir);
			}

		} catch (Errors e) {
			System.err.println(e.getMessage());

		} catch (IOException e) {
			System.err.println("ERROR: can't open this file");

		}


	}
}
