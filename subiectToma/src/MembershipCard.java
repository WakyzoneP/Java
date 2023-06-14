import java.io.Serializable;
import java.util.Date;

public abstract class MembershipCard implements ICard, Cloneable, Serializable {

    private int id;
    private String cardHolder;
    private float loyaltyPoints;
    private Date expiryDate;

    private static final long serialVersionUID = 35243212L;

    public MembershipCard() {
        id = 0;
        cardHolder = "";
        loyaltyPoints = 0;
        expiryDate = null;
    }

    private static int nrOfElements = 0;

    public MembershipCard(String cardHolder, float loyaltyPoints, Date expiryDate) throws Exception {
        if (cardHolder == null)
            throw new Exception("cardHolder must not be null");
        if (loyaltyPoints < 0)
            throw new Exception("loyaltyPoints must be greater than 0");
        this.cardHolder = cardHolder;
        this.loyaltyPoints = loyaltyPoints;
        this.expiryDate = expiryDate;
        nrOfElements += 1;
        this.id = nrOfElements;
    }

    public int getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) throws Exception {
        if (cardHolder == null)
            throw new Exception("cardHolder must not be null");
        this.cardHolder = cardHolder;
    }

    public float getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(float loyaltyPoints) throws Exception {
        if (loyaltyPoints < 0)
            throw new Exception("loyaltyPoints must be greater than 0");
        this.loyaltyPoints = loyaltyPoints;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((cardHolder == null) ? 0 : cardHolder.hashCode());
        result = prime * result + Float.floatToIntBits(loyaltyPoints);
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MembershipCard other = (MembershipCard) obj;
        if (cardHolder == null) {
            if (other.cardHolder != null)
                return false;
        } else if (!cardHolder.equals(other.cardHolder))
            return false;
        if (Float.floatToIntBits(loyaltyPoints) != Float.floatToIntBits(other.loyaltyPoints))
            return false;
        if (expiryDate == null) {
            if (other.expiryDate != null)
                return false;
        } else if (!expiryDate.equals(other.expiryDate))
            return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        MembershipCard newCard = (MembershipCard) super.clone();
        newCard.id = id;
        newCard.cardHolder = cardHolder;
        newCard.loyaltyPoints = loyaltyPoints;
        newCard.expiryDate = expiryDate;
        return newCard;
    }

}
