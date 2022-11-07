package offerGenerator.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClient;

    @Column
    private String clientName;
    @Column
    private String addressStreet;
    @Column
    private String addressNumberOfBuilding;
    @Column
    private String postalCode;
    @Column
    private String city;
    @Column
    private String nip;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private User user;
    public static Client of(String clientName, String city, String addressStreet, String addressNumberOfBuilding, String postalCode, String nip){
        Client client = new Client();
        client.setClientName(clientName);
        client.setCity(city);
        client.setAddressStreet(addressStreet);
        client.setAddressNumberOfBuilding(addressNumberOfBuilding);
        client.setPostalCode(postalCode);
        client.setNip(nip);
        return client;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String companyName) {
        this.clientName = companyName;
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
