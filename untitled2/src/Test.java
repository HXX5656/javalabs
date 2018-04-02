import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test {
    private static String answer="";
    private static String key="EVIL";
    private static String text="";
    public static void main(String[] args) throws FileNotFoundException {
        decrypt(args[0]);

        // 代码规范
    }
    private static void decrypt(String path) throws FileNotFoundException {
        int l=0;
        int to=0;
        File file=new File(path);
        Scanner output=new Scanner(file);
        Scanner output1=new Scanner(file);
        String x=file.getName();
        String x1="";
        for(int i=0;i<x.length();i++) {
            if(x.charAt(i)=='.') {
                break;
            }
            else{
                x1+=x.charAt(i);
            }
        }
        x=x1;
        java.io.PrintWriter input=new java.io.PrintWriter(x+"_plain.txt");
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
            text=output.nextLine();
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
            s1.println(answer);
        }
        output.close();
        s1.close();


    }
}
