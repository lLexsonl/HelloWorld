/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

import Hash.ProbeHashMap;
import Map.Map;
import Position.LinkedPositionalList;
import Position.Position;
import Position.PositionalList;

/**
 * An implementation for a graph structure using an adjacency map for each vertex.
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @param <V> data type of the vertex
 * @param <E> data type of the edges
 */
public class AdjancencyMapGraph<V, E> implements Graph<V, E> {
    //<editor-fold defaultstate="collapsed" desc="AdjancencyMapGraph">
    private boolean isDirected;
    private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();
    /**
     * Constructs an empty graph(either undirected or directed)
     * @param directed The parameter determines whether this is an undirected or directed graph.
     */
    public AdjancencyMapGraph(boolean directed){isDirected = directed;}
    /**
     * Returns the number of vertices of the graph
     * @return 
     */
    @Override
    public int numVertices(){return vertices.size();}
    /**
     * Returns the vertices of the graph as an iterable collection
     * @return 
     */
    @Override
    public Iterable<Vertex<V>> vertices(){return vertices;}
    /**
     * Returns the number of edges of the graph
     * @return 
     */
    @Override
    public int numEdges(){return edges.size();}
    /**
     * Returns the edges of the graph as an iterable collection
     * @return 
     */
    @Override
    public Iterable<Edge<E>> edges(){return edges;}
    /**
     * Returns the number of edges for which vertex v is the origin
     * @param v
     * @return 
     */
    @Override
    public int outDegree(Vertex<V> v)throws IllegalArgumentException{
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().size();
    }
    /**
     * Returns an iterable collection of edges for which vertex v is the origin
     * @param v
     * @return 
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)throws IllegalArgumentException{
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().values();//edges are the values in the adjancency map
    }
    /**
     * Returns the number of edges for which vertex v is the destination
     * @param v
     * @return 
     */
    @Override
    public int inDegree(Vertex<V> v)throws IllegalArgumentException{
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().size();
    }
    /**
     * Returns an iterable collection of edges for which vertex v is the destination
     * @param v
     * @return 
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v)throws IllegalArgumentException{
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().values();
    }
    /**
     * Returns the edge from u to v, or null if they are not adjacent.
     * @param u
     * @param v
     * @return 
     */
    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)throws IllegalArgumentException{
        InnerVertex<V> origin = validate(u);
        return origin.getOutgoing().get(v);// will be null if no edge from u to v
    }
    /**
     * Returns the vertices of edges e as an array of lenght two
     * @param e
     * @return 
     */
    @Override
    public Vertex<V>[] endVertices(Edge<E> e)throws IllegalArgumentException{
        InnerEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }
    /**
     * Returns the vertex that is opposite vertex v in edge e
     * @param v
     * @param e
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException{
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoint = edge.getEndpoints();
        if(endpoint[0] == v)
            return endpoint[1];
        else if(endpoint[1] == v)
            return endpoint[0];
        else
            throw new IllegalArgumentException("v is not incident to this edge");
    }
    /**
     * Inserts and returns a new vertex with the given element.
     * @param element
     * @return 
     */
    @Override
    public Vertex<V> insertVertex(V element){
        InnerVertex<V> v = new InnerVertex<>(element, isDirected);
        v.setPosition(vertices.addLast(v));
        return v;
    }
    /**
     * Inserts and returns a new vertex with the given element.
     * @param u
     * @param v
     * @param element
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException{
        if(getEdge(u, v) == null){
            InnerEdge<E> e = new InnerEdge<>(u, v, element);
            e.setPosition(edges.addLast(e));
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.getOutgoing().put(v, e);
            dest.getIncoming().put(u, e);
            return e;
        }else
            throw new IllegalArgumentException("Edge from u to v exist");
    }
    /**
     * Removes a vertex and all its incident edges from the graph. 
     * @param v 
     */
    @Override
    public void removeVertex(Vertex<V> v){
        InnerVertex<V> vert = validate(v);
        //remove all incident edges from the graph
        for(Edge<E> e : vert.getOutgoing().values())
            removeEdge(e);
        for(Edge<E> e : vert.getIncoming().values())
            removeEdge(e);
        //remove this vertex from the list of vertices
        vertices.remove(vert.getPosition());
        vert.setPosition(null);         //invalidates the vertex
    }
    /**
     * Removes an edge from the grapph
     * @param e
     * @throws IllegalArgumentException 
     */
    @Override
    public void removeEdge(Edge<E> e) throws IllegalArgumentException{
        InnerEdge<E> edge = validate(e);
        //remove this edge from vertices' adjacencies
        InnerVertex<V>[] verts = (InnerVertex<V>[]) edge.getEndpoints();
        verts[0].getOutgoing().remove(verts[1]);
        verts[1].getOutgoing().remove(verts[0]);
        //Removes this edges from the list of edges
        edges.remove(edge.getPosition());
        edge.setPosition(null);         //invalidates the edges
    }
    
    private InnerVertex<V> validate(Vertex<V> v){
        if(!(v instanceof InnerVertex)) throw new IllegalArgumentException("Invalid vertex");
        InnerVertex<V> vert = (InnerVertex<V>) v;       //safe cast
        if(!vert.validate(this)) throw new IllegalArgumentException("Invalid vertex");
        return vert;
    }
    
    private InnerEdge<E> validate(Edge<E> e){
        if(!(e instanceof InnerEdge)) throw new IllegalArgumentException("Invalid edge");
        InnerEdge<E> edge = (InnerEdge<E>) e;       // safe cast
        if(!edge.validate(this)) throw new IllegalArgumentException("Invalid edge");
        return edge;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="InnerVertex Class">
    /**
     * A vertex of an adjencency map graph representation.
     * @param <V> 
     */
    private class InnerVertex<V> implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> pos;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;
        /**
         * Constructs a new InnerVertex instance storing the given element.
         * @param elem
         * @param graphlsDirected 
         */
        public InnerVertex(V elem, boolean graphIsDirected){
            element = elem;
            outgoing = new ProbeHashMap<>();
            if(graphIsDirected)
                incoming = new ProbeHashMap<>();
            else
                incoming = new ProbeHashMap<>();        //if undirected, alias outgoing map
        }
        /**
         * Validates that this vertex instance belongs to the given graph.
         * @param graph
         * @return 
         */
        public boolean validate(Graph<V, E> graph){
            return (AdjancencyMapGraph.this == graph && pos != null);
        }
        /**
         * Returns the element associated with the vertex.
         * @return 
         */
        @Override
        public V getElement(){return element;}
        /**
         * Stores the position of this vertex within the graph's vertex list.
         * @param p 
         */
        public void setPosition(Position<Vertex<V>> p){pos = p;}
        /**
         * Returns the position of this vertex within the graph's vertex list.
         * @return 
         */
        public Position<Vertex<V>> getPosition(){return pos;}
        /**
         * Returns reference to the undelying map of outgoing edges.
         * @return 
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing(){return outgoing;}
        /**
         * Returns reference to the undelying map of incoming edges.
         * @return 
         */
        public Map<Vertex<V>, Edge<E>> getIncoming(){return incoming;}

    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="InnerEdge Class">
    private class InnerEdge<E> implements Edge<E>{
        private E element;
        private Position<Edge<E>> pos;
        private Vertex<V>[] endpoints;
        /**
         * Constructs InnerEdge indtsnce from u to v, storing the given element.
         * @param u
         * @param v
         * @param elem 
         */
        public InnerEdge(Vertex<V> u, Vertex<V> v, E elem){
            element = elem;
            endpoints = (Vertex<V> []) new Vertex[]{u,v};//array of lenght 2
        }
        /**
         * Returns the element associated eith the edge.
         * @return 
         */
        @Override
        public E getElement(){return element;}
        /**
         * Returns reference to the endpoint array.
         * @return 
         */
        public Vertex<V>[] getEndpoints(){return endpoints;}
        /**
         * Validates that this edge instance belongs to the given graph.
         * @param graph
         * @return 
         */
        public boolean validate(Graph<V, E> graph){
            return AdjancencyMapGraph.this == graph && pos != null;
        }
        /**
         * Stores the position of this edge within the graph's vertex list.
         * @param p 
         */
        public void setPosition(Position<Edge<E>> p){pos = p;}
        /**
         * Returns the position of this edge within the graph's vertex list.
         * @return 
         */
        public Position<Edge<E>> getPosition(){ return pos;}
    }
    //</editor-fold>
}
