package offerGenerator.entity;
import javax.persistence.*;
@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPosition;

    @Column
    private String positionName;

    @Column
    private float priceNetto;

    @Column
    private int quantity;
    @Column
    private float priceBrutto;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_offer")
    private Offer offer;

    public long getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(long idPosition) {
        this.idPosition = idPosition;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public float getPriceNetto() {
        return priceNetto;
    }

    public void setPriceNetto(float priceNetto) {
        this.priceNetto = priceNetto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPriceBrutto() {
        return priceBrutto;
    }

    public void setPriceBrutto(float priceBrutto) {
        this.priceBrutto = priceBrutto;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
