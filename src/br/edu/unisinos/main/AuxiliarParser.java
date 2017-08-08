/**
 * 
 */
package br.edu.unisinos.main;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
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
			//	String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
				System.out.println(word + "/" + pos );
				 

			}
		}

	}

	public static void main(String[] args) {
	
		
		// https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html
		
		
		String frase3 = "The computer is black";
	//	String frase = "Salma Hayek goes to school at Stanford University";
//		String frase = "He is the teacher";
		String frasev = "He is the teacher";
	//	String frase= "John hit the ball";
		String frase1 = "The chef cooks the soup";
		String frase2 = "Your name is Luis";
		//  "The babies are tired"; 
		String frasex =  "The color is blue";
		//"She is the doctor";
		//"This is the best making of movie";
		String frase4 = "Penélope  was born in Madrid" ; 
		
		String frase5 = "The party is on Friday at 2 o'clock";
		// When is the party?
		
	//	String frase = "Penélope  was born at home";
		String frase6 = "On Friday at 2 o'clock";
		
		// Where was Lincon born?
		
		
		
		
			AuxiliarParser.parser(frasex);;
		

		
		
		

		
		
		
		

	}

}
