package uml.extraction;

import uml.exception.UML_ArgumentNotExtractedException;
import uml.exception.UML_MissingSyntaxException;
import uml.exception.UML_SyntaxLengthException;

public final class ExtractArgument extends Extractor {

	/*
	 * vu que les arguments sont des attributs, alors on utilise un
	 * ExtractAttribute pour extraire les arguments individuellement
	 */
	private String[][] arguments; /* le tableau des arguments */
	private int indexOfLastArgument; /* index du dernier argument extrait */

	ExtractArgument() {

		super(3);
	}

	ExtractArgument(String line) {

		super(line, 3);

	}

	@Override
	public String extract(UML_Element element) {

		String line = this.getLine();

		/* s'il n'y a aucun argument */
		if (line.contains("()"))

			return null;

		switch (element) {

		case ARGUMENTS:
			return this.extractArguments();
		case IDENTIFIER:
			return this.extracIdentifier();
		case TYPE:
			return this.extractType();
		default:
			throw new IllegalArgumentException();
		}

	}

	public int getNbOfArguments() {

		if (this.getLine().contains("()"))

			return 0;

		if (this.arguments == null)

			throw new UML_ArgumentNotExtractedException(this.getLine());

		return this.arguments.length;
	}

	@Override
	public Extractor newLine(String line) {

		this.arguments = null;
		return super.newLine(line);
	}

	/* extrait l'identifiant */
	private String extracIdentifier() {

		return this.extractSpecificElement(0);

	}

	/*
	 * extrait le type de l'argument courant, incremente automatiquement le
	 * compteur du prochain argument
	 */
	private String extractType() {

		String type = this.extractSpecificElement(2);
		this.indexOfLastArgument++;
		return type;
	}

	/*
	 * sert a extraire soit l'identifiant, soit le type. Index 0 =>
	 * l'identifiant, Index 2 => le type
	 */
	private String extractSpecificElement(int index) {

		if (this.arguments == null)

			throw new UML_ArgumentNotExtractedException(this.getLine());

		return this.arguments[indexOfLastArgument][index];
	}

	/*
	 * fonction servant a sortir les arguments puis les stocker dans un tableau
	 */
	/**
	 * 
	 * @return null si l'extraction des arguments a ete reussie, sinon une
	 *         exception est lancee
	 */
	private String extractArguments() {

		String line = this.getLine();

		/* s'il manque des parentheses */
		if (!line.contains("(") || !line.contains(")"))

			throw new UML_MissingSyntaxException("(", ")");

		/* chercher le text qui se trouve entre les parentheses */

		int firstParenthese = line.indexOf('(');
		int secondParenthese = line.indexOf(')');

		line = line.substring(firstParenthese + 1, secondParenthese);

		/*
		 * s'il n'y a pas de virgule, c'est-a-dire qu'il y a qu'un seul argument
		 */
		if (!line.contains(","))

			return this.extractUniqueArgument(line);

		return this.extractMultipleArguments(line);

	}

	private String extractUniqueArgument(String line) {

		this.newLine(line);
		String[] elements = this.removeWhitespace();

		if (elements.length != this.getNumberOfElements())

			throw new UML_SyntaxLengthException(this.getNumberOfElements(), elements.length,this.getLine());

		this.arguments = new String[1][3];
		this.arguments[0] = this.removeWhitespace();

		if (!this.arguments[0][1].equals(":"))

			throw new UML_MissingSyntaxException(":");

		return null;

	}

	private String extractMultipleArguments(String line) {

		int numberOfArguments = countCharacters(line, ',') + 1;
		this.arguments = new String[numberOfArguments][this.getNumberOfElements()];
		int virguleIndex = 0;
		int i = 0;

		do {

			/*
			 * chercher la position de la virgule. S'il n'y a plus de virgule,
			 * alors la position est la fin de la String. Dans ce cas la, sortir
			 * de la boucle : On a extrait tous les arguments.
			 */
			virguleIndex = getVirguleIndex(line);
			String extractedArgument = line.substring(0,
					virguleIndex); /*
									 * Le premier argument est la ligne jusqu'a
									 * la virgule (ou la fin)
									 */
			String[] argumentElements = removeWhitespace(
					extractedArgument); /*
										 * extrait mot par mot cette ligne et la
										 * mettre dans un tableau
										 */

			if (argumentElements.length != this.getNumberOfElements())

				throw new UML_SyntaxLengthException(this.getNumberOfElements(), argumentElements.length,this.getLine());

			if (!argumentElements[1].equals(":"))

				throw new UML_MissingSyntaxException(":");

			this.arguments[i] = argumentElements; /*
													 * mettre l'argument i dans la
													 * ieme case du tableau
													 * this.arguments
													 */
			line = line.substring(line.indexOf(',') + 1,
					line.length()); /*
									 * enlever la partie de la ligne qui vient
									 * d'etre traitee
									 */
			i++;

		} while (virguleIndex != line.length());

		return null;
	}

	private static int getVirguleIndex(String line) {

		int virguleIndex = line.indexOf(',');

		if (virguleIndex == -1)

			return line.length();

		return virguleIndex;
	}

}
