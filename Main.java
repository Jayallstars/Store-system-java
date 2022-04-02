import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
        public static String flag_check;
        public static float flag_price;

        public static String isFlag_check() {
                return flag_check;
        }

        public static float isFlag_price (){
                return flag_price;
        }

        public void setFlag_check(String flag_check_1) {
                this.flag_check = flag_check_1;
        }

        public void setFlag_price(float flag_price_1){
                this.flag_price = flag_price_1;
        }

        public static void main(String[] args) throws IOException {
                String check;
                Main main = new Main();
                Menu.ClearConsoleScreen();
                Menu.welcome();
                check = isFlag_check();
                if (check == "cus_check"){
                        System.out.println(String.format("%s",
                                "---------------------------------------------------------------------------------------"));
                        System.out.println();
                        Customer.ChoiceChoose();
                        System.out.print("Back to main menu please press enter : ");
                        check = "";
                        try {
                                System.in.read();
                                Menu.customerSection();
                        } catch (Exception e) {
                        }
                }
                else if (check == "owner_check"){
                        System.out.println(String.format("%s",
                                        "---------------------------------------------------------------------------------------"));
                        System.out.print("Back to main menu please press enter : ");
                        try {
                                System.in.read();
                                Menu.ownerSection();
                        } catch (Exception e) {
                        }
                }
        }
}