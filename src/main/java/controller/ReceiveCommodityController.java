package controller;

import model.Empire;
import model.Player;
import model.Shop;
import model.TradeCommodity;

public class ReceiveCommodityController {
    public void showPriceList() {
        for (Shop commodities : Shop.getCommodities()) {
            System.out.println("name commodities: " + commodities.getItemName() + " purchase price: " + commodities.getPrice()
                    + " sales price: " + (commodities.getPrice() / 2) + " amount: " + commodities.getItemAmount() + " category: " + commodities.getCategory());
        }
    }

    public void buyCommodities(Player player, String category, String itemName, int itemAmount) {
        Shop commoditiesBuy = serchCommoditiesByItemName(itemName);
        commoditiesBuy.setItemAmount(commoditiesBuy.getItemAmount() - itemAmount);
        int priceCommodities = (commoditiesBuy.getPrice() * itemAmount);
        //paiin empire player ro migire va be meghdar item va gheymatesh pol kam mishe
        //Empire empire = serchEmpireByPlayer(player);

        if (category.equals("armoury")) {
            //be kala to armoury on ezaffe mishe
        }
        if (category.equals("stockpile")) {
            //be kala to stockpile on ezaffe mishe
        }
        if (category.equals("granary")) {
            //be kala to granary on ezaffe mishe
        }
    }

    public void sellCommodities(Player player, String category, String itemName, int itemAmount) {
        Shop commoditiesBuy = serchCommoditiesByItemName(itemName);
        commoditiesBuy.setItemAmount(commoditiesBuy.getItemAmount() + itemAmount);
        int priceCommodities = ((commoditiesBuy.getPrice() / 2) * itemAmount);
        //paiin empire player ro migire va az meghdar item va gheymatesh pol ziad mishe
        //Empire empire = serchEmpireByPlayer(player);

        if (category.equals("armoury")) {
            //az kala to armoury on kam mishe
        }
        if (category.equals("stockpile")) {
            //az kala to stockpile on kam mishe
        }
        if (category.equals("granary")) {
            //az kala to granary on kam mishe
        }

    }

    private Shop serchCommoditiesByItemName(String itemName) {
        for (Shop commodity : Shop.getCommodities()) {
            if (commodity.getItemName().equals(itemName)) return commodity;
        }
        return null;
    }

    public void addCommodities() {
    }

    //---------------------------------------------------------------------------------------
    public void showAllPlayer() {
        //player haye dakhel baziro besorat for migire va name & id ro mide
    }

    public void sendRequest(Empire senderRequest,
                            Empire receiverRequest,
                            String resourceType,
                            String resourceAmount,
                            int price,
                            String message) {
        TradeCommodity.addTradeCommodities(new TradeCommodity(senderRequest, receiverRequest, resourceType, resourceAmount, price, message));
    }

    public void showMyRequest(Empire empire) {
        for (TradeCommodity tradeCommodity : TradeCommodity.getTradeCommodities()) {
            if (tradeCommodity.getReceiverRequest().getPlayer().getUsername().equals(empire.getPlayer().getUsername())) {
                System.out.print("sender request name: " + tradeCommodity.getSenderRequest().getPlayer().getUsername() +
                        "\n resource type: " + tradeCommodity.getResourceType() + "\n resource amount: " + tradeCommodity.getResourceAmount() +
                        "\nprice: " + tradeCommodity.getPrice() + "\nmessage Sender: " + tradeCommodity.getMessageSender());
                if (tradeCommodity.isAccept())
                    System.out.print("message receiver: " + tradeCommodity.getMessageReceiver()
                            + "\nthe request has been accepted");

                if (!tradeCommodity.isAccept()) System.out.println("the request has not been accepted");

                System.out.println("-------------------------------------------------------------------------");
            }
        }
    }

    public void tradeAccept(Empire receiverRequest,
                            Empire senderRequest,
                            String message) {
        TradeCommodity getTradCommodityByUsername = serchCommoditiesByName(senderRequest, receiverRequest);
        getTradCommodityByUsername.setAccept(true);
        getTradCommodityByUsername.setMessageReceiver(message);
        //age tedad kalaye darkast shode kmtar az sefr nashod trade kone
    }

    private TradeCommodity serchCommoditiesByName(Empire senderRequest, Empire receiverRequest) {
        for (TradeCommodity tradeCommodity : TradeCommodity.getTradeCommodities()) {
            if ((tradeCommodity.getSenderRequest().equals(senderRequest)) &&
                    (tradeCommodity.getReceiverRequest().equals(receiverRequest)) &&
                    (!tradeCommodity.isAccept())) return tradeCommodity;
        }
        return null;
    }

    public void tradeHistory(Empire empire) {
        for (TradeCommodity tradeCommodity : TradeCommodity.getTradeCommodities()) {
            if (tradeCommodity.isAccept()) {
                if ((tradeCommodity.getReceiverRequest().equals(empire)) ||
                        (tradeCommodity.getSenderRequest().equals(empire))) {
                    System.out.print("Sender request name: " + tradeCommodity.getSenderRequest().getPlayer().getUsername() +
                            "\nReceiver request: " + tradeCommodity.getReceiverRequest().getPlayer().getUsername() +
                            "\n resource type: " + tradeCommodity.getResourceType() + "\n resource amount: " + tradeCommodity.getResourceAmount() +
                            "\nprice: " + tradeCommodity.getPrice() + "\nmessage Sender: " + tradeCommodity.getMessageSender() +
                            "\nmessage receiver: " + tradeCommodity.getMessageReceiver());
                }
            }
        }
    }

    public void sowNewRequest(){
        for (int i = TradeCommodity.getCounter(); i < TradeCommodity.getTradeCommodities().size(); i++) {
            TradeCommodity tradeCommodity = TradeCommodity.getTradeCommodities().get(i);
            System.out.print("sender request name: " + tradeCommodity.getSenderRequest().getPlayer().getUsername() +
                    "\n resource type: " + tradeCommodity.getResourceType() + "\n resource amount: " + tradeCommodity.getResourceAmount() +
                    "\nprice: " + tradeCommodity.getPrice() + "\nmessage Sender: " + tradeCommodity.getMessageSender());
            TradeCommodity.setCounter();
        }
    }
}