import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Customer {
        private int price;
        private String customer_name;
        private int customer_amount;
        private boolean customer_flag;
        public static void ChooseProductList() {
                Scanner scan = new Scanner(System.in);
        }

        public static void ChoiceChoose() throws IOException {
                String [] lists;
                String [] amount;
                int count1 = 1;
                int products;
                Scanner scan = new Scanner(System.in);
                String str = Menu.readFile("Product.txt");
                String[] strArr = null;
                String[] strArr2 = null;
                String st;
                String removeDigit;
                String [] storeCompare;
                int count = 0;
                System.out.print("How many products do you want? : ");
                products = scan.nextInt();
                lists = new String[products];
                amount = new String[products];
                storeCompare = new String[products];
                for (int i = 0; i < products; i++) {
                        System.out.print("Choose your list and amount with id " + "[" + count1++ + "]" + " : ");
                        lists[i] = scan.next();
                        amount[i] = scan.next();
                }
                strArr = str.split("\n");
                int index = strArr.length;
                for (int k = 0; k < index; k++){
                        char[] store1 = new char[3];
                        for (int j = 0; j < 3; j++){
                                store1[j] += (strArr[k].charAt(j));
                        }
                        for (int n = 0; n < lists.length; n++){
                                char [] convert = lists[n].toCharArray();
                                if (Arrays.equals(store1, convert)){
                                        storeCompare[n] = strArr[k];
                                }
                        }
                }
                String[] storeArr = null;
                String [] name_store;
                String [] total_price_store;
                String [] total_price;
                String [] price_unit;
                String [] price_unit_store;
                int totalLastPrice = 0;
                name_store = new String[storeCompare.length];
                total_price = new String[storeCompare.length];
                for (int i = 0; i < storeCompare.length; i++){
                        name_store[i] = Menu.removeAllDigit(storeCompare[i]);
                        total_price_store = storeCompare[i].split(" ");
                        total_price[i] = total_price_store[total_price_store.length - 2]; 
                }
                for (int i = 0; i < storeCompare.length; i++) {
                        
                }
                System.out.println(String.format("%s",
                                "------------------------------------------------------------------------"));
                System.out.println(String.format("%20s %15s %10s %5s %15s %2s", "Products", "|", "Amount", "|",
                                "Total-Price($)", "|"));
                System.out.println(String.format("%s",
                                "------------------------------------------------------------------------"));
                for (int i = 0; i < storeCompare.length; i++) {
                        totalLastPrice += Integer.parseInt(total_price[i]) * Integer.parseInt(amount[i]);
                        System.out.println(String.format("%30s %5s %10s %5s %15s %2s", name_store[i] + "(" + total_price[i] + ")", "|", amount[i], "|",
                                        Integer.parseInt(total_price[i]) * Integer.parseInt(amount[i]), "|"));
                }
                System.out.println(String.format("%s",
                                "------------------------------------------------------------------------"));
                System.out.println(String.format("%20s %15s %32s %2s", "Total", "|", totalLastPrice, "|"));
                System.out.println(String.format("%s",
                                "------------------------------------------------------------------------"));
                String readPrice = Menu.readFile("Store.txt");
                int storeLastPrice = 0;
                storeLastPrice = Integer.parseInt(readPrice.trim());
                totalLastPrice = totalLastPrice + storeLastPrice;
                readPrice = Integer.toString(totalLastPrice);
                try {
                        FileWriter myWriter = new FileWriter("Store.txt");
                        myWriter.write(readPrice);
                        myWriter.close();
                        System.out.println("Successfully to shopping!");
                        System.out.println("You store's have current balance : " + readPrice + " THB ");
                } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                }    
        }
}
