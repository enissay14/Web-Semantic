package org.javacademie.services.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.log4j.Logger;
import org.javacademie.services.Classifier;
import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class ClassifierImpl implements Classifier {
	
	private static Logger logger = Logger.getLogger(Classifier.class);
	
	Model shema = null;
    Model data = null;
    InfModel infmodel = null;
	String uriShema = "http://xmlns.com/foaf/0.1/";
	
	//Constructor
	public ClassifierImpl(String uriData){
		super();
		data = ModelFactory.createDefaultModel();
		shema = ModelFactory.createDefaultModel();
		
		JenaRdfaReader.inject();
		logger.info("read the RDFa in :"+uriShema);
        shema.read(uriShema, "RDFA");
        logger.info("read the RDFa in :"+uriData);
        data.read(uriData, "RDFA");
        infmodel = ModelFactory.createRDFSModel(shema, data);
	}

	@Override
	public Collection<String> retrieveTypes(String iri) {
		logger.info("in retrievTypes");
		Collection<String> classes = new HashSet<>();
	    logger.info("création de la Resource  ("+iri+")");
        Resource resIRI = ResourceFactory.createResource(iri);
        for (StmtIterator k = infmodel.listStatements(new SimpleSelector(resIRI, RDF.type,(RDFNode) null)); k.hasNext(); ) {
	        Statement stmt = k.nextStatement();
	        logger.info("Found c : "+stmt.getObject().toString()+"  such that i rdf:type c is a triple in gi ");
	        classes.add(stmt.getObject().toString());
	        logger.info(stmt.getObject().toString() + " added to classes1 ");
        }
		return classes;
	}

	@Override
	public Collection<String> retrieveSuperClasses(String iri) {
		logger.info("in retrievSuperClasses");
		Collection<String> classes = new HashSet<>();
		logger.info("création de la Resource  ("+iri+")");
		Resource resIRI = ResourceFactory.createResource(iri);
		for (StmtIterator j = infmodel.listStatements(new SimpleSelector(resIRI, RDFS.subClassOf,(RDFNode) null)); j.hasNext(); ) {
        		 Statement stmt = j.nextStatement();
        		 logger.info("Found d : "+stmt.getObject().toString()+" such that e rdfs:subClassof d is a triple in ge ");
        		 classes.add(stmt.getObject().toString());
        		 logger.info(stmt.getObject().toString() + " added to classes2 ");
        	 }
        
		return classes;
	}

	@Override
	public Collection<String> getAllTypes(String url) {
		logger.info("in getAllTypes");
		Collection<String> classes = new HashSet<>();
		Collection<String> classesinter = new HashSet<>();
		
		for(String classe : this.retrieveTypes(url)){
			classesinter = this.retrieveSuperClasses(classe);
			for(String classeinter : classesinter){
				classes.add(classeinter);
			}
			
        }
		return classes;
	}

	@Override
	public boolean isOfType(String entityIRI, String classIRI) {
		logger.info("isOfType");
		Collection<String> classes = new HashSet<>();
		classes = this.getAllTypes(entityIRI);
		for (String classe : classes){
			if(classe.contentEquals(classIRI))
				return true;
		}
		return false;
	}
	
	

}
