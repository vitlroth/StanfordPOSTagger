/**
 * 
 */
package br.edu.unisinos.main;

/**
 * @author LCRO
 *
 */
public class WQ {

	public static String wQuestion(String type){
		
	String retorno = "";
		
		if("TIME".equals(type)){
			retorno = "When";
		}else if("CHOICE".equals(type)){
			retorno = "Which";
		}else if("PLACE".equals(type)){
			retorno = "Where";
		}else if("PERSON".equals(type)){
			retorno = "Who";
		}else if("REASON".equals(type)){
			retorno = "Why";
		}else if("THING".equals(type)){
			retorno = "What";
		}else{
			return retorno;
		}
		return retorno;
		}

}

