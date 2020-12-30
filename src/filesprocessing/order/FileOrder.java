package filesprocessing.order;
import filesprocessing.Utils;

import java.io.File;
import java.util.List;

public class FileOrder {
	/**
	 * merge to part of array to one sort part
	 * @param arr : array of Files
	 * @param leftInd
	 * @param middleInd
	 * @param rightInd
	 * @param compere
	 * @param parameters
	 * @throws OrderWarning : worning if there is problem in
	 */
	private void merge(File[] arr, int leftInd, int middleInd, int rightInd, ICompere compere,
					   List<String> parameters) throws OrderWarning {
		int i1 = middleInd - leftInd + 1;
		int i2 = rightInd - middleInd;

		File[] leftArr = new File[i1];
		File[] rightArr = new File[i2];


		if (i1 >= 0) System.arraycopy(arr, leftInd, leftArr, 0, i1);
		for (int j = 0; j < i2; ++j)
			rightArr[j] = arr[middleInd + 1 + j];


		String isRevers;
		boolean result = false;
		int i = 0, j = 0;
		if (parameters.size() == 1) {
			isRevers = parameters.get(0);
			if (isRevers.equals("REVERSE")) {
				result = true;
			}
		}

		try {
			int k = leftInd;
			while (i < i1 && j < i2) {
				if (result) {
					if (compere.compereFiles(leftArr[i], rightArr[j]) > 0) {
						arr[k] = leftArr[i];
						i++;
					} else {
						arr[k] = rightArr[j];
						j++;
					}
				}
				else {
					if (compere.compereFiles(leftArr[i], rightArr[j]) < 0) {
						arr[k] = leftArr[i];
						i++;
					}
					else {
						arr[k] = rightArr[j];
						j++;
					}
				}
				k++;
			}

			while (j < i2) {
				arr[k] = rightArr[j];
				j++;
				k++;
			}

			while (i < i1) {
				arr[k] = leftArr[i];
				i++;
				k++;
			}


		}
		catch (NullPointerException e){
			throw new OrderWarning();
		}
	}

	public void sort(File[] arr, int leftInd, int rightInd, ICompere compere, List<String> parameters,
					 int lineCount)
	{
		if(compere == null){
			System.err.printf("Warning in line %s%n", lineCount);
			String line = "abs";
			List<String> params = Utils.getFilterParameters(line);
			AbsCompere defaults = new AbsCompere();
			sort(arr, 0, arr.length - 1, defaults, params.subList(1, params.size()), lineCount);
		}
		else {
			try {
				if (leftInd < rightInd) {
					int middleInd = (leftInd + rightInd) / 2;
					sort(arr, leftInd, middleInd, compere, parameters, lineCount);
					sort(arr, middleInd + 1, rightInd, compere, parameters, lineCount);
					merge(arr, leftInd, middleInd, rightInd, compere, parameters);
				}
			}
			catch (OrderWarning e) {
				System.err.printf("Warning in line %s%n", lineCount);
				String line = "abs";
				List<String> params = Utils.getFilterParameters(line);
				AbsCompere compere1 = new AbsCompere();
				sort(arr, 0, arr.length - 1, compere1, params.subList(1, params.size()), lineCount);

			}
		}
	}
}
