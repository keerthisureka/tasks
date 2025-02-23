package understanding_streams;

import java.util.*;
import java.util.stream.Collectors;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class StreamOperations {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Apple", "banana", "Avocado", "grape", "kiwi", "banana");
        List<Integer> numbers = Arrays.asList(-10, 12, 5, 7, 9, 15, 20, 25, 30, 50, 100);
        List<Double> doubles = Arrays.asList(1.5, 3.2, 9.8, 4.6, 2.1);
        List<Person> people = Arrays.asList(new Person("John", 28), new Person("Alice", 22), new Person("Bob", 30));

        // Convert all strings to lowercase
        List<String> lowerCaseStrings = strings.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        System.out.println("Lowercase Strings: " + lowerCaseStrings);

        // Filter to keep only odd numbers
        List<Integer> oddNumbers = numbers.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        System.out.println("Odd Numbers: " + oddNumbers);

        // Find the product of all integers
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product of all numbers: " + product);

        // Check if any string starts with 'a'
        boolean anyStartsWithA = strings.stream()
                .anyMatch(s -> s.toLowerCase().startsWith("a"));
        System.out.println("Any string starts with 'a': " + anyStartsWithA);

        // Find the shortest string
        String shortestString = strings.stream()
                .min(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Shortest string: " + shortestString);

        // Calculate average length of strings
        double avgLength = strings.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);
        System.out.println("Average length of strings: " + avgLength);

        // Convert list of strings to a set of unique strings
        Set<String> uniqueStrings = new HashSet<>(strings);
        System.out.println("Unique Strings: " + uniqueStrings);

        // Group words by their first letter
        Map<Character, List<String>> groupedByFirstLetter = strings.stream()
                .collect(Collectors.groupingBy(s -> s.toLowerCase().charAt(0)));
        System.out.println("Grouped by first letter: " + groupedByFirstLetter);

        // Count occurrences of each word
        Map<String, Long> wordCount = strings.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println("Word occurrences: " + wordCount);

        // Find the maximum value in a list of doubles
        double maxDouble = doubles.stream()
                .max(Double::compareTo)
                .orElse(Double.NaN);
        System.out.println("Max double: " + maxDouble);

        // Transform list of integers into cubes
        List<Integer> cubes = numbers.stream()
                .map(n -> n * n * n)
                .collect(Collectors.toList());
        System.out.println("Cubes: " + cubes);

        // Concatenate all strings with spaces
        String concatenatedString = strings.stream()
                .collect(Collectors.joining(" "));
        System.out.println("Concatenated String: " + concatenatedString);

        // Find the third largest number
        List<Integer> sortedNumbers = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
        int thirdLargest = sortedNumbers.size() == 3 ? sortedNumbers.get(2) : -1;
        System.out.println("Third Largest Number: " + thirdLargest);

        // Check if all elements are greater than 10
        boolean allGreaterThan10 = numbers.stream()
                .allMatch(n -> n > 10);
        System.out.println("All elements > 10: " + allGreaterThan10);

        // Partition numbers into positive and negative
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n >= 0));
        System.out.println("Partitioned (Positive & Negative): " + partitioned);

        // Sum of squares of even numbers
        int sumOfSquares = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println("Sum of squares of evens: " + sumOfSquares);

        // Remove duplicates while preserving order
        List<String> noDuplicates = strings.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("List without duplicates: " + noDuplicates);

        // Total number of characters in all strings
        int totalCharacters = strings.stream()
                .mapToInt(String::length)
                .sum();
        System.out.println("Total characters in all strings: " + totalCharacters);

        // Skip first 5 elements and take next 3 elements
        List<Integer> subList = numbers.stream()
                .skip(5)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("SubList (skip 5, take 3): " + subList);

        // Get names of people older than 25
        List<String> namesOlderThan25 = people.stream()
                .filter(p -> p.getAge() > 25)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("People older than 25: " + namesOlderThan25);
    }
}
