package org.javacademie.services;

import java.util.Collection;

public interface Classifier {

	/**
	 * This method retrieves all the IRIs that correspond to classes of which the argument is an instance.
	 * These classes are determined by dereferencing the IRI in parameter and finding all the objects of triples having the parameter as a subject and <tt>rdf:type</tt> as its predicate.
	 * Note that the property <tt>rdf:type</tt> is defined as a static attribute of the Jena class <tt>RDF</tt>. That is, the Jena <tt>Property</tt> <tt>RDF.type</tt> can be used directly without using the full IRI for <tt>rdf:type</tt>.
	 * 
	 * @param iri the IRI of an entity
	 * @return The collection of IRIs that correspond to the classes of which <tt>iri</tt> is an instance.
	 */
	public Collection<String> retrieveTypes(String iri);
	
	/**
	 * This method retrieves all the IRIs that correspond to classes of which the argument is a subclass.
	 * These classes are determined by dereferencing the IRI in parameter and finding all the objects of triples having the parameter as a subject and <tt>rdfs:subClassOf</tt> as its predicate.
	 * Note that the property <tt>rdfs:subClassOf</tt> is defined as a static attribute of the Jena class <tt>RDFS</tt>. That is, the Jena <tt>Property</tt> <tt>RDFS.subClassOf</tt> can be used directly without using the full IRI for <tt>rdfs:subClassOf</tt>.
	 * 
	 * @param iri the IRI of a class
	 * @return The collection of IRIs that correspond to the classes of which <tt>iri</tt> is a subclass.
	 */
	public Collection<String> retrieveSuperClasses(String iri);
	
	
	/**
	 * This method retrieves all the IRIs of all the classes of which the argument is an instance.
	 * These classes are determined recursively by dereferencing the IRI in parameter and finding all its types, then by dereferencing all the resulting IRIs to find their superclasses, and repeating the process recursively.
	 * 
	 * @param iri the IRI of an entity
	 * @return The collection of IRIs that correspond to the classes of which <tt>iri</tt> is an instance.
	 */
	public Collection<String> getAllTypes(String url);
	
	
	/**
	 * Returns true if <tt>entityIRI</tt> is of type <tt>classIRI</tt> based on the exploration that <tt>getAllTypes</tt> does.
	 *
	 * @param entityIRI the IRI of an entity
	 * @param classIRI the IRI of a class
	 * @return True if <tt>entityIRI</tt> is of type <tt>classIRI</tt>.
	 */	  
	public boolean isOfType(String entityIRI, String classIRI);
}