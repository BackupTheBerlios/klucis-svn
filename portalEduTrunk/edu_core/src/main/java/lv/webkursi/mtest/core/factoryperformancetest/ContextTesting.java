package lv.webkursi.mtest.core.factoryperformancetest;

public abstract class ContextTesting {
	public long testingTemplate(long times, Object componentId) {
		long timer1 = System.currentTimeMillis();
		long count = 0L;
		for (long i = 0L; i < times; i++) {
			SampleComponent c = lookup(componentId);
			count += c.getName1().length();
			count += c.getName2().length();

		}
		long timer2 = System.currentTimeMillis();

		System.out.println("In " + times + " iterations for component '"
				+ componentId + "'");
		long msec = timer2 - timer1;
		System.out.println("Time in milliseconds: " + msec);
//		System.out.println("Read characters: " + count);
		System.out.println("Average time (seconds) to retrieve 10^6 items: " + 1000.0*msec/times);
		return msec;
	}

	public abstract SampleComponent lookup(Object comonentId);
}
