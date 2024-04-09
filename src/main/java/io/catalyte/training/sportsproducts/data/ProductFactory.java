package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.utils.RandomTextGenerator;
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
      "#000000|black",
      "#73000A|garnet",
      "#6CABDD|sky blue",
      "#00008B|dark blue",
      "#E0B0FF|mauve",
      "#FF2247|infrared",
      "#FFA500|orange",
      "#FFF5EE|seashell",
      "#800080|purple",
      "#F6EB61|gold",
      "#808000|olive",
      "#FFDB58|mustard",
      "#00FFEC|aqua",
      "#FF00FF|fuchsia",
      "#CEFF00|volt"
  };

  private static final String[] demographics = {
      "Men",
      "Women",
      "Kids",
      "Pets"
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
      "Shoes",
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
  private static final String[] petCategories = {
      "Spring Pole",
      "Car Rides",
      "Play Gyms",
      "Swimming",
      "Outdoor Adventure"
  };

  private static final String[] petNames = {
      "Fit Paws",
      "Woof Gear"
  };
  /**
   * Returns a random category from the list of categories.
   *
   * @return - a category string
   */
  public static String getCategory(String demographic) {
    Random randomGenerator = new Random();
    if ("Pets".equalsIgnoreCase(demographic)) {
      return petCategories[randomGenerator.nextInt(petCategories.length)];
    } else {
      return categories[randomGenerator.nextInt(categories.length)];
    }
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
  public static String getName(String demographic) {
    Random randomGenerator = new Random();
    if ("Pets".equalsIgnoreCase(demographic)) {
      return petNames[randomGenerator.nextInt(petNames.length)];
    } else {
      return names[randomGenerator.nextInt(names.length)];
    }
  }

  /**
   * Returns a random color code and color name combined in a single string delimited by |.
   *
   * @return - a string containing both color code and name
   */
  public static String getColorCodeWithName() {
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

  /**
   * Returns a random long description between 200 - 500 chars of Lorem ipsum text.
   *
   * @return - a long string
   */
  public static String getLongDescription() {
    return RandomTextGenerator.generateRandomText();
  }

  public static Boolean getActive() {
    Random randomGenerator = new Random();
    return randomGenerator.nextBoolean();
  }

  public static Boolean getPets(String demographic) {
    return "Pets".equalsIgnoreCase(demographic);
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
   * Loads distinct primary and secondary colors.
   *
   * @return - an array of two strings containing primary and secondary colors
   */
  public static String[] loadDistinctColors() {
    String[] distinctColors = new String[2];
    distinctColors[0] = getColorCodeWithName();
    do {
      distinctColors[1] = getColorCodeWithName();
    } while (distinctColors[0].equals(distinctColors[1]));
    return distinctColors;
  }

  /**
   * Uses random generators to build a product.
   *
   * @return - a randomly generated product
   */
  public Product createRandomProduct() {
    Product product = new Product();
    String demographic = ProductFactory.getDemographic();
    String[] distinctColors = loadDistinctColors();

    product.setCategory(ProductFactory.getCategory(demographic));
    product.setType(ProductFactory.getType());
    product.setPrimaryColorCodeWithName(distinctColors[0]);
    product.setSecondaryColorCodeWithName(distinctColors[1]);
    product.setDescription(ProductFactory.getDescription());
    product.setLongDescription(ProductFactory.getLongDescription());
    product.setName(ProductFactory.getName(demographic));
    product.setPrice(ProductFactory.getPrice());
    product.setReleaseDate((between(ZonedDateTime.of(1921,01,31,00,00,00,00, ZoneId.systemDefault()).toLocalDate(), ZonedDateTime.now().toLocalDate())).toString());
    product.setActive(ProductFactory.getActive());
    product.setPets(ProductFactory.getPets(demographic));
    product.setDemographic(demographic);
    product.setGlobalProductCode(ProductFactory.getRandomProductId());
    product.setStyleNumber(ProductFactory.getStyleCode());

    return product;
  }

}