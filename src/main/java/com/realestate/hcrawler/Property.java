package com.realestate.hcrawler;


public class Property {
    private float price;
    private String url;
    private Address address;
    private int terrainSize;
    private int buildingSize;
    private String source;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getTerrainSize() {
        return terrainSize;
    }

    public void setTerrainSize(int terrainSize) {
        this.terrainSize = terrainSize;
    }

    public int getBuildingSize() {
        return buildingSize;
    }

    public void setBuildingSize(int buildingSize) {
        this.buildingSize = buildingSize;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public static class Address {
        private String suburb;
        private String town;
        private String state;
        private String country;

        public Address() {
        }

        public Address(String suburb, String town, String state, String country) {
            this.suburb = suburb;
            this.town = town;
            this.state = state;
            this.country = country;
        }

        public String getSuburb() {
            return suburb;
        }

        public void setSuburb(String suburb) {
            this.suburb = suburb.toUpperCase();
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town.toUpperCase();
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state.toUpperCase();
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country.toUpperCase();
        }
    }
}
