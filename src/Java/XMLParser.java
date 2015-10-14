package Java;

/**
 * Created by AveryDingler on 10/12/15.
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Java.Objects.*;
import io.github.jgkamat.JayLayer.JayLayer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Arrays;

public class XMLParser {

    private Player[] players;
    private Tile[][] tiles = new Tile[5][9];
    private Map map;
    private Store store;
    private JayLayer sound = new JayLayer();
    private MuleGame muleGame;


    public XMLParser() {

    }

    public void setSound(JayLayer sound) {
        this.sound = sound;
    }

    public MuleGame loadGame(String fileLocation) {

        try {

            File fXmlFile = new File(fileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());


            //----------CREATE PLAYER ARRAY------------

            NodeList nList = doc.getElementsByTagName("player");
            players = new Player[nList.getLength()];
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Race : " + eElement.getElementsByTagName("race").item(0).getTextContent());
                    System.out.println("Color : " + eElement.getElementsByTagName("color").item(0).getTextContent());
                    System.out.println("Food : " + eElement.getElementsByTagName("food").item(0).getTextContent());
                    System.out.println("Score : " + eElement.getElementsByTagName("score").item(0).getTextContent());

                    players[temp] = new Player();
                    players[temp].setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    players[temp].setRace(eElement.getElementsByTagName("race").item(0).getTextContent());
                    players[temp].setColor(eElement.getElementsByTagName("color").item(0).getTextContent());
                    players[temp].setFood(Integer.parseInt(eElement.getElementsByTagName("food").item(0).getTextContent()));
                    players[temp].setEnergy(Integer.parseInt(eElement.getElementsByTagName("energy").item(0).getTextContent()));
                    players[temp].setOre(Integer.parseInt(eElement.getElementsByTagName("ore").item(0).getTextContent()));
                    players[temp].setMoney(Integer.parseInt(eElement.getElementsByTagName("money").item(0).getTextContent()));
                    players[temp].setCrystite(Integer.parseInt(eElement.getElementsByTagName("crystite").item(0).getTextContent()));
                    players[temp].setLandCounter(Integer.parseInt(eElement.getElementsByTagName("landCounter").item(0).getTextContent()));
                    players[temp].setScore(Integer.parseInt(eElement.getElementsByTagName("score").item(0).getTextContent()));
                    players[temp].setNumberOfMules(Integer.parseInt(eElement.getElementsByTagName("numberOfMules").item(0).getTextContent()));
                    boolean isLast = false;
                    if (eElement.getElementsByTagName("isLast").item(0).getTextContent().equals("true")) {
                        isLast = true;
                    }
                    players[temp].setIsLast(isLast);

                }
            }

            //-------END CREATE PLAYER ARRAY------------

            //----------CREATE MAP TILES and CRATE MAP------------

            NodeList tileList = doc.getElementsByTagName("tile");
            for (int temp = 0; temp < tileList.getLength(); temp++) {

                Node nNode = tileList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Row : " + eElement.getElementsByTagName("row").item(0).getTextContent());
                    System.out.println("Col : " + eElement.getElementsByTagName("col").item(0).getTextContent());
                    System.out.println("Owner : " + eElement.getElementsByTagName("owner").item(0).getTextContent());
                    System.out.println("Terrain : " + eElement.getElementsByTagName("terrain").item(0).getTextContent());
                    System.out.println("Mule : " + eElement.getElementsByTagName("mule").item(0).getTextContent());

                    int row = Integer.parseInt(eElement.getElementsByTagName("row").item(0).getTextContent());
                    int col = Integer.parseInt(eElement.getElementsByTagName("col").item(0).getTextContent());
                    Player owner = null;
                    Mule mule = null;
                    String terrain = eElement.getElementsByTagName("terrain").item(0).getTextContent();

                    //set player
                    if (!eElement.getElementsByTagName("owner").item(0).getTextContent().equals("null")) {
                        for (Player player : players) {
                            if (eElement.getElementsByTagName("owner").item(0).getTextContent().equals(player.getName())) {
                                owner = player;
                            }
                        }
                    }

                    //set mule

                    if (!eElement.getElementsByTagName("mule").item(0).getTextContent().equals("null")) {
                        mule = new Mule(eElement.getElementsByTagName("mule").item(0).getTextContent());
                    }

                    tiles[row][col] = new Tile(row, col, terrain, owner, mule);
                }
            }

            map = new Map(tiles);

            //-------END MAP TILES-----------

            //------CREATE THE STORE---------

            NodeList storeList = doc.getElementsByTagName("store");
            Node nNode = storeList.item(0);
            Element eElement = (Element) nNode;
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                int oreStock = Integer.parseInt(eElement.getElementsByTagName("oreStock").item(0).getTextContent());
                int crystiteStock = Integer.parseInt(eElement.getElementsByTagName("crystiteStock").item(0).getTextContent());
                int foodStock = Integer.parseInt(eElement.getElementsByTagName("foodStock").item(0).getTextContent());
                int energyStock = Integer.parseInt(eElement.getElementsByTagName("energyStock").item(0).getTextContent());
                int muleStock = Integer.parseInt(eElement.getElementsByTagName("muleStock").item(0).getTextContent());
                int orePrice = Integer.parseInt(eElement.getElementsByTagName("orePrice").item(0).getTextContent());
                int crystitePrice = Integer.parseInt(eElement.getElementsByTagName("crystitePrice").item(0).getTextContent());
                int foodPrice = Integer.parseInt(eElement.getElementsByTagName("foodPrice").item(0).getTextContent());
                int energyPrice = Integer.parseInt(eElement.getElementsByTagName("energyPrice").item(0).getTextContent());
                store = new Store(oreStock, crystiteStock, foodStock, energyStock, muleStock,
                        orePrice, crystitePrice, foodPrice, energyPrice);

            }

            //-------END CREATE THE STORE-------

            //-------FINALIZE MULE GAME-----------
            muleGame = new MuleGame(map, players, sound, store);
            NodeList muleGameList = doc.getElementsByTagName("gameInfo");
            Node muleGameNode = muleGameList.item(0);
            Element gameElement = (Element) muleGameNode;
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                String difficulty = gameElement.getElementsByTagName("difficulty").item(0).getTextContent();
                int round = Integer.parseInt(gameElement.getElementsByTagName("round").item(0).getTextContent());
                int purchasePrice = Integer.parseInt(gameElement.getElementsByTagName("purchasePrice").item(0).getTextContent());
                int selectPrice = Integer.parseInt(gameElement.getElementsByTagName("selectPrice").item(0).getTextContent());
                int grantPrice = Integer.parseInt(gameElement.getElementsByTagName("grantPrice").item(0).getTextContent());
                int playerCount = Integer.parseInt(gameElement.getElementsByTagName("playerCount").item(0).getTextContent());
                Boolean selectionRound = false;
                if (gameElement.getElementsByTagName("selectionRound").item(0).getTextContent().equals("true")) {
                    System.out.println("selection round true");
                    selectionRound = true;
                }
                int currentPlayer = Integer.parseInt(gameElement.getElementsByTagName("currentPlayer").item(0).getTextContent());
                int timeForTurn = Integer.parseInt(gameElement.getElementsByTagName("timeForTurn").item(0).getTextContent());
                muleGame.setRound(round);
                muleGame.setDifficulty(difficulty);
                muleGame.setPurchasePrice(purchasePrice);
                muleGame.setSelectPrice(selectPrice);
                muleGame.setGrantPrice(grantPrice);
                muleGame.setPlayerCount(playerCount);
                muleGame.setSelectionRound(selectionRound);
                muleGame.setCurrentPlayer(currentPlayer);
                muleGame.setTimeForTurn(timeForTurn);
                muleGame.setPlayers(players);
                muleGame.setStore(store);
                muleGame.setMap(map);
                muleGame.setSound(sound);
            }

            //-------FINALIZE MULE GAME--------


        } catch (Exception e) {
            e.printStackTrace();
        }

        return muleGame;
    }

}
