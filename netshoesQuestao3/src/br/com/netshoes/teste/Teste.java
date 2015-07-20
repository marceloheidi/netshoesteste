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
			System.out.println("O primeiro caracter que nao se repete é: [" + val + "]");
		} else {
			System.out.println("Não existe caractere que não se repete no conteudo.");
		}
	}

	public static char firstChar(Stream input) {

		List<String> chars = new ArrayList<String>();
		List<String> compareChars = new ArrayList<String>();

		//Realizo a leitura da stream e coloco as informações em uma ArrayList
		while (input.hasNext()) {
			chars.add(String.valueOf(input.getNext()));
		}

		//Adiciono as mesmas informações em uma segunda ArrayList para comparação das informações
		compareChars.addAll(chars);

		//Realizo a iteração do primeiro array chars
		for (int count = 0; count <= chars.size(); count++) {
			
			if (count != chars.size()) {
				
				//Removo o caracter a ser comparado do segundo array compareChars
				compareChars.remove(count);
				
				//Verifico se o caracter ainda existe no array comparador
				if (!compareChars.contains(chars.get(count))) {
					
					//Caso nao exista, quer dizer que o caractere nao se repete e retorno
					return chars.get(count).charAt(0);
				}
				
				//Caso o caracter ainda exista, readiciono todas as informações do primeiro array
				compareChars = new ArrayList<String>();
				compareChars.addAll(chars);
			}
		}

		return 0;
	}
}
