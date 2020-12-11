import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ModificationChecker {

    private List<Memento> mementos;

    private UrlAddressState[] urlAddresses;

    private HashMap<String, List<Observer>> observableMap;

    private int counter = 0;

    public ModificationChecker(UrlAddressState[] states, Observer... observables) {

        mementos = new ArrayList<>();
        urlAddresses = states;

        fillMap(observables);
    }


    private void fillMap(Observer[] observables) {
        observableMap = new HashMap<>();
        for (int i = 0; i < observables.length; i++) {
            if (observables[i] == null) continue;
            if(!UrlAddressState.containsUrl(urlAddresses, observables[i].getUrl())) continue;
            if (!observableMap.containsKey(observables[i].getUrl())) {
                observableMap.put(observables[i].getUrl(), new ArrayList<>());
            }
            observableMap.get(observables[i].getUrl()).add(observables[i]);
        }
    }

    public void startCheck() {
        while (counter < 2) {
            counter++;
            for (int i = 0; i < urlAddresses.length; i++) {
                if (urlAddresses[i] == null) continue;
                long longTime = urlAddresses[i].getConnection().getLastModified();

                if (urlAddresses[i].getTime() == null) {
                    urlAddresses[i].setTime(longTime);
                } else {
                    Date modifiedDate = new Date(longTime);
                    if (modifiedDate.after(urlAddresses[i].getTime())) {
                        tryNotifyObservers(urlAddresses[i]);
                    }
                }
            }
            try {
                Memento memento = new Memento(urlAddresses,observableMap,counter);
                mementos.add(memento);
                memento.write();
                System.out.println("Sleeping");
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tryNotifyObservers(UrlAddressState urlAddress) {
        for (Observer ob : observableMap.get(urlAddress.getUrlAsString())) {
            ob.update();
        }
    }

    class Memento {

        private UrlAddressState[] urlAddressStates;

        private HashMap<String, List<Observer>> observableMap;

        private int counter;

        Memento(UrlAddressState[] urlAddressStates, HashMap<String, List<Observer>> observableMap, int counter){
            this.counter = counter;
            this.urlAddressStates = urlAddressStates;
            this.observableMap = observableMap;
        }

        public void write(){

            try {
                File file = new File("outputFiles");
                if(!file.exists() || !file.isDirectory()) file.mkdir();
                PrintWriter writer = new PrintWriter(new FileOutputStream(new File(file.getPath() + "/" + System.currentTimeMillis() + ".mem")));

                writer.write("Iteration passed: " + counter + "\n");
                writer.write("Url addresses checked: " + Arrays.toString(urlAddressStates) + "\n");
                writer.write("Observers: " + observableMap.toString() + "\n");

                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
