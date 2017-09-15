/**
 * 
 */
package br.edu.unisinos.main;

import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

/**
 * @author LCRO
 *
 */
public class AuxiliarParser {

	static boolean simplifique = false;
	static int contadorSimpl = 1;

	static void parser(String frase) {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		Annotation annotation = new Annotation(frase);
		pipeline.annotate(annotation);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

		for (CoreMap sentence : sentences) {
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				String word = token.get(CoreAnnotations.TextAnnotation.class);
				// String ne =
				// token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
				System.out.println(word + "/" + pos);

			}
		}

	}

	static int contaCaracteresEspeciais(String frase) {
		int total = frase.length();
		int contador = 0;

		for (int i = 0; i < total; i++) {

			char ch = frase.charAt(i);
			String x = String.valueOf(ch);
			if (x.equals(",")) {
				contador++;
			}

		}

		return contador;

	}

	static String[][] parserWORD(String frase) {

		// cria um objeto StanfordCorelNLP object a partir de um conjunto de
		// propriedades
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String[] array = frase.split(" ");

		String[][] novoarray = null;
		int tam = 0;
		boolean simplifica = false;
		int contador = contaCaracteresEspeciais(frase);
		if (contador == 0) {
			novoarray = new String[1][array.length];
		} else {
			tam = array.length + contador;
			novoarray = new String[1][tam];
			simplifica = true;
		}

		// cria uma anotação vazia com o frase de entrada
		Annotation annotation = new Annotation(frase);

		// executa todos os anotators desse texto
		pipeline.annotate(annotation);

		// Split - Divide uma sequencia de TOKENS em frases. Pode ser um
		// paragrafo, um texto - todas as frases do documento
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		int linha = 0;
		int coluna = 0;

		for (CoreMap sentence : sentences) {

			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

				// String pos =
				// token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				String word = token.get(CoreAnnotations.TextAnnotation.class);

				novoarray[linha][coluna] = word;
				coluna++;

			}
		}

		// ENTRADA DO MÉTODO SIMPLIFICA FRASE
		if (simplifica) {

			novoarray = simplificaFrase(novoarray, tam);

		}

		return novoarray;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static String[][] parserSIMPLIFICADOR(String frase) {

		// cria um objeto StanfordCorelNLP object a partir de um conjunto de
		// propriedades
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String[] array = frase.split(" ");

		String[][] novoarray = null;
		int tam = 0;
		boolean simplifica = false;
		int contador = contaCaracteresEspeciais(frase);
		if (contador == 0) {
			novoarray = new String[1][array.length];
		} else {
			tam = array.length + contador;
			novoarray = new String[1][tam];
			simplifica = true;
		}

		// cria uma anotação vazia com o frase de entrada
		Annotation annotation = new Annotation(frase);

		// executa todos os anotators desse texto
		pipeline.annotate(annotation);

		// Split - Divide uma sequencia de TOKENS em frases. Pode ser um
		// paragrafo, um texto - todas as frases do documento
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		int linha = 0;
		int coluna = 0;

		for (CoreMap sentence : sentences) {

			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

				// String pos =
				// token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				String word = token.get(CoreAnnotations.TextAnnotation.class);

				novoarray[linha][coluna] = word;
				coluna++;

			}
		}

		// ENTRADA DO MÉTODO SIMPLIFICA FRASE
		if (simplifica) {

			novoarray = simplificaFrase(novoarray, tam);

		}

		return novoarray;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	static String[][] parserPOS(String frase) {

		// cria um objeto StanfordCorelNLP object a partir de um conjunto de
		// propriedades
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String[] array = frase.split(" ");
		int tamanhoArray = 0;
		String[][] novoarray = null;
		int tam = 0;
		boolean simplifica = false;
		int contador = contaCaracteresEspeciais(frase);
		if (contador == 0) {
			novoarray = new String[1][array.length + 1];
			tamanhoArray = array.length + 1;

		} else {
			tam = array.length + contador;
			tamanhoArray = tam + 1;
			novoarray = new String[1][tamanhoArray];
			simplifica = true;
			simplifique = true;

		}

		// cria uma anotação vazia com o frase de entrada
		Annotation annotation = new Annotation(frase);

		// executa todos os anotators desse texto
		pipeline.annotate(annotation);

		// Split - Divide uma sequencia de TOKENS em frases. Pode ser um
		// paragrafo, um texto - todas as frases do documento
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		int linha = 0;
		int coluna = 0;

		for (CoreMap sentence : sentences) {

			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

				String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				// String word =
				// token.get(CoreAnnotations.TextAnnotation.class);

				novoarray[linha][coluna] = pos;
				if (coluna == (tamanhoArray - 2) && simplifica == false) {
					novoarray[0][coluna + 1] = "#";
				} else if (coluna == (tamanhoArray - 2) && simplifique) {
					novoarray[0][coluna + 1] = "#";
				}
				coluna++;

			}
		}

		// ENTRADA DO MÉTODO SIMPLIFICA FRASE
		if (simplifica) {

			novoarray = simplificaFrase(novoarray, tam);

		}

		return novoarray;
	}

	static String[] transformarArray(int tam, String[][] aux) {

		int tamanho = tam + 1;
		String[] newA = new String[tamanho];

		for (int i = 0; i < aux.length; i++) {

			for (int j = 0; j < aux[i].length; j++) {
				if ((j + 1) == tam) {
					newA[j + 1] = "#";
					newA[j] = aux[i][j];
					break;
				}
				newA[j] = aux[i][j];

			}

		}

		return newA;

	}

	static String[][] simplificaFrase(String[][] array, int tam) {

		String[][] spf = new String[1][tam];

		int marc1 = 0;
		int marc2 = 0;
		int marc3 = 0;
		int contador = 0;

		for (int linha = 0; linha < array.length; linha++) {

			for (int coluna = 0; coluna < array[linha].length; coluna++) {

				if (array[linha][coluna].equals(",") && marc1 == 0) {
					marc1 = coluna;
				} else if (array[linha][coluna].equals(",") && marc2 == 0 && marc1 != 0) {
					marc2 = coluna;
					marc3 = 3;
				} else if ((coluna > marc2) && (!array[linha][coluna].equals(",")) && marc3 == 3) {
					spf[0][marc1] = array[linha][coluna];
					marc1++;
					contador++;
				} else if ((marc1 == 0) && (!array[linha][coluna].equals(","))) {
					spf[linha][coluna] = array[linha][coluna];
					contador++;
				} else {
					spf[0][coluna] = " ";

				}
			}
		}

		String[][] newArray = new String[1][contador];
		for (int i = 0; i < spf.length; i++) {
			for (int j = 0; j < spf[i].length; j++) {

				if ((spf[0][j] != null) && (!spf[0][j].equals(" "))) {
					newArray[0][j] = spf[0][j];
				} else {
					break;
				}

			}
		}

		return newArray;

	}

	public static void main(String[] args) {

		// SITE DAS FRASES
		// https://www.thoughtco.com/what-is-appositive-grammar-1689128

	//	String frasex = "The color is blue";
		String frasex = "Friend, what we do in life, echoes in eternity";
	//	String frasex = "Brothers, what we do in life, echoes in eternity";
		
		
		String frase2 = "The hangman, a grey-haired convict in the white uniform of the prison, was waiting beside his machine";
		String frase3 = "Television was left on, a running tap, from morning till night";
		String frase4 = "The king, my brother, has been murdered";
		String frase1 = "We spotted Tom Hanks, the movie star, at the cafe yesterday";

		String[] fraseOrig = frasex.split(" ");

		// String[][] l = AuxiliarParser.parserWORD(frasex).clone();
		String[][] lx = null;

		// ARRAY PARA COMPARAR COM O ARRAY DE REGRAS
		String[][] pos = AuxiliarParser.parserPOS(frasex);
		int tamanho = pos[0].length;

		// Procura a regra correspondente comparando com o resultado da analise
		// inclusive se simplificada se for o caso.
		// negocio.comparaArraysBID(regrasB, Parsing.parserBid(frase[i]));

		if (AuxiliarParser.simplifique) {https://super.abril.com.br/cultura/inteligencia-artificial-esta-escrevendo-o-fim-de-game-of-thrones/
			lx = AuxiliarParser.parserWORD(frasex).clone();
		}

		Util u = new Util();
		System.out.println("Frase Original");
		String frOr = "";
		for (int i = 0; i < fraseOrig.length; i++) {
			frOr += fraseOrig[i] + " ";
		}
		System.out.println(frOr);

		System.out.println("Frase simplifica ");
	//	u.exibeBidmenssional(lx);
		System.out.println("Analise da Frase simplifica ");
		u.exibeBidmenssional(pos);

		// String [] arrayNovo =
		// AuxiliarParser.transformarFrasesSimplificadas(l,tamanho);

		// u.exibeArray(arrayNovo);

		// u.exibeBidmenssional(pos);

	}

}
