import java.util.*;

/**
 * @author vvala1
 */
public class BatcherBanyan8 {

    /**
     * This is the main method
     */
    public static void main(String[] args) {

        // Taking number of inputs numbers from user to perform banyan-batcher network
        System.out.println("Provide the number of inputs: ");
        // Scanning the input using scanner class
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // Generating n random numbers to perform banyan-batcher network
        int[] numbers = generateRandomNumbers(n);

        // Executing batcher module
        batcherImplementation(numbers);

        // Converting decimal numbers to binary number
        String[] binaryNumbers = convertingToBinary(numbers);

        // Executing banyan module
        banyanImplementation(numbers, binaryNumbers);

    }

    /**
     * This method is used to generate random numbers
     *
     * @param n - Tells us how many random numbers should be generate. Eg. 5
     * @return - It returns an array of generated random numbers
     */
    private static int[] generateRandomNumbers(int n) {
        int[] numbers;
        // Declaring LinkedHashSet to remove duplicates in generated numbers
        Set<Integer> set = new LinkedHashSet<>();
        // Used to generate random numbers
        Random random = new Random();
        System.out.println("\nGenerating and printing random numbers:");
        // While the size of set is less than n, keep adding values to set
        while (set.size() < n) {
            set.add(random.nextInt(8));
        }
        // Storing data from set to an array
        numbers = set.stream().mapToInt(Integer::intValue).toArray();
        // Printing randomly generated array
        printNumbers(numbers);
        return numbers;
    }

    /**
     * This method is used to print an int array
     *
     * @param numbers - Provides an array of integers which is to be printed
     */
    private static void printNumbers(int[] numbers) {
        for (int number : numbers) {
            System.out.print(number + "  ");
        }
    }

    /**
     * This method is an implementation of batcher module which sorts the inputs.
     *
     * @param numbers - Gives an array of integers
     */
    private static void batcherImplementation(int[] numbers) {
        // Sorting an array using this predefined-method
        System.out.println("\n\n-----------------------------------------------------------------");
        System.out.println("|                   Batcher implementation                      |");
        System.out.println("-----------------------------------------------------------------");
        // Using quick sort to sort the given array
        quickSort(numbers, 0, numbers.length - 1 );
        System.out.print("|                       Sorted array:                           |");
        System.out.println("\n-----------------------------------------------------------------");
        System.out.print("                       ");
        printNumbers(numbers);
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("\n");
    }

    /**
     * This method is used to sort an array using quicksort algorithm
     * @param numbers - pass an array which needs to be sorted
     * @param low - initial index of that array
     * @param high - last index of an array
     */
    private static void quickSort(int[] numbers, int low, int high) {
        if(low < high){
            // Partitioning index of an array
            int partitioningIndex = partition(numbers, low, high);
            quickSort(numbers, low, partitioningIndex - 1);
            quickSort(numbers, partitioningIndex + 1, high);
        }
    }

    /**
     * This function uses first element as a pivot element and places it in correct position.
     * Further, it will place all the smaller elements to the pivot on its left side and all
     * the larger elements then pivot on its right side
     * @param numbers - Int array which we need split in two halves
     * @param low - Gives an initial index of an array
     * @param high - Gives the ending index of an array
     * @return - Returns partitioning index
     */
    private static int partition(int[] numbers, int low, int high) {
        // Sets the first element as pivot
        int pivot = numbers[low];
        // Sets i as the next index to the pivot
        int i = low + 1;

        for(int j = low + 1; j <= high ; j++ ){
            if ( numbers[ j ] < pivot) {
                swapNumbers(numbers, i, j);
                i += 1;
            }
        }

        // Keeping pivot at correct position so that elements on its left are smaller than it
        // and elements to its right are greater than it
        swapNumbers(numbers, low, i - 1);
        // Returning partitioning index
        return i-1;
    }

    /**
     * This method is used to swap two numbers.
     * @param numbers - Passing an int array
     * @param i - index1
     * @param j - index2
     */
    private static void swapNumbers(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }


