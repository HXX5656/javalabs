public class Test {
    //很多人要打疫苗，开始时假设10个人正在排队打疫苗，每人打疫苗需要3分钟，问15分钟后轮到第几个人打疫苗
    public static void main(String[] args) {
        int min=3;
        int pe=10;
        int minAll=15;
        Queue queue=new Queue(5);
        for (int i=1;i<=pe;i++) {
            queue.add(i);
        }
        for (int j=1;j<=(minAll/min);j++) {
            queue.de();
        }
        System.out.print(""+queue.peek());
    }
}
