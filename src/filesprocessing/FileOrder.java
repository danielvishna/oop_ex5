package filesprocessing;

import java.io.File;
import java.util.List;

public class FileOrder {
	void merge(File[] arr, int l, int m, int r, ICompere compere,  List<String> parameters)
	{
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		File[] L = new File[n1];
		File[] R = new File[n2];

		/*Copy data to temp arrays*/
		if (n1 >= 0) System.arraycopy(arr, l, L, 0, n1);
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		String isRevers;
		boolean result = false;
		int i = 0, j = 0;
		if (parameters.size() == 2) {
			isRevers = parameters.get(1);
			//todo resise exsepsion if not equal NOT
			if (isRevers.equals("NOT")) {
				result = true;
			}
		}
		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if(result) {
				if (compere.compereFiles(L[i], R[j]) > 0) {
					arr[k] = L[i];
					i++;
				} else {
					arr[k] = R[j];
					j++;
				}
			}
			else{
				if (compere.compereFiles(L[i], R[j]) < 0) {
					arr[k] = L[i];
					i++;
				} else {
					arr[k] = R[j];
					j++;
				}
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	void sort(File[] arr, int l, int r, ICompere compere, List<String> parameters)
	{
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			sort(arr, l, m, compere, parameters);
			sort(arr, m + 1, r, compere, parameters);

			// Merge the sorted halves
			merge(arr, l, m, r, compere, parameters);
		}
	}
}
