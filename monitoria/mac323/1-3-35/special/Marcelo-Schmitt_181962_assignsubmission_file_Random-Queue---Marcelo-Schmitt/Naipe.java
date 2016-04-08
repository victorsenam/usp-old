import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
/**
 * 
 * @author Marcelo Schmitt
 * Número USP: 9297641
 *
 */
public enum Naipe {
	PAUS,
	COPAS,
	ESPADAS,
	OUROS;
	
	public String toString() {
		switch (this) {
		case PAUS:
			return "C";
		case COPAS:
			return "H";
		case ESPADAS:
			return "S";
		case OUROS:
			return "D";
		}
		return null;
	};
}
