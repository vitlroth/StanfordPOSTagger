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

	static String[][] parserTeste(String frase) {

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

				//String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				String word = token.get(CoreAnnotations.TextAnnotation.class);

				novoarray[linha][coluna] = word;
				coluna++;

			}
		}
	
		
		
		// ENTRADA DO MÉTODO SIMPLIFICA FRASE
		if (simplifica) {

	novoarray =	simplificaFrase(novoarray, tam);

		}

		return novoarray;
	}
	
	
	
	
	
	/**
	 * Algoritmo transforma frase aplicando um split e adicionando um caracter no fim do array
	 * @param frase
	 * @return
	 */
	public String[] transformarFrasesSimplificadas(String[][] frase, int tam) {

		//String[] array = frase.split(" ");
		String[] newA = new String[tam];

		
		for (int i = 0; i < frase.length; i++) {
			
			
			for (int j = 0; j < frase[i].length; j++) {
				
		//		newA[j] = frase[][];
				
				
			}
			
			
			
		}
		
		
		


		return null;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	static String [][] simplificaFrase(String [][] array, int tam){
		
		String [][] spf = new String [1][tam];
		int marc1 = 0;
		int marc2 = 0;
		int marc3 = 0;
	
		
		for (int linha = 0; linha < array.length; linha++) {
		
			
			for (int coluna = 0; coluna < array[linha].length; coluna++) {

				if(array[linha][coluna].equals(",") && marc1==0){
					marc1=coluna;					
				}else if(array[linha][coluna].equals(",") && marc2==0 && marc1!=0){
					marc2=coluna;
					marc3=3;
				}else if ( (coluna > marc2)  &&  (!array[linha][coluna].equals(",") ) && marc3==3 ){
					spf[0][marc1] = array[linha][coluna];
					marc1++;
				}else if( ( marc1==0 ) &&  ( !array[linha][coluna].equals(",") )){
					spf[linha][coluna] = array[linha][coluna];
				}else{
					spf[0][coluna] = " ";
					
				}												                 
			}						
		}
		
		
		return spf;
		
		
		
		
		
		
		
		
		
		
	}

	public static void main(String[] args) {

		// https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html

		String frasex = "Alan, the chief executive of the project, has just called for a meeting ";

		// AuxiliarParser.parser(frasex);;

		String[][] l = AuxiliarParser.parserTeste(frasex);

		Util u = new Util();
		u.exibeBidmenssional(l);
	//	u.exibeArray(array);

	}

}
