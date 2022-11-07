package offerGenerator.entity;
import javax.persistence.*;
@Entity
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUserInformation;

    @Column
    private String companyName = "Set details";
    @Column
    private String addressStreet = "Set details";
    @Column
    private String addressNumberOfBuilding = "Set details";
    @Column
    private String postalCode = "Set details";
    @Column
    private String city = "Set details";
    @Column
    private String nip = "Set details";

    @OneToOne(mappedBy = "userInformation", cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private User user;

    public UserInformation() {
    }

    public long getIdUserInformation() {
        return idUserInformation;
    }

    public void setIdUserInformation(long idUserInformation) {
        this.idUserInformation = idUserInformation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressNumberOfBuilding() {
        return addressNumberOfBuilding;
    }

    public void setAddressNumberOfBuilding(String addressNumberOfBuilding) {
        this.addressNumberOfBuilding = addressNumberOfBuilding;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
