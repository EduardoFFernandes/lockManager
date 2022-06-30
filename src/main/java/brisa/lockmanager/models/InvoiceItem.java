package brisa.lockmanager.models;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class InvoiceItem {

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    private String name;
    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    private String description;
    @NotNull(message = "is mandatory")
    @PositiveOrZero(message = "should be greater or equal to zero")
    private BigDecimal price;
    @NotNull(message = "is mandatory")
    @DecimalMin(value = "1", message = "should be greater than zero")
    private int quantity;
    private BigDecimal amount;

    public InvoiceItem(String description, BigDecimal price, int quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.amount = price == null ? BigDecimal.ZERO : price.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
