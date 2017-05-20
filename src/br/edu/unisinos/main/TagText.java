/**
 * 
 */
package br.edu.unisinos.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.ling.tokensregex.NodePattern.EqualsNodePattern;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;

/**
 * @author LCRO
 *
 */
public class TagText {

	static int linhaKey = 0;
	static int colunaKey = 0;
	static String index = "";
	
	
	
	

	public static String getIndex() {
		return index;
	}
	
	
	
	

	
	static void testaProjeto(){
		
	
		String [][] regras = {{"NN", "NN", "VBZ", "DT", "NN", "IN", "NN", "#", "What", "1"},{"DT","VBZ","#","Who","0"},
				{ "DT","VBZ","DT","NN","#","Who","0"}};
		// String pos = "DT VBZ #";
		String pos ="DT VBZ DT NN #";
	//	String pos = "NN NN VBZ DT NN IN NN #";  
	//	String pos ="DT VBZ DT NN #";
		
		// Substantivo composto seguido de verbo be
		//String word = "Barack Obama is the president of the America #" ;
						
		
		// Pronome pessoal seguindo de verbo de verbo be
		String word = "She is the teacher #";			
		
		// Pronome Demonstrativo  seguido de verbo be
//		String word = "This is a dog #" ;
				
	
		
		
		
		
		
		
		
		String[] array = word.split(" ");
		
		
		
		// Clona o array com os elementos da frase.
		String[][] f = TagText.arrayFrase(pos).clone();
		
		
		
		// Compara os arrays
		TagText.comparaArrays(regras, f);
		
		
		
		
		
		
		TagText.wiewQuestion(array, TagText.getIndex(),TagText.keyWQ(regras));
		
		
	}
	
	
	
	

	/**
	 * Exibe o conteúdo de um array bidimenssional teste
	 * 
	 * @param regras
	 */
	static void exibeBidmenssional(String[][] array) {
		
		
		
		int linha;
		for (linha = 0; linha < array.length; linha++) {
		//	System.out.printf(" %da. linha : ", (linha + 1));
			for (int coluna = 0; coluna < array[linha].length; coluna++) {
				System.out.print(" " + array[linha][coluna]);
			}
			System.out.printf("\n");

		}
	}

