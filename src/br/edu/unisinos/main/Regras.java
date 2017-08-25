package br.edu.unisinos.main;

public class Regras {
	
	
	static String index;
	static String word;
	public static String getWord() {
		return word;
	}
	
	
	public static void setWord(String word) {
		Regras.word = word;
	}
	
	

	static String concatenaAnaliseGerada(String[] questao) {
		String analiseR = "";

		for (int i = 0; i < questao.length; i++) {
			if((!questao.equals("")) && (questao!=null)){
			analiseR += questao[i] + " ";
			}
			
		}
		return analiseR;

	}
	
	

	
	
	

}
