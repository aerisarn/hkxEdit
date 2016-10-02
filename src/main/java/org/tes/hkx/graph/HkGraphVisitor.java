package org.tes.hkx.graph;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.tes.hkx.model.IHkParentVisitor;
import org.tes.hkx.model.IHkVisitable;

public class HkGraphVisitor implements IHkParentVisitor<DefaultDirectedGraph<IHkVisitable, DefaultEdge>>{
	
	DefaultDirectedGraph<IHkVisitable, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

	@Override
	public void visit(IHkVisitable obj, IHkVisitable parent) {
		g.addVertex(parent);
		g.addVertex(obj);
		g.addEdge(parent, obj);
	}

	@Override
	public DefaultDirectedGraph<IHkVisitable, DefaultEdge> getResults() {
		return g;
	}

}
