import edu.duke.*;

/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

public class MarkovRunner {
	public void runMarkovZero() {
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setRandom(1024);
		markov.setTraining(st);
		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovOne() {
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setRandom(42);
		markov.setTraining(st);
		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovFour() {
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovModel markov = new MarkovModel(8);
		markov.setRandom(365);
		markov.setTraining(st);
		// MarkovZero markov = new MarkovZero();
		// markov.setRandom(88);
		// markov.setTraining(st);
		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}
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

	public static void main(String[] args) {
		MarkovRunner mr = new MarkovRunner();
		// mr.runMarkovFour();
		String s1 = "abcde";
		s1 = s1.substring(3);
		System.out.println(s1);
		mr.runMarkovZero();
	}

}
