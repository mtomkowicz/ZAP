/**
 *
 *  @author Tomkowicz Małgorzata S12281
 *
 */

package zad33;

public class Main {

	public static void main(String[] args) {

		int tnum = 20; // liczba wątków
		int max = 40000; // liczba powtórzeń pętli w run()
		long startTime;
		long endTime;

		Balance b = new Balance();

		Thread[] threads = new Thread[tnum];
		Thread[] threads_rwLocks = new Thread[tnum];
		Thread[] threads_rLocks = new Thread[tnum];

		// Test 1
		startTime = System.currentTimeMillis();

		TestStandardowaSynchronizacja(tnum, max, threads, b);

		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Standardowa synchronizacja             		- czas: " + endTime 
				+ " ms"
				+ ", wynik = " + b.getBalance());

		// Test - Synchronizacja typu read/write locks
		startTime = System.currentTimeMillis();

		TestWithRWLocks(tnum, max, threads_rwLocks, b);

		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Synchronizacja typu read/write locks 		- czas: " + endTime
				+ " ms"
				+ ", wynik = " + b.getBalance());

		// Test - Synchronizacja typu reentrant lock
		startTime = System .currentTimeMillis();

		TestWithReentrantLocks(tnum, max, threads_rLocks, b);

		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Synchronizacja typu reentrant lock   		- czas: " + endTime
				+ " ms"
				+ ", wynik = " + b.getBalance());

		Thread.yield();
	}

	private static void TestStandardowaSynchronizacja(int tnum, int max,
			Thread[] threads, Balance b) {
		for (int i = 0; i < tnum; i++)
			threads[i] = new BalanceThread("SSW" + (i + 1), b, max);
		try {
			for (int i = 0; i < tnum; i++)
				threads[i].join();
		} catch (InterruptedException exc) {
			System.exit(1);
		}
	}

	private static void TestWithRWLocks(int tnum, int max, Thread[] threads,
			Balance l) {
		for (int i = 0; i < tnum; i++)
			threads[i] = new BalanceThreadWithRWLocks("RWLW" + (i + 1), l, max);
		try {
			for (int i = 0; i < tnum; i++)
				threads[i].join();
		} catch (InterruptedException exc) {
			System.exit(1);
		}
	}

	private static void TestWithReentrantLocks(int tnum, int max,
			Thread[] threads, Balance l) {
		for (int i = 0; i < tnum; i++)
			threads[i] = new BalanceWithReentrantLock("RLW" + (i + 1), l, max);
		try {
			for (int i = 0; i < tnum; i++)
				threads[i].join();
		} catch (InterruptedException exc) {
			System.exit(1);
		}
	}

}