 package model;

import java.util.ArrayList;

public class TradeCommodity {
    private static int counter = 0;
    private static final ArrayList<TradeCommodity> tradeCommodities = new ArrayList<>();
    private final Empire senderRequest;
    private final Empire receiverRequest;
    private final String resourceType;
    private final String resourceAmount;
    private final int price;
    private final String messageSender;
    private String messageReceiver;
    private boolean accept;
    public TradeCommodity(Empire senderRequest,
                          Empire receiverRequest,
                          String resourceType,
                          String resourceAmount,
                          int price,
                          String message) {
        this.senderRequest = senderRequest;
        this.receiverRequest = receiverRequest;
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.messageSender = message;
        this.messageReceiver = null;
        this.accept = false;
    }

    public static ArrayList<TradeCommodity> getTradeCommodities() {
        return tradeCommodities;
    }

    public static void addTradeCommodities(TradeCommodity tradeCommoditiy) {
        tradeCommodities.add(tradeCommoditiy);
    }

    public Empire getSenderRequest() {
        return senderRequest;
    }
    public Empire getReceiverRequest() {
        return receiverRequest;
    }
    public String getResourceType() {
        return resourceType;
    }
    public String getResourceAmount() {
        return resourceAmount;
    }
    public int getPrice() {
        return price;
    }
    public String getMessageSender() {
        return messageSender;
    }

    public String getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(String messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
    public static int getCounter() {
        return counter;
    }

    public static void setCounter() {
        counter++;
    }
}
