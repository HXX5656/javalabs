import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Task2 {
    private static int l;
    private static int to;
    private static String answer="";
    private static String key="EVIL";
    public static void main(String[] args) throws FileNotFoundException {
        Scanner output=new Scanner(new File("task2.txt"));
        Scanner output1=new Scanner(new File("task2.txt"));
        java.io.PrintWriter input=new java.io.PrintWriter("task2_plain.txt");
        PrintWriter s1=new PrintWriter(input);
        for(;output1.hasNext();) {
            String m=output1.next();
            for(int i=0;i<m.length();i++) {
                if(Character.isUpperCase(m.charAt(i))||Character.isLowerCase(m.charAt(i))) {
                    l++;
                }
            }
        }
        output1.close();
        int p = key.length();
        int n = key.length()+1;
        while (n<=l) {
            char m = key.charAt((n-1)% p);
            n++;
            key += m;
        }
        for(;output.hasNext();) {
            decrypt(output.nextLine());
            s1.println(answer);
        }
        output.close();
        s1.close();
    }
    private static void decrypt(String text) {
            int a = 0;int b = 0;int c = 0;
            String letterU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String letterL = "abcdefghijklmnopqrstuvwxyz";
            String decryptedMessage = "";

            for(int i = 0;i < text.length();i++) {
                if(Character.isUpperCase(text.charAt(i))) {
                for(int j = 0;j < 26;j++) {

                    if(letterU.charAt(j) == text.charAt(i))
                        a = j;
                    if(letterU.charAt(j) == key.charAt(to))
                        b = j;

                }
                if(a >= b)
                    c = a - b;
                if(a < b)
                    c = 26 - (b - a);
                decryptedMessage += letterU.charAt(c);
                to++;
                }
                else if(Character.isLowerCase(text.charAt(i))) {
                    for(int j = 0;j < 26;j++) {

                        if(letterL.charAt(j) == text.charAt(i))
                            a = j;
                        if(letterU.charAt(j) == key.charAt(to))
                            b = j;

                    }
                    if(a >= b)
                        c = a - b;
                    if(a < b)
                        c = 26 - (b - a);
                    decryptedMessage += letterL.charAt(c);
                    to++;
                }
                else {
                    decryptedMessage+=text.charAt(i);
                }
            }
            answer=decryptedMessage;
        }


}
