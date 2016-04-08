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
public enum Nome {
	ÁS,
	DOIS,
	TRÊS,
	QUATRO,
	CINCO,
	SEIS,
	SETE,
	OITO,
	NOVE,
	DEZ,
	VALETE,
	DAMA,
	REI;
	
	public String toString() {
		switch (this) {
		case ÁS:
			return "A";
		case DOIS:
			return "2";
		case TRÊS:
			return "3";
		case QUATRO:
			return "4";
		case CINCO:
			return "5";
		case SEIS:
			return "6";
		case SETE:
			return "7";
		case OITO:
			return "8";
		case NOVE:
			return "9";
		case DEZ:
			return "T";
		case VALETE:
			return "J";
		case DAMA:
			return "Q";
		case REI:
			return "K";
		}
		return null;
	};
}
