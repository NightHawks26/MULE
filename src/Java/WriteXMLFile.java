package Java;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Java.Objects.MuleGame;
import Java.Objects.Player;
import Java.Objects.Store;
import Java.Objects.Tile;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

    public static void saveGame(MuleGame muleGame, String fileName) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("muleGame");
            doc.appendChild(rootElement);

            //SAVE GAME INFO
            // staff elements
            Element gameInfo = doc.createElement("gameInfo");
            rootElement.appendChild(gameInfo);

            // difficulty elements
            Element difficulty = doc.createElement("difficulty");
            difficulty.appendChild(doc.createTextNode(muleGame.getDifficulty()));
            gameInfo.appendChild(difficulty);

            // round elements
            Element round = doc.createElement("round");
            round.appendChild(doc.createTextNode(Integer.toString(muleGame.getRound())));
            gameInfo.appendChild(round);

            // purchasePrice elements
            Element purchasePrice = doc.createElement("purchasePrice");
            purchasePrice.appendChild(doc.createTextNode(Integer.toString(muleGame.getPurchasePrice())));
            gameInfo.appendChild(purchasePrice);

            // selectPrice elements
            Element selectPrice = doc.createElement("selectPrice");
            selectPrice.appendChild(doc.createTextNode(Integer.toString(muleGame.getSelectPrice())));
            gameInfo.appendChild(selectPrice);
            // grantPrice elements
            Element grantPrice = doc.createElement("grantPrice");
            grantPrice.appendChild(doc.createTextNode(Integer.toString(muleGame.getGrantPrice())));
            gameInfo.appendChild(grantPrice);
            // playercount elements
            Element playerCount = doc.createElement("playerCount");
            playerCount.appendChild(doc.createTextNode(Integer.toString(muleGame.getPlayers().length)));
            gameInfo.appendChild(playerCount);
            // selectionRound elements
            Element selectionRound = doc.createElement("selectionRound");
            String selectionRoundString = "";
            if (muleGame.selectionRound) {
                selectionRoundString = "true";
            } else {
                selectionRoundString = "false";
            }
            selectionRound.appendChild(doc.createTextNode(selectionRoundString));
            gameInfo.appendChild(selectionRound);
            // currentPlayer elements
            Element currentPlayer = doc.createElement("currentPlayer");
            currentPlayer.appendChild(doc.createTextNode(Integer.toString(muleGame.getCurrentPlayer())));
            gameInfo.appendChild(currentPlayer);
            // timeForTurn elements
            Element tineForTurn = doc.createElement("timeForTurn");
            tineForTurn.appendChild(doc.createTextNode(Integer.toString(muleGame.getTimeForTurn())));
            gameInfo.appendChild(tineForTurn);

            //NOW SAVE PLAYERS
            for (int x = 0; x < muleGame.getPlayers().length; x++) {
                Player current = muleGame.getPlayers()[x];
                // staff elements
                Element player = doc.createElement("player");
                rootElement.appendChild(player);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(current.getName()));
                player.appendChild(name);

                Element race = doc.createElement("race");
                race.appendChild(doc.createTextNode(current.getRace()));
                player.appendChild(race);

                Element color = doc.createElement("color");
                color.appendChild(doc.createTextNode(current.getColor()));
                player.appendChild(color);

                Element food = doc.createElement("food");
                food.appendChild(doc.createTextNode(Integer.toString(current.getFood())));
                player.appendChild(food);

                Element energy = doc.createElement("energy");
                energy.appendChild(doc.createTextNode(Integer.toString(current.getEnergy())));
                player.appendChild(energy);

                Element ore = doc.createElement("ore");
                ore.appendChild(doc.createTextNode(Integer.toString(current.getOre())));
                player.appendChild(ore);

                Element money = doc.createElement("money");
                money.appendChild(doc.createTextNode(Integer.toString(current.getMoney())));
                player.appendChild(money);

                Element crystite = doc.createElement("crystite");
                crystite.appendChild(doc.createTextNode(Integer.toString(current.getCrystite())));
                player.appendChild(crystite);

                Element landCounter = doc.createElement("landCounter");
                landCounter.appendChild(doc.createTextNode(Integer.toString(current.getLandCounter())));
                player.appendChild(landCounter);

                Element score = doc.createElement("score");
                score.appendChild(doc.createTextNode(Integer.toString(current.getScore())));
                player.appendChild(score);

                Element isLast = doc.createElement("isLast");
                String isLastString = "";
                if (current.getIsLast()) {
                    isLastString = "true";
                } else {
                    isLastString = "false";
                }
                isLast.appendChild(doc.createTextNode(isLastString));
                player.appendChild(isLast);

                Element numberOfMules = doc.createElement("numberOfMules");
                numberOfMules.appendChild(doc.createTextNode(Integer.toString(current.getNumberOfMules())));
                player.appendChild(numberOfMules);
            }

            //NOW SAVE TILES
            for (int i = 0; i < 5; i++) {
                for (int k = 0; k < 9; k++) {

                    Tile tileCur = muleGame.getMap().getTile(i, k);
                    // staff elements
                    Element tile = doc.createElement("tile");
                    rootElement.appendChild(tile);

                    Element row = doc.createElement("row");
                    row.appendChild(doc.createTextNode(Integer.toString(tileCur.getRow())));
                    tile.appendChild(row);

                    Element col = doc.createElement("col");
                    col.appendChild(doc.createTextNode(Integer.toString(tileCur.getColumn())));
                    tile.appendChild(col);

                    Element owner = doc.createElement("owner");
                    String ownerString = "";
                    if (tileCur.getOwner() == null) {
                        ownerString = "null";
                    } else {
                        ownerString = tileCur.getOwner().getName();
                    }
                    owner.appendChild(doc.createTextNode(ownerString));
                    tile.appendChild(owner);

                    Element terrain = doc.createElement("terrain");
                    terrain.appendChild(doc.createTextNode(tileCur.getTerrain().getName()));
                    tile.appendChild(terrain);

                    Element mule = doc.createElement("mule");
                    String muleString = "";
                    if (tileCur.getMule() == null) {
                        muleString = "null";
                    } else {
                        muleString = tileCur.getMule().getMuleType();
                    }
                    mule.appendChild(doc.createTextNode(muleString));
                    tile.appendChild(mule);

                }
            }

            //NOW SAVE STORE
            // staff elements
            Element store = doc.createElement("store");
            rootElement.appendChild(store);
            Store curStore = muleGame.getStore();

            Element oreStock = doc.createElement("oreStock");
            oreStock.appendChild(doc.createTextNode(Integer.toString(curStore.getOreStock())));
            store.appendChild(oreStock);

            Element crystiteStock = doc.createElement("crystiteStock");
            crystiteStock.appendChild(doc.createTextNode(Integer.toString(curStore.getCrystiteStock())));
            store.appendChild(crystiteStock);

            Element foodStock = doc.createElement("foodStock");
            foodStock.appendChild(doc.createTextNode(Integer.toString(curStore.getFoodStock())));
            store.appendChild(foodStock);

            Element energyStock = doc.createElement("energyStock");
            energyStock.appendChild(doc.createTextNode(Integer.toString(curStore.getEnergyStock())));
            store.appendChild(energyStock);

            Element muleStock = doc.createElement("muleStock");
            muleStock.appendChild(doc.createTextNode(Integer.toString(curStore.getMuleStock())));
            store.appendChild(muleStock);

            Element orePrice = doc.createElement("orePrice");
            orePrice.appendChild(doc.createTextNode(Integer.toString(curStore.getOrePrice())));
            store.appendChild(orePrice);

            Element crystitePrice = doc.createElement("crystitePrice");
            crystitePrice.appendChild(doc.createTextNode(Integer.toString(curStore.getCrystitePrice())));
            store.appendChild(crystitePrice);

            Element foodPrice = doc.createElement("foodPrice");
            foodPrice.appendChild(doc.createTextNode(Integer.toString(curStore.getFoodPrice())));
            store.appendChild(foodPrice);

            Element energyPrice = doc.createElement("energyPrice");
            energyPrice.appendChild(doc.createTextNode(Integer.toString(curStore.getEnergyPrice())));
            store.appendChild(energyPrice);



            /////-----------------------------------
            /////END OF FILE GENERATING
            ////------------------------------------

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + "/src/resources/xml/" + fileName + ".xml"));

            //new File(System.getProperty("user.dir") + "/src/resources/xml"

            // Output to console for testing
             //StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}