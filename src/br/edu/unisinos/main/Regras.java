/**
 * 
 */
package br.edu.unisinos.main;

/**
 * @author LCRO
 *
 */
public class Regras {
	
	static TypeQuestion q;
	static Pronomes  p;
	static String [] novoarray;
	
	public Regras(int tamanhoArray) {
		
	    novoarray = new String[tamanhoArray];
	}
	
	public  void regras(String pos, String word, boolean testa, int cont){
			
		
		if ("DT".equalsIgnoreCase(pos) && cont == 0) {
			testa = true;
			novoarray[cont + 1] = word.toLowerCase();
		} else if ("NNP".equalsIgnoreCase(pos) && cont == 0) {
			testa = true;			
			q = new TypeQuestion("PERSON");
			novoarray[cont] = WQ.wQuestion(q.getType());
			p = new Pronomes();
			p.setPronome(true);
			
		} else if ("PRP".equalsIgnoreCase(pos) && cont == 0) {
			testa = true;			
			new TypeQuestion("PERSON");
			novoarray[cont] = WQ.wQuestion(q.getType());

		}

		if (("NNP".equalsIgnoreCase(pos) && cont == 1) && p.isPronome() == true ) {

			novoarray[cont] = novoarray[0];
			novoarray[cont - 1] = "";

		}

		if (("VBZ".equalsIgnoreCase(pos)) && (cont == 1) && (p.isPronome() == true)
				) {

			novoarray[cont] = word;

		}

		if (("VBZ".equalsIgnoreCase(pos)) && (cont == 1) && (p.isPronome() == true)
				&& !q.getType().equalsIgnoreCase("PERSON")) {

			String p = word.substring(0, 1).toUpperCase().concat(word.substring(1));
			novoarray[cont - 1] = p;

		}

		if ((cont != 1) && (cont != 0)) {

			if (".".equalsIgnoreCase(word)) {
				novoarray[cont] = "?";

			} else {
				novoarray[cont] = word;
			}

		}

		
		
		
	}
	
	
	public  String[] getNovoarray() {
		return novoarray;
	}
	

}
