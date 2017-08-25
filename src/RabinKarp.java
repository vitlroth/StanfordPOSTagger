/***************************************************************
 *  Compilation:  javac RabinKarp.java
 *  Execution:    java RabinKarp pat txt
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  Las Vegas version of the Rabin-Karp algorithm.
 *
 *  % java RabinKarp abracadabra abacadabrabracabracadabrabrabracad
 *  pattern: abracadabra
 *  text:    abacadabrabracabracadabrabrabracad 
 *  match:                 abracadabra          
 *
 ***************************************************************/

import java.math.BigInteger;
import br.edu.*;
import br.edu.unisinos.main.Regras;

import java.util.Random;

/**
 * This class implements the Rabin-Karp algorithm to search for an occurrence of
 * a given string pat in a given string txt. (The first string is called
 * pattern, the second is called text.) The Rabin-Karp algorithm is
 * probabilistic: in its Monte Carlo version, it may return a wrong answer (with
 * a very low probability); in its Las Vegas version, it always return a correct
 * answer but may not run in linear time (with very low probability).
 * <p>
 * An example of output of the teste client:
 * 
 * <pre>
 *  % java RabinKarp abracadabra abacadabrabracabracadabrabrabracad
 *  pattern: abracadabra
 *  text:    abacadabrabracabracadabrabrabracad 
 *  match:                 abracadabra
 * </pre>
 * <p>
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/53substring/">Section 5.3</a> of
 * "Algorithms, 4th Edition", by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class RabinKarp {
	private String pat; // the pattern (needed only for Las Vegas)
	private long patHash; // pattern hash value
	private int M; // pattern length
	private long Q; // large prime, small enough to avoid long overflow
	private int R; // radix
	private long RM; // R^(M-1) % Q

	/**
	 * Not supported yet. Should construct a RabinKarp object for an R-character
	 * alphabet and a pattern given by a character array.
	 */
	public RabinKarp(int R, char[] pattern) {
		throw new UnsupportedOperationException("Operation not supported yet");
	}

	/**
	 * Constructs a RabinKarp object for the extended ASCII alphabet and a
	 * pattern pat given by a string.
	 */
	public RabinKarp(String pat) {
		this.pat = pat; // save pattern (needed only for Las Vegas)
		R = 256;
		M = pat.length();
		Q = longRandomPrime();

		// precompute R^(M-1) % Q for use in removing leading digit
		RM = 1;
		for (int i = 1; i <= M - 1; i++)
			RM = (R * RM) % Q;
		patHash = hash(pat, M);
	}

	// Compute hash for key[0..M-1].
	private long hash(String key, int M) {
		long h = 0;
		for (int j = 0; j < M; j++)
			h = (R * h + key.charAt(j)) % Q;
		return h;
	}

	// Las Vegas version: does pat[] match txt[i..i-M+1] ?
	private boolean check(String txt, int i) {
		for (int j = 0; j < M; j++)
			if (pat.charAt(j) != txt.charAt(i + j))
				return false;
		return true;
	}

	// Monte Carlo version: always return true
	private boolean check(int i) {
		return true;
	}

	/**
	 * Returns the starting index of the first (exact) occurrence of the pattern
	 * in txt. This is the Las Vegas version; for the Monte Carlo version, must
	 * replace "check(txt, offset)" by "check(offset)" in the code.
	 */
	public int search(String txt) {
		int N = txt.length();
		if (N < M)
			return N;
		long txtHash = hash(txt, M);

		// check for match at offset 0
		if ((patHash == txtHash) && check(txt, 0))
			return 0;

		// check for hash match; if hash match, check for exact match
		for (int i = M; i < N; i++) {
			// remove leading digit, add trailing digit, check for match
			txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;

			// match
			int offset = i - M + 1;
			if ((patHash == txtHash) && check(txt, offset))
				return offset;
		}

		// no match
		return N;
	}

	// a random 31-bit prime
	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}

	
	/**
	 * @author LCRO
	 * @param txt
	 * @param pat
	 * @return
	 */
	static int emparelhamentoDeCadeias(String txt, String padroes) {
		RabinKarp searcher = new RabinKarp(padroes);
		int offset = searcher.search(txt);
		return offset;

	}

	/**
	 * Test client. Examples of output:
	 * 
	 * <pre>
	 *  % java RabinKarp abracadabra abacadabrabracabracadabrabrabracad
	 *  pattern: abracadabra
	 *  text:    abacadabrabracabracadabrabrabracad 
	 *  match:                 abracadabra          
	 *
	 *  % java RabinKarp rab abacadabrabracabracadabrabrabracad
	 *  pattern: rab
	 *  text:    abacadabrabracabracadabrabrabracad 
	 *  match:           rab                         
	 *
	 *  % java RabinKarp bcara abacadabrabracabracadabrabrabracad
	 *  pattern: bcara
	 *  text:         abacadabrabracabracadabrabrabracad 
	 *
	 *  %  java RabinKarp rabrabracad abacadabrabracabracadabrabrabracad
	 *  text:    abacadabrabracabracadabrabrabracad
	 *  pattern:                        rabrabracad
	 *
	 *  % java RabinKarp abacad abacadabrabracabracadabrabrabracad
	 *  text:    abacadabrabracabracadabrabrabracad
	 *  pattern: abacad
	 * </pre>
	 */
	public static void main(String[] args) {


		
		/* PADRÃO DE FRASES  DT NN VBZ PARA W-WORD WHERE
		The station is located in Porto Alegre
		The cafeteria is on the first floo
		The nurse’s office is across from the elevator
		The bookstore is in the student center 
		The computer lab is on the third floo
		*/
				
		String pat = "DT NN VBZ";
		
		String[] r = new String[5];

	
		r[0] = "NN VBZ IN #";
		r[1] = "DT NN VBZ IN NN NN #";
		r[2] = "NN VBZ IN #";
		r[3] = "DT NN VBZ #";
		r[4] = "NN VBZ #";

		
		for (int i = 0; i < r.length; i++) {
			
			int offset = RabinKarp.emparelhamentoDeCadeias(r[i], pat);						
			if(offset==0){
              r[i] += " " + "Where";
	
			}
			
			
		}
		
		for (int i = 0; i < r.length; i++) {
			System.out.println("Regra : " + i + ")" + r[i] + " ");
			
			
		}
		

	}
}
