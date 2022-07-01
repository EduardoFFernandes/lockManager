package brisa.lockmanager.models;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Invoice extends _BaseModelId {

    private static final long serialVersionUID = 6394884687122112956L;

    @NotNull(message = "is mandatory")
    @Valid()
    private ArrayList<InvoiceItem> itens = new ArrayList<InvoiceItem>();

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    private String companyName;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    private String address;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    private String postal;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    private String destinataryName;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    private String destinataryAdress;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    private String destinataryPostal;

    private String termsAndConditions;

    @NotNull(message = "is mandatory")
    @NotEmpty(message = "is mandatory")
    private String date;

    @NotNull(message = "is either null or in wrong format (MM/dd/yy)")
    @NotEmpty(message = "is mandatory")
    private String dueDate;

    @NotNull(message = "is mandatory")
    @DecimalMin(value = "0", message = "should be greater or equal to zero")
    private BigDecimal discount;

    @DecimalMin(value = "0", message = "should be greater or equal to zero")
    @NotNull(message = "is mandatory")
    private BigDecimal taxRate;

    private BigDecimal tax;
    private String image;

    public Invoice(
            ArrayList<InvoiceItem> itens, String companyName, String address, String postal, String destinataryName,
            String destinataryAdress, String destinataryPostal, String termsAndConditions, String date, String dueDate, BigDecimal discount, BigDecimal taxRate, String image) {

        this.itens = itens;
        this.companyName = companyName;
        this.address = address;
        this.postal = postal;
        this.destinataryName = destinataryName;
        this.destinataryAdress = destinataryAdress;
        this.destinataryPostal = destinataryPostal;
        this.termsAndConditions = termsAndConditions;
        this.date = isValid(date) ? date : null;
        this.dueDate = isValid(dueDate) ? dueDate : null;
        this.discount = discount;
        this.taxRate = taxRate;
        this.image = image;
    }

    public boolean isValid(String dueDate) {        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        return sdf.parse(dueDate, new ParsePosition(0)) != null;
    }
    
    public void addItem(InvoiceItem item) {
        this.itens.add(item);
    }

    public ArrayList<InvoiceItem> getItens() {
        return itens;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getDestinataryName() {
        return destinataryName;
    }

    public void setDestinataryName(String destinataryName) {
        this.destinataryName = destinataryName;
    }

    public String getDestinataryAdress() {
        return destinataryAdress;
    }

    public void setDestinataryAdress(String destinataryAdress) {
        this.destinataryAdress = destinataryAdress;
    }

    public String getDestinataryPostal() {
        return destinataryPostal;
    }

    public void setDestinataryPostal(String destinataryPostal) {
        this.destinataryPostal = destinataryPostal;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal vatRate) {
        this.taxRate = vatRate;
    }

    public BigDecimal getTax() {
        this.tax = this.taxRate.multiply(getSubtotal()).divide(new BigDecimal("100"));
        return tax;
    }

    public BigDecimal getSubtotal() {

        BigDecimal subtotal = BigDecimal.ZERO;

        for (InvoiceItem item : this.itens) {
            subtotal = subtotal.add(item.getAmount());
        }
        return subtotal;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;

        this.tax = getTax();

        total = getSubtotal().add(tax).subtract(discount);

        if (total.compareTo(BigDecimal.ZERO) >= 0) {
            return total;
        }
        return BigDecimal.ZERO;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;

    }

}
