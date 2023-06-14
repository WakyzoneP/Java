import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProgMainPlayGround {
    public static void main(String[] args) throws Exception {
        
        List<ElectronicDevices> list1 = Utils.createPhones(10);

        // for(var element : list1)
        //     System.out.println(element);

        List<ElectronicDevices> list2 = Utils.readPhones("data/phoneList.txt");

        // for(var element : list2)
        // {
        //     Phone p = (Phone)element;
        //     System.out.println(p.getProducer());
        // }

        System.out.println(list2.size());

        Utils.writeBinaryPhones("data/phoneList2.dat", list2);

        List<ElectronicDevices> list3 = Utils.readBinaryPhones("data/phoneList2.dat");

        for(var element : list3)
        {
            Phone p = (Phone)element;
            System.out.println(p.getProducer());
        }

        System.out.println(list3.size());

        // ExecutorService executor = Executors.newFixedThreadPool(2);
        // VectThread[] runners = new VectThread[2];

        // for(int i = 0; i < 2; ++i)
        // {
        //     runners[i] = new VectThread("");
        //     executor.execute(runners[i]);
        // }

        // executor.shutdown();

        // try {
        //     executor.awaitTermination(5, TimeUnit.MINUTES);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // for(int i = 0; i < 2; ++i)
        //     System.out.println(runners[i].getAvgWeight());
    }
}
