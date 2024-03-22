
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunnerWithInterface {
	public void runModel(IMarkovModel markov, String text, int size, int seed) {
		markov.setTraining(text);
		markov.setRandom(seed);
		System.out.println("running with " + markov);
		for (int k = 0; k < 3; k++) {
			String st = markov.getRandomText(size);
			printOut(st);
		}
	}

	public void runMarkov() {
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 500;
		int seed = 531;
		// MarkovZero mz = new MarkovZero();
		// int seed = 200;
		// runModel(mz, st, size, seed);

		EfficientMarkovModel mOne = new EfficientMarkovModel(5);
		runModel(mOne, st, size, seed);

		// MarkovModel mThree = new MarkovModel(3);
		// runModel(mThree, st, size, seed);

		// MarkovModel mFour = new MarkovModel(4);
		// runModel(mFour, st, size, seed);

	}

	private void printOut(String s) {
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for (int k = 0; k < words.length; k++) {
			System.out.print(words[k] + " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public void testHashMap() {
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		EfficientMarkovModel emm = new EfficientMarkovModel(2);
		emm.setRandom(615);
		emm.setTraining(st);
		emm.buildMap();
		// emm.getRandomText(50);
	}

	public void compareMethods() {
		FileResource fr = new FileResource("data/romeo.txt");
		String st = fr.asString();
		MarkovModel mm = new MarkovModel(5);
		EfficientMarkovModel emm = new EfficientMarkovModel(5);
		// mm.setRandom(615);
		// mm.setTraining(st);
		emm.setRandom(615);
		emm.setTraining(st);
		long t1 = System.nanoTime();
		String s1 = mm.getRandomText(1000);
		System.out.println(s1);
		long t2 = System.nanoTime();
		String s2 = emm.getRandomText(1000);
		System.out.println(s2);
		long t3 = System.nanoTime();
		System.out.println("Without hashmap: " + (t2 - t1));
		System.out.println("Without hashmap: " + (t3 - t2));
	}

	public static void main(String[] args) {
		MarkovRunnerWithInterface mri = new MarkovRunnerWithInterface();
		mri.runMarkov();
		// mri.testHashMap();
		// mri.compareMethods();
	}

}
