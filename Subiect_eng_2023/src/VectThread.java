import java.util.List;

class VectThread implements Runnable {
    private List<ElectronicDevices> phoneList = null;
    private double avgWeight = 0;

    public VectThread(String filePath) {
        phoneList = Utils.readBinaryPhones(filePath);
    }

    public List<ElectronicDevices> getPhoneList() {
        return phoneList;
    }

    public double getAvgWeight() {
        return avgWeight;
    }

    @Override
    public void run() {
        avgWeight = 0;
        for(var element : phoneList)
        {
            Phone phone = (Phone)element;
            avgWeight += phone.getWeight();
        }
        avgWeight /= phoneList.size();
    }



    public void setPhoneList(List<ElectronicDevices> phoneList) {
        this.phoneList = phoneList;
    }



    public void setAvgWeight(double avgWeight) {
        this.avgWeight = avgWeight;
    }
}
