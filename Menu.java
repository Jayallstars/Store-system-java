import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.text.StyledEditorKit.BoldAction;

public class Menu extends Main {
        private static String[] id;
        private String[] name;
        private double[] price;
        private int[] qty;

        public static void ClearConsoleScreen() { // clear screen before display
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }

        public static void welcome() { // List name program and name author
                String text;
                Scanner scan = new Scanner(System.in);
                System.out.println("********************STORE'S PROGRAME********************");
                System.out.println("Author // Jay Kamolchai ");
                System.out.print("Press enter to continue to store.... ");
                try {
                        System.in.read();
                        mainMenu();
                } catch (Exception e) {
                }
        }

        public static void printNull() { // Print null text
                for (int n = 1; n <= 10; n++)
                        System.out.print(" ");
        }

        public static void mainMenu() throws IOException { // List main menu
                String text;
                Scanner scan = new Scanner(System.in);
                ClearConsoleScreen();
                System.out.println("*************STORE'S SHOP**************");
                printNull();
                System.out.println("Choose your option");
                printNull();
                System.out.println("1.Owner section");
                printNull();
                System.out.println("2.Customer section");
                printNull();
                System.out.println("3.Exit programe");
                printNull();
                System.out.print("Enter your option : ");
                text = scan.next();
                switch (text) {
                        case "1":
                                ownerSection();
                                break;
                        case "2":
                                customerSection();
                                break;
                        case "3":
                                System.exit(0);
                                break;
                        default:
                                System.out.println("Out of bound !!");
                }
        }

        public static void customerSection() throws IOException{ // List customer menu
                char text;
                String flagCus = "cus_check";
                Scanner scan = new Scanner(System.in);
                Main main = new Main();
                ClearConsoleScreen();
                System.out.println("*************STORE'S SHOP CUSTOMER*************");
                printNull();
                System.out.println("Choose your option");
                printNull();
                System.out.println("1.Product Lists");
                printNull();
                System.out.println("2.Back to main menu");
                printNull();
                System.out.println("3.Exit programe");
                printNull();
                System.out.print("Enter your option : ");
                text = scan.next().charAt(0);
                if (text == '1') {
                        ClearConsoleScreen();
                        main.setFlag_check(flagCus);
                        printProductList();
                }
                else if (text == '2') {
                        mainMenu();;
                }
                else if (text == '3'){
                        System.exit(0);
                } else {
                        System.out.println("Out of bound !!");
                }
        }

        public static void goBacktoOwner() throws IOException {
                ownerSection();
        }

