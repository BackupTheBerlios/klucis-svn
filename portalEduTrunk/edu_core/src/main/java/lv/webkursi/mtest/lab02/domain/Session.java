package lv.webkursi.mtest.lab02.domain;

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

	public void init(Assessment a) {
		this.assessment = a;
		for (Module m : a.getWeights().keySet()) {
			int total = m.getQuestions().size();
			int weight = a.getWeights().get(m);
			List<Integer> numbers = QuestionRandomizer.shuffleNumbers(total,
					weight);
			for (Integer num : numbers) {
				Answer answer = new Answer();
				answer.setQuestion(m.getQuestions().get(num));
				answers.add(answer);
			}
		}
	}

	public void setAnswer(int i, String value) {
		Date now = new Date();
		long timespan = (now.getTime() - sessionOpened.getTime());
		if (sessionClosed != null || timespan > maxTime * 60 * 1000) {
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

		public static List<Integer> shuffleNumbers(int total, int num) {
			List<Integer> result = new ArrayList<Integer>();
			if (num > total) {
				throw new RuntimeException("Cannot get " + num
						+ " items out of " + total);
			}
			while (result.size() < num) {
				int next = rand.nextInt(total);
				if (!result.contains(next)) {
					result.add(next);
				}
			}
			return result;
		}
	}
}
