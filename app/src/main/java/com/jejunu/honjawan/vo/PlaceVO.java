package com.jejunu.honjawan.vo;

public class PlaceVO {
    private String placeName;
    private String placeAdress;
    private String placeImage;
    private int placePrice;
    private int placePriceOrigin;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceAdress() {
        return placeAdress;
    }

    public void setPlaceAdress(String placeAdress) {
        this.placeAdress = placeAdress;
    }

    public String getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }

    public int getPlacePrice() {
        return placePrice;
    }

    public void setPlacePrice(int placePrice) {
        this.placePrice = placePrice;
    }

    public int getPlacePriceOrigin() {
        return placePriceOrigin;
    }

    public void setPlacePriceOrigin(int placePriceOrigin) {
        this.placePriceOrigin = placePriceOrigin;
    }
}
