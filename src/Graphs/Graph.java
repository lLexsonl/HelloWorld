/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

/**
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @author MIPC
 * @param <V>
 * @param <E>
 */
public interface Graph<V, E> {
    /**
     * Returns the number of the vertices of the graph.
     * @return 
     */
    int numVertices();
    /**
     * Returns the number of edges of the graph.
     * @return 
     */
    int numEdges();
    /**
     * Returns the vertices of the graph as an iterable collection.
     * @return 
     */
    Iterable<Vertex<V>> vertices();
    /**
     * Returns the edges of the graph as an iterable collection.
     * @return 
     */
    Iterable<Edge<E>> edges();
    /**
     * Returns the number of the edges leaving vertex v.
     * Note that for an undirected graph, this is the same result
     * returned by inDegree
     * @param v
     * @return
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    int outDegree(Vertex <V> v) throws IllegalArgumentException;
    /**
     * Returns the number of edges for which vertex v is the destination.
     * Note that for an undirected graph, this is the same result
     * returned by onDegree
     * @param v
     * @return
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    int inDegree(Vertex<V> v)throws IllegalArgumentException;
    /**
     * Returns an iterable collection of edges for which vertex v is the origin.
     * Note that for an undirected graph, this is the same result
     * returned by incomingEdges.
     * @param v
     * @return
     * @throws IllegalArgumentException 
     */
    Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException;
    /**
     * Returns an iterable collection of edges for which vertex v is the origin.
     * Note that for an undirected graph, this is the same result
     * returned by outcomingEdges.
     * @param v
     * @return
     * @throws IllegalArgumentException 
     */
    Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException;
    /**
     * Returns the edges from u to v, null if they  are not adjencent.
     * @param u
     * @param v
     * @return
     * @throws IllegalArgumentException 
     */
    Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException;
    /**
     * Returns the vertices of edges e as an array of lenght two.
     * @param e
     * @return
     * @throws IllegalArgumentException 
     */
    Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException;
    /**
     * Raturns the vertex that is opposite vertex v on edge e.
     * @param v
     * @param e
     * @return
     * @throws IllegalArgumentException 
     */
    Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException;
    /**
     * Inserts and returns a new vertex with the given element.
     * @param element
     * @return 
     */
    Vertex<V> insertVertex(V element);
    /**
     * Inserts and returns a new edge between vertices u and v, sorting given element.
     * @param u
     * @param v
     * @param element
     * @return
     * @throws IllegalArgumentException if u or v are invalid vertices, or if an edge already exists between u and v.
     */
    Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException;
    /**
     * Removes a vertex and all its inceident edges from the graph.
     * @param v
     * @throws IllegalArgumentException 
     */
    void removeVertex(Vertex<V> v) throws IllegalArgumentException;
    /**
     * Removes an edge from the graph.
     * @param e
     * @throws IllegalArgumentException 
     */
    void removeEdge(Edge<E> e) throws IllegalArgumentException;
}
