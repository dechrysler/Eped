package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.GTree;
import es.uned.lsi.eped.DataStructures.GTreeIF;
import es.uned.lsi.eped.pract2019_2020.Node.NodeType;

public class Dictionary {

	private GTree<Node> dict; /* El diccionario es un árbol general de nodos */
	/* Constructor de la clase */
	static int palabras;
	public Dictionary() {
		super();
		dict= new GTree<Node>();
		dict.setRoot(new RootNode());
	}
	
	/* Método de inserción de una nueva palabra en el diccionario */
	public void insert(String word) {
		/* Insertamos la palabra a partir del nodo raíz del árbol */
		insertInTree(word,this.dict);
	}

	/* Metodo privado llamado por el anterior */
	private void insertInTree(String word, GTreeIF<Node> node) {
				
		if(word.length()>0){
				
					if(node.getNumChildren()==0){
						GTreeIF<Node> subarbol = new GTree<Node>();
						Node hijo = new LetterNode(word.charAt(0));
						subarbol.setRoot(hijo);
						node.addChild(node.getNumChildren()+1, subarbol);
						insertInTree(word.substring(1),node.getChild(node.getNumChildren()));
					}
					else {
						LetterNode aux;
						int i=1;
						boolean igual=false;
						do {
							if(node.getChild(i).getRoot().getNodeType()==NodeType.LETTERNODE) {
								aux= (LetterNode) node.getChild(i).getRoot();
								if(aux.getCharacter()==word.charAt(0))
									igual=true;
							}
							i++;
							
						}while(!igual && i<=node.getNumChildren());
						
						if(igual)
							insertInTree(word.substring(1),node.getChild(i-1));
						else {
							
								GTreeIF<Node> subarbol2= new GTree<Node>();
								Node hijo = new LetterNode(word.charAt(0));
								subarbol2.setRoot(hijo);
								boolean inserto=false;
								int count=1;
								LetterNode nodoaux;
								do {
									if(node.getChild(count).getRoot().getNodeType()==NodeType.LETTERNODE) {
										nodoaux=(LetterNode) node.getChild(count).getRoot();
										if(nodoaux.getCharacter()>word.charAt(0)) {
											node.addChild(count, subarbol2);
											inserto=true;
											}
									}
									count++;
									
								}while(!inserto && count<=node.getNumChildren());
								if(!inserto) {
									node.addChild(count, subarbol2);
									insertInTree(word.substring(1),node.getChild(count));
								}
								else
								{
									i--;
									insertInTree(word.substring(1), node.getChild(i));
								}
						}
					}
				}
				else {
					int count=1;
					boolean comprobar=false;
					while(!comprobar && node.getNumChildren()>=count) {
						
						if(node.getChild(count).getRoot().getNodeType()==NodeType.WORDNODE)
						{
							comprobar=true;
						}
						count++;
					}
					if(comprobar==false) {
						GTreeIF<Node> subarbol3 = new GTree<Node>();
						Node hoja = new WordNode();
						subarbol3.setRoot(hoja);
						node.addChild(1, subarbol3);
					}
				}
			
			}
		
	

	/* Método público de búsqueda de todas las palabras a partir de una secuencia */
	public WordList search(String sequence) {
		WordList salida = new WordList();           /* Variable donde construiremos la salida */
		searchInTree(sequence,"",this.dict,salida); /* Construimos la salida recursivamente */
		System.out.println(palabras);
		return salida;	
	}
	
	/* Método privado llamado por el anterior */
	private void searchInTree(String sequence, String word,
			GTreeIF<Node> node, WordList salida) {
		if(sequence.length()!=0){
			LetterNode nodo;
			for(int i=1;i<node.getNumChildren()+1;i++) {
				if(node.getChild(i).getRoot().getNodeType()==NodeType.WORDNODE)
				{
					palabras++;
					if(word.length()<=sequence.length()){
						String aux= sequence;
						boolean dentro=true;
						int count=0 ,sum;
						boolean contenido =false;
						do {
							contenido=false;
							sum=0;
							do {
								if(word.charAt(count)==aux.charAt(sum))
									contenido=true;
								sum++;
							}
							while(!contenido && sum<aux.length());
							
							if(contenido)
								aux=aux.replaceFirst(word.charAt(count)+"", "");
							else
								dentro=false;
							count++;
						}while(count<word.length() && dentro);
						if(dentro) {
							salida.add(word);
						}
					}
				}
				else {
					nodo=(LetterNode)node.getChild(i).getRoot();
					searchInTree(sequence,word+nodo.getCharacter(),node.getChild(i),salida);

				}
			}
		}
		}
		//hacer el metodo de buscar
	

	/* Método público de búsqueda de todas las palabras de tamaño size a partir de una secuencia */
	public WordListN search(String sequence, int size) {
		WordListN salida = new WordListN(size);           /* Variable donde construiremos la salida */
		searchInTreeN(sequence,"",this.dict,salida,size); /* Construimos la salida recursivamente */
		return salida;
	}
	
	/* Método privado llamado por el anterior */
	private void searchInTreeN(String sequence, String word,
							   GTreeIF<Node> node, WordListN salida,
							   int size) {
		if(sequence.length()!=0){
			LetterNode nodo;
			for(int i=1;i<node.getNumChildren()+1;i++) {
				if(node.getChild(i).getRoot().getNodeType()==NodeType.WORDNODE)
				{
					if(word.length()==size){
						boolean dentro=true;
						int count=0,sum;
						boolean contenido =false;
						do {
							contenido=false;
							sum=0;
							do {
								if(word.charAt(count)==sequence.charAt(sum))
									contenido=true;
								sum++;
							}
							while(!contenido && sum<sequence.length());
							
							if(contenido)
								sequence=sequence.replaceFirst(word.charAt(count)+"", "");
							else
								dentro=false;
							count++;
						}while(count<word.length() && dentro);
						if(dentro) {
							salida.add(word);
						}
					}
				}
				else {
					nodo=(LetterNode)node.getChild(i).getRoot();
					searchInTreeN(sequence,word+nodo.getCharacter(),node.getChild(i),salida,size);

				}
			}
		}
		
			}
	
}
