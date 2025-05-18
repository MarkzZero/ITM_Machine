import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
public class Index{
	public static void main(String[] args) throws IOException {
		int[] notas = new int[7];
		int total = 0;
		int media = 0;
		int quantidade = 0;
		int sobras = 0;
		int vTotal = 0;
		boolean continuar = true;
		
		// MENU
		while(continuar) {
			String [] opcoes = {"Carregar Notas", "Retirar Notas", "Estatística", "Encerrar"};
			int escolha = JOptionPane.showOptionDialog(null, "Escolha um opção:", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
			switch(escolha) {
			case 0:
				notas = Carregar();
				total = vInicial(notas);
				break;
			case 1:
				int[] resultado = Sacar(notas);
				media = resultado[0];
				quantidade = resultado[1];
				vTotal = resultado[2];
				sobras = sobras(notas);
				break;
			case 2:
				Estatisticas(total, media, quantidade, sobras, vTotal);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Programa encerrado.");
				System.exit(0);
			}
			
		}
		
	}
	
	
	// Depositar as notas.
	
	public static int[] Carregar(){
		// Aqui, criei um vetor com o valores das notas em String para facilitar a demonstração na caixa de diálogo do JOptionPane.
		String[] valores = {"2.00", "5.00", "10.00", "20.00", "50.00", "100.00", "200.00"};
		
		int[] notas = new int[7];
		
		for(int i = 0; i<notas.length;i++) {
			notas[i] = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade de notas de "+ valores[i]));
			while(notas[i]>100 || notas[i]<0) {
				notas[i] = Integer.parseInt(JOptionPane.showInputDialog("Número de notas inválido!! Insira a quantidade de notas de "+ valores[i]));
			}
		}
		return notas;
	}
	
	//Verifica qual o valor inicial das notas antes do saque.
	public static int vInicial(int[] notas) {
		int[] valores = {2, 5, 10, 20, 50, 100, 200};
		int total = 0;
		for(int i=0; i<notas.length; i++) {
			int n = valores[i]*notas[i];
			total = total + n;
		}
		return total;
	}
	
	// Sacar o dinheiro.
	
	public static int[] Sacar(int[] notas) {
		int j = 0;
		int soma = 0;
		int media = 0;
		int total = 0;
		
		while(j<100 || !Vazio(notas)) {
			String input = JOptionPane.showInputDialog("Insira a quantidade a sacar:");
			
			if(input==null) {
				break;
			}
			
			
			int value;
			try {
				value = Integer.parseInt(input);
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "VALOR SOLICITADO NÃO PODE SER SACADO.");
				continue;
			}
			
			if(value<=0) {
				JOptionPane.showMessageDialog(null, "VALOR SOLICITADO NÃO PODE SER SACADO.");
				continue;
			}
			
			if(value==3) {
				JOptionPane.showMessageDialog(null, "VALOR SOLICITADO INVÁLIDO.");
				continue;
			}
			
			if(value<2||value>3000) {
				JOptionPane.showMessageDialog(null, "VALOR SOLICITADO INVÁLIDO.");
				continue;
			}
			
			int[] resultado = tentarSaque(notas, value);
			j++;
			soma = soma + value;
			total += value;
		}
		
		
		if(Vazio(notas)) {
			JOptionPane.showMessageDialog(null, "EXCEDEU O LIMITE DO CAIXA.");
		}
		
	    media = (j>0) ? soma / j : 0;
	    return new int[] {media, j, total};
	}
	

	public static int[] tentarSaque(int[] notas, int value) {
		int[] cash = new int[7];
		int[] valores = {2, 5, 10, 20, 50, 100, 200};
		
		for(int i=6 ;i>=0; i--) {
			while(value >= valores[i] && notas[i]>0) {
				value -= valores[i];
				notas[i]--;
				cash[i]++;
			}
		}
		
		if(value>0) {
			//Caso o saque for incompleto: devolve as notas.
			for(int i=0; i<7; i++) {
				notas[i] += cash[i];
			}
			JOptionPane.showMessageDialog(null, "Não foi possível sacar esse valor com as notas disponíveis.");
			return new int[7]; // vazio
		}
		
		String[] vNotas = {"2.00", "5.00", "10.00", "20.00", "50.00", "100.00", "200.00"};
		StringBuilder recibo = new StringBuilder();
		for(int i=0;i<7; i++) {
			if(cash[i]>0) {
				recibo.append("R$").append(vNotas[i]).append(": ").append(cash[i]).append("\n");
			}
		}
		
		JOptionPane.showMessageDialog(null, recibo.toString());
		return cash;
	}
	
	//Verifica o que sobrou no caixa.
	public static int sobras(int[] notas) {
		int[] valores = {2, 5, 10, 20, 50, 100, 200};
		int sobras = 0;
		for(int i=0; i<notas.length;i++) {
			sobras += notas[i] * valores[i];
		}
		return sobras;
	}
	
	
	
	//Verifica se o vetor com as notas está vazio.
	public static boolean Vazio(int[] notas) {
		for(int i=0; i<notas.length;) {
			if(notas[i]!=0) {
				return false;
			}
		}
		return true;
	}
	
	
	
	//Apenas uma função teste.
	public static void VerificarVetor(int[] notas) {
		StringBuilder mostrar = new StringBuilder();
		for(int i=0; i<notas.length;i++) {
			mostrar.append(notas[i]).append(" ");
		}
		
		JOptionPane.showMessageDialog(null, mostrar.toString());
		
	}
	
	public static void Estatisticas(int total, int media, int quantidade, int sobras, int vTotal) throws IOException {
		JOptionPane.showMessageDialog(null, "Valor total inicial antes dos saques: " + total + "\n"
	                + "A média dos saques: " + media + "\n"
	                + "Valor total dos saques: " + vTotal + "\n"
	                + "Quantidade de saques: " + quantidade + "\n"
	                + "Valor total das sobras do caixa: " + sobras);
		
	    String arquivo = "Resumo.txt";

	    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {
	        escritor.write("Valor total inicial antes dos saques: " + total + "\n"
	                + "A média dos saques: " + media + "\n"
	                + "Valor total dos saques: " + vTotal + "\n"
	                + "Quantidade de saques: " + quantidade + "\n"
	                + "Valor total das sobras do caixa: " + sobras);
	    } catch (IOException erro) {
	        JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo");
	        erro.printStackTrace();
	    }

	}

}