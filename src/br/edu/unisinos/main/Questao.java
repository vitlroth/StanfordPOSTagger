/**
 * 
 */
package br.edu.unisinos.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class Questao {

	static int linhaKey = 0;
	static int colunaKey = 0;
	static String index = "";
	static String word = "";
	public String index1 ="";
	public String word1 = "";



	static void exibeBidmenssional(String[][] array) {
		int linha;
		for (linha = 0; linha < array.length; linha++) {
			// System.out.printf(" %da. linha : ", (linha + 1));
			for (int coluna = 0; coluna < array[linha].length; coluna++) {
				System.out.print(" " + array[linha][coluna]);
			}
			System.out.printf("\n");

		}
	}



	static String[][] populaArrayAnalise(String pos, int tamanho) {
		String[][] arrayBid = new String[1][tamanho];
		for (int linha = 0; linha <= 0; linha++) {
			for (int coluna = 0; coluna < arrayBid.length; coluna++) {
				arrayBid[0][coluna] = pos;
			}
		}
		return arrayBid;

	}

	static int contaPalavrasFrase(String frase) {

		String[] array = frase.split(" ");
		int tamanhoArray = array.length;
		return tamanhoArray;
	}

	public static String[] arrayNew(int tamanho) {
		String[] array = new String[tamanho];
		return array;
	}

	static String[] creationQuestion(String[] array, String index, String word) {
		String[] arrayQ = new String[array.length];
		String c = "";
		String wquestion = "";
		boolean endTroca = false;
		int nnCof = 0;
		boolean wordEnd = true;
		boolean verif = false;
		AuxiliarCreateQuestion p = new AuxiliarCreateQuestion();
		Questao tg = new Questao();

		for (int i = 0; i < array.length; i++) {

			if (tg.index.equals("-1")) {
				break;
			}

			// variavel c pois senão toda vez vai trocar
			if (("0".equals(index) && c.equals(""))) {
				arrayQ[i] = word + " ";
				c = "0";
				endTroca = true;
			} else if ("5".equals(index) && c.equals("")) {
				arrayQ[i] = word + " ";
				c = "0";
				endTroca = true;
			} else if ("1".equals(index) && c.equals("")) {
				arrayQ[i] = " ";
				c = "1";
				endTroca = true;
				nnCof = i;
			} else if (("1".equals(index)) && (c.equals("1")) && endTroca && (nnCof != i) && wordEnd) {

				arrayQ[i] = word + " ";
				endTroca = true;
				nnCof = 0;
				wordEnd = false;
			} else if ("2".equals(index) && !endTroca && (!verif)) {

				endTroca = false;
				if (i == 0) {
					p.setA(array[i]);
					arrayQ[i] = " ";
				} else if (i == 1) {
					p.setB(array[i]);
					arrayQ[i] = word + " ";
				} else if (i == 2) {

					arrayQ[i] = array[i] + " ";

				} else if (i == 3) {
					arrayQ[i] = p.getA().toLowerCase() + " ";

				} else if (i == 4) {
					arrayQ[i] = p.getB() + " ";
				} else if (i == 5) {
					arrayQ[i] = " ?";
				} else {
					arrayQ[i] = "";
				}

			} else if ("3".equals(index)) {
				endTroca = false;
				if (i == 0) {
					p.setA(array[i]);
					arrayQ[i] = word + " ";
				} else if (i == 1) {
					p.setB(array[i]);
					// arrayQ[i] = arrayQ[i-1] + "" ;
				} else if (i == 2) {
					arrayQ[i - 1] = array[i] + " ";
					arrayQ[i] = p.getA().toLowerCase() + " ";

				} else if (i == 3) {
					arrayQ[i] = p.getB() + " ";
					endTroca = false;
					verif = false;
				} else if (i == 4) {
					arrayQ[i] = " ?";
				}

			} else if ("4".equals(index) && c.equals("")) {
				arrayQ[i] = " ";
				c = "1";
				endTroca = true;
				nnCof = i;

			} else if ("4".equals(index) && (c.equals("4")) && endTroca && (nnCof != i) && wordEnd) {

				arrayQ[i] = word + " ";
				endTroca = true;
				nnCof = 0;
				wordEnd = false;

			} else {
				if (!array[i].equals("#") && endTroca) {
					arrayQ[i] = array[i] + " ";
					verif = true;
				} else if (verif) {
					arrayQ[i] = " ?";
					break;
				}

			}
		}

		return arrayQ;
	}

	@SuppressWarnings("unused")
	static String searchWordQuetion(int keyLinQuestion, int Key, String[][] array) {
		String word = "";
		int KeyColQuestion = Key + 1;
		primeiroloop: for (int linha = 0; linha < array.length; linha++) {
			segundoloop: for (int coluna = 0; coluna < array[linha].length; coluna++) {
				if (linha == keyLinQuestion && (coluna == KeyColQuestion)) {
					word = array[linha][coluna];
					break primeiroloop;
				}

			}
		}
		return word;
	}

	public static String[][] arrayFrase(String frase) {
		String[] array = frase.split(" ");
		String[][] newArray = new String[1][array.length];
		for (int linha = 0; linha < 1; linha++) {
			for (int coluna = 0; coluna < array.length; coluna++) {
				newArray[linha][coluna] = array[coluna];
			}
		}
		return newArray;
	}

	/**
	 * https://stanfordnlp.github.io/CoreNLP/api.html
	 * 
	 * @param frase
	 * @return
	 */
	static String[][] parser(String frase) {

		// cria um objeto StanfordCorelNLP object a partir de um conjunto de
		// propriedades
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String[] array = frase.split(" ");
		String[][] novoarray = new String[1][array.length + 1];
		int tamanhoArray = array.length + 1;
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

				novoarray[linha][coluna] = pos;
				if (coluna == (tamanhoArray - 2)) {
					novoarray[0][coluna + 1] = "#";
				}
				coluna++;
			}
		}
		return novoarray;
	}







	
	
	
	
	
	static String[] arrayFraseConteudo(String frase) {

		String[] array = frase.split(" ");
		String[] newA = new String[array.length + 1];

		for (int i = 0; i <= array.length; i++) {

			if (i != array.length) {
				newA[i] = array[i];
			} else if (i == array.length) {
				newA[i] = "#";
			}
		}

		return newA;

	}



}
