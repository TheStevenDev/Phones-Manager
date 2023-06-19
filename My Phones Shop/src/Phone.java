public class Phone {
    private String brand;
    private String name;
    private double price;

    public Phone(String brand, String name,  double price ){
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    public String getBrand(){
        return this.brand;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return price;
    }

    public String toString(){
        return this.brand+" - "+this.name+" - "+this.price+"$";
    }

}
