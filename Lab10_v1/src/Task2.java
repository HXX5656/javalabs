import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;
//这个类用来解决task2
public class Task2 {
    //l是文本里的英文字母数
    //to是现在密钥下标位置
    private static int l;
    private static int to;
    private static String answer="";
    private static String key="EVIL";
    public static void main(String[] args) throws FileNotFoundException {
        //读取文件并建文件
        Scanner output=new Scanner(new File("task2.txt"));
        Scanner output1=new Scanner(new File("task2.txt"));
        java.io.PrintWriter input=new java.io.PrintWriter("task2_plain.txt");
        PrintWriter s1=new PrintWriter(input);
        //判断文本里多少个英文字母相应填充密钥
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
    //分大小写以及非英文情况处理解密
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
