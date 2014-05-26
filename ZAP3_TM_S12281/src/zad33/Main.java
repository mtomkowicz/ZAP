/**
 *
 *  @author Tomkowicz Małgorzata S12281
 *
 */

package zad33;

public class Main {

	public static void main(String[] args) {

		int tnum = 20; // liczba wątków
		int max = 300000; // liczba powtórzeń pętli w run()
		long startTime;
		long endTime;

		Licznik l = new Licznik();

		Thread[] threads = new Thread[tnum];
		Thread[] threads_rwLocks = new Thread[tnum];
		Thread[] threads_rLocks = new Thread[tnum];

		// Test 1
		startTime = System.currentTimeMillis();

		TestStandardowaSynchronizacja(tnum, max, threads, l);

		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Standardowa synchronizacja             		- czas: " + endTime 
				+ " ms"
				+ ", licznik = " + l.getLicznik());

		// Test - Synchronizacja typu read/write locks
		startTime = System.currentTimeMillis();

		TestWithRWLocks(tnum, max, threads_rwLocks, l);

		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Synchronizacja typu read/write locks 		- czas: " + endTime
				+ " ms"
				+ ", licznik = " + l.getLicznik());

		// Test - Synchronizacja typu reentrant lock
		startTime = System.currentTimeMillis();

		TestWithReentrantLocks(tnum, max, threads_rLocks, l);

		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Synchronizacja typu reentrant lock   		- czas: " + endTime
				+ " ms"
				+ ", licznik = " + l.getLicznik());

	}

	private static void TestStandardowaSynchronizacja(int tnum, int max,
			Thread[] threads, Licznik l) {
		for (int i = 0; i < tnum; i++)
			threads[i] = new LicznikThread("SSW" + (i + 1), l, max);
		try {
			for (int i = 0; i < tnum; i++)
				threads[i].join();
		} catch (InterruptedException exc) {
			System.exit(1);
		}
	}

	private static void TestWithRWLocks(int tnum, int max, Thread[] threads,
			Licznik l) {
		for (int i = 0; i < tnum; i++)
			threads[i] = new LicznikThreadWithRWLocks("RWLW" + (i + 1), l, max);
		try {
			for (int i = 0; i < tnum; i++)
				threads[i].join();
		} catch (InterruptedException exc) {
			System.exit(1);
		}
	}

	private static void TestWithReentrantLocks(int tnum, int max,
			Thread[] threads, Licznik l) {
		for (int i = 0; i < tnum; i++)
			threads[i] = new LicznikWithReentrantLock("RLW" + (i + 1), l, max);
		try {
			for (int i = 0; i < tnum; i++)
				threads[i].join();
		} catch (InterruptedException exc) {
			System.exit(1);
		}
	}

}