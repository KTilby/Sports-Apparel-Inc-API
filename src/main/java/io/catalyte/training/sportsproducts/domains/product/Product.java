package io.catalyte.training.sportsproducts.domains.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class is a representation of a sports apparel product.
 */
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private String longDescription;

  private String demographic;

  private String category;

  private String type;

  private String releaseDate;

  private String primaryColorCodeWithName;

  private String secondaryColorCodeWithName;

  private String styleNumber;

  private String globalProductCode;

  private Boolean active;

  private Boolean pets;

  private Double price;

  public Product() {
  }

  public Product(Long id, String name, String description, String longDescription,
      String demographic,
      String category, String type, String releaseDate, String primaryColorCodeWithName,
      String secondaryColorCodeWithName, String styleNumber, String globalProductCode, Boolean active,
      Boolean pets, Double price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.longDescription = longDescription;
    this.demographic = demographic;
    this.category = category;
    this.type = type;
    this.releaseDate = releaseDate;
    this.primaryColorCodeWithName = primaryColorCodeWithName;
    this.secondaryColorCodeWithName = secondaryColorCodeWithName;
    this.styleNumber = styleNumber;
    this.globalProductCode = globalProductCode;
    this.active = active;
    this.pets = pets;
    this.price = price;
  }

  public Product(String name, String description, String longDescription, String demographic,
      String category, String type, String releaseDate, String primaryColorCodeWithName,
      String secondaryColorCodeWithName, String styleNumber, String globalProductCode,
      Boolean active,
      Boolean pets, Double price) {
    this.name = name;
    this.description = description;
    this.longDescription = longDescription;
    this.demographic = demographic;
    this.category = category;
    this.type = type;
    this.releaseDate = releaseDate;
    this.primaryColorCodeWithName = primaryColorCodeWithName;
    this.secondaryColorCodeWithName = secondaryColorCodeWithName;
    this.styleNumber = styleNumber;
    this.globalProductCode = globalProductCode;
    this.active = active;
    this.pets = pets;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public String getDemographic() {
    return demographic;
  }

  public void setDemographic(String demographic) {
    this.demographic = demographic;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getPrimaryColorCodeWithName() {
    return primaryColorCodeWithName;
  }

  public void setPrimaryColorCodeWithName(String primaryColorCodeWithName) {
    this.primaryColorCodeWithName = primaryColorCodeWithName;
  }

  public String getSecondaryColorCodeWithName() {
    return secondaryColorCodeWithName;
  }

  public void setSecondaryColorCodeWithName(String secondaryColorCodeWithName) {
    this.secondaryColorCodeWithName = secondaryColorCodeWithName;
  }

  public String getStyleNumber() {
    return styleNumber;
  }

  public void setStyleNumber(String styleNumber) {
    this.styleNumber = styleNumber;
  }

  public String getGlobalProductCode() {
    return globalProductCode;
  }

  public void setGlobalProductCode(String globalProductCode) {
    this.globalProductCode = globalProductCode;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getPets() {
    return pets;
  }

  public void setPets(Boolean pets) {
    this.pets = pets;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Product product = (Product) o;

    if (name != null ? !name.equals(product.name) : product.name != null) {
      return false;
    }
    if (description != null ? !description.equals(product.description)
        : product.description != null) {
      return false;
    }
    if (longDescription != null ? !longDescription.equals(product.longDescription)
        : product.longDescription != null) {
      return false;
    }
    if (demographic != null ? !demographic.equals(product.demographic)
        : product.demographic != null) {
      return false;
    }
    if (category != null ? !category.equals(product.category) : product.category != null) {
      return false;
    }
    if (type != null ? !type.equals(product.type) : product.type != null) {
      return false;
    }
    if (releaseDate != null ? !releaseDate.equals(product.releaseDate)
        : product.releaseDate != null) {
      return false;
    }
    if (primaryColorCodeWithName != null ? !primaryColorCodeWithName.equals(product.primaryColorCodeWithName)
        : product.primaryColorCodeWithName != null) {
      return false;
    }
    if (secondaryColorCodeWithName != null ? !secondaryColorCodeWithName.equals(product.secondaryColorCodeWithName)
        : product.secondaryColorCodeWithName != null) {
      return false;
    }
    if (styleNumber != null ? !styleNumber.equals(product.styleNumber)
        : product.styleNumber != null) {
      return false;
    }
    if (globalProductCode != null ? !globalProductCode.equals(product.globalProductCode)
        : product.globalProductCode != null) {
      return false;
    }
    if (price != null ? !price.equals(product.price) : product.price != null) {
      return false;
    }
    if (active != null ? !active.equals(product.active) : product.active != null) {
      return false;
    }
    return pets != null ? pets.equals(product.pets) : product.pets == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (longDescription != null ? longDescription.hashCode() : 0);
    result = 31 * result + (demographic != null ? demographic.hashCode() : 0);
    result = 31 * result + (category != null ? category.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
    result = 31 * result + (primaryColorCodeWithName != null ? primaryColorCodeWithName.hashCode() : 0);
    result = 31 * result + (secondaryColorCodeWithName != null ? secondaryColorCodeWithName.hashCode() : 0);
    result = 31 * result + (styleNumber != null ? styleNumber.hashCode() : 0);
    result = 31 * result + (globalProductCode != null ? globalProductCode.hashCode() : 0);
    result = 31 * result + (active != null ? active.hashCode() : 0);
    result = 31 * result + (pets != null ? pets.hashCode() : 0);
    result = 31 * result + (price != null ? price.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", longDescription='" + longDescription + '\'' +
        ", demographic='" + demographic + '\'' +
        ", category='" + category + '\'' +
        ", type='" + type + '\'' +
        ", releaseDate='" + releaseDate + '\'' +
        ", primaryColorCodeWithName='" + primaryColorCodeWithName + '\'' +
        ", secondaryColorCodeWithName='" + secondaryColorCodeWithName + '\'' +
        ", styleNumber='" + styleNumber + '\'' +
        ", globalProductCode='" + globalProductCode + '\'' +
        ", active='" + active + '\'' +
        ", pets='" + pets + '\'' +
        ", price='" + String.format("%.2f", price) + '\'' +
        '}';
  }
}