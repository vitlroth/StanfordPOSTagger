/**
 * 
 */
package br.edu.unisinos.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @author LCRO
 *
 */
public class ImportaArquivos {

	/**
	 * Mantém o controle do número de linhas em um arquivo A classe
	 * LineNumberReader possui o método getLineNumber que é incrementado a cada
	 * quebra de linha em um arquivo. Método Skip navega até o fim do arquivo
	 * 
	 * @param arquivoTXT
	 * @return
	 */
	public int totalDeLinhas(String arquivoTXT) {

		int retorno = 0;
		try {

			LineNumberReader lnr = new LineNumberReader(new FileReader(arquivoTXT));
			lnr.skip(Long.MAX_VALUE);
			retorno = lnr.getLineNumber();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return retorno;

	}

	public int quantidadeDeLInhas(FileReader arquivo) throws IOException {

		int qtdLinha = 0;
		try {

			LineNumberReader linhaLeitura;

			linhaLeitura = new LineNumberReader(arquivo);
			qtdLinha = linhaLeitura.getLineNumber();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return qtdLinha;
	}

	/**
	 * 
	 * @param FileReade,
	 *            tamanhoArray
	 * @return Array
	 */
	public String[] importaArquivoTexto(FileReader file, int totalDeLinhas) {
		String[] array = null;
		try {

			int tamanhoArray = totalDeLinhas;
			BufferedReader in = new BufferedReader(file);
			array = new String[tamanhoArray];
			String linha = "";
			int contador = 0;
			while ((linha = in.readLine()) != null) {
				array[contador] = linha;
				contador++;
			}

			in.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return array;

	}

}
