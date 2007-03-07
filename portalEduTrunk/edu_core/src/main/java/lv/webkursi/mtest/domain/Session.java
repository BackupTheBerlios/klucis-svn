package lv.webkursi.mtest.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Session {
	
	protected Date sessionOpened;
	protected Date sessionClosed;
	
	/**
	 * Maximum time allowed for this test (in minutes)
	 */
	protected int maxTime;
	
	protected List<Answer> answers;
	
	protected int rawGrade;
	
	protected int normalizedGrade;
	
	protected Assessment assessment;
	
	public Session(Assessment a) {
		this.assessment = a;
		
		
	}
	
	public void setAnswer(int i, String value) {
		Date now = new Date();
		long timespan = (now.getTime() - sessionOpened.getTime());
		if (sessionClosed != null || timespan > maxTime*60*1000) {
			if (sessionClosed == null) {
				close();
			}
			throw new RuntimeException("Session should be closed");
		}
		answers.get(i).setValue(value);
	}
	
	public void open() {
		if (sessionOpened == null) {
			sessionOpened = new Date();
		}
	}
	
	public void close() {
		if (sessionClosed == null) {
			sessionClosed = new Date();
		}
	}
	
	public static class QuestionRandomizer {
		private static Random rand = new Random();

		public static List<Integer> shuffleNumbers(Module m, int num) {
			List<Integer> result = new ArrayList<Integer>();
			int actualSize = m.getQuestions().size();
			if (num > actualSize) {
				throw new RuntimeException("Cannot get " + num + " items, module '" + m.getName() + "' only has " + actualSize);				
			}
			while (result.size() < num) {
				int next = rand.nextInt(actualSize);
				if (!result.contains(next)) {
					result.add(next);
				}
			}
			return result;
		}
	}
}
