import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
      List<List<Integer>> calories = new ArrayList<>();
      List<Integer> currentElfCalories = new ArrayList<>();

      try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
          String line;
          while ((line = reader.readLine()) != null) {
              if (!line.trim().isEmpty()) { // Skip blank lines.
                currentElfCalories.add(Integer.parseInt(line));
              } else { // This line is blank, so we've reached the end of the current Elf's food.
                calories.add(currentElfCalories);
                currentElfCalories = new ArrayList<>(); // Start a new list for the next Elf.
              }
          }
      } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }

      // If the last Elf's food list is not empty, we need to append it to the calories list.
      if (!currentElfCalories.isEmpty()) {
          calories.add(currentElfCalories);
      }

      // Sort the calories list in descending order by the sum of each Elf's food Calories.
      Collections.sort(calories, new Comparator<List<Integer>>() {
          @Override
          public int compare(List<Integer> o1, List<Integer> o2) {
              return Integer.compare(o2.stream().mapToInt(Integer::intValue).sum(), o1.stream().mapToInt(Integer::intValue).sum());
          }
      });

      // Take the top three Elves carrying the most Calories.
      List<List<Integer>> topCalories = calories.subList(0, 3);

      // Compute the total number of Calories carried by these Elves.
      int totalCalories = 0;
      for (List<Integer> elfCalories : topCalories) {
          totalCalories += elfCalories.stream().mapToInt(Integer::intValue).sum();
      }

      // Print the total number of Calories carried by the top three Elves carrying the most Calories.
      System.out.println(totalCalories);
  }
}
