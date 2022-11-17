package offerGenerator.entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOffer;

    @Column
    private int nrOfe;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_user_information")
    private UserInformation userInformation;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<Position> positions;

    public long getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(long idOffer) {
        this.idOffer = idOffer;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public int getNrOfe() {
        return nrOfe;
    }

    public void setNrOfe(int nrOfe) {
        this.nrOfe = nrOfe;
    }
}
