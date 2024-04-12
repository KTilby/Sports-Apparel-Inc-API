package io.catalyte.training.sportsproducts.domains.user;

import javax.persistence.Entity;
import java.util.Arrays;

@Entity
public class Customer extends User{
  String phoneNumber, shippingAddress1, shippingAddress2, shippingCity, shippingState, shippingZipCode,
      billingAddress1, billingAddress2, billingCity, billingState, billingZipCode;

  Object[] wishlist;
  Object[] savedCart;

  public Customer(){};

  public Customer(Long id, String email, String password, String role, String firstName, String lastName,
      String shippingAddress1, String shippingAddress2, String shippingCity, String shippingState,
      String shippingZipCode, String billingAddress1, String billingAddress2, String billingCity,
      String billingState, String billingZipCode, String phoneNumber) {
    super(email, password, role, firstName, lastName);
    this.shippingAddress1 = shippingAddress1;
    this.shippingAddress2 = shippingAddress2;
    this.shippingCity = shippingCity;
    this.shippingState = shippingState;
    this.shippingZipCode = shippingZipCode;
    this.billingAddress1 = billingAddress1;
    this.billingAddress2 = billingAddress2;
    this.billingCity = billingCity;
    this.billingState = billingState;
    this.billingZipCode = billingZipCode;
    this.phoneNumber = phoneNumber;
  }

  public Customer(String email, String password, String role, String firstName, String lastName,
      String shippingAddress1, String shippingAddress2, String shippingCity, String shippingState,
      String shippingZipCode, String billingAddress1, String billingAddress2, String billingCity,
      String billingState, String billingZipCode, String phoneNumber) {
    super(email, password, role, firstName, lastName);
    this.shippingAddress1 = shippingAddress1;
    this.shippingAddress2 = shippingAddress2;
    this.shippingCity = shippingCity;
    this.shippingState = shippingState;
    this.shippingZipCode = shippingZipCode;
    this.billingAddress1 = billingAddress1;
    this.billingAddress2 = billingAddress2;
    this.billingCity = billingCity;
    this.billingState = billingState;
    this.billingZipCode = billingZipCode;
    this.phoneNumber = phoneNumber;
  }

  public String getShippingAddress1() {
    return shippingAddress1;
  }

  public void setShippingAddress1(String shippingAddress1) {
    this.shippingAddress1 = shippingAddress1;
  }

  public String getBillingAddress1() {
    return billingAddress1;
  }

  public void setBillingAddress1(String billingAddress1) {
    this.billingAddress1 = billingAddress1;
  }

  public String getShippingAddress2() {
    return shippingAddress2;
  }

  public void setShippingAddress2(String shippingAddress2) {
    this.shippingAddress2 = shippingAddress2;
  }

  public String getBillingAddress2() {
    return billingAddress2;
  }

  public void setBillingAddress2(String billingAddress2) {
    this.billingAddress2 = billingAddress2;
  }

  public String getShippingCity() {
    return shippingCity;
  }

  public void setShippingCity(String shippingCity) {
    this.shippingCity = shippingCity;
  }

  public String getBillingCity() {
    return billingCity;
  }

  public void setBillingCity(String billingCity) {
    this.billingCity = billingCity;
  }

  public String getShippingState() {
    return shippingState;
  }

  public void setShippingState(String shippingState) {
    this.shippingState = shippingState;
  }

  public String getBillingState() {
    return billingState;
  }

  public void setBillingState(String billingState) {
    this.billingState = billingState;
  }

  public String getShippingZipCode() {
    return shippingZipCode;
  }

  public void setShippingZipCode(String shippingZipCode) {
    this.shippingZipCode = shippingZipCode;
  }

  public String getBillingZipCode() {
    return billingZipCode;
  }

  public void setBillingZipCode(String billingZipCode) {
    this.billingZipCode = billingZipCode;
  }

  public Object[] getWishlist() {
    return wishlist;
  }

  public void setWishlist(Object[] wishlist) {
    this.wishlist = wishlist;
  }

  public Object[] getSavedCart() {
    return savedCart;
  }

  public void setSavedCart(Object[] savedCart) {
    this.savedCart = savedCart;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "phoneNumber='" + phoneNumber + '\'' +
            ", shippingAddress1='" + shippingAddress1 + '\'' +
            ", shippingAddress2='" + shippingAddress2 + '\'' +
            ", shippingCity='" + shippingCity + '\'' +
            ", shippingState='" + shippingState + '\'' +
            ", shippingZipCode='" + shippingZipCode + '\'' +
            ", billingAddress1='" + billingAddress1 + '\'' +
            ", billingAddress2='" + billingAddress2 + '\'' +
            ", billingCity='" + billingCity + '\'' +
            ", billingState='" + billingState + '\'' +
            ", billingZipCode='" + billingZipCode + '\'' +
            ", wishlist=" + Arrays.toString(wishlist) +
            ", savedCart=" + Arrays.toString(savedCart) +
            ", id=" + id +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", role='" + role + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
