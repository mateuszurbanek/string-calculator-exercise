import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public static int Add(String numbers) {
        List<Integer> intList = new ArrayList<>();
        List<Integer> negativeNumbers = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
        int sum = 0;

        if(numbers.isEmpty()) return sum;

        String delimiter = ",";
        String numbersPart = numbers;

        if(numbers.startsWith("//")) {
            int newLineIndex = numbers.indexOf("\n");

            if(newLineIndex == -1) {
                errorMessages.add("Illegal format - no \\n after separator declaration");
                numbersPart = "";
            } else {
                delimiter = numbers.substring(2, newLineIndex);
                numbersPart = numbers.substring(newLineIndex + 1);
            }
        } else {
            numbersPart = numbers.replace("\n", delimiter);
        }

        if(numbersPart.endsWith(delimiter)) {
            errorMessages.add("Separators are not allowed at the end of String");
        }


        int position = 0;
        StringBuilder currentNumber = new StringBuilder();

        while(position < numbersPart.length()) {
            if(numbersPart.startsWith(delimiter, position)) {
                String value = currentNumber.toString();
                if(!value.isEmpty()) {
                    int number = Integer.parseInt(value);
                    if (number < 0) negativeNumbers.add(number);
                    else intList.add(number);
                    currentNumber.setLength(0);
                }
                position += delimiter.length();
            } else if(Character.isDigit(numbersPart.charAt(position)) || numbersPart.charAt(position) == '-') {
                currentNumber.append(numbersPart.charAt(position));
                position++;
            } else {
                errorMessages.add("'" + delimiter + "' expected but '" + numbersPart.charAt(position) + "' found at position " + position + ".");
                currentNumber.setLength(0);
                position++;
            }
        }

        if(!currentNumber.toString().trim().isEmpty()) {
            int number = Integer.parseInt(currentNumber.toString());
            if (number < 0) negativeNumbers.add(number);
            else intList.add(number);
        }

        if(!negativeNumbers.isEmpty()) {
            errorMessages.add("Negative number(s) not allowed: " + negativeNumbers.toString().replaceAll("[\\[\\]]", ""));
        }

        if(!errorMessages.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errorMessages));
        }

        for(int i : intList) {
            sum += i;
        }


        return sum;
    }
}