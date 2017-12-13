package com.devsio.teseprojects;

import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.core.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import javax.swing.*;
import java.awt.*;

public class JadeAgent extends GuiAgent {
    JadeGUI gui = new JadeGUI(this);
    public void setup(){
        CyclicBehaviour cb = new TalkBehaviour(this);
        addBehaviour(cb);
        gui.CreateWindow();
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        String receiverName = (String) guiEvent.getParameter(0);
        String msgContent = (String) guiEvent.getParameter(1);
        ACLMessage toSend = new ACLMessage(ACLMessage.INFORM);
        toSend.setContent(msgContent);
        toSend.setPerformative(ACLMessage.INFORM);
        toSend.addReceiver(new AID(receiverName, AID.ISLOCALNAME));
        send(toSend);
    }

    class TalkBehaviour extends CyclicBehaviour
    {
        public TalkBehaviour(GuiAgent agent) {
            super(agent);
        }

        @Override
        public void action() {

            ACLMessage reply = receive();
            if(reply != null){
                String content = reply.getContent();
                String sender = reply.getSender().getName();
                gui.area.append("Music Store MX: " + " " + content + "\n");
            }else{
                block();
            }
        }
    }
}
