package map.project.demo.Model.dto;

public class BuildingDto {
    private Long buildingId;

    private String name;

    private String street;

    private String town;

    private int numberOfStories;

    public BuildingDto(Long buildingId, String name, String street, String town, int numberOfStories) {
        this.buildingId = buildingId;
        this.name = name;
        this.street = street;
        this.town = town;
        this.numberOfStories = numberOfStories;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getNumberOfStories() {
        return numberOfStories;
    }

    public void setNumberOfStories(int numberOfStories) {
        this.numberOfStories = numberOfStories;
    }
}
