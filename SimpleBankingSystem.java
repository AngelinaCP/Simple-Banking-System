package banking;
import java.util.*;

public class Main {
    static  int arrayPIN[] = new int[100];
    static  long arrayCardNum[] = new long[100];
    static int  count = 0;

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        message();
        int i = scanner.nextInt();
        while (i != 0) {
            if (i == 1) {
                createAccount();
            }   else if (i == 2) {
                logIn();
            }
            message();
            i = scanner.nextInt();
        }
    }

    public static void message() {
        
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    public static String findCheckSum(String cardNum) {

        int []tmp = new int[cardNum.length()];
        char[] chars = cardNum.toCharArray();
        int num = 0;
        int b;
        int k = 0;
        int c;

        for (int i = 0; i < cardNum.length(); i++) {
            b = chars[i] - '0';
            if ((i + 1) % 2 != 0) {
                b *= 2;
            }
            tmp[i] = b;
        }
        for (int i = 0; i < cardNum.length(); i++) {
            if (tmp[i] > 9) {
                tmp[i] -= 9;
            }
        }
        for (int i = 0; i < cardNum.length(); i++) {
            num += tmp[i];
        }
        if (num % 10 > 0) {
            k = 10 - (num % 10);   
        }
        return (cardNum + k);

    }

    public static void createAccount() {
        Random random = new Random();
        long    fullNum;
        int     BIN = 400000;
        long    accountNum = 0; //to generate 9 digits;
        int     checkSum = 0;
        int     intervalLength = 999999999 - 100000000 + 1;

        String cardNum = findCheckSum(BIN + Integer.toString(random.nextInt(intervalLength)+ 100000000));
        fullNum = Long.parseLong(cardNum);
        System.out.println(fullNum);

        int intervalPIN = 9999 - 1111 + 1;
        int PIN = random.nextInt(intervalPIN) + 1111;
        System.out.println(PIN);
        init_PIN_Card(fullNum, PIN);
    }

    public static void init_PIN_Card(long cardNum, int PIN) {
        
        arrayPIN[count] = PIN;
        arrayCardNum[count] = cardNum;
        count++;
    }

    public static void message2() {
        
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }

    public static void logging() {

        message2();
        Scanner scanner = new Scanner(System.in);

        int var = scanner.nextInt();

        while (var != 0) {
            if (var == 1) {
                System.out.println("Balance: 0");
            }   else if (var == 2) {
                System.out.println("You have successfully logged out!");
                return ;
            }   else if (var == 0) {
                System.out.println("Bye!");
                System.exit(0);
            }
            message2();
            var = scanner.nextInt();
        }
    }

    public static void  logIn() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number:");
        long num = scanner.nextLong();
        System.out.println("Enter your PIN:");
        int PIN = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            if (num == arrayCardNum[i] && PIN == arrayPIN[i]) {
                System.out.println("You have successfully logged in!");
                logging();
                return ;
            }   else {
                continue;
            }
        }
        System.out.println("Wrong card number or PIN!");
    }
}
