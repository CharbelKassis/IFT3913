package uml.parser;

import java.io.*;
import java.util.ArrayList;

import uml.exception.UML_FileNotParsedException;
import uml.exception.UML_MissingSyntaxException;
import uml.extraction.*;
import uml.struct.*;

import static uml.extraction.UML_Element.*;

/**
 * 
 * @author Charbel Kassis
 *
 * La classe principale du parseur UML. Lis le fichier puis genere une structure d'objet UML qui va se lire en profondeur
 * lors de l'affichage de la GUI. La racine de la structure est l'objet UML_Model model.
 */
public final class UML_Parser {
	
	private File file; /* le fichier a parser */
	private BufferedReader reader; /* l'objet servant a lire un fichier */
	private String line; /* pointeur vers la ligne extraite avec this.reader */
	private UML_Element nextElement; /* l'element UML courant qui se fait parser */
	private UML_Model model; /* pointeur vers le debut de la structure en arbre du modele UML */
	private Extractor extractor; /* pointeur vers l'objet de type Extractor */
	/**
	 * @param file Le fichier a parser
	 * @throws FileNotFoundException 
	 * @throws IOException
	 * 
	 */
	
	public UML_Parser(File file) throws FileNotFoundException {
		
		this.addFile(file);
	}
	
	public UML_Parser() {
		
	}
	
