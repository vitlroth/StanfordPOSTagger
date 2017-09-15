package br.edu.unisinos.main;

import java.util.Stack;

public class QuestionGeneration {

	static String[] questionGeneration1(String[] array, String index, String word) {

		String[] arrayQ = new String[array.length];
		String c = "";
		boolean endTroca = false;
		int nnCof = 0;
		boolean wordEnd = true;
		boolean verif = false;
		AuxiliarCreateQuestion p = new AuxiliarCreateQuestion();
		for (int i = 0; i < array.length; i++) {
			if ("".equals(index) || index.equals(word)) {
				arrayQ = null;
				break;
			} else if (("0".equals(index) && c.equals(""))) {
				arrayQ[i] = word + " ";
				c = "0";
				endTroca = true;
			} else if ("5".equals(index) && c.equals("")) {
				arrayQ[i] = word + " ";
				c = "0";
				endTroca = true;
			} else if ("1".equals(index) && c.equals("")) {
				arrayQ[i] = "";
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
					arrayQ[i] = "?";
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
					arrayQ[i] = array[i];
					verif = true;
				} else if (verif) {
					arrayQ[i] = "?";
					break;
				}

			}
		}

		return arrayQ;

	}

	static String concatenaQuestaoGerada(String[] questao) {
		String questaoR = "";

		for (int i = 0; i < questao.length; i++) {
			if (questao[i].equals("#")) {
				questaoR += " ";
			} else if (questao[i] == null) {
				questaoR += " ";
			} else {
				questaoR += questao[i] + " ";
			}
		}
		return questaoR;

	}

	static String[] questionGeneration(String[] array, String index, String word) {

		String[] arrayQ = new String[array.length];
		String[] aux = new String[array.length];
		boolean endTroca = false;
		boolean verb = false;
		boolean verif = false;
		AuxiliarCreateQuestion auxiliar = new AuxiliarCreateQuestion();
		Stack p = new Stack();
		String[] ary = new String[array.length];
		int cont = 0;

		for (int i = 0; i < array.length; i++) {
			if ("".equals(index) || index.equals(word)) {
				arrayQ = null;
				break;
			} else if (("1".equals(index) && endTroca == false) || ("3".equals(index) && endTroca == false)
					|| ("4".equals(index) && endTroca == false) || ("5".equals(index) && endTroca == false)
					|| ("9".equals(index) && endTroca == false) || ("11".equals(index) && endTroca == false)) {
				arrayQ[i] = word + " ";
				endTroca = true;
			} else if ("2".equals(index) && (i == 0)) {
				arrayQ[i] = "";
			} else if ("2".equals(index) && (i == 1)) {
				arrayQ[i] = word + " ";
				endTroca = true;
			} else if ("6".equals(index)) {

				if (i == 0) {
					// guarda o determinante
					auxiliar.setA(array[i]);
					// armazena a w-word
					arrayQ[i] = word;
				} else if (i == 1) {
					// guarda o substantivo
					auxiliar.setB(array[i]);
				} else if (i == 2) {
					// armazena o verbo na posição 1
					arrayQ[i - 1] = array[i];
					// armazena determinante
					arrayQ[i] = auxiliar.getA().toLowerCase();
				} else {

					if (i == 3) {
						arrayQ[i] = auxiliar.getB().toLowerCase();
					} else if (i == 4) {
						arrayQ[i] = "?";
					} else {
						arrayQ[i] = "";
					}
				}
			} else if ("10".equals(index)) {

				if (i == 0) {
					auxiliar.setA(array[i]);
					int n = array.length - 2;
					String a = array[n];
					ReconhecimentoEntidade rec = new ReconhecimentoEntidade();
					String entidade = rec.verificaEntidade(a);
					if (entidade.equals("TOUCH")) {
						arrayQ[i] = "Who";
					} else if (entidade.equals("WEEK")) {
						arrayQ[i] = "When";
					} else if (entidade.equals("LOCATION")) {
						arrayQ[i] = "Where";
					} else if (entidade.equals("DESCRIBE")) {
						arrayQ[i] = "Who";
					}

					else {
						arrayQ[i] = word;
					}

				} else if (i == 1) { // guarda o substantivo
					auxiliar.setB(array[i]);
				} else if (i == 2) { // armazena o
					arrayQ[i - 1] = array[i]; // armazena arrayQ[i] =
					arrayQ[i] = auxiliar.getA().toLowerCase();
				} else {
					if (i == 3) {
						arrayQ[i] = auxiliar.getB().toLowerCase();
					} else if (i == 4) {
						arrayQ[i] = "?";
					} else {
						arrayQ[i] = "";
					}
				}

				/*
				 * if (endTroca==false) {
				 * 
				 * if (!"is".equals(array[i])) { ary[i] = array[i]; if (cont ==
				 * 0) { arrayQ[cont] = word; }
				 * 
				 * } else { cont++; arrayQ[cont] = array[i]; cont++; for (int j
				 * = 0; j < ary.length; j++) { if (ary[j] != null) {
				 * arrayQ[cont] = ary[j]; cont++; } else { arrayQ[cont] = "?";
				 * cont++; endTroca=true; break;
				 * 
				 * }
				 * 
				 * }
				 * 
				 * }
				 * 
				 * } if((endTroca) && (!array[i].equals(""))){ arrayQ[cont] =
				 * ""; }
				 */

			} else {
				if (!array[i].equals("#") && endTroca) {
					arrayQ[i] = array[i];
					verif = true;
				} else if (verif) {
					arrayQ[i] = "?";
					break;
				}

			}
		}

		return arrayQ;

	}

}
