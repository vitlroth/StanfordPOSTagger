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
public class TagText {

	static int linhaKey = 0;
	static int colunaKey = 0;
	static String index = "";
	static String word = "";

	public static void setWord(String word) {
		TagText.word = word;
	}

	public static String getWord() {
		return word;
	}

	public static String getIndex() {
		return index;
	}

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

	static void evibeArray(String[] array) {

		if (TagText.index.equals("-1")) {
			System.out.println("Regra não encontrada!");
		} else {
			for (int i = 0; i < array.length; i++) {

				System.out.print(array[i]);

			}
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

		for (int i = 0; i < array.length; i++) {

			if (TagText.index.equals("-1")) {
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

	static String[][] regras() {

		String[][] regras = { { "NNP", "NNP", "VBZ", "DT", "NN", "IN", "DT", "NNP", "#", "Who", "1" },
				{ "PRP", "VBZ", "DT", "NN", "#", "Who", "0" },
				{ "DT", "VBZ", "DT", "JJS", "NN", "IN", "NN", "#", "What", "5" },
				{ "DT", "VBZ", "DT", "NN", "#", "Who", "0" },
				{ "NNP", "VBZ", "DT", "NN", "IN", "DT", "NNP", "#", "Who", "0" },
				{ "DT", "NN", "VBZ", "IN", "DT", "NN", "NN", "#", "Where", "1" },
				{ "DT", "NNS", "VBP", "VBN", "#", "Who", "4" }, { "DT", "NN", "VBZ", "NNP", "#", "Which", "3" } };
		return regras;
	}

	@SuppressWarnings("unused")
	static boolean comparaArrays(String[][] regras, String[][] parser) {
		int linhaFrase = 0;
		try {
			primeiroloop: for (int linha = 0; linha < regras.length; linha++) {
				segundoloop:
				// System.out.printf(" %da. linha : ", (linha + 1));
				for (int coluna = 0; coluna < regras[linha].length; coluna++) {
					if (!parser[linhaFrase][coluna].equals(regras[linha][coluna])) {

						int regr = regras.length;
						if ((linha + 1) == regr) {
							return false;
						}
						break;
					} else if ((regras[linha][coluna].equals("#")) && (parser[linhaFrase][coluna].equals("#"))) {
						TagText.linhaKey = linha;
						TagText.colunaKey = coluna;
						int c = coluna + 2;
						TagText.index = regras[linha][c];
						return true;
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	static String keyWQ(String[][] regras) {

		if (TagText.linhaKey == -1 && TagText.colunaKey == -1) {
			return "";

		}
		int keyLinQuestion = TagText.linhaKey;
		int KeyColQuestion = TagText.colunaKey + 1;
		String word = "";
		primeiroloop: for (int linha = 0; linha < regras.length; linha++) {
			segundoloop: for (int coluna = 0; coluna < regras[linha].length; coluna++) {
				if (linha == keyLinQuestion && (coluna == KeyColQuestion)) {
					word = regras[linha][coluna];
					break primeiroloop;
				}

			}
		}
		return word;

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

	static String retornaString(String[] frase) {
		String questao = "";

		for (int i = 0; i < frase.length; i++) {
			questao += frase[i] + " ";
		}
		return questao;

	}

	@SuppressWarnings("unused")
	static void comparaArraysBID(String[][] regras, String[][] parser) {
	//	String[] questoesGeradas = null;
	//	String[] questionGeneration = new String[parser.length];
		int linhaFrase = 0;
		try {
			primeiroloop: 
				for (int linha = 0; linha < regras.length; linha++) {
				segundoloop: 
					for (int coluna = 0; coluna < regras[linha].length; coluna++) {
					if (!parser[linhaFrase][coluna].equals(regras[linha][coluna])) {
						TagText.index = "";
						TagText.word = "";
						break;
					} else if (parser[linhaFrase][coluna].equals(regras[linha][coluna])) {
						if ((regras[linha][coluna].equals("#")) && (parser[linhaFrase][coluna].equals("#"))) {														
							int indice = coluna + 2;
							int word = coluna + 1;
							TagText.index = regras[linha][indice];
							TagText.word = regras[linha][word];																																	
							int c = coluna + 1;
							// Busca a palavra chave e o indice
						//	String wordQuestion = buscaWordQuestion(regras, linha, coluna);
						//	int indiceRegula = coluna + 2;
					//		String ind = regras[linha][indiceRegula];
							// Gerador de questão
					//		questoesGeradas = questionGeneration(arrayFrases, ind, wordQuestion);
							// int c = coluna + 2;
							// TagText.index = regras[linha][c];
							// int indice = coluna + 2;
							// arrayAux [indiceAux] = indice;
						//	return questoesGeradas;

						}

					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//return questoesGeradas;

	}

	static String buscaWordQuestion(String[][] regras, int linhaN, int colunaN) {

		if (linhaN == -1 && colunaN == -1) {
			return "";

		}
		int keyLinQuestion = linhaN;
		int KeyColQuestion = colunaN + 1;
		String word = "";
		primeiroloop: for (int linha = 0; linha < regras.length; linha++) {
			segundoloop: for (int coluna = 0; coluna < regras[linha].length; coluna++) {
				if (linha == keyLinQuestion && (coluna == KeyColQuestion)) {
					word = regras[linha][coluna];
					break primeiroloop;
				}

			}
		}
		return word;

	}

	static String[] questionGeneration(String[] array, String index, String word) {
		String[] arrayQ = new String[array.length];
		String c = "";
		String wquestion = "";
		boolean endTroca = false;
		int nnCof = 0;
		boolean wordEnd = true;
		boolean verif = false;
		AuxiliarCreateQuestion p = new AuxiliarCreateQuestion();
		
		
		
		

		for (int i = 0; i < array.length; i++) {
			
			
			if("".equals(index) || index.equals(word)){
				arrayQ = null;
				break;
			}else if (("0".equals(index) && c.equals(""))) {
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

	static String questionGenerationString(String[] array, String index, String word) {
		String q = "";
		for (int i = 0; i < array.length; i++) {
			if("0".equals(index)){
				array[i] = word;
			}						
		}
		return q;
	}

	public static void main(String[] args) {

		/*
		 * String[] frase = new String[22]; frase[0] =
		 * "This is the best making of movie"; frase[1] =
		 * "Trump is the president of the America"; frase[2] =
		 * "The training is at the music store"; frase[3] =
		 * "She is the teacher"; frase[4] = "The training is PLN"; frase[5] =
		 * "The training is Monday"; frase[6] =
		 * "Donald Trump is the president of the America"; frase[7] =
		 * "The babies are tied"; frase[8] = "The babies are tired"; frase[9] =
		 * "She is the Queen"; frase[10] = "You is the teacher"; frase[11] =
		 * "He is the person"; frase[12] = "The food is Vegetable"; frase[13] =
		 * "The key is at the abbey road"; frase[14] = "This is the animal";
		 * frase[15] = "FIFA is the organization of the Soccer"; frase[16] =
		 * "The dialogue is at the centre answer"; frase[17] =
		 * "The politicians are outdated"; frase[18] = "This is the car";
		 * frase[18] = "She is the nurse"; frase[19] =
		 * "Trump is the woman of the City"; frase[20] = "She is the actress";
		 * frase[21] = "She the is actres";
		 */

		
		try {
		
		

			FileReader fr = new FileReader("BackupFrases.txt");
			ImportaArquivos arquivo = new ImportaArquivos();
			int totalDeLinhas = arquivo.totalDeLinhas("BackupFrases.txt");
			
			String[] frase = arquivo.importaArquivoTexto(fr, totalDeLinhas).clone();
			String[] armazena = new String[totalDeLinhas];
			int cont = 1;
			for (int i = 0; i < frase.length; i++) {
				System.out.println(cont + ")" + frase[i]);
				cont++;
			}

			for (int i = 0; i < frase.length; i++) {

				/*
				 * String[] array =
				 * TagText.arrayFraseConteudo(frase[i]).clone();
				 * 
				 * if (TagText.comparaArrays(TagText.regras(),
				 * TagText.parser(frase[i]))) {
				 * 
				 * System.out.println("Frase de Entrada : " + frase[i]);
				 * System.out.print("Pergunta : ");
				 * 
				 * TagText.evibeArray( TagText.creationQuestion(array,
				 * TagText.getIndex(), TagText.keyWQ(TagText.regras())));
				 * System.out.println();
				 * 
				 * } else {
				 * 
				 * System.out.println("Variavel não encontrada!");
				 * 
				 * 
				 * }
				 */

				// Armazena o indice da frase de entrada

				String[] percorre = TagText.arrayFraseConteudo(frase[i]).clone();
		//		String[] ret = TagText.comparaArraysBID(TagText.regras(), TagText.parser(frase[i]), percorre).clone();
				
				 TagText.comparaArraysBID(TagText.regras(), TagText.parser(frase[i]));
				 String [] ret =	 questionGeneration(percorre, TagText.index, TagText.word);
				 String questao = "";
				 if(ret==null){
					 
					 armazena[i] = "Regra não encontrada";
				 } else{
					 questao = retornaString(ret);	 
					 armazena[i] = questao;	
				 }
			

				

			}
                int c = 1;
			for (int i = 0; i < armazena.length; i++) {
				System.out.println(c + ")" + armazena[i]);
				c++;
			}

		} catch (FileNotFoundException e) { // TODO Auto-generated catch

			e.printStackTrace();
		}

	}

}
