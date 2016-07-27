/******************************************************************************
 *  Compilation:  javac LinkFinderNEW.java In.java
 *  Execution:    java LinkFinderNEW url
 *
 *  Downloads the web page and prints out all urls on the web page.
 *  Gives an idea of how Google's spider crawls the web. Instead of
 *  looking for hyperlinks, we just look for patterns of the form:
 *  http:// followed by any non-whitespace characters except ".
 *
 *  % java LinkFinder http://www.cs.princeton.edu
 *  http://www.w3.org/TR/REC-html40/loose.dtd
 *  http://www.cs.princeton.edu/
 *  http://maps.yahoo.com/py/maps.py?addr=35+Olden+St&csz=Princeton,+NJ,+08544-2087
 *  http://www.princeton.edu/
 *  http://www.Princeton.EDU/Siteware/WeatherTravel.shtml
 *  http://ncstrl.cs.Princeton.EDU/
 *  http://www.genomics.princeton.edu/
 *  http://www.math.princeton.edu/PACM/
 *  http://libweb.Princeton.EDU/
 *  http://libweb2.princeton.edu/englib/
 *  http://search.cs.princeton.edu/query.html
 *
 ******************************************************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

//import edu.princeton.cs.algs4.*;

public class LinkFinderNEW {

   /*************************************************************
      *  \\S for not whitespace characters
      *  ^\" for all characters except "     (add ? to list...)
      *  && for intersection of two character classes
      *  + for one or more occurrences
   *************************************************************/

   public static void main(String[] args) {

      if (args.length == 1) {
         In in = new In(args[0]);
         String source = args[0];
         String input = in.readAll();

         String regexp = "http[s]?://[[\\S]&&[^\"]]+";
         Pattern patternA = Pattern.compile(regexp);
         Matcher matcherA = patternA.matcher(input);

         while (matcherA.find())
            System.out.println(matcherA.group());

         /* link sem o http(s) */
         String regexpp = "\"/[[/\\S]&&[^\"]]+";
         Pattern patternB = Pattern.compile(regexpp);
         Matcher matcherB = patternB.matcher(input);

         while (matcherB.find())
            System.out.println(source + matcherB.group().substring(1));
      }

      else if (args.length == 2) {
         In in = new In(args[1]);
         String source = args[1];
         String input = in.readAll();
         RedBlackBST<String, Integer> rbst = new RedBlackBST<String, Integer>();

         String regexp = "http[s]?://[[\\S]&&[^\"]]+";
         Pattern patternA = Pattern.compile(regexp);
         Matcher matcherA = patternA.matcher(input);

         while (matcherA.find())
            rbst.put(matcherA.group(), 0);

         /* link sem o http(s) */
         String regexpp = "\"/[[/\\S]&&[^\"]]+";
         Pattern patternB = Pattern.compile(regexpp);
         Matcher matcherB = patternB.matcher(input);

         while (matcherB.find())
            rbst.put(source + matcherB.group().substring(1), 0);

         for (String s : rbst.keys()) {
            System.out.println(s);
         }
      }
   }

}