	public void addFile(File file) throws FileNotFoundException {
		
		this.file = file;
		FileInputStream fileInputStream = new FileInputStream(this.file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		this.reader = new BufferedReader(inputStreamReader);
		this.nextElement = MODEL; /* le premier element a parser est le model */
		
	}

	public UML_Model getModel() {
		
		if(this.model == null)
			
			throw new UML_FileNotParsedException();
		
		return this.model;
	}
	/**
	 * @throws IOException
	 * Debuter le parsing du fichier
	 */
	public void parse() throws IOException {
		
		
		while( (this.line = this.reader.readLine()) != null) {

			/* si la ligne est vide alors sauter a la prochaine ligne */
			if (this.line.isEmpty())
				
				continue;
			
			else if (this.line.equals(";")) {
				
				this.nextElement = LIST_DEC;
				continue;
				
			}
			
			/* sinon, dependement de l'element a parser, effectuer une action */
			switch(this.nextElement) {
			
			case MODEL: this.parseModel();break;
			case LIST_DEC: this.parseListDec();break;
			case ATTRIBUTES: this.parseAttributes();break;
			case ATTRIBUTE: this.parseAttribute();break;	
			case OPERATIONS: this.parseOperations();break;
			case OPERATION: this.parseOperation();break;
			case END: this.parseEnd();break;
			case ROLES: this.parseRoles();break;
			case ROLE: this.parseRole();break;
			case CONTAINER: this.parseContainer();break;
			case CONTAINER_ROLE: this.parseContainerRole();break;
			case PARTS: this.parseParts();break;
			case PART_ROLES: this.parsePart();break;
			case SUBCLASSES: this.parseSubclasses();break;
			default: throw new IllegalArgumentException();
			}

		}
	}
	
	private void parseSubclasses() {
		
		/* Chercher la derniere generalization qui a ete ajoutee */
		ArrayList<UML_Generalization> generalizations = this.model.getGeneralizations();
		UML_Generalization generalization = generalizations.get(generalizations.size() - 1);
		
		this.extractor = new ExtractorBuilder(this.line).build(SUBCLASSES);
		this.extractor.extract(CLASSES);
		
		int nbOfClasses = ((ExtractSubclass)extractor).getNbOfClasses();
		
		/* Extraire l'identifiant de chaque classes, puis les ajouter au tableau generalizations */
		
		for(int i=0;i<nbOfClasses;i++) {
			
			String identifier = this.extractor.extract(IDENTIFIER);
			generalization.addSubClass(identifier);
					
		}
		
		this.nextElement = END;
	}

	private void parsePart() {
		
		/* Chercher la derniere aggregation qui a ete ajoutee */
		ArrayList<UML_Aggregation> aggregations = this.model.getAggregations();
		UML_Aggregation aggregation = aggregations.get(aggregations.size() - 1 );
		
		this.extractor = new ExtractorBuilder(this.line).build(ROLES);
		this.extractor.extract(ROLES);
		
		int nbOfRoles = ((ExtractMultipleRoles)extractor).getNbOfRoles();
		
		/* Extraire l'identifiant et la multiplicite de chaque role,
		 *  creer un objet UML_Role avec ces parametres puis l'ajouter a l'objet aggregation */
		for(int i=0;i<nbOfRoles;i++) {

			String identifier = this.extractor.extract(IDENTIFIER);
			String multiplicity = this.extractor.extract(MULTIPLICITY);
			UML_Role role = new UML_Role(identifier, multiplicity);
			
			aggregation.addPart(role);	
		}
		
		this.nextElement = END;

	}

	private void parseParts() {
		
		/* Verifier si le mot cle PARTS existe puis aller chercher chaque part individuelle */
		this.extractor = new ExtractorBuilder(this.line).build(AGGREGATION);
		this.extractor.extract(PARTS);
		this.nextElement = PART_ROLES;
		
	}

	private void parseContainerRole() {
		
		/* Chercher la derniere aggregation qui a ete ajoutee */
		ArrayList<UML_Aggregation> aggregations = this.model.getAggregations();
		UML_Aggregation aggregation = aggregations.get(aggregations.size() - 1 );
		
		/* Extraire l'identifiant et la multiplicite du role */
		this.extractor = new ExtractorBuilder(this.line).build(ROLE);
		String identifier = extractor.extract(IDENTIFIER);
		String multiplicity = extractor.extract(MULTIPLICITY);
		
		/* L'ajouter a l'association */
		UML_Role role = new UML_Role(identifier,multiplicity);
		aggregation.setContainer(role);
		
		this.nextElement = PARTS;
		
	}

	private void parseContainer() {
		
		/* Verifier si le mot cle CONTAINER existe puis aller cherche le role */
		this.extractor = new ExtractorBuilder(this.line).build(AGGREGATION);
		this.extractor.extract(CONTAINER);
		this.nextElement = CONTAINER_ROLE;
		
	}

	private void parseRole() {
		
		/* Chercher la derniere association qui a ete ajoutee */
		ArrayList<UML_Association> associations = this.model.getAssociations();
		UML_Association association = associations.get(associations.size() - 1 );
		
		/* Extraire l'identifiant et la multiplicite du role */
		this.extractor = new ExtractorBuilder(this.line).build(ROLE);
		String identifier = extractor.extract(IDENTIFIER);
		String multiplicity = extractor.extract(MULTIPLICITY);
		
		/* L'ajouter a l'association */
		UML_Role role = new UML_Role(identifier,multiplicity);
		association.addRole(role);
		
		if( ((ExtractRole)this.extractor).isLast() )
			
			this.nextElement = END;
	
	}

	private void parseRoles() {
		
		/* Verifier si le mot cle ROLES existe puis aller cherche le ou les roles */
		this.extractor = new ExtractorBuilder(this.line).build(ASSOCIATION);
		this.extractor.extract(ROLES);
		this.nextElement = ROLE;
		
	}

	private void parseEnd() {

		/* Si un ";" est rencontre, alors on a fini de parser un element de LIST_DEC, aller chercher le prochain */
		if (this.line.trim().equals(";"))
			
			this.nextElement = LIST_DEC;
		
		else
			
			throw new UML_MissingSyntaxException(";");
		
	}

	private void parseArguments(UML_Operation operation) {
				
		/* Extraire les arugments */
		this.extractor = new ExtractorBuilder(this.line).build(ARGUMENTS);
		this.extractor.extract(ARGUMENTS);
		
		int nbOfArgs = ((ExtractArgument)this.extractor).getNbOfArguments();
		
		/* Extraire l'identifiant et le type de chaque argument,
		 *  creer un objet UML_Data avec ces parametres puis l'ajouter a l'objet operation */
		for(int i=0;i<nbOfArgs;i++) {
			
			String identifier = this.extractor.extract(IDENTIFIER);
			String type = this.extractor.extract(TYPE);
			UML_DataItem argument = new UML_DataItem(identifier, type);
			operation.addArgument(argument);
			
		}
	
		this.nextElement = OPERATION;
	}

	private void parseOperation() {
		
		/* Chercher la derniere classe qui a ete ajoutee */
		ArrayList<UML_Class> umlClasses = this.model.getClasses();
		UML_Class umlClass = umlClasses.get(umlClasses.size() - 1 );
		
		/* Extraire l'identifiant et le type de l'operation */
		this.extractor = new ExtractorBuilder(this.line).build(OPERATION);
		String identifier = this.extractor.extract(IDENTIFIER);
		String type = this.extractor.extract(TYPE);

		/* L'ajouter a la classe */
		UML_Operation operation = new UML_Operation(identifier,type);
		umlClass.addOperation(operation);
		
		/* garder un pointeur vers l'objet extracteur pour verifier la condition d'arret une fois tous les arguments extrait */
		ExtractOperation operationExtractor = (ExtractOperation)this.extractor;
		
		/* aller directement chercher ces arguments */
		this.parseArguments(operation);
		
		if( operationExtractor.isLast() )
			
			this.nextElement = END;
	}

	private void parseOperations() {
		
		/* Verifier si le mot cle OPERATION existe puis aller cherche le ou les operations */
		this.extractor = new ExtractorBuilder(this.line).build(CLASS);
		this.extractor.extract(OPERATIONS);
		this.nextElement = OPERATION;
		
	}

	private void parseAttribute() {
		
		/* Chercher la derniere classe qui a ete ajoute */
		ArrayList<UML_Class> umlClasses = this.model.getClasses();
		UML_Class umlClass = umlClasses.get(umlClasses.size() - 1 );
		
		/* Extraire l'identifiant et le type de l'attribut */
		this.extractor = new ExtractorBuilder(this.line).build(ATTRIBUTE);
		String identifier = this.extractor.extract(IDENTIFIER);
		String type = this.extractor.extract(TYPE);
		
		/* L'ajouter a la classe */
		UML_DataItem attribute = new UML_DataItem(identifier, type);
		umlClass.addAttribute(attribute);
		
		if( ((ExtractAttribute)this.extractor).isLast() )
			
			this.nextElement = OPERATIONS;
	}

	private void parseAttributes() {
		/* Verifier si le mot cle ATTRIBUTES existe puis aller chercher le ou les attributs */
		this.extractor = new ExtractorBuilder(this.line).build(CLASS);
		this.extractor.extract(ATTRIBUTES);
		this.nextElement = ATTRIBUTE;
		
	}

	private void parseListDec() {
		
		/* aller chercher le prochain element de ListDec puis executer directement sa fonction de parse */
		this.extractor = new ExtractorBuilder(this.line).build(MODEL);
		String ListDecType = this.extractor.extract(LIST_DEC);
		
		switch(ListDecType) {
		
		case "CLASS": this.parseClass();break;
		case "RELATION": this.parseAssociation();break;
		case "AGGREGATION": this.parseAggregation();break;
		case "GENERALIZATION": this.parseGeneralization();break;
		}
	}

	private void parseGeneralization() {
		
		this.extractor = new ExtractorBuilder(this.line).build(GENERALIZATION);
		String identifier = this.extractor.extract(IDENTIFIER);
		UML_Generalization generalization = new UML_Generalization(identifier);
		this.model.addGeneralization(generalization);
		this.nextElement = SUBCLASSES;
	}

	private void parseAggregation() {
		
		this.extractor = new ExtractorBuilder(this.line).build(AGGREGATION);
		this.extractor.extract(IDENTIFIER);
		UML_Aggregation aggregation = new UML_Aggregation();
		this.model.addAggregations(aggregation);
		this.nextElement = CONTAINER;
		
	}

	private void parseAssociation() {
		
		this.extractor = new ExtractorBuilder(this.line).build(ASSOCIATION);
		String identifier = this.extractor.extract(IDENTIFIER);
		UML_Association association = new UML_Association(identifier);
		this.model.addAssociation(association);
		this.nextElement = ROLES;
		
	}

	private void parseClass() {
		
		this.extractor = new ExtractorBuilder(this.line).build(CLASS);
		String identifier = this.extractor.extract(IDENTIFIER);
		UML_Class umlClass = new UML_Class(identifier);
		this.model.addClass(umlClass);
		this.nextElement = ATTRIBUTES;
		
	}
	
	private void parseModel() {
		
		this.extractor = new ExtractorBuilder(this.line).build(MODEL);
		String identifier = this.extractor.extract(IDENTIFIER);
		this.model = new UML_Model(identifier);
		this.nextElement = LIST_DEC;
		
	}
}
