package projects.lcr.nodes.messages;


import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Message;

public class Jeton extends Message {
final private Node jtn;
	
	public Jeton(Node jtn) {
		this.jtn = jtn;
	}
	
	public Node getJeton() {
		return jtn;
	}
	
	@Override
	public Message clone() {
		return this;
	}}


