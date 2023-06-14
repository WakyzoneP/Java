import java.io.*;
import java.lang.reflect.Member;
import java.util.*;
import java.util.function.Predicate;

import org.json.JSONArray;
import org.json.JSONObject;

public class Gym extends Thread {
    private HashSet<GymCard> members;

    public Gym() {
        members = new HashSet<>();
    }

    public void addMemeber(GymCard g) {
        members.add(g);
    }

    public void deleteMember(GymCard g) {
        members.remove(g);
    }

    public Set<GymCard> getMembers() {
        return members;
    }

    public void saveData(String file) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for (var member : members) {
                out.writeObject(member);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadData(String file) {
        ObjectInputStream in = null;
        members.clear();
        members = new HashSet<>();
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            while (true) {
                GymCard card = (GymCard) in.readObject();
                members.add(card);
            }
        } catch (EOFException eof) {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Predicate<GymCard> p;

    public List<GymCard> getGoldMembers() {
        p = (GymCard g) -> g.getMembershipType() == GymCard.CardType.gold;
        List<GymCard> memberList = members.stream().filter(p).toList();

        return memberList;
    }

    public List<ICard> expiredCards;

    @Override
    public void run() {
        expiredCards = members.stream().filter((GymCard g) -> g.isExpired()).map((GymCard g) -> (ICard) g).toList();
    }

    public JSONArray serializeToJson() {
        JSONArray jsonArray = new JSONArray();
        for (var member : members) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", member.getId());
                jsonObject.put("cardHolder", member.getCardHolder());
                jsonObject.put("loyaltyPoints", member.getLoyaltyPoints());
                jsonObject.put("expiryDate", member.getExpiryDate());
                jsonObject.put("membershipType", member.typeToString());

                jsonArray.put(jsonObject);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

}
