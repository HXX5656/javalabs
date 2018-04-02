

public class Queue {
       private static int elements;
       private static int start;
       private static int end;
       public   static  Object[] array;
       private static int size;
       //构造方法
    public Queue() {
        elements=1024;
        array=new Object[elements];
        start=0;
        end=0;
    }
    public Queue(int a) {
        elements=a;
        array=new Object[elements];
        start=0;
        end=0;
    }
    //获取行列大小
    public static int getSize() {
      if (empty()) {
          return 0;
      }
      else {
          return size;
      }
    }
//判断行列大小是否为空
    public static boolean empty() {
        return (size==0);
    }
    //在行列末尾加一个元素
    public static void add(Object value) {
        if (getSize()>=elements) {
            Object[] temp=new Object[array.length*2];
            System.arraycopy(array,0,temp,0,array.length);
            array=temp;
        }
        array[size++]=value;
        end++;
    }
    //在行列前去掉一个
    public static void de() {
        if (empty()) {
            System.out.print("该行列没有元素了，无法去除");
        }
        start++;
        size--;
    }
    //看最前方的
    public static Object peek() {
        return array[start];
    }

}
