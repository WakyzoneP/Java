import java.util.Date;

public class GymCard extends MembershipCard {
    public static enum CardType {
        bronze,
        silver,
        gold,
    }

    private CardType membershipType;

    public String typeToString() {
        String str = "";
        switch (membershipType) {
            case bronze:
                str = "bronze";
                break;
            case silver:
                str = "silver";
                break;
            case gold:
                str = "gold";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public GymCard(String cardHolder, float loyaltyPoints, Date expiryDate, GymCard.CardType membershipType)
            throws Exception {
        super(cardHolder, loyaltyPoints, expiryDate);
        this.membershipType = membershipType;
    }

    public CardType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(CardType membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((membershipType == null) ? 0 : membershipType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        GymCard other = (GymCard) obj;
        if (membershipType != other.membershipType)
            return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        GymCard newCard = (GymCard) super.clone();
        newCard.membershipType = membershipType;
        return newCard;
    }

    @Override
    public boolean isExpired() {
        Date nowDate = new Date();
        Date cardDate = getExpiryDate();
        if (nowDate.compareTo(cardDate) > 0)
            return false;
        else
            return true;
    }

}
