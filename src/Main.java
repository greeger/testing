import java.io.*;
import java.nio.charset.StandardCharsets;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.*;
import java.util.Random;

//import lists.List;
//import maps.Map;


public class Main {
    /*public static int fact(int n){
        if(n<1) return 0;
        if(n==1) return 1;
        else return n*fact(n-1);
    }*/

    /*public static String toUpper(String str){
        String rez="";
        boolean state=true;
        for(int i=0; i<str.length(); i++){
            char currChar=str.charAt(i);
            if(currChar=='.'){
                state=true;
                rez+=Character.toString(currChar);
            }
            else if(((currChar>='A'&&currChar<='Z')||(currChar>='А'&&currChar<='Я'))&&state){
                state=false;
                rez+=Character.toString(currChar);
            }
            else if(currChar>='a'&&currChar<='z'&&state){
                state=false;
                rez+=Character.toString(currChar+'A'-'a');
            }
            else if(currChar>='а'&&currChar<='я'&&state){
                state=false;
                rez+=Character.toString(currChar+'А'-'а');
            }
            else
                rez+=Character.toString(currChar);
        }
        return rez;
    }*/

    /*public static int countWords(String str) {
        boolean state=true;
        int count=0;
        for(int i=0; i<str.length(); i++){
            char currChar=str.charAt(i);
            if(currChar==' '&&!state){
                state=true;
                count++;
            }
            if(currChar!=' '&&state)
                state=false;
        }
        if(!state)count++;
        return count;
    }*/

    /*public static double[] merge(double[] a, double[] b){
        double[] rez=new double[a.length+b.length];
        int ai=0,bi=0;
        for(;ai+bi<a.length+b.length;){
            if(ai<a.length&&bi<b.length){
                if(a[ai]<b[bi]){
                    rez[ai+bi]=a[ai];
                    ai++;
                }
                else{
                    rez[ai+bi]=b[bi];
                    bi++;
                }
            }
            else if(ai==a.length){
                rez[ai+bi]=b[bi];
                bi++;
            }
            else if(bi==b.length){
                rez[ai+bi]=a[ai];
                ai++;
            }
        }
        return rez;
    }*/

    public static double[] task(double[] a) {
        if(Arrays.stream(a).anyMatch(x -> x<0)) throw new IllegalArgumentException("subzero");
        return Arrays.stream(a).collect(
                LinkedHashSet::new,
                (acc, item) -> {
                    acc.remove(item);
                    acc.add(item);
                },
                (acc1, acc2) -> acc1.addAll(acc2)
        ).stream().flatMapToDouble(x -> DoubleStream.of((Double)x)).toArray();
    }


    public static void main(String[] args) {
        PrintStream out;
        out = new PrintStream(System.out, true, StandardCharsets.UTF_8);


        /*Random rd = new Random();
        double[] arr = new double[1000000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rd.nextInt(0,arr.length);
        long time = System.currentTimeMillis();
        out.println(task(arr).length);
        out.println(System.currentTimeMillis() - time);*/


        List<OrderService.OrderData> listOfOrders = new ArrayList<>();
        listOfOrders.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "EUR", (long)2000));
        listOfOrders.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "USD", (long)15));
        listOfOrders.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "RUB", (long)200));
        listOfOrders.add(new OrderService.OrderData(OrderService.Type.PICKUP, "RUB", (long)1250));
        listOfOrders.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "USD", (long)35));
        listOfOrders.add(new OrderService.OrderData(OrderService.Type.PICKUP, "USD", (long)55));
        listOfOrders.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "RUB", (long)100));
        listOfOrders.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "EUR", (long)100));
        Map<String, Double> rezMap=OrderService.getMaxMinusMinDeliveryMapByCurrency(listOfOrders);
        for(Map.Entry<String, Double> entry : rezMap.entrySet()){
            out.println(entry.getKey()+" -> "+entry.getValue());
        }


        //File currFiler=new File("C:/Users/79371/Desktop/testingJaba/test.txt");

        /*try {
            FileWriter writer = new FileWriter(currFiler, true);
            String text = "Hello Gold!\n";
            writer.write(text);
            writer.append("рашн текст\n");
            writer.close();
        }
        catch(IOException ex){
            out.println("fuuu");
        }*/

        /*try {
            FileReader reader = new FileReader(currFiler);
            Scanner scaner = new Scanner(reader);
            while (scaner.hasNextLine()){
                String str = scaner.nextLine();
                out.println(str);
            }
            reader.close();
        }
        catch(IOException ex){
            out.println("fuuu");
        }*/

        //out.println(fact(5));

        //out.println();

        /*double[] massive = new double[]{2.4, -3.2, 0., 4.6, 2.99, -4., -0.0, 1.};
        double rez=1;
        for (int i=0; i<massive.length; i++) {
            if(massive[i]<0) rez*=Math.pow(massive[i],3);
        }
        out.println(rez);*/
        /*int k=3; double l=3.;
        out.println(k+" "+l);
        out.println((int)Math.pow(k,3)+" "+Math.pow(l,3));*/

        /*List<Double> myList=new LinkedList<>(new Double[]{2.4, -3.2, 0., 4.6, 2.99, -4., -0.0, 1.});
        myList.print(out);
        for(int i=0;i<12;i++)
            myList.insert((double)i,4+i);
        myList.print(out);
        myList.delete(8);
        myList.print(out);
        try {
            out.println(myList.getElem(myList.getSize()-1));
            out.println(myList.getElem(myList.getSize()));
        }
        catch (PositionOutOfBoundsException e){
            out.println(e.getMessage());
        }*/

        /*Map<Integer,String> myMap=new TreeMap<>();
        myMap.add(7,"seven");
        myMap.add(10,"ten");
        myMap.add(9,"nine");
        myMap.add(8,"eight");
        myMap.add(6,"six");
        myMap.add(5,"five");
        myMap.print(out);

        myMap.delete(7);
        myMap.print(out);
        myMap.add(11,"eleven");
        myMap.add(12,"twelve");
        myMap.print(out);*/

        //out.println(toUpper("рашн текст. И рашн буквы . я лох. А я нет в..в"));
        //out.println(toUpper("jfd.f. Am I loh? da. zdravo.   greg.   gfe"));

        //out.println(countWords(" e gds gd "));

        /*double[] a=new double[]{0.1, 3, 4, 10};
        double[] b=new double[]{-1, 5};
        double[] c=merge(a,b);
        for(int i=0; i<c.length; i++)
            out.print(c[i]+" ");*/

        //Stream<String> streamFromValues = Stream.of("a1", "a2", "a3", "a2");
        //out.println(streamFromValues.filter("a2"::equals).count());
    }
}