        public static void ownerSection() { // List owner menu
                int choice;
                String choice_in;
                String flagOwner = "owner_check";
                Main main = new Main();
                Scanner scan = new Scanner(System.in);
                ClearConsoleScreen();
                System.out.println("*************STORE'S SHOP OWNER*************");
                printNull();
                System.out.println("Choose your option");
                printNull();
                System.out.println("1.Look all products");
                printNull();
                System.out.println("2.Add product");
                printNull();
                System.out.println("3.Delete product");
                printNull();
                System.out.println("4.Back to main menu");
                printNull();
                System.out.print("Enter your option : ");
                choice = scan.nextInt();
                if (choice == 1){
                        ClearConsoleScreen();
                        main.setFlag_check(flagOwner);
                        try {
                                printProductList();
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }           
                }
                else if (choice == 2){
                        ClearConsoleScreen();
                        addProducts();
                }
                else if (choice == 3){
                        ClearConsoleScreen();
                        deleteProducts();
                }
                else if (choice == 4){
                        try {
                                mainMenu();
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                }
        }

        public static String capitalize(String str) {
                if (str == null || str.isEmpty()) {
                        return str;
                }

                return str.substring(0, 1).toUpperCase() + str.substring(1);
        }

        public static void addProducts() {
                String [] productsName;
                String [] productsPrice;
                String [] productsQty;
                String [] id;
                String amount;
                boolean flag = false;
                int count = 1,index = 0,index_id = 0,id_add = 0,total_product_price = 0,totalLastPrice = 0;
                Scanner scan = new Scanner(System.in);
                System.out.print("How many products do you want to add? : ");
                amount = scan.nextLine();
                index = Integer.parseInt(amount);
                productsName = new String[index];
                productsPrice = new String[index];
                productsQty = new String[index];
                for (int i = 0; i < index; i++){
                        System.out.print("Enter your name of products " + "[" + count + "]" + " : ");
                        productsName[i] = scan.nextLine();
                        System.out.print("Enter your price of products " + "[" + count + "]" + " : ");
                        productsPrice[i] = scan.nextLine();
                        System.out.print("Enter your quantity of products " + "[" + count + "]" + " : ");
                        productsQty[i] = scan.nextLine();
                        total_product_price += Integer.parseInt(productsPrice[i]) * Integer.parseInt(productsQty[i]);
                        count++;
                }
                try {
                        String readPrice;
                        readPrice = Menu.readFile("Store.txt");
                        int storeLastPrice = 0;
                        storeLastPrice = Integer.parseInt(readPrice.trim());
                        if (storeLastPrice > total_product_price) {
                                try {
                                        for (int i = 0; i < index; i++) {
                                                index_id = pullIdFromText().length;
                                                index_id = index_id + 2;
                                                String data = "0" + index_id + " " + capitalize(productsName[i]) + " "
                                                                + productsPrice[i] + " "
                                                                + productsQty[i];
                                                File file = new File(
                                                                "C:\\Users\\acer\\Desktop\\GitHub\\Store-system-java\\Product.txt");
                                                // if file doesnt exists, then create it
                                                if (!file.exists()) {
                                                        file.createNewFile();
                                                        System.out.println("New File Created Now");
                                                }
                                                String[] allId = pullIdFromText();
                                                String[] allName = pullNameFormText();
                                                String[] allQty = pullQuantityFromText();
                                                int temp;
                                                for (int j = 0; j < pullIdFromText().length; j++) {
                                                        String compare1 = allName[j].toLowerCase().trim();
                                                        String compare2 = productsName[i].toLowerCase().trim();
                                                        if (compare1.equals(compare2)) {
                                                                flag = true;
                                                                temp = Integer.parseInt(allQty[j].trim());
                                                                temp += Integer.parseInt(productsQty[i]);
                                                                allQty[j] = String.valueOf(temp);
                                                                String str = Menu.readFile("Product.txt");
                                                                String[] strArr = null;
                                                                String store;
                                                                strArr = str.split("\n");
                                                                store = allId[j] + " ";
                                                                store += capitalize(productsName[i]) + " ";
                                                                store += productsPrice[i] + " ";
                                                                store += temp;
                                                                store = store.trim();
                                                                strArr[j] = store;
                                                                FileWriter writer = new FileWriter("Product.txt");
                                                                for (String st : strArr) {
                                                                        writer.write(st + System.lineSeparator());
                                                                }
                                                                writer.close();
                                                                System.out.println("Success! Add : " + productsName[i]
                                                                                + " : "
                                                                                + temp + " left");
                                                                break;
                                                        }
                                                }
                                                if (flag == false) {
                                                        // true = append file
                                                        FileWriter fileWritter = new FileWriter(file, true);
                                                        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                                                        bufferWritter.write("\n" + data);
                                                        bufferWritter.close();
                                                        fileWritter.close();
                                                        System.out.println("Success! Add : " + productsName[i] + " : "
                                                                        + productsQty[i] + " left");
                                                        break;
                                                }
                                        }
                                        {
                                                totalLastPrice = storeLastPrice - total_product_price;
                                                readPrice = Integer.toString(totalLastPrice);
                                                try {
                                                        FileWriter myWriter = new FileWriter("Store.txt");
                                                        myWriter.write(readPrice);
                                                        myWriter.close();
                                                        System.out.println("Successfully to add products!");
                                                        System.out.println("You store's have current balance : " + readPrice + " THB ");
                                                } catch (IOException e) {
                                                        System.out.println("An error occurred.");
                                                        e.printStackTrace();
                                                }
                                        }
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        } else {
                                System.out.println("Your balance store's is not enough!");
                                System.out.print("Go back to owner's press enter");
                                try {
                                        System.in.read();
                                        ownerSection();
                                } catch (Exception e) {
                                }
                        }
                } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }   
        }

        public static void deleteProducts() {
                String[] productsName;
                String[] productsPrice;
                String[] productsQty;
                String[] id;
                String amount;
                boolean flag = false;
                int count = 1, index = 0, index_id = 0, id_add = 0;
                Scanner scan = new Scanner(System.in);
                System.out.print("How many products do you want to delete? : ");
                amount = scan.nextLine();
                index = Integer.parseInt(amount);
                productsName = new String[index];
                productsPrice = new String[index];
                productsQty = new String[index];
                for (int i = 0; i < index; i++) {
                        System.out.print("Enter your name of products " + "[" + count + "]" + " : ");
                        productsName[i] = scan.nextLine();
                        System.out.print("Enter your quantity of products " + "[" + count + "]" + " : ");
                        productsQty[i] = scan.nextLine();
                        count++;
                }
                try {
                        for (int i = 0; i < index; i++) {
                                String[] allId = pullIdFromText();
                                String[] allName = pullNameFormText();
                                String[] allQty = pullQuantityFromText();
                                String[] allprice = pullPriceFormText();
                                int temp;
                                index_id = pullIdFromText().length;
                                index_id = index_id + 2;
                                File file = new File(
                                                "C:\\Users\\acer\\Desktop\\GitHub\\Store-system-java\\Product.txt");
                                // if file doesnt exists, then create it
                                if (!file.exists()) {
                                        file.createNewFile();
                                        System.out.println("New File Created Now");
                                }     
                                for (int j = 0; j < pullIdFromText().length; j++) {
                                        String compare1 = allName[j].toLowerCase().trim();
                                        String compare2 = productsName[i].toLowerCase().trim();
                                        if (compare1.equals(compare2)) {
                                                flag = true;
                                                temp = Integer.parseInt(allQty[j].trim());
                                                temp -= Integer.parseInt(productsQty[i]);
                                                allQty[j] = String.valueOf(temp);
                                                String str = Menu.readFile("Product.txt");
                                                String[] strArr = null;
                                                String store;
                                                strArr = str.split("\n");
                                                store = allId[j] + " ";
                                                store += capitalize(productsName[i]);
                                                store += allprice[j] + " ";
                                                store += temp;
                                                store = store.trim();
                                                strArr[j] = store;
                                                FileWriter writer = new FileWriter("Product.txt");
                                                for (String st : strArr) {
                                                        writer.write(st + System.lineSeparator());
                                                }
                                                writer.close();
                                                System.out.println("Delete Success : " + productsName[i] + " : " + temp + " left");
                                                break;
                                        }
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

        public static void printProductList() throws IOException { // Print list of products
                String[] id = pullIdFromText();
                String[] name = pullNameFormText();
                String[] price = pullPriceFormText();
                String[] qty = pullQuantityFromText();
                System.out.println(String.format("%s",
                                "---------------------------------------------------------------------------------------"));
                System.out.println(String.format("%10s %10s %20s %15s %12s %3s %5s %5s", "Id", "|", "Item", "|",
                                "Price($)", "|", "Qty", "|"));
                System.out.println(String.format("%s",
                                "---------------------------------------------------------------------------------------"));
                int index = id.length;
                final Object[][] table = new String[index][];
                for (int i = 0; i < index; i++) {
                        table[i] = new String[] { id[i], "|", name[i], "|", price[i], "|", qty[i], "|" };
                }
                for (int i = 0; i <= index; i++) {
                        System.out.println(String.format("%10s %10s %25s %10s %8s %7s %5s %5s %n", id[i], "|", name[i],
                                        "|",
                                        price[i], "|", qty[i], "|"));
                }
                System.out.println(String.format("%s",
                                "---------------------------------------------------------------------------------------"));
        }

        public String[] getId() {
                return this.id;
        }

        public String[] getName() {
                return this.name;
        }

        public double[] getPrice() {
                return this.price;
        }

        public int[] getQty() {
                return this.qty;
        }

        public static String readFile(String fileName) throws IOException { // Read file from text
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                try {
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();

                        while (line != null) {
                                sb.append(line);
                                sb.append("\n");
                                line = br.readLine();
                        }
                        return sb.toString();
                } finally {
                        br.close();
                }
        }

        public static String[] pullIdFromText() throws IOException { // Pull id from text
                String str = Menu.readFile("Product.txt");
                String[] strArr = null;
                strArr = str.split(" ");
                str = str.replaceAll("[^-?0-9]+", " ");
                List stringList = Arrays.asList(str.trim().split(" "));
                String store = stringList.toString();
                store = store.replace("[", " ");
                store = store.replace("]", " ");
                String[] strArr1 = null;
                strArr1 = store.split(",");
                String[] strArr2 = new String[strArr1.length];
                for (int i = 0; i < strArr1.length; i++) {
                        if (i % 3 == 0) {
                                strArr2[i] = strArr1[i];
                        } else {
                                strArr2[i] = null;
                        }
                }
                List<String> list = new ArrayList<String>(Arrays.asList(strArr2));
                list.removeAll(Arrays.asList("", null));
                String[] array = new String[list.size()];
                array = list.toArray(array);
                return array;
        }

        public static String[] pullPriceFormText() throws IOException { // Pull price from text
                String str = Menu.readFile("Product.txt");
                String[] strArr = null;
                strArr = str.split(" ");
                str = str.replaceAll("[^-?0-9]+", " ");
                List stringList = Arrays.asList(str.trim().split(" "));
                String store = stringList.toString();
                store = store.replace("[", " ");
                store = store.replace("]", " ");
                String[] strArr1 = null;
                strArr1 = store.split(",");
                String[] strArr2 = new String[strArr1.length];
                for (int i = 0; i < strArr1.length; i++) {
                        if (i % 3 == 1) {
                                strArr2[i] = strArr1[i];
                        } else {
                                strArr2[i] = null;
                        }
                }
                List<String> list = new ArrayList<String>(Arrays.asList(strArr2));
                list.removeAll(Arrays.asList("", null));
                String[] array = new String[list.size()];
                array = list.toArray(array);
                return array;
        }

        public static String[] pullQuantityFromText() throws IOException { // Pull quantity from text
                String str = Menu.readFile("Product.txt");
                String[] strArr = null;
                strArr = str.split(" ");
                str = str.replaceAll("[^-?0-9]+", " ");
                List stringList = Arrays.asList(str.trim().split(" "));
                String store = stringList.toString();
                store = store.replace("[", " ");
                store = store.replace("]", " ");
                String[] strArr1 = null;
                strArr1 = store.split(",");
                String[] strArr2 = new String[strArr1.length];
                for (int i = 0; i < strArr1.length; i++) {
                        if (i % 3 == 2) {
                                strArr2[i] = strArr1[i];
                        } else {
                                strArr2[i] = null;
                        }
                }
                List<String> list = new ArrayList<String>(Arrays.asList(strArr2));
                list.removeAll(Arrays.asList("", null));
                String[] array = new String[list.size()];
                array = list.toArray(array);
                return array;
        }

        public static String[] pullNameFormText() throws IOException { // Pull name from text
                String str = Menu.readFile("Product.txt");
                String[] strArr = null;
                String store;
                int index;
                strArr = str.split(" ");
                store = Arrays.toString(strArr);
                store = store.replace("[", "");
                store = store.replace("]", "");
                store = store.replace(",", "");
                store = Menu.removeAllDigit(store);
                strArr = store.split("\n");
                return strArr;
        }

        public static String removeAllDigit(String str) { // Remove all digit
                String result = "";

                // Traverse the String from start to end
                for (int i = 0; i < str.length(); i++) {

                        // Check if the specified character is not digit
                        // then add this character into result variable
                        if (!Character.isDigit(str.charAt(i))) {
                                result = result + str.charAt(i);
                        }
                }

                // Return result
                return result;
        }
}
