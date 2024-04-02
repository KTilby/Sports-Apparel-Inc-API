package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Product;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * This class provides tools for random generation of products.
 */
public class ProductFactory {

  private static final String[] colors = {
      "beige",
      "black",
      "lightblue",
      "darkblue",
      "olive",
      "red",
      "orange",
      "lavender",
      "purple",
      "aqua",
      "green",
      "khaki",
      "darkgray",
      "pink",
      "lightgray"
  };
  private static final String[] demographics = {
      "Men",
      "Women",
      "Kids"
  };
  private static final String[] categories = {
      "Golf",
      "Soccer",
      "Basketball",
      "Hockey",
      "Football",
      "Running",
      "Baseball",
      "Skateboarding",
      "Boxing",
      "Weightlifting"
  };
  private static final String[] adjectives = {
      "Lightweight",
      "Slim",
      "Shock Absorbing",
      "Exotic",
      "Elastic",
      "Fashionable",
      "Trendy",
      "Next Gen",
      "Colorful",
      "Comfortable",
      "Water Resistant",
      "Wicking",
      "Heavy Duty"
  };
  private static final String[] types = {
      "Pant",
      "Short",
      "Shoe",
      "Glove",
      "Jacket",
      "Tank Top",
      "Sock",
      "Sunglasses",
      "Hat",
      "Helmet",
      "Belt",
      "Visor",
      "Shin Guard",
      "Elbow Pad",
      "Headband",
      "Wristband",
      "Hoodie",
      "Flip Flop",
      "Pool Noodle"
  };
  private static final String[] names = {
      "Excite",
      "Shock",
      "Overlord",
      "Extreme",
      "Tubular",
      "Overdrive",
      "Rebound",
      "Honor",
      "Streamline",
      "Crusher",
      "Gratitude",
      "Vision",
      "Defender",
      "Midnight",
      "Olympic",
      "Compete",
      "Hyper",
      "Aftershock",
      "Generate"
  };
  private static final Double[] prices = {
      14.99,
      18.99,
      32.59,
      18.29,
      13.24,
      11.95,
      14.38,
      17.26,
      19.75,
      34.99,
      187.98,
      234.25,
      17.63,
      75.99,
      13.43,
      16.99
  };

  /**
   * Returns a random category from the list of categories.
   *
   * @return - a category string
   */
  public static String getCategory() {
    Random randomGenerator = new Random();
    return categories[randomGenerator.nextInt(categories.length)];
  }

  /**
   * Returns a random type from the list of types.
   *
   * @return - a type string
   */
  public static String getType() {
    Random randomGenerator = new Random();
    return types[randomGenerator.nextInt(types.length)];
  }

  /**
   * Returns a random price from the list of prices.
   *
   * @return - a price double
   */
  public static Double getPrice() {
    Random randomGenerator = new Random();
    return prices[randomGenerator.nextInt(prices.length)];
  }

  /**
   * Returns a random name from the list of names.
   *
   * @return - a name string
   */
  public static String getName() {
    Random randomGenerator = new Random();
    return names[randomGenerator.nextInt(names.length)];
  }

  /**
   * Returns a random color from the list of colors.
   *
   * @return - a color string
   */
  public static String getColorCode() {
    Random randomGenerator = new Random();
    return colors[randomGenerator.nextInt(colors.length)];
  }

  /**
   * Returns a random demographic from the list of demographics.
   *
   * @return - a demographic string
   */
  public static String getDemographic() {
    Random randomGenerator = new Random();
    return demographics[randomGenerator.nextInt(demographics.length)];
  }

  /**
   * Returns a random adjective from the list of adjectives.
   *
   * @return - an adjective string
   */
  public static String getDescription() {
    Random randomGenerator = new Random();
    return adjectives[randomGenerator.nextInt(adjectives.length)];
  }

  public static Boolean getActive() {
    Random randomGenerator = new Random();
    return randomGenerator.nextBoolean();
  }

  /**
   * Generates a random product offering id.
   *
   * @return - a product offering id
   */
  public static String getRandomProductId() {
    return "po-" + RandomStringUtils.random(7, false, true);
  }

  /**
   * Generates a random style code.
   *
   * @return - a style code string
   */
  public static String getStyleCode() {
    return "sc" + RandomStringUtils.random(5, false, true);
  }

  /**
   * Finds a random date between two date bounds.
   *
   * @param startInclusive - the beginning bound
   * @param endExclusive   - the ending bound
   * @return - a random date as a LocalDate
   */
  private static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
    long startEpochDay = startInclusive.toEpochDay();
    long endEpochDay = endExclusive.toEpochDay();
    long randomDay = ThreadLocalRandom
        .current()
        .nextLong(startEpochDay, endEpochDay);

    return LocalDate.ofEpochDay(randomDay);
  }


  /**
   * Generates a number of random products based on input.
   *
   * @param numberOfProducts - the number of random products to generate
   * @return - a list of random products
   */
  public List<Product> generateRandomProducts(Integer numberOfProducts) {

    List<Product> productList = new ArrayList<>();

    for (int i = 0; i < numberOfProducts; i++) {
      productList.add(createRandomProduct());
    }

    return productList;
  }

  /**
   * Uses random generators to build a product.
   *
   * @return - a randomly generated product
   */
  public Product createRandomProduct() {
    Product product = new Product();
    String demographic = ProductFactory.getDemographic();
    product.setCategory(ProductFactory.getCategory());
    product.setType(ProductFactory.getType());
    product.setPrimaryColorCode(ProductFactory.getColorCode());
    product.setSecondaryColorCode(ProductFactory.getColorCode());
    product.setDescription(ProductFactory.getDescription());
    product.setName(ProductFactory.getName());
    product.setPrice(ProductFactory.getPrice());
    product.setReleaseDate((between(ZonedDateTime.of(1921,01,31,00,00,00,00, ZoneId.systemDefault()).toLocalDate(), ZonedDateTime.now().toLocalDate())).toString());
    product.setActive(ProductFactory.getActive());
    product.setDemographic(demographic);
    product.setGlobalProductCode(ProductFactory.getRandomProductId());
    product.setStyleNumber(ProductFactory.getStyleCode());

    return product;
  }

}
