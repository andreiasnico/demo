package map.project.demo.Model.dto;

public class RenterDto {

    private Long renterId;

    private String firmName;

    private String email;

    private String IBAN;

    public RenterDto(Long renterId, String firmName, String email, String IBAN) {
        this.renterId = renterId;
        this.firmName = firmName;
        this.email = email;
        this.IBAN = IBAN;
    }

    public Long getRenterId() {
        return renterId;
    }

    public void setRenterId(Long renterId) {
        this.renterId = renterId;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}
