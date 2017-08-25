package br.edu.unisinos.main;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class GeradorDeRegras {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		
		
		
		
		
		FileReader fr;
		try {
			fr = new FileReader("BackupFrases.txt");
			ImportaArquivos arquivo = new ImportaArquivos();
			int totalDeLinhas = arquivo.totalDeLinhas("BackupFrases.txt");
			String[] frase = arquivo.importaArquivoTexto(fr, totalDeLinhas).clone();
			String regra = "";
			String [] fraseReg = null;
			String [] regrasTotal = new String[totalDeLinhas];
			 Util u = new Util();
			for (int i = 0; i < frase.length; i++) {
				
				
				// Retorna um array com a analise da frase (simplifica se necessário)
				String[][] array = AuxiliarParser.parserPOS(frase[i]);
				int tam = array[0].length;
				
				
			//	fraseReg = AuxiliarParser.transformarArray(tam, array);
				fraseReg = u.transformarArrayB(tam, array);
				regra = Regras.concatenaAnaliseGerada(fraseReg);
				if(!regra.equals("") && regra!=null )
				regrasTotal[i] = regra;
				
			}
			
		
		 u.exibeArray(regrasTotal);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
