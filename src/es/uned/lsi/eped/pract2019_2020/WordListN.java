package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.List;

public class WordListN {
	/* Atributos de la clase con la estructura adecuada */
	private int size;
	private List<String> listOfWords;
	

	public WordListN(int size) {
		this.listOfWords=new List<String>();
		this.size= size;
	}
	
	public void add(String word) {
		listOfWords.insert(listOfWords.size()+1, word);
	}
	
	public int getWordSize() {
		return size;
	}
	
	public String toString() {
		StringBuilder salida = new StringBuilder();
		int numPalabras = listOfWords.size(); /* Longitud de la secuencia de palabras */
		salida.append("-Palabras de ");
		salida.append(this.getWordSize());
		salida.append(" letra");
		if ( this.getWordSize() > 1 ) { salida.append('s'); }
		salida.append(": ");
		String word;
		for (int pos = 1 ; pos < numPalabras+1 ; pos++) {
			 
			word = listOfWords.get(pos);
			salida.append(word);
			
			if ( pos < numPalabras ) 
				salida.append(", ");
			
		}
		salida.append('\n');
		return salida.toString();
	}
}
