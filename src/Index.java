import javax.swing.JOptionPane;
public class Index {
    public static void main(String[] args) {
        int[] v = new int [7];
        int total = 0, value = 0;
        boolean keepRunning = true;

        while(keepRunning){
            String [] options = {"Carregar", "Sacar dinheiro", "Estatísticas", "Encerrar"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice){
                case 0:
                    v = Load();
                    total = Total(v);
                    break;
                case 1:
                	
	                    value = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade a ser sacado:"));
	                    while(value < 0 || value == 3){
	                        value = Integer.parseInt(JOptionPane.showInputDialog("Valor inválido!! Insira a quantidade a ser sacado:"));
	                    }
                    int[] cash = Take(v, value);

                    break;
                case 2:Statistics(total, v);
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }

    }
    
    

    public static int[] Load(){
        String[] values = {"2.00", "5.00", "10.00", "20.00", "50.00", "100.00", "200.00"};
        int[] v = new int [7];
        int total = 0;

        for(int i = 0; i<=6; i++){
        	
        	do {
        		v[i] = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade de notas de R$" + values[i]));
        	}while(v[i]>100 || v[i]<0);
        }

        return v;
    }

    public static int Total(int[] v){
        int[] values = {2, 5, 10, 20, 50, 100, 200};
        int total = 0;
        for(int i = 0;i<=6;i++){
            int n =  v[i] * values[i];
            total = total + n;
        }

        return total;
    }

    public static int[] Take(int[] v, int value) {
        int[] cash = new int[7];
        int[] valores = {2, 5, 10, 20, 50, 100, 200};
        int j = 0;
        
        //refazer essa parte
        
        return cash;
    }
        
        public static boolean zeroV(int[] v) {
    		for(int i = 0; i<v.length; i++) {
    			if(v[i]!=0) {
    				return false;
    			}
    		}
    		return true;
        }
    	


    public static void Statistics(int total, int[] v){
    JOptionPane.showMessageDialog(null, total);
    	StringBuilder mostrar = new StringBuilder();
    	for(int i = 0; i < v.length; i++) {
    		mostrar.append(v[i]).append(" ");
    	}
    	
    	JOptionPane.showMessageDialog(null, mostrar.toString());
        
    }
}
