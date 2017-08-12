/**
 * 
 */
package br.edu.unisinos.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author LCRO
 *
 */
public class Principal2 {

	public static void main(String[] args) {
		try {
			Util u = new Util();
			// IMPORTAÇÃO DO ARQUIVO DE FRASES
			FileReader fr = new FileReader("BackupFrases.txt");
			ImportaArquivos arquivo = new ImportaArquivos();
			int totalDeLinhas = arquivo.totalDeLinhas("BackupFrases.txt");
			String[] frase = arquivo.importaArquivoTexto(fr, totalDeLinhas).clone();
			// IMPORTAÇÃO DO ARQUIVO DE REGRAS
			FileReader rg = new FileReader("regras.txt");
			ImportaArquivos arquivorg = new ImportaArquivos();
			int totalDeLinhasRG = arquivorg.totalDeLinhas("regras.txt");
			// importa regras de um arquivo e armazena em um array.
			String[] regras = arquivo.importaArquivoTexto(rg, totalDeLinhasRG).clone();
			// Tranforma array de regras em um arrayBidmenssional
			String[][] regrasB = u.retornaBidRegras(totalDeLinhasRG, regras).clone();
			// EXIBE AS REGRAS
		//	u.exibeArray(regras);
			// Armazena um lista de questões geradas pelo sistema.
			String[] listaDeQuestoes = new String[totalDeLinhas];
			Negocio negocio = new Negocio();
			// Exibe lista de Frases de entrada extraidas do arquivo.
			// u.exibeArrayFrases(frase);
			String[] guardaQuestao = null;
			String[] transformacao = null;
			String[][] auxFrSimp = null;
			int contaSimpl = 0;
			
	
			for (int i = 0; i < frase.length; i++) {

				// String[] transformacao =
				// u.transformarFrases(frase[i]).clone();
				// negocio.comparaArraysBID(regrasB,
				// Parsing.parserBid(frase[i]));

				// Alteração com simplificação
				negocio.comparaArraysBID(regrasB, AuxiliarParser.parserPOS(frase[i]));
				if (AuxiliarParser.simplifique) {				
					
					AuxiliarParser.simplifique = false;
					auxFrSimp = AuxiliarParser.parserWORD(frase[i]);
				   
					contaSimpl++;
					int tam = auxFrSimp[0].length;
					transformacao = AuxiliarParser.transformarArray(tam, auxFrSimp);
					guardaQuestao = QuestionGeneration.questionGeneration(transformacao, Regras.index, Regras.word);
				} else {
					transformacao = u.transformarFrases(frase[i]).clone();
					guardaQuestao = QuestionGeneration.questionGeneration(transformacao, Regras.index, Regras.word);
				}
                                                             
				String questao = "";
				if (guardaQuestao == null) {
					listaDeQuestoes[i] = "Não foi possivel gerar a pergunta";
				} else {
					questao = QuestionGeneration.concatenaQuestaoGerada(guardaQuestao);
					listaDeQuestoes[i] = questao;
				}
			}
			
			
			u.exibeResultado(frase, listaDeQuestoes);
			
			
			String[][] auxFrSimplificador = null;	
			String [] arraySimp = null;
			 String []  qS = new String[contaSimpl];
			 int contO = contaSimpl;
			 String [] qO = new String [contO];
			 String simpl =  "";
			// Listar frases simplificadas
			int contadorF = 0;
			int contar = 0;
			for (int i = 0; i < frase.length; i++) {
				contadorF = AuxiliarParser.contaCaracteresEspeciais(frase[i]);
				if(contadorF>0){
					auxFrSimplificador = AuxiliarParser.parserWORD(frase[i]);
					int tam = auxFrSimplificador[0].length;
					arraySimp = AuxiliarParser.transformarArray(tam, auxFrSimplificador);
					simpl = QuestionGeneration.concatenaQuestaoGerada(arraySimp);
			
						qS[contar] = simpl;
						qO[contar] = frase[i];
						contar++;	
					
					
				}
				
				
			}
			
			
			for (int i = 0; i < qS.length; i++) {
				System.out.println("Frase Original : " + qO[i]);
				System.out.println("Frase simplificada : " + qS[i]);
			}
			
			
			
			
			
			
			// u.exibeArrayFrases(listaDeQuestoes);
				
		//	System.out.println("ABAIXO AS FRASES QUE FORAM SIMPLIFICADAS");
	//		u.exibeArray(q);													
			// GERA UM ARQUIVO DE SAIDA CONTENDO AS QUESTÕES GERADAS
		//	 GeraArquivo gerador = new GeraArquivo();
			// FileWriter fw = new FileWriter("ListaDeQuestoes.txt");
			// Gera arquivo com as questoes
			// gerador.geraArquivoTXT(totalDeLinhas, fw, listaDeQuestoes);
			// Gera arquivo com as Frases de entrada e as questoes geradas.
			// gerador.gerarTXT(totalDeLinhas, fw, frase, listaDeQuestoes);

		} catch (FileNotFoundException e) { // TODO Auto-generated catch

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
