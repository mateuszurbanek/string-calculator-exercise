import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public static int Add(String numbers) {
        if(numbers.isEmpty()) return 0;

        String normalizedNumbers = numbers.replace("\n", ",");
        String[] numbersToSum = normalizedNumbers.split(",");
        List<Integer> intList = new ArrayList<>();
        int sum = 0;

        for(String number : numbersToSum) {
            if(!number.trim().isEmpty()) {
                intList.add(Integer.parseInt(number.trim()));
            }
        }

        for(int i : intList) {
            sum += i;
        }


        return sum;
    }
}