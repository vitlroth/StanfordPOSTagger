/**
 * 
 */
package br.edu.unisinos.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

/**
 * @author LCRO
 *
 */
public class Principal {



	public static void main(String[] args) {

		try {

			Util u = new Util();

			// IMPORTAÇÃO DO ARQUIVO DE FRASES
			FileReader fr = new FileReader("frases.txt");
			ImportaArquivos arquivo = new ImportaArquivos();
			int totalDeLinhas = arquivo.totalDeLinhas("frases.txt");
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
			u.exibeArray(regras);

			// Armazena um lista de questões geradas pelo sistema.
			String[] listaDeQuestoes = new String[totalDeLinhas];
			Negocio negocio = new Negocio();

			// Exibe lista de Frases de entrada extraidas do arquivo.
			// u.exibeArrayFrases(frase);
			for (int i = 0; i < frase.length; i++) {

				String[] transformacao = u.transformarFrases(frase[i]).clone();
				negocio.comparaArraysBID(regrasB, Parsing.parserBid(frase[i]));
				String[] guardaQuestao = QuestionGeneration.questionGeneration(transformacao, Regras.index,
						Regras.word);

				String questao = "";
				if (guardaQuestao == null) {
					listaDeQuestoes[i] = "Não foi possivel gerar a pergunta";
				} else {
					questao = QuestionGeneration.concatenaQuestaoGerada(guardaQuestao);
					listaDeQuestoes[i] = questao;
				}

			}

			// u.exibeArrayFrases(listaDeQuestoes);
			u.exibeResultado(frase, listaDeQuestoes);

			// GERA UM ARQUIVO DE SAIDA CONTENDO AS QUESTÕES GERADAS
			GeraArquivo gerador = new GeraArquivo();
			FileWriter fw = new FileWriter("ListaDeQuestoes.txt");
			// Gera arquivo com as questoes
			// gerador.geraArquivoTXT(totalDeLinhas, fw, listaDeQuestoes);
			// Gera arquivo com as Frases de entrada e as questoes geradas.
			 gerador.gerarTXT(totalDeLinhas, fw, frase,listaDeQuestoes);

		} catch (FileNotFoundException e) { // TODO Auto-generated catch

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
