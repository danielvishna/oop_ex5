package filesprocessing.order;
import filesprocessing.Utils;

import java.io.File;
import java.util.List;

/**
 * create structure of sorted files by given way to compere to files
 */
public class FileOrder {
	static final int SIZE_WITH_REVERS = 1;
	static final int FIRST_PARM = 0;

	/**
	 * merge to part of array to one sort part
	 * @param arr : array of Files
	 * @param leftBound the left bound of the unsort part we work on now
	 * @param runInd the run index of the merge say where to start look right and left
	 * @param rightBound the right bound of the unsort part we work on now
	 * @param compere the IComper object the get the order between the 2 files
	 * @param parameters the parameters of the commend the told as how to sort the files
	 * @throws OrderWarning : warning if there is problem in
	 */
	private void merge(File[] arr, int leftBound, int runInd, int rightBound, ICompere compere,
					   List<String> parameters) throws OrderWarning {
		int leftArrInd = runInd - leftBound + 1;
		int rightArrInd = rightBound - runInd;

		File[] leftArr = new File[leftArrInd];
		File[] rightArr = new File[rightArrInd];


		if (leftArrInd >= 0) System.arraycopy(arr, leftBound, leftArr, 0, leftArrInd);
		for (int j = 0; j < rightArrInd; j++)
			rightArr[j] = arr[runInd + 1 + j];


		String isRevers;
		boolean result = false;
		int i = 0, j = 0;
		if (parameters.size() == SIZE_WITH_REVERS) {
			isRevers = parameters.get(FIRST_PARM);
			if (isRevers.equals("REVERSE")) {
				result = true;
			}
		}

		try {
			int k = leftBound;
			while (i < leftArrInd && j < rightArrInd) {
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

			while (j < rightArrInd) {
				arr[k] = rightArr[j];
				j++;
				k++;
			}

			while (i < leftArrInd) {
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
