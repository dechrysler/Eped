package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

public class WordList {
	private ListIF<WordListN> wordList;
	
	public WordList() {
		this.wordList = new List<WordListN>();
	}
	
	public void add(String word) {
		if(this.wordList.size()==0) {
			this.wordList.insert(wordList.size()+1,new WordListN(word.length()));
			this.wordList.get(wordList.size()).add(word);
		}
		else
		{
			boolean encontrado= false;
			int i=1;
			do {
				if(this.wordList.get(i).getWordSize()==word.length())
				{
					encontrado=true;
					this.wordList.get(i).add(word);
				}
				i++;
			}
			while(i<=this.wordList.size() && encontrado==false);
			if(!encontrado) {
				this.wordList.insert(this.wordList.size()+1,new WordListN(word.length()));
				this.wordList.get(this.wordList.size()).add(word);
				int x,j,
				compare;
				WordListN aux;
				for(x=2;x<wordList.size()+1;x++) 
					for(j=wordList.size();j>=x;j--) {
						compare = wordList.get(j-1).getWordSize()-wordList.get(j).getWordSize();
						if(compare<0){
							aux=wordList.get(j-1);
							wordList.set(j-1,wordList.get(j));
							wordList.set(j, aux);
						}
					}
			}
		}
	}
	
	public String toString() {
		StringBuilder salida = new StringBuilder();
		for ( int pos = 1 ; pos < this.wordList.size()+1 ; pos++ ) {
			salida.append(this.wordList.get(pos).toString());
		}
		return salida.toString();
	}
}
