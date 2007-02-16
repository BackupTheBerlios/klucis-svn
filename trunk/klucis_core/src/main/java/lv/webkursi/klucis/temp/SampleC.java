package lv.webkursi.klucis.temp;

public class SampleC extends SampleA implements ISampleB {
	
	/**
	 * Contract: Must return 0 (same as its superclass)
	 * @return
	 */
	public int e() {
		return 0;
	}
	
	/**
	 * Contract: Must return 2 (different from its superclass)
	 * @return
	 */
	public int f() {
		return 2;
	}
	
	/**
	 * Contract: Must return something divisible by 12 
	 * (i.e. divisible both by 4 AND by 6)
	 */
	public int g() {
		return 12;
	}

}
