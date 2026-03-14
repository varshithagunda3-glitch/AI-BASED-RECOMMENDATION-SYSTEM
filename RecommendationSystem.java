import java.util.*;

public class RecommendationSystem {

    public static void main(String[] args) {

        Map<String, Map<String, Integer>> userRatings = new HashMap<>();

        // User1 ratings
        Map<String, Integer> user1 = new HashMap<>();
        user1.put("Laptop", 5);
        user1.put("Phone", 3);
        user1.put("Tablet", 4);

        // User2 ratings
        Map<String, Integer> user2 = new HashMap<>();
        user2.put("Laptop", 4);
        user2.put("Phone", 5);
        user2.put("Headphones", 4);

        // User3 ratings
        Map<String, Integer> user3 = new HashMap<>();
        user3.put("Phone", 4);
        user3.put("Tablet", 5);
        user3.put("Headphones", 3);

        userRatings.put("User1", user1);
        userRatings.put("User2", user2);
        userRatings.put("User3", user3);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User Name (User1/User2/User3): ");
        String user = scanner.nextLine();

        recommendProducts(userRatings, user);

        scanner.close();
    }

    public static void recommendProducts(Map<String, Map<String, Integer>> data, String user) {

        if (!data.containsKey(user)) {
            System.out.println("User not found!");
            return;
        }

        Map<String, Integer> userProducts = data.get(user);

        Map<String, Integer> productScores = new HashMap<>();
        Map<String, Integer> productCounts = new HashMap<>();

        for (String otherUser : data.keySet()) {

            if (!otherUser.equals(user)) {

                for (Map.Entry<String, Integer> entry : data.get(otherUser).entrySet()) {

                    String product = entry.getKey();
                    int rating = entry.getValue();

                    if (!userProducts.containsKey(product)) {

                        productScores.put(product,
                                productScores.getOrDefault(product, 0) + rating);

                        productCounts.put(product,
                                productCounts.getOrDefault(product, 0) + 1);
                    }
                }
            }
        }

        System.out.println("\nRecommended Products:");

        for (String product : productScores.keySet()) {

            double avg = (double) productScores.get(product) / productCounts.get(product);

            System.out.println(product + " (Score: " + avg + ")");
        }
    }
}