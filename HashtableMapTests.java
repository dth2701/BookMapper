// --== CS400 Project One File Header ==--
// Name: Thien Huong Do
// CSL Username: tdo
// Email: tdo@wisc.edu
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This class contains 5 test methods to test 5 methods in the class HashtableMap.
 *
 * @author Gary Dahl
 * @author Thien Huong Do
 */
public class HashtableMapTests {

    private class OrderedPair<KeyType, ValueType> {
        private KeyType key; //the key of the (key, value) pair.
        private ValueType value; //the value that the key will map to.

        OrderedPair(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
        }
        public KeyType getKey() {return key;}
        public void setKey(KeyType key) {this.key = key;}

        public KeyType getValue() {return key;}
        public void setValue(ValueType value) {this.value = value;}
    }

    /**
     * The main method only runs 5 tests.
     * @param args unused
     */
    public static void main(String args[]) {
        System.out.println(test1() && test2() && test3() && test4() && test5());
    }

    /**
     * Checks whether HashtableMap’s put() and get() work as expected.
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test1() {
        HashtableMap<Integer, String> hashtable = new HashtableMap<>(7);
        //Test 1: Adding a unique key into the map
        if (hashtable.put(123, "blue") == false) return false;

        //Test 2: Adding duplicate keys with different value into the map.
        if (hashtable.put(202, "first pink") == true) {
            if (hashtable.put(202, "second pink") == false) {
                if (!hashtable.get(202).equals("first pink")) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Checks whether HashtableMap’s resize() works as expected.
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test2() {
        HashtableMap<Integer, String> hashtable = new HashtableMap<>();

        //Check the specific load factor.
        hashtable.put(11,"blue");
        hashtable.put(271,"pink");
        hashtable.put(41,"yellow");
        hashtable.put(21,"black");
        hashtable.put(31,"purple");
        hashtable.put(231,"red");
        hashtable.put(61,"green");
        hashtable.put(71,"white");
        hashtable.put(81,"brown");
        hashtable.put(91,"orange");
        hashtable.put(101,"violet");
        hashtable.put(121,"coral");

        if ((float)hashtable.size() / hashtable.capacity >= 0.7) {
            return false;
        }
        return true;
    }
    /**
     * Checks whether HashtableMap’s remove() and return its current size works as expected.
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test3() {
        HashtableMap<String, Integer> hashtable = new HashtableMap<>();
        if (hashtable.put("blue", 123) == true
                && hashtable.put("pink", 271) == true
                && hashtable.put("", -5) == true
                && hashtable.put("black", 333) == true){

            //Case 1: remove 1 pair in the hashtable that one pair is stored.
            if (!hashtable.remove("pink").toString().equals("271")
                    || hashtable.size() != 3) {
                return false;
            }
            //Case 2: check to get a key from a hash after it has been removed.
            try {
                hashtable.get("pink");
            } catch (Exception NoSuchElementException){
                return true;
            }
        }
        return false;
    }
    /**
     * Checks whether HashtableMap’s containsKey() works as expected.
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test4() {
        HashtableMap<String, Integer> hashtable = new HashtableMap<>();

        if (hashtable.put("blue", 123) == true
                && hashtable.put("pink", 271) == true
                && hashtable.put("", -5) == true
                && hashtable.put("black", 333) == true){
            //Case 1: Check the valid key.
                if(!hashtable.containsKey("pink")) return false;
            //Case 2: Check the invalid key.
                if( hashtable.containsKey("no color")) return false;
            }
        return true;

    }
    /**
     * Checks whether HashtableMap’s clear() works as expected.
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test5() {
        HashtableMap<String, Integer> hashtable = new HashtableMap<>();
        if (hashtable.put("blue", 123) == true
                && hashtable.put("pink", 271) == true
                && hashtable.put("", -5) == true
                && hashtable.put("black", 333) == true) {
            hashtable.clear();
            //Check the current number of pairs.
            if (hashtable.size() != 0) return false;

            //Check if the map contains the value.
            if (hashtable.containsKey("")) return false;
        }

        return true;
    }
}
