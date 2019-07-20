package projects.lcr.nodes.messages;

import projects.sample5.nodes.timers.RetryPayloadMessageTimer;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Message;

public class DataMessage extends Message {
	private int destination; 
	private int source; 
	


 public DataMessage(int destination,int source) {
		
		this.source=source;
		this.destination=destination;
	}
 

	@Override
	public Message clone() {
		return this; 
	}


	public int getDestination() {
		return destination;
	}





	public int getSource() {
		return source;
	}




}