	/**
	 * Exibe o conteúdo de um array dimenssional
	 * @author LCRO
	 * @param array
	 */
	static void evibeArray(String[] array) {

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);

		}

	}

	/**
	 * 
	 * @param pos
	 * @param tamArray
	 * @return
	 */
	static String[][] populaArrayAnalise(String pos, int tamanho) {
		String[][] arrayBid = new String[1][tamanho];
		System.out.println("Tamanho do arrayBid : " + arrayBid.length);
		for (int linha = 0; linha <= 0; linha++) {
			for (int coluna = 0; coluna < arrayBid.length; coluna++) {
				arrayBid[0][coluna] = pos;
			}
		}
		return arrayBid;

	}

	/**
	 * Retorna a quantidade de palavras que a frase possui (sem os espaços em
	 * branco)
	 * 
	 * @param frase
	 * @return
	 */
	static int contaPalavrasFrase(String frase) {

		String[] array = frase.split(" ");
		int tamanhoArray = array.length;
		return tamanhoArray;
	}

	/**
	 * Retorna um array dimenssional
	 * 
	 * @param tamanho
	 * @return
	 */

	public static String[] arrayNew(int tamanho) {
		String[] array = new String[tamanho];
		return array;
	}

	
	
	
	/**
	 * 
	 * @param array
	 * @param index
	 * @param word(pronome para geração da perguntas), index (array de regras que sera trocado), array (Array que será guardado a pergunda) 
	 * @return Trabalha em cima das regras
	 */		
	static String[] creationQuestion(String[] array, String index, String word) {
		String[] arrayQ = new String[array.length];
		String c = "";		

		boolean endTroca =  false;
		boolean end = false;
		int nnCof = 0;
		boolean wordEnd = true;
		boolean verif = false;
		String suja = "";
		Help p =  new Help();
		
		for (int i = 0; i < array.length; i++) {
			// variavel c pois senão toda vez vai trocar
			if (("0".equals(index) && c.equals("") )) {                
				arrayQ[i] = word + " ";
		         c = "0";
				endTroca = true;
			}else if("1".equals(index) && c.equals("")){
				arrayQ[i] = " ";				
		        c = "1";
		        endTroca = true;
		        nnCof = i;
			}else if(("1".equals(index)) &&  (c.equals("1"))  && endTroca && (nnCof!=i) && wordEnd){
				
				arrayQ[i] = word + " ";
				endTroca = true;
				nnCof = 0;
				wordEnd = false;
			}else if("2".equals(index) && !endTroca && (!verif)){
				
				endTroca = false;
				if(i==0){										
			     p.setA(array[i]);			     				
				arrayQ[i] = " ";
				}else if(i==1){
	            p.setB(array[i]);
					arrayQ[i] = word + " ";
				}else if(i==2){
					
					arrayQ[i] = array[i] + " ";
					end = true;
					
				}else if(i==3){
					arrayQ[i] = p.getA().toLowerCase() + " ";
					
				}else if(i==4){
					arrayQ[i] = p.getB() + " ";
				}else if(i==5){
					arrayQ[i] = " ?";
				}else{
					arrayQ[i] = "";
				}				
				
			}else if("3".equals(index)){
				 endTroca = false;
				if(i==0){										
				     p.setA(array[i]);			     				
					arrayQ[i] = word + " ";
					}else if(i==1){
		            p.setB(array[i]);
						//arrayQ[i] = arrayQ[i-1] + "" ;
					}else if(i==2){						
						arrayQ[i-1] = array[i] + " ";
						arrayQ[i] = p.getA().toLowerCase() + " ";
						end = true;
						
					}else if(i==3){
						arrayQ[i] = p.getB() + " ";
						endTroca=false;
						verif=false;
					}else if(i==4){
						arrayQ[i] = " ?";
					}
				
				
				
				
				
				
				
			}   
			
					
			
			else{
				
				
				if(!array[i].equals("#") && endTroca){
				arrayQ[i] = array[i] + " ";
				verif = true;
				} else if(verif){
										
					arrayQ[i] = " ?";
				}
			
			
			
			
			}

			
		}

		return arrayQ;
	}



	
	
	/**
	 * 
	 * @param keyLinQuestion
	 * @param KeyColQuestion
	 * @param array
	 * @return O pronome para criação da pergunta
	 */	
	@SuppressWarnings("unused")
	static String searchWordQuetion(int keyLinQuestion, int KeyColQuestion, String[][] array) {
		String word = "";
		boolean t = false;
		primeiroloop: 
			for (int linha = 0; linha < array.length; linha++) {
			segundoloop: 
				for (int coluna = 0; coluna < array[linha].length; coluna++) {
				if (linha == keyLinQuestion && (coluna == KeyColQuestion)) {
					word = array[linha][coluna];
					break primeiroloop; 
				}

			}
		}
		return word;
	}

	/**
	 * 
	 * @author LCRO
	 * @param frase
	 * @return recebe uma string e retorna um array bidimenssional
	 */
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

	
	/***
	 * 
	 * @param frase
	 * @return um array bidimenssional com a analise morfologica da frase
	 */
	
	
	
	static String [][] parser(String frase) {

		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String[] array = frase.split(" ");
		String[][] novoarray = new String[1][array.length+1];
		int tamanhoArray = array.length+1;				
		Annotation annotation = new Annotation(frase);
		pipeline.annotate(annotation);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		int linha = 0;
		int coluna = 0;
		for (CoreMap sentence : sentences) {			
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
		//		String word = token.get(CoreAnnotations.TextAnnotation.class);
				String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
		//		System.out.println(word + "/" + pos);					
				System.out.println("POSTAG : " + pos);
			    String posTag = pos;
				novoarray[linha][coluna] = posTag;
				if(coluna == (tamanhoArray - 2)){
					novoarray[0][coluna+1] = "#";
				}								
				coluna++; 								
				}							 						                			
			}							
		return novoarray;
	}
	
	
	static void comparaArrays(String [][]regras, String [][] f){
	
	
        int linhaFrase = 0;
        int cont = 0;
		try {
			
			primeiroloop: 
				for (int linha = 0; linha < regras.length; linha++) {					
				segundoloop: 
					//System.out.printf(" %da. linha : ", (linha + 1));					
				for (int coluna = 0; coluna < regras[linha].length; coluna++) {													
					if(!f[linhaFrase][coluna].equals(regras[linha][coluna])){
												
						break;
						
					}else if ((regras[linha][coluna].equals("#")) && (f[linhaFrase][coluna].equals("#"))) {						
							TagText.linhaKey = linha;
							TagText.colunaKey = coluna;
							int c = coluna + 2;
							TagText.index = regras[linha][c];
							break primeiroloop;
						}																																											
					}																								 
									
				}								
				
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}
		
	
	

/**
 * 	
 * @param regras
 * @return palavra Chave para Construção da Pergunta
 */
	static String keyWQ(String [][] regras){
	
		// Percorre array para encontrar a palavra para construção de pergunta
		int keyLinQuestion = TagText.linhaKey;
		int KeyColQuestion = TagText.colunaKey + 1;									
		String keyWord = TagText.searchWordQuetion(keyLinQuestion, KeyColQuestion, regras);
		return keyWord;			
		
	}
	
	
	
	static void wiewQuestion(String [] array , String index , String word){
		TagText.evibeArray(TagText.creationQuestion(array,index,word));
		}
		
	
	
	
	
	
	static String[][] regras(){
		
		String [][] regras = {
				{"NNP", "NNP", "VBZ", "DT", "NN", "IN", "DT", "NNP", "#", "Who", "1"},
				{"PRP", "VBZ", "DT", "NN", "#", "Who", "0"},
				{ "DT","VBZ","DT","NN","#","Who","0"}, 
				{"NNP", "VBZ", "DT", "NN", "IN", "DT", "NNP", "#", "Who", "0"}, 
				{"DT", "NN", "VBZ", "IN", "DT", "NN", "NN", "#", "Where", "2"}, 
				{ "DT","NN","VBZ","NNP","#","Which","3"}};
		
		return regras;
	}
	
	
	
	
	
	static String [] arrayFraseConteudo(String frase){
		
		String[] array = frase.split(" ");
		String[] newA = new String[array.length+1];
	
	
		for (int i = 0; i <= array.length; i++) {
			
			if(i!=array.length){
				newA[i] = array[i];
			}else if(i==array.length){                	
                	  newA[i] = "#";
                  }	
		}
		
		
		return newA;
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
	

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {

		
		// Frase
    	
//		String frase = "Donald Trump  is the president of the America" ;				
	//	String frase = "She is the teacher";	
	//	String frase = "This is a dog" ;
	//	String frase = "Trump is the president of the America" ;
		 //  String frase =  "The training is at the music store";
		
		
		
		// Abaixo fazer os teste e colocar no simulação da conflinto
		      String frase = "The training is PLN";
		// String frase = "The training is Monday";
		
		
		
		
	
		
		
				
		TagText.comparaArrays(TagText.regras(), TagText.parser(frase));
		
		String [] array = TagText.arrayFraseConteudo(frase).clone();
		String index =  TagText.getIndex();
		
		String palavraChave = TagText.keyWQ(TagText.regras());
		
		
		//TagText.creationQuestion(array, index, palavraChave);
		
		
	    TagText.wiewQuestion(array, index,palavraChave);
					

	//	TagText.testaProjeto();
		

			
			
			


		


	}

}


