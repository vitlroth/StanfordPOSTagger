/**
 * 
 */
package br.edu.unisinos.main;

/**
 * @author LCRO
 *
 */
public class MaxentTagger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// MaxentTagger tagger = new
				// MaxentTagger("taggers/english-bidirectional-distsim.tagger");
				// MaxentTagger taggerSpan = new
				// MaxentTagger("taggers/spanish-distsim.tagger");
				// MaxentTagger taggerDeut = new
				// MaxentTagger("taggers/german-fast-caseless.tagger");

				// String sample2 = "Claudia Schiffer ist ein deutsches Modell";
				// String sample = "This is a sample sentence";
				// String sample1 = "Esta es una sentencia de la muestra";

				// set up grammar and options as appropriate
				/*
				 * LexicalizedParser lp = LexicalizedParser.loadModel(); String[] frase
				 * = { "It", "can", "can", "it", "." }; // Parser gets tag of second
				 * "can" wrong without help String[] tag3 = { "PRP", "MD", "VB", "PRP",
				 * "." }; List sentence = new ArrayList(); for (int i = 0; i <
				 * frase.length; i++) { sentence.add(new TaggedWord(frase[i], tag3[i]));
				 * } Tree parse = lp.parse(sentence); parse.pennPrint();
				 */

				// String tagged = tagger.tagString(sample);
				// String taggedSpan = taggerSpan.tagString(sample1);
				// String taggedDeut = taggerDeut.tagString(sample2);

				// System.out.println("Espanhol " + taggedSpan);
				// System.out.println("Alemão " + taggedDeut);
				// System.out.println(tagged);
		
		
		/**
		 * Importa frases de uma arquivo e armazena as saidas das frases tagged
		 * em outro arquivo.
		 */

		/*
		 * try { FileInputStream fstream = new FileInputStream("Input.txt");
		 * DataInputStream in = new DataInputStream(fstream); br = new
		 * BufferedReader(new InputStreamReader(in));
		 * 
		 * while ((sample = br.readLine()) != null) { // TAG the string String
		 * tg = tagger.tagString(sample); FileWriter q = new
		 * FileWriter("output.txt", true); BufferedWriter out = new
		 * BufferedWriter(q); out.write(tg); out.newLine(); out.close(); }
		 * 
		 * } catch (FileNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		
		
		

	}

}
