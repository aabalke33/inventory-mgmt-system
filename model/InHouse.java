package balke.c482;

/**
 * This class defines parts In-House, not outsourced. In-House parts have additional Machine ID's.
 */
public class InHouse extends Part {
    /**
     * This variable defines the machine ID of the part.
     */
    private int machineId;

    /**
     * This is the constructor for the in-house part
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * This method sets the machine ID of the part
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * This method returns the machine ID of the part
     * @return
     */
    public int getMachineId() {

        return machineId;
    }
}