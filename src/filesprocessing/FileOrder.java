package filesprocessing;

import java.io.File;
import java.util.List;

public class FileOrder {
	/**
	 * merge to part of array to one sort part
	 * @param arr : array of Files
	 * @param l
	 * @param m
	 * @param r
	 * @param compere
	 * @param parameters
	 * @throws Warning
	 */
	private void merge(File[] arr, int l, int m, int r, ICompere compere,  List<String> parameters)//todo
			throws Warning {
		int i1 = m - l + 1;
		int i2 = r - m;

		/* Create temp arrays */
		File[] L = new File[i1];
		File[] R = new File[i2];


		if (i1 >= 0) System.arraycopy(arr, l, L, 0, i1);
		for (int j = 0; j < i2; ++j)
			R[j] = arr[m + 1 + j];


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
			int k = l;
			while (i < i1 && j < i2) {
				if (result) {
					if (compere.compereFiles(L[i], R[j]) > 0) {
						arr[k] = L[i];
						i++;
					} else {
						arr[k] = R[j];
						j++;
					}
				}
				else {
					if (compere.compereFiles(L[i], R[j]) < 0) {
						arr[k] = L[i];
						i++;
					}
					else {
						arr[k] = R[j];
						j++;
					}
				}
				k++;
			}

			while (i < i1) {
				arr[k] = L[i];
				i++;
				k++;
			}

			while (j < i2) {
				arr[k] = R[j];
				j++;
				k++;
			}
		}
		catch (NullPointerException e){
			throw new Warning();
		}
	}

	void sort(File[] arr, int l, int r, ICompere compere, List<String> parameters, int lineCount)
	{
		if(compere == null){
			System.err.printf("Warning in line %s%n", lineCount);
			String line = "abs";
			List<String> parms = Utils.getFilterParameters(line);
			AbsCompere defoult = new AbsCompere();
			sort(arr, 0, arr.length - 1,defoult, parms.subList(1, parms.size()), lineCount);
		}
		else {
			try {
				if (l < r) {
					int m = (l + r) / 2;
					sort(arr, l, m, compere, parameters, lineCount);
					sort(arr, m + 1, r, compere, parameters, lineCount);
					merge(arr, l, m, r, compere, parameters);
				}
			}
			catch (Warning e) {
				System.err.printf("Warning in line %s%n", lineCount);
				String line = "abs";
				List<String> parms = Utils.getFilterParameters(line);
				AbsCompere compere1 = new AbsCompere();
				sort(arr, 0, arr.length - 1, compere1, parms.subList(1, parms.size()), lineCount);

			}
		}
	}
}
