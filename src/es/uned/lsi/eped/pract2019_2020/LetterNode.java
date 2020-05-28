package es.uned.lsi.eped.pract2019_2020;

public class LetterNode extends Node {
	private char car;

	public LetterNode(char car) {
		this.car=car;
	}
	@Override
	public NodeType getNodeType() {
		return NodeType.LETTERNODE;
	}
	
	public char getCharacter() {
		return car;
	}
}
