package lv.webkursi.mtest.mvc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeworkNumberGenerator {
	public static void main(String[] args) {
		Random random = new Random("jurijs.fjodorovs@accenture.com".hashCode());
		List<Integer> list = new ArrayList<Integer>();
		while (list.size() < 3) {
			// random integer from [1,20]
			int current = random.nextInt(20)+1; 
			if (!list.contains(current)) {
				list.add(current);
			}
		}
		System.out.println("The possible homework numbers are " + list);
	}
}
