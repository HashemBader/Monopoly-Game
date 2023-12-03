package upei.project;
import upei.project.Properties.Country;
import upei.project.Properties.Station;
import upei.project.Properties.Utility;
import upei.project.Setup.PlayersInit;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SimulationExperiment {

    public static int numOfInf = 0;
    public static void main(String[] args) {

        HashMap<Integer, Player> dataset = new HashMap<>();
        ArrayList<Player> players;
        for(int i=0; i<2000; i++) {
            players = PlayersInit.getPlayers(new String[]{"Zeyad", "Hashem"},
                    new Player.strategy[]{Player.strategy.STATION_GUY, Player.strategy.UTILITY_GUY});
            dataset.put(i, MonopolyGame.playGame(players));
        }

        /*for(int i=0; i< dataset.size(); i++){
            System.out.print(i + " " + dataset.get(i).getStratey().toString() + "\n");
        }*/
        System.out.print("Here is the HashMap: \n" + calcWinRatePerStrategy(dataset));
        System.out.print("\nNum inf: \n" + numOfInf);

        //hashMapToCSV(dataset, "./data/stationGuyVsUtilityGuy.csv");
    }

    private static HashMap<String, Integer> calcWinRatePerStrategy(HashMap<Integer, Player> dataset){
        HashMap<String, Integer> winsPerStrategy = new HashMap<>();
        String tempStrategy;
        for(int i=0; i< dataset.size(); i++){
            if(dataset.get(i) != null) {
                tempStrategy = dataset.get(i).getStratey().toString();

                if (winsPerStrategy.containsKey(tempStrategy)) {
                    winsPerStrategy.put(tempStrategy, winsPerStrategy.get(tempStrategy) + 1);
                } else {
                    winsPerStrategy.put(tempStrategy, 1);
                }
            }
        }
        return winsPerStrategy;
    }

    private static void hashMapToCSV(HashMap<Integer, Player> hashMap, String fileName){
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write("strategy,money,countries,utilities,stations,totalLandsOwned\n");
            for(int i=0; i<hashMap.size(); i++){
                Player p = hashMap.get(i);
                if(p != null) {
                    String strategy = p.getStratey().toString();
                    int money = p.getMoney();
                    int countries = p.getLandsOwnedOfType(Country.class).size();
                    int utilities = p.getLandsOwnedOfType(Utility.class).size();
                    int stations = p.getLandsOwnedOfType(Station.class).size();
                    int landsOwned = p.getLandsOwned().size();
                    writer.write(strategy +","+ money +","+ countries +","+ utilities +","+ stations +","+ landsOwned + "\n");
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
