package Java;

/**
 * Created by AveryDingler on 10/12/15.
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Java.Objects.MuleGame;
import Java.Objects.Player;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Arrays;

public class XMLParser {

    private Player[] players;

    public XMLParser() {

    }

    public void loadGame(String fileLocation) {

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

                }
            }

            //-------END CREATE PLAYER ARRAY------------


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(players));
    }

}
