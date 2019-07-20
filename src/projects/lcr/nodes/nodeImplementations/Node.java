package projects.lcr.nodes.nodeImplementations;

import java.awt.Color;  

import java.awt.Graphics;
import java.util.Random;



import projects.lcr.nodes.timers.MessageTimer;

import projects.lcr.nodes.messages.DataMessage;
import projects.lcr.nodes.messages.Jeton;
import projects.lcr.nodes.nodeImplementations.Node;
import projects.sample5.nodes.messages.FloodFindMsg;
import projects.sample5.nodes.messages.PayloadMsg;
import projects.sample5.nodes.nodeImplementations.FNode;
import projects.sample5.nodes.timers.PayloadMessageTimer;
import sinalgo.configuration.Configuration.SectionInConfigFile;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.helper.NodeSelectionHandler;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.Node.NodePopupMethod;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;
import sinalgo.tools.Tools;

public class Node extends sinalgo.nodes.Node {
	
    private int source;
	private int destin;
	Boolean envoyer=false;
	
	@Override
	public void handleMessages(Inbox inbox) {
		while(inbox.hasNext()) {
            Message msg = inbox.next();
			
			
			if (msg instanceof DataMessage) {
				treat((DataMessage) msg);
			} 
			if (msg instanceof Jeton) {
				treat2((Jeton) msg);
			}
				}
	}
		
	private void treat2(Jeton j) {
		
			if(envoyer){
				System.out.println("le noeud :"+source +" capture le jeton \n");
				new MessageTimer(new DataMessage(destin, source)).startRelative(1, this);
				
				
				envoyer=false;
			}
			else {
				broadcast(j);
			}
		
	}
	private void treat(DataMessage m) {
	;
		if(m.getSource()!=this.ID){
			if (m.getDestination()==this.ID){
			System.out.println(this+" recu le message \n");
		this.setColor(Color.CYAN);
		broadcast(m);
			
		
			
		}else {
			broadcast(m);
			System.out.println(this+" recu le message \n");	
		}
		
		
		}else{if(m.getSource()==this.ID) {
			envoyer=false;
	
	System.out.println(this+" libere le jeton \n");
	
	
		broadcast(new Jeton(this));
		this.setColor(Color.MAGENTA);
		
		}else {broadcast(m);
		
		System.out.println(this+" recu le message \n");	
		}
		
		
		}
		
		} 
		

	@Override
	public void init() {
	
		
	}

	@Override
	public void draw(Graphics g, PositionTransformation pt, boolean highlight) {
		drawNodeAsDiskWithText(g, pt, highlight, (new Integer(ID).toString()), 25, Color.white);
	}
	@NodePopupMethod(menuText = "Envoyer message à...")
	public void sendMessageTo() {
		Tools.getNodeSelectedByUser(new NodeSelectionHandler() {
		public void handleNodeSelectedEvent(sinalgo.nodes.Node des) {
				
				destin=des.ID;
			    des.setColor(Color.blue);
				source =Node.this.ID;
				Node.this.setColor(Color.green);
				envoyer=true;
						    
			    System.out.println("le noeud :"+source +" est la source \n");
			    System.out.println("le noeud :"+destin+" est la destination \n");
					
				
				
			
			}
		}, "Selectionner un noeud à qui envoyer un message...");
	}


	@NodePopupMethod(menuText="Lancer le jeton")
	public void broadcast() {
	
		new MessageTimer(new Jeton(this)).startRelative(1, this);
		System.out.println(this +" lance le jeton\n");
	}
	
	@Override
	public void checkRequirements() throws WrongConfigurationException { }

	@Override
	public void preStep() { }

	@Override
	public void neighborhoodChange() { }

	@Override
	public void postStep() {  }
	
}
