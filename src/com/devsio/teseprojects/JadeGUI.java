package com.devsio.teseprojects;

import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JadeGUI {
    private JTextArea messagesReceived;
    public JTextField Message;
    private JButton enviarButton;
    private JPanel mainPanel;
    private JLabel mensajesRecibidosLabel;
    public JTextArea area;

    private String texto;

    public void setMessageText(String message){
        texto = message;
    }

    private GuiAgent agent;

    public JadeGUI(GuiAgent agent)
    {
        this.agent = agent;
        messagesReceived.setText(texto);
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiEvent ge = new GuiEvent(this, 0);
                ge.addParameter("MusicStore");
                ge.addParameter(Message.getText());
                agent.postGuiEvent(ge);
                messagesReceived.append("Yo: " + " " + Message.getText() + "\n");
                Message.setText("");

            }
        });
    }

    public void CreateWindow() {

        JFrame frame = new JFrame("JadeGUI");
        frame.setContentPane(new JadeGUI(agent).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Container c = frame.getContentPane();
        area =(JTextArea) c.getComponent(1);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
