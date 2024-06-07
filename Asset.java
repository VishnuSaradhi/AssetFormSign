// Asset.java

public Class Asset {

 private String serialNo;
 private String name;
 private int noOfPorts;
 private String assetType;

 public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfPorts() {
        return noOfPorts;
    }

    public void setNoOfPorts(int noOfPorts) {
        this.noOfPorts = noOfPorts;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

}