package com.realestate.hcrawler;


public class Property {
    private float price;
    private String url;
    private Address address;
    private int lotSize;
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
        return lotSize;
    }

    public void setTerrainSize(int terrainSize) {
        this.lotSize = terrainSize;
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
    	private String neighborhood;
        private String city;
        private String state;
        private String country;

        public Address() {
        }

		public Address(String neighborhood, String city, String state, String country) {
			super();
			this.neighborhood = neighborhood;
			this.city = city;
			this.state = state;
			this.country = country;
		}



		public String getNeighborhood() {
			return neighborhood;
		}

		public void setNeighborhood(String neighborhood) {
			this.neighborhood = neighborhood;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

        
    }
}
