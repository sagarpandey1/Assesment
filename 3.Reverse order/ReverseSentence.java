package skyDance;

public class ReverseSentence {
	public static String reverseString(String sentence) {
		if (sentence.length() == 0 || sentence == null) {
			return "";
		}
		String[] wordList = sentence.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = wordList.length - 1; i >= 0; i--) {
			if (i == wordList.length - 1)
				wordList[i] = wordList[i].substring(0, 1).toUpperCase()
						+ wordList[i].substring(1, wordList[i].length() - 1);
			if (i == 0)
				wordList[i] = wordList[i].substring(0, 1).toLowerCase()
						+ wordList[i].substring(1, wordList[i].length());
			sb.append(wordList[i]).append(" ");
		}

		return sb.substring(0, sb.length() - 1) + ".".trim();
	}

	public static void main(String[] args) {
		String sentence = "The quick brown fox jumps over a very lazy dog.";
		System.out.println(reverseString(sentence));

	}
}