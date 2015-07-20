package br.com.netshoes.teste;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para terceira questao
 * @author Marcelo
 *
 */
public class Teste {

	public static void main(String[] args) {

		char val = firstChar(new Input("ababc"));
		
		if (val != 0) {
			System.out.println("O primeiro caracter que nao se repete �: [" + val + "]");
		} else {
			System.out.println("N�o existe caractere que n�o se repete no conteudo.");
		}
	}

	public static char firstChar(Stream input) {

		List<String> chars = new ArrayList<String>();
		List<String> compareChars = new ArrayList<String>();

		//Realizo a leitura da stream e coloco as informa��es em uma ArrayList
		while (input.hasNext()) {
			chars.add(String.valueOf(input.getNext()));
		}

		//Adiciono as mesmas informa��es em uma segunda ArrayList para compara��o das informa��es
		compareChars.addAll(chars);

		//Realizo a itera��o do primeiro array chars
		for (int count = 0; count <= chars.size(); count++) {
			
			if (count != chars.size()) {
				
				//Removo o caracter a ser comparado do segundo array compareChars
				compareChars.remove(count);
				
				//Verifico se o caracter ainda existe no array comparador
				if (!compareChars.contains(chars.get(count))) {
					
					//Caso nao exista, quer dizer que o caractere nao se repete e retorno
					return chars.get(count).charAt(0);
				}
				
				//Caso o caracter ainda exista, readiciono todas as informa��es do primeiro array
				compareChars = new ArrayList<String>();
				compareChars.addAll(chars);
			}
		}

		return 0;
	}
}
