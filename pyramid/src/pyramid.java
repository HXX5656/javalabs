import javax.swing.*;


public class pyramid {
    public static void main(String[] args) {
        String integerStr = JOptionPane.showInputDialog("please enter an integer from 1 to 15: ");
        int integer = Integer.parseInt(integerStr);
        String output = "";
        if (integer>=1 & integer<=9) {
            nextRow:
          for (int row=1;row<=integer;row++) {
            output += "\n";
            for (int column = 1;column <= 4*(integer-1)+2;column++) {
              if (column<= 2*(integer - row) ){
                  output += " ";
              }
              else if (column <= 2*(integer-1)+2*row ){
                  output = output +"" + (Math.abs((column-1)/2-integer+1)+1+" ");
                  column++;
              }
              else {
                  continue nextRow;
              }
            }
          }
           System.out.print(""+ output);}
        else if (integer<=15){
            nextRow:
            for (int row=1;row<=integer;row++) {
                output += "\n";
                if (row<=9) {
                    int line=row-1;
                for (int column = 1;column <= 34+6*(integer-9);column++) {
                    if (column< 3*(integer - 9)+2*(9-row) + 1){
                        output += " ";
                    }
                    else if (column <= 3*(integer-9)+2*(9-row)+2*(2*row-1) ){


                        output = output +" " + (Math.abs(line)+1);
                        line--;

                        column++;
                    }
                    else {
                        continue nextRow;}

                }
                }
                else{
                    int line=row-1;
                for (int column = 1;column <= 34+6*(integer-9) ;column++) {
                    if (column< 3*(integer -row) + 1){
                        output += " ";
                    }
                    else if (column <= 3*(integer-row)+34+6*(row-9) ){

                        output = output +" " + (Math.abs(line)+1);
                        line--;
                        if (Math.abs(line)+1>=10){
                            column=column+2;
                        }
                        else {
                        column++;
                        }
                    }
                    else {
                        continue nextRow;}
                }
                }
            }
            System.out.print(""+ output);
        }
        else {
           System.out.println("您所输入的值不在运行范围内！");
        }

        System.exit(0);
    }
}
