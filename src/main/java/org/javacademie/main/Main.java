package org.javacademie.main;



import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.javacademie.services.Classifier;
import org.javacademie.services.impl.ClassifierImpl;
import org.javacademie.services.impl.RetrieverImpl;
import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.vocabulary.RDF;

public class Main {
	
	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		
		/*
		String uriData  = "http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi";
		ClassifierImpl classifierIml = new ClassifierImpl(uriData);
		
		//returns the collection of IRIs that are classes of the input uriData
		printClass(classifierIml.retrieveTypes(uriData));
		
		// returning the collection of IRIs that are superclasses of the input uriData
		//printClass(classifierIml.retrieveSuperClasses(uriData));
		
		//returning the complete set of classes that the input uriData belongs to
		printClass(classifierIml.getAllTypes(uriData));
		*/
		
		//Navigating the model 
		
		/*
		//1
		String uriData  = "http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html";
		RetrieverImpl retrieverImpl = new RetrieverImpl(uriData);
		int numberOfPerson = retrieverImpl.numberOfPerson(uriData);
		logger.info("The number of entities of type foaf:Person there is in the RDF of the  W3C Staff page is : "+ numberOfPerson);
		*/
		
		/*
		//2
		String classIRI ="http://www.emse.fr/~zimmermann/Teaching/SemWeb/vocab.ttl#Scientist";
		String entityIRI ="http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi";
		ClassifierImpl classifierIml = new ClassifierImpl(entityIRI);
		printClass(classifierIml.getAllTypes(entityIRI));
		if(classifierIml.isOfType(entityIRI, classIRI))
			logger.info(entityIRI+" is of type :"+classIRI);
		else
			logger.info(entityIRI+" is not of type :"+classIRI);	
			*/
	
	}

	private static void printClass(Collection<String> classes) {
		
		logger.info("affichage des classes...");
        for(String str : classes){
        	logger.info(str);
        }
		
	}

	
}
