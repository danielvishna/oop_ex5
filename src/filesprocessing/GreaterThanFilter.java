package filesprocessing;

import java.io.File;
import java.util.List;

public class GreaterThanFilter implements IFilter<String> {

	@Override
	public File[] filterFiles(File dir, List<String> parameters) {

		return dir.listFiles((file, name) -> {
			boolean result;
			double size = Double.parseDouble(parameters.get(0));
			//TOdo check if the parameters contain only one var
			result = file.length() >= size && file.isFile();
			String isNot = "";
			if (parameters.size() == 2) {
				isNot = parameters.get(1);
				//todo resise exsepsion if not equal NOT
				if (isNot.equals("NOT")) {
					result = !result;
				}
			}

			return result;
		});
	}
}
