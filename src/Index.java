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
                    int[] filtered = filterBank(cash);
                    break;
                case 2:Statistics(total);
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
            if(total<100){
                v[i] = Integer.parseInt(JOptionPane.showInputDialog("Insira a nota de R$" + values[i]));
            }else{
                JOptionPane.showMessageDialog(null, "Limite de depósito atingido.");
                return v;
            }
            total = total + v[i];
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

    public static int[] Take(int [] v, int value){
        int[] cash = new int[7];
        if(v[6]!=0){
            while(value>=200){
                value= value - 200;
                v[6] = v[6]-1;
                cash[6] = cash[6] + 1;
                if(v[6]==0){
                    break;
                }
            }
        }

        if(v[5]!=0){
            while(value>=100){
                value = value - 100;
                v[5] = v[5] - 1;
                cash[5] = cash[5] + 1;
                if(v[5]==0){
                    break;
                }
            }
        }

        if(v[4]!=0){
            while(value>=50){
                value = value - 50;
                v[4] = v[4] - 1;
                cash[4] = cash[4] + 1;
                if(v[4]==0){
                    break;
                }
            }
        }

        if(v[3]!=0){
            while(value>=20){
                value = value - 20;
                v[3] = v[3] - 1;
                cash[4] = cash[4] + 1; 
                if(v[3]==0){
                    break;
                }
            }
        }
        
        if(v[2]!=0){
            while(value>=10){
                value = value - 10;
                v[2] = v[2] - 1;
                cash[2] = cash[2] + 1;
                if(v[2]==0){
                    break;
                }
            }
        }
        
        if(v[1]!=0){
            while(value>=5){
                value = value - 5;
                v[1] = v[1] - 1;
                cash[1] = cash[1] + 1;
                if(v[1]==0){
                    break;
                }
            }
        }
        
        if(v[0]!=0){
            while(value>=2){
                value = value - 2;
                v[0] = v[0] - 1;
                cash[0] = cash[0] + 1;
                if(v[0]==0){
                    break;
                }
            }
        }
        
        return cash;


    }

    public static int filterBank(){

    }

    public static void Statistics(int total){
        JOptionPane.showMessageDialog(null, total);
    }
}
