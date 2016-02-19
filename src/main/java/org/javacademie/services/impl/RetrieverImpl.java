package org.javacademie.services.impl;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.javacademie.main.Main;
import org.javacademie.services.Retriever;
import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.vocabulary.RDF;

public class RetrieverImpl implements Retriever {
	
	private static Logger logger = Logger.getLogger(RetrieverImpl.class);
	
	Model shema = null;
    Model data = null;
    InfModel infmodel = null;
	String uriShema = "http://xmlns.com/foaf/0.1/";
	
	//Constructor
		public RetrieverImpl(String uriData){
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
		public int numberOfPerson(String iri){
			int numberOfPerson = 0 ;
			Resource person = infmodel.getResource("http://xmlns.com/foaf/0.1/Person");
	        logger.info("the instances of foaf:Person in the dataset:");
	        for (StmtIterator i = infmodel.listStatements(null,RDF.type,person); i.hasNext(); ) {
	        	Statement stmt = i.nextStatement();
	        	numberOfPerson++;
		    }
	        return numberOfPerson;
		}
		
		
		
		
}
