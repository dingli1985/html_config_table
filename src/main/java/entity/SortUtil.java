package entity;

public class SortUtil {

	public static void main(String args[]) {
		int a[] = { 11, 2, 5, 82, 7, 0, 4, 89, 72, 42, 16, 34, 58, 23 };
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}

		for (int x : a) {
			System.out.println(x);
		}
	}

}
