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
public class Parsing {

	/**
	 * https://stanfordnlp.github.io/CoreNLP/api.html Método que faz um analise
	 * morfosintatica de um frase ou texto e retorna uma string dos resultados
	 * de cada palavra da frase.
	 * 
	 * @param frase
	 * @return
	 */
	static String parser(String frase) {

		// cria um objeto StanfordCorelNLP object a partir de um conjunto de
		// propriedades
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos");

		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// Retira os espaços em branco da frase.
		// String[] array = frase.split(" ");

		String retornoPOSTAG = "";

		// cria uma anotação vazia com o frase de entrada
		Annotation annotation = new Annotation(frase);

		// executa todos os anotators desse texto
		pipeline.annotate(annotation);

		// Split - Divide uma sequencia de TOKENS em frases. Pode ser um
		// paragrafo, um texto - todas as frases do documento
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);

				retornoPOSTAG += pos + " ";

			}
		}
		return retornoPOSTAG;
	}

	public String[][] parserListaDeFrases(int tamanhoFrase, String[] frases) {
		String[][] resultParser = new String[tamanhoFrase][40];
		String[] frasesClone = frases.clone();
		for (int i = 0; i < frasesClone.length; i++) {
			String fr = parser(frasesClone[i]);
			String[] f = fr.split(" ");
			for (int j = 0; j < f.length; j++) {
				resultParser[i][j] = f[j];
				if (j == f.length - 1) {
					resultParser[i][j + 1] = "#";
				}
			}
		}
		return resultParser;
	}
	
	
	
	
	
	

	static String[][] parserBid(String frase) {

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
