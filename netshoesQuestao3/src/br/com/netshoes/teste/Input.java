package br.com.netshoes.teste;


public class Input implements Stream {

	private String input;
	private int x = 0;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Input(String valor) {
		this.input = valor;
	}
	
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	@Override
	public char getNext() {
		return input.charAt(x++);
	}

	@Override
	public boolean hasNext() {
		if (input.length() > x) return true;
		return false;
	}

}
