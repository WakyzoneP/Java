import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

class JSONData {
    private final String headline;
    private final LocalDate date;
    private final String newsDescription;

    private JSONData(String headline, LocalDate date, String newsDescription) {
        this.headline = headline;
        this.date = date;
        this.newsDescription = newsDescription;
    }

    public static JSONData createNews(String headline, String newsDescription) {
        return new JSONData(headline, LocalDate.now(), newsDescription);
    }

    public String getHeadline() {
        return headline;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    @Override
    public String toString() {
        return "JSONData [date=" + date + ", headline=" + headline + ", newsDescription=" + newsDescription + "]";
    }

}

class NewsSubstriber implements Flow.Subscriber<JSONData> {

    private List<Subscription> objFieldSubscriptionList = new ArrayList<>();
    private int noRecvNews = 0;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.printf("\nSubscribed to %s", subscription);
        objFieldSubscriptionList.add(subscription);
        for(var objSubscription : objFieldSubscriptionList) {
            objSubscription.request(1);
        }
    }

    @Override
    public void onNext(JSONData item) {
        System.out.printf("\nReceived %s, at date %s", item, item.getDate());
        noRecvNews++;
        if (noRecvNews >= 8) {
            System.out.println("Subscription for " + this + " cancelled!");
            objFieldSubscriptionList.forEach(Subscription::cancel);
            return;
        }
        for(var objSubscription : objFieldSubscriptionList) {
            objSubscription.request(1);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("Error occured %s", throwable.getLocalizedMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("The object " + this + " is completed!");
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        try (SubmissionPublisher<JSONData> objRectStreamPubNews1 = new SubmissionPublisher<>();
                SubmissionPublisher<JSONData> objRectStreamPubNews2 = new SubmissionPublisher<>();) {
            NewsSubstriber objSub1 = new NewsSubstriber();
            objRectStreamPubNews1.subscribe(objSub1);
            objRectStreamPubNews2.subscribe(objSub1);

            List<JSONData> reutersNews = List.of(JSONData.createNews("Headline 1", "News Description 1"),
                    JSONData.createNews("Headline 2", "News Description 2"),
                    JSONData.createNews("Headline 3", "News Description 3"),
                    JSONData.createNews("Headline 4", "News Description 4"));

            List<JSONData> bloombergNews = List.of(JSONData.createNews("Headline 5", "News Description 5"),
                    JSONData.createNews("Headline 6", "News Description 6"),
                    JSONData.createNews("Headline 7", "News Description 7"),
                    JSONData.createNews("Headline 8", "News Description 8"));
            
            reutersNews.forEach(objRectStreamPubNews1::submit);
            bloombergNews.forEach(objRectStreamPubNews2::submit);
            while(objRectStreamPubNews1.hasSubscribers()) {
            }
            while(objRectStreamPubNews2.hasSubscribers()) {
            }
        } finally {
            System.out.println("Playing with Reactive Streams!");
            ;
        }
    }
}
