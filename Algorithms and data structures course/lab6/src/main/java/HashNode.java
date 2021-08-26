public class HashNode {
    private double key;
    private double value;

    public HashNode(double key, double value) {
        this.key = key;
        this.value = value;
    }

    public double getKey() { return key; }

    public void setKey(double key) { this.key = key; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }
}
