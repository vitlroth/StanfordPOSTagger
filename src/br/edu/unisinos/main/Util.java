/**
 * 
 */
package br.edu.unisinos.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author LCRO
 *
 */
public class Util {

	public void exibeResultado(String[] f, String[] p) {

		primeirofor: 
			for (int i = 0; i < f.length; i++) {
			System.out.println("Frase de Entrada : " + f[i]);			
				for (int j = 0; j < p.length; j++) {
				System.out.println("Questão : " + p[i]);
				System.out.println();
				break;
			}

		}

	}

	/**
	 * 
	 * 
	 * @param array
	 */
	public void exibeArray(String[] array) {
		int cont = 1;
		System.out.println();
		for (int i = 0; i < array.length; i++) {
			System.out.println(cont + "." + " " + array[i]);
			cont++;
		}

	}

	/***
	 * Coloca o arrauUnidimenssional dentro de um bidimenssional
	 * 
	 * @param tamanhoLinhasRegras
	 * @param regras
	 * @return
	 */
	public String[][] retornaBidRegras(int tamanhoLinhasRegras, String[] regras) {
		String[][] resultado = new String[tamanhoLinhasRegras][40];
		String[] regrasClone = regras.clone();
		for (int i = 0; i < regrasClone.length; i++) {
			String fr = regrasClone[i];
			String[] f = fr.split(" ");
			for (int j = 0; j < f.length; j++) {
				resultado[i][j] = f[j];
			}
		}
		return resultado;
	}

	public void exibeBidmenssional(String[][] array) {
		int linha;
		int contador = 1;
		for (linha = 0; linha < array.length; linha++) {
			// System.out.printf(" %da. linha : ", (linha + 1));
			System.out.println(contador + ")");
			for (int coluna = 0; coluna < array[linha].length; coluna++) {

				if (array[linha][coluna] != null) {
					System.out.print(" " + array[linha][coluna]);
				}

			}
			contador++;
			System.out.printf("\n");

		}
	}
	
	
	
	
	
	
	
	
	/**
	 * Algoritmo transforma frase aplicando um split e adicionando um caracter no fim do array
	 * @param frase
	 * @return
	 */
	public String[] transformarFrases(String frase) {

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
	
	
	
	
	
	
	

}
