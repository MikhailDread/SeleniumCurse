import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.util.Objects;

public class DuckModel {
    String regularPrice;
    String salePrice;
    String name;
    String saleColor;
    String regularColor;
    Dimension saleSize;
    Dimension regularSize;
    Point saleLocation;
    Point regularLocation;

    public String getRegularPrice() {
        return regularPrice;
    }

    public DuckModel withRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public DuckModel withSalePrice(String salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    public String getName() {
        return name;
    }

    public DuckModel withName(String name) {
        this.name = name;
        return this;
    }

    public String getSaleColor() {
        return saleColor;
    }

    public DuckModel withSaleColor(String saleColor) {
        this.saleColor = saleColor;
        return this;
    }

    public String getRegularColor() {
        return regularColor;
    }

    public DuckModel withRegularColor(String regularColor) {
        this.regularColor = regularColor;
        return this;
    }

    public Dimension getSaleSize() {
        return saleSize;
    }

    public DuckModel withSaleSize(Dimension saleSize) {
        this.saleSize = saleSize;
        return this;
    }

    public Dimension getRegularSize() {
        return regularSize;
    }

    public DuckModel withRegularSize(Dimension regularSize) {
        this.regularSize = regularSize;
        return this;
    }

    public Point getSaleLocation() {
        return saleLocation;
    }

    public DuckModel withSaleLocation(Point saleLocation) {
        this.saleLocation = saleLocation;
        return this;
    }

    public Point getRegularLocation() {
        return regularLocation;
    }

    public DuckModel withRegularLocation(Point regularLocation) {
        this.regularLocation = regularLocation;
        return this;
    }

    public String passTestSale(String color){
        String newSaleColor = color.substring(color.indexOf("(") + 1);
        String [] colors = newSaleColor.split(", ");
        if(colors[1].equals("0") && colors[2].equals("0")){
            return "Pass!";
        } else return "Not match";
    }
    public String passTestRegular(String color){
        String newSaleColor = color.substring(color.indexOf("(") + 1);
        String [] colors = newSaleColor.split(", ");
        if(colors[0].equals(colors[1]) && colors[2].equals(colors[0])){
            return "Pass!";
        } else return "Not match";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DuckModel duckModel = (DuckModel) o;
        return Objects.equals(regularPrice, duckModel.regularPrice) &&
                Objects.equals(salePrice, duckModel.salePrice) &&
                Objects.equals(name, duckModel.name) &&
                Objects.equals(saleColor, duckModel.saleColor) &&
                Objects.equals(regularColor, duckModel.regularColor) &&
                Objects.equals(saleSize, duckModel.saleSize) &&
                Objects.equals(regularSize, duckModel.regularSize) &&
                Objects.equals(saleLocation, duckModel.saleLocation) &&
                Objects.equals(regularLocation, duckModel.regularLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regularPrice, salePrice, name, saleColor, regularColor, saleSize, regularSize, saleLocation, regularLocation);
    }
}
