package br.edu.unisinos.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeraArquivo {

	
	
	/***
	 * Gera um arquivo com uma lista contendo perguntas geradas pelo sistema
	 * @param tamanho
	 * @param fw
	 * @param frases
	 * @param questoes
	 */
	public void geraArquivoTXT(int tamanho, FileWriter fw, String[] frases) {

		try {			
			PrintWriter grava = new PrintWriter(fw);

			for (int i = 0; i < frases.length; i++) {

				grava.println(frases[i]);

			}

			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	/***
	 * Gera um arquivo com uma lista contendo frases e as perguntas geradas pelo sistema
	 * @param tamanho
	 * @param fw
	 * @param frases
	 * @param questoes
	 */
	public void gerarTXT(int tamanho, FileWriter fw, String[] frases, String[] questoes) {

		try {			
			PrintWriter grava = new PrintWriter(fw);
			primeirofor: for (int i = 0; i < frases.length; i++) {
				grava.println(frases[i]);
				segundofor: for (int j = 0; j < questoes.length; j++) {
					grava.println(questoes[i]);
					break;
				}

			}

			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
