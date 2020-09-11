package newCode.model;

import java.util.Date;

public class Purchase {
    private String firstName;
    private String lastName;
    private String customerId;
    private String creditCardNumber;
    private String itemPurchased;
    private String department;
    private int quantity;
    private double price;
    private Date purchaseDate;
    private String storeId;

    public Purchase() {
    }

    public Purchase(String firstName, String lastName, String customerId, String creditCardNumber, String itemPurchased, String department, int quantity, double price, Date purchaseDate, String storeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerId = customerId;
        this.creditCardNumber = creditCardNumber;
        this.itemPurchased = itemPurchased;
        this.department = department;
        this.quantity = quantity;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getItemPurchased() {
        return itemPurchased;
    }

    public String getDepartment() {
        return department;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setItemPurchased(String itemPurchased) {
        this.itemPurchased = itemPurchased;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", itemPurchased='" + itemPurchased + '\'' +
                ", department='" + department + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                ", storeId='" + storeId + '\'' +
                '}';
    }
}