    /**
     * This method converts decimal numbers to binary number
     *
     * @param numbers - Provides an input of int array. Eg. numbers = {1,4,2,7,5}
     * @return - An array of string of binary numbers. Eg. result = {"0000", "0010", "0101"}
     */
    private static String[] convertingToBinary(int[] numbers) {
        String[] binaryNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            // Formatting the string if after conversion, it is less than four digits
            binaryNumbers[i] = String.format("%3s", Integer.toBinaryString(numbers[i]))
                    .replace(' ', '0');
        }
        // Printing binary numbers
        printBinaryNumbers(binaryNumbers);
        return binaryNumbers;
    }

    /**
     * This method is used to print binary numbers
     *
     * @param binaryNumbers - Array of String. Eg, {"0100", "0000", "0110"}
     */
    private static void printBinaryNumbers(String[] binaryNumbers) {
        System.out.println("Printing binary numbers: ");
        for (String number : binaryNumbers) {
            System.out.print(number + "  ");
        }
        System.out.println("\n\n");
    }

    /**
     * This method is implementation of banyan module
     *
     * @param numbers       - int array of randomly generated decimal numbers
     * @param binaryNumbers - Their corresponding binary values in string array
     */
    private static void banyanImplementation(int[] numbers, String[] binaryNumbers) {

        // Declaring hashmap to initialise input ports
        HashMap<Integer, String> inputPorts = new HashMap<>();
        inputPorts.put(0, "A1");
        inputPorts.put(1, "A2");
        inputPorts.put(2, "A3");
        inputPorts.put(3, "A4");
        inputPorts.put(4, "A1");
        inputPorts.put(5, "A2");
        inputPorts.put(6, "A3");
        inputPorts.put(7, "A4");

        System.out.print("-----------------------------------------------------------------\n");
        System.out.println("|                   Banyan implementation                       |");
        for (int i = 0; i < numbers.length; i++) {
            String initialPort = inputPorts.get(numbers[i]);
            String path1;
            String path2 = "";
            String outputPort = "";
            path1 = switchA_to_switchB(initialPort, binaryNumbers[i]);
            if (path1 != null)
                path2 = switchB_to_switchC(path1, binaryNumbers[i]);
            if (path2 != null)
                outputPort = switchC_to_output(path2, binaryNumbers[i]);
            System.out.print("-----------------------------------------------------------------\n");
            System.out.println("|            Path :  " + initialPort + " --> " + path1 + " --> "
                    + path2 + " --> " + outputPort + "               |");
            if (i == (numbers.length - 1))
                System.out.print("-----------------------------------------------------------------\n");
        }

    }

    /**
     * This method is used to find the path from switch A to switch B
     *
     * @param initialPort  - Value of the port from which value is coming
     * @param binaryNumber - Binary equivalent of the number. Eg. {"0001"}
     * @return - Returns the destination port in switch B. Eg. "B2"
     */
    private static String switchA_to_switchB(String initialPort, String binaryNumber) {
        char direction = binaryNumber.charAt(0);
        if (initialPort.equals("A1"))
            return direction == '0' ? "B1" : "B3";

        if (initialPort.equals("A2"))
            return direction == '0' ? "B2" : "B4";

        if (initialPort.equals("A3"))
            return direction == '0' ? "B1" : "B3";

        if (initialPort.equals("A4"))
            return direction == '0' ? "B2" : "B4";

        return null;
    }

    /**
     * This method is used to find the path from switch B to switch C
     *
     * @param initialPort  - Value of the port from which value is coming. Eg. "B2"
     * @param binaryNumber - Binary equivalent of the number. Eg. {"0101"}
     * @return - Returns the destination port in switch C. Eg. "C5"
     */
    private static String switchB_to_switchC(String initialPort, String binaryNumber) {
        char direction = binaryNumber.charAt(1);
        if (initialPort.equals("B1"))
            return (direction == '0') ? "C1" : "C2";

        if (initialPort.equals("B2"))
            return direction == '0' ? "C1" : "C2";

        if (initialPort.equals("B3"))
            return direction == '0' ? "C3" : "C4";

        if (initialPort.equals("B4"))
            return direction == '0' ? "C3" : "C4";

        return null;
    }

    /**
     * This method is used to find the path from switch C to Output port
     *
     * @param initialPort  - Value of the port from which value is coming. Eg. "C4"
     * @param binaryNumber - Binary equivalent of the number. Eg. {"1001"}
     * @return - Returns the output port number. Eg. "output3"
     */
    private static String switchC_to_output(String initialPort, String binaryNumber) {
        char direction = binaryNumber.charAt(2);

        if (initialPort.equals("C1"))
            return direction == '0' ? "output0" : "output1";

        if (initialPort.equals("C2"))
            return direction == '0' ? "output2" : "output3";

        if (initialPort.equals("C3"))
            return direction == '0' ? "output4" : "output5";

        if (initialPort.equals("C4"))
            return direction == '0' ? "output6" : "output7";

        return null;
    }
}
