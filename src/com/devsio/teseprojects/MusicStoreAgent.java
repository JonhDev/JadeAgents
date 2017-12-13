package com.devsio.teseprojects;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class MusicStoreAgent extends Agent {
    public void setup(){
        CyclicBehaviour cb = new MusicStoreBehaviour(this);
        addBehaviour(cb);
    }

    class MusicStoreBehaviour extends CyclicBehaviour{

        public MusicStoreBehaviour(Agent agent){
            super(agent);
        }

        @Override
        public void action() {
            ACLMessage reply = receive();
            if(reply != null){
                ACLMessage replyMsg = reply.createReply();
                String content = reply.getContent();
                String sender = reply.getSender().getName();
                switch (content){
                    case "hola":
                        replyMsg.setPerformative(ACLMessage.INFORM);
                        replyMsg.setContent("Hola, ¿en que puedo ayudarte?");
                        send(replyMsg);
                        break;
                    case "":
                        replyMsg.setPerformative(ACLMessage.INFORM);
                        replyMsg.setContent("Al parecer a alguien le dio hueva escribir :/");
                        send(replyMsg);
                        break;
                    default:
                        replyMsg.setPerformative(ACLMessage.INFORM);
                        replyMsg.setContent("No entendi tu mensaje");
                        send(replyMsg);
                }
            }else{
                block();
            }
        }
    }
}
