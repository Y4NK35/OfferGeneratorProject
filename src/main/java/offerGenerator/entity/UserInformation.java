package offerGenerator.entity;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.DecimalFormat;
import java.util.List;

@Entity
public class UserInformation {
   // private DecimalFormat nrOfeFormat = new DecimalFormat("00000");

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
    @Column
    private static int  nrOfOffer=0;


    @OneToOne(mappedBy = "userInformation", cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private User user;

    @OneToMany(mappedBy = "userInformation", cascade = CascadeType.ALL)
    private List<Offer> offers;

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

    public int getNrOfOffer() {
        return nrOfOffer;
    }

    public void setNrOfOffer(int nrOfOffer) {
        this.nrOfOffer = nrOfOffer;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
