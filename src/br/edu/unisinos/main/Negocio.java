/**
 * 
 */
package br.edu.unisinos.main;

/**
 * @author LCRO
 *
 */
public class Negocio {

	
	public void comparaArraysBID(String[][] regras, String[][] parser) {
         
		boolean verificador = false;
		int linhaFrase = 0;
		try {
			primeiroloop: 
				for (int linha = 0; linha < regras.length; linha++) {
					if(verificador){
						break;
					}
				segundoloop: 
					for (int coluna = 0; coluna < regras[linha].length; coluna++) {
					
					if(!parser[linhaFrase][coluna].equals(regras[linha][coluna])) {
						Regras.index = "";
						Regras.word = "";
						break;
					} else if (parser[linhaFrase][coluna].equals(regras[linha][coluna])) {						
						if ((regras[linha][coluna].equals("#")) && (parser[linhaFrase][coluna].equals("#"))) {														
							int indice = coluna + 2;
							int indiceWord = coluna + 1;
							
							
							
							
							
							// Continue
							Regras.index = regras[linha][indice];
							Regras.word = regras[linha][indiceWord];
							verificador = true;
							break;
						}

					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERRO AO COMPARAR REGRAS");
		}		
	}		
}
