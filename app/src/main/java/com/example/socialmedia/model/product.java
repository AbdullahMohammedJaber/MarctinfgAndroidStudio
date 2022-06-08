package com.example.socialmedia.model;

public class product {

    private String nameProduct;
    private String imageProduct;
    private String priceProduct;
    private String desProduct;


    public product(  String nameProduct, String imageProduct, String priceProduct, String desProduct ) {

        this.nameProduct = nameProduct;
        this.imageProduct = imageProduct;
        this.priceProduct = priceProduct;
        this.desProduct = desProduct;

    }

    public product() {

    }


    public void setName(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setImage(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public void setPrice(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public void setDes(String desProduct) {
        this.desProduct = desProduct;
    }





    public String getnameProduct() {
        return nameProduct;
    }

    public String getimageProduct() {
        return imageProduct;
    }

    public String getpriceProduct() {
        return priceProduct;
    }

    public String getdesProduct() {
        return desProduct;
    }


    @Override
    public String toString() {
        return "product{" +
                "nameProduct='" + nameProduct + '\'' +
                ", imageProduct='" + imageProduct + '\'' +
                ", priceProduct='" + priceProduct + '\'' +
                ", desProduct='" + desProduct + '\'' +
                '}';
    }
}
