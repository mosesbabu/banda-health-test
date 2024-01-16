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

       // Find the Elf carrying the most Calories.
       int maxCalories = 0;
       for (List<Integer> elfCalories : calories) {
           int totalCalories = 0;
           for (int calorie : elfCalories) {
               totalCalories += calorie;
           }
           maxCalories = Math.max(maxCalories, totalCalories);
       }

       // Print the number of total Calories carried by the Elf carrying the most Calories.
       System.out.println(maxCalories);
   }
}
