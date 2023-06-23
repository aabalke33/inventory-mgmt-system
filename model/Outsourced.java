package balke.c482;
/**
 * This class defines parts Outsourced, not in-house. Outsourced parts have additional Company Name's.
 */
public class Outsourced extends Part {
    /**
     * This variable defines the company name of the part.
     */
    private String companyName;

    /**
     * this is the constructor of the outsourced part
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * This method sets the company name
     * @param CompanyName
     */
    public void setCompanyName(String CompanyName) {
        this.companyName = companyName;
    }

    /**
     * This method returns the company name
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }
}