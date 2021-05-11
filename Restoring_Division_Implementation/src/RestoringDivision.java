/**
 * @author vibhutee
 */
import java.lang.reflect.Array;
import java.util.Scanner;


/**
 * Java program that implements the Restoring two's Complement Division process
 */
public class RestoringDivision {
    /**
     * @param args
     */
    public static void main(String[] args) {

        // An integer used to input divisor
        int divisorM;

        // An integer used to input dividend
        int dividendQ;

        System.out.println("\n*************************************************************************************************");
        System.out.println("*         Java program that implements the Restoring two's complement division process.         *");
        System.out.println("*************************************************************************************************\n");

        // Taking user defined input
        System.out.print("Enter divisor : ");
        Scanner input = new Scanner(System.in);
        divisorM = input.nextInt();

        System.out.print("\nEnter dividend : ");
        dividendQ = input.nextInt();

        System.out.println();

        // Calling restoring method
        restoringDivision(divisorM, dividendQ);

    }

    /**
     * @param divisorM - passing divisor
     * @param dividendQ - passing dividend
     */
    private static void restoringDivision(int divisorM, int dividendQ) {

        // An array to store divisor in binary format
        int[] divisor = new int[8];

        // An array to store dividend in binary format
        int[] dividend = new int[8];

        // An array to store accumulator in binary format
        int[] accumulator = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

        // An array to store divisor in binary format
        int[] finalDivisor = new int[8];

        // An integer to store remainder
        int remainder = 0;

        // An integer to store quotient
        int quotient = 0;

        // A flag to determine if the divisor is positive or negative
        boolean divisorFlag = false;

        // A flag to determine if the dividend is positive or negative
        boolean dividendFlag = false;

        // If the divisor is negative then convert it into positive and set the flag to true
        if (divisorM < 0) {
            divisorM = divisorM * (-1);
            divisorFlag = true;
        }

        // If the dividend is negative then convert it into positive and set the flag to true
        if (dividendQ < 0) {
            dividendQ = dividendQ * (-1);
            dividendFlag = true;
        }

        // Converting divisor from decimal number to binary
        divisor = convertToBinary(divisorM);

        // Setting divisor to finalDivisor before making any changes to divisor
        for (int i = 0; i <= divisor.length - 1; i++) {
            finalDivisor[i] = divisor[i];
        }

        // Converting dividend from decimal number to binary
        dividend = convertToBinary(dividendQ);

        // A variable to count the number of iterations
        int count = dividend.length - 1;

        // A method to find 2's complement. Here, we are finding (-M)
        int[] twosComplement = twosComplement(divisor);

        System.out.println("Here we have to divide "+dividendQ+" with "+divisorM+"\n");

        System.out.println("-------------------------------------------------------------------------------------------------");

        // Initialization
        System.out.println("Accumulator(A)\t\t Dividend(Q)\t\t Divisor(M)\t\t\t   Comment");
        System.out.println("-------------------------------------------------------------------------------------------------\n");
        printTable(accumulator,dividend,finalDivisor," Initialization\n");

        while (count >= 0) {

            // Logic for Left Shift
            int temp = dividend[0];
            for (int i = 0; i < dividend.length - 1; i++) {
                dividend[i] = dividend[i + 1];
                accumulator[i] = accumulator[i + 1];
            }
            accumulator[accumulator.length - 1] = temp;
            dividend[dividend.length - 1] = '\0';

            printTable(accumulator, dividend, finalDivisor, " Left Shift");

            // Logic for A = A - M
            accumulator = add(accumulator, twosComplement);
            printTable(accumulator, dividend, finalDivisor, " A --> A - M");


            // Check the msb of accumulator
            // If the number is negative then set Q[0] = 0 and restore A
            if (accumulator[0] == 1) {
                Array.set(dividend, dividend.length - 1, 0);
                accumulator = add(accumulator, finalDivisor);
                printTable(accumulator, dividend, finalDivisor, " Set q[0] to 0 and restore\n");
            }
            else {
                Array.set(dividend, dividend.length - 1, 1);
                printTable(accumulator, dividend, finalDivisor, " Set q[0] = 1\n");
            }
            count--;
        }

        // Convert remainder from binary to decimal
        remainder = convertToDecimal(accumulator);

        // Convert quotient from binary to decimal
        quotient = convertToDecimal(dividend);

        // To check if divisor/dividend were negative earlier and assigning signs accordingly
        if (divisorFlag) {
            if (dividendFlag) {
                remainder = remainder * (-1);
            } else {
                quotient = quotient * (-1);
            }
        } else {
            if (dividendFlag) {
                remainder = remainder * (-1);
                quotient = quotient * (-1);
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Quotient is : " + quotient);
        System.out.println("Accumulator contains remainder which is : " + remainder);
        System.out.println("-------------------------------------------------------------------------------------------------");

    }

    /**
     * Printing a table in structured format
     * @param accumulator
     * @param dividend
     * @param finalDivisor
     * @param s
     */
    private static void printTable(int[] accumulator, int[] dividend, int[] finalDivisor, String s) {
        print(accumulator);
        System.out.print(" ");
        print(dividend);
        System.out.print(" ");
        print(finalDivisor);
        System.out.print(" ");
        System.out.println(" "+s);
    }

    /**
     * Logic for converting binary number to decimal number
     * @param number
     * @return
     */
    private static int convertToDecimal(int[] number) {
        int decimal = 0;
        int power = 0;
        for (int i = number.length - 1; i >= 0; i--) {
            decimal += ((number[i]) * Math.pow(2, power));
            power++;
        }
        return decimal;
    }

    /**
     * Logic for adding two binary numbers
     * @param accumulator
     * @param twosComplement
     * @return - sum of two binary array
     */
    private static int[] add(int[] accumulator, int[] twosComplement) {
        int[] sum = new int[8];
        int carry = 0;
        for (int i = 7; i >= 0; i--) {
            switch (accumulator[i] + twosComplement[i] + carry) {
                case 0:
                    sum[i] = 0;
                    carry = 0;
                    break;
                case 1:
                    sum[i] = 1;
                    carry = 0;
                    break;
                case 2:
                    sum[i] = 0;
                    carry = 1;
                    break;
                case 3:
                    sum[i] = 1;
                    carry = 1;
                    break;
            }
        }
        return sum;
    }

    /**
     * Logic for finding 2's complement
     * @param divisor
     * @return
     */
    private static int[] twosComplement(int[] divisor) {
        int[] onesComplement;
        int[] twosComplement = new int[8];
        int carry = 1;
        onesComplement = onesComplement(divisor);
        for (int i = onesComplement.length - 1; i >= 0; i--) {
            if (onesComplement[i] == 1 && carry == 1) {
                twosComplement[i] = 0;
            } else if (onesComplement[i] == 0 && carry == 1) {
                twosComplement[i] = 1;
                carry = 0;
            } else {
                twosComplement[i] = onesComplement[i];
            }
        }
        return twosComplement;
    }

    /**
     * Logic for finding one's complement
     * @param number
     * @return
     */
    private static int[] onesComplement(int[] number) {
        for (int i = 0; i < number.length; i++) {
            number[i] = (number[i] == 0) ? 1 : 0;
        }
        return number;
    }

    /**
     * Logic for printing an array
     * @param number
     */
    private static void print(int[] number) {
        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i]);
        }
        System.out.print("\t\t\t");
    }

    /**
     * Logic for converting decimal number to binary number
     * @param number
     * @return
     */
    private static int[] convertToBinary(int number) {
        int[] binary = new int[8];
        int i = 0;

        while (number > 0) {
            binary[i++] = number % 2;
            number = number / 2;
        }
        binary = reverse(binary);
        return binary;
    }

    /**
     * Logic for reversing an array
     * @param binary
     * @return - an reverse array
     */
    private static int[] reverse(int[] binary) {
        int temp;
        int n = binary.length;
        for (int i = 0; i < n / 2; i++) {
            temp = binary[i];
            binary[i] = binary[n - i - 1];
            binary[n - i - 1] = temp;
        }
        return binary;
    }
}
