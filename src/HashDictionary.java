import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {
    private int size;
    private LinkedList[] hashDictionary;
    private int numRecords;
    // private instance variables, array of linkedLists, of length size, with the number of records as well
    // Further down we return numRecords since we increment for put and decrement for remove

    public HashDictionary(int size) {
        this.size = size;
        this.hashDictionary = new LinkedList[size];
        this.numRecords = 0;
        // initialize numRecords to 0 for a new hashDictionary
        // initialize size to our private instance variable
        // initialize this.hashDictionary to the one we are creating using an array of linkedList of size 'size'.
        for (int i = 0; i < size; i++) {
            hashDictionary[i] = new LinkedList();
            // for each index of the array make a new linkedList
        }
    }


    public int hash(String key) {
        int hash = 0;
        int primeNumber = 17;

        for (int i = 0; i < key.length(); i++) {
            hash = (primeNumber * hash + key.charAt(i)) %size;
        }
        //here is our polynomial hash function. Chose prime number of 17 due to its good dispersion and lack of collisions
        // Since our hashIndex is very large for various strings and may be too big we must use the '%' operator to then
        // give it an index which fits well in the hashDictionary
        return hash;
        // return the hashed string through an index value which will be its location in the hashDictionary array
    }

    public int put(Data record) throws DictionaryException {
        int hashIndex = (hash(record.getConfiguration()));
        if (hashDictionary[hashIndex] != null) {
            // check if that index in the hashDictionary is null
            for (int i = 0; i < hashDictionary[hashIndex].size(); i++) {
                Data dataComparison = (Data) hashDictionary[hashIndex].get(i);
                // for the size of that linkedList in that hashIndex we will go through every data record in the list by
                // first converting it to a Data record by calling the item at the index and making it a type
                // 'Data' and calling it dataComparison since we will compare it further down to string config
                if (dataComparison.getConfiguration().equals(record.getConfiguration())) {
                    throw new DictionaryException();
                    //if this data record already exists we will return a dictionary exception
                }
            }
            // we check the whole list to see if its in there, if not then we add to an empty or non-empty list
            if (!hashDictionary[hashIndex].isEmpty()) {
                hashDictionary[hashIndex].add(record);
                numRecords++;
                return 1;

                //if that list does not contain the item already, we will simply just add the record, add +1 to
                //numRecords once we add it to the empty list
            }
        }
        hashDictionary[hashIndex] = new LinkedList();
        hashDictionary[hashIndex].add(record);
        numRecords++;
        // if this list is null, we will make a new list in that position and then fill in this new empty list with our
        // record, and again we increment numRecords by 1


        return 0;
        //return 0 if none of this works but each case has been accounted for
    }


    public void remove(String config) throws DictionaryException {
        int getValue = get(config);
        int hashIndex = (hash(config));
        // use get to see if the item is already in there.
        // use our hashIndex to hash the config again
        if (getValue != -1) {
            for (int i = 0; i < hashDictionary[hashIndex].size(); i++) {
                Data dataComparison = (Data) hashDictionary[hashIndex].get(i);
                if (dataComparison.getConfiguration().equals(config)) {
                    hashDictionary[hashIndex].remove();
                    numRecords--;
                    // if the getValue is not -1 that means it's in the dictionary, and we can remove it by comparing
                    // it to all the values in the list and find it to remove it once the configuration we are looking
                    // for matches
                }
            }
        } else {
            throw new DictionaryException();
        }
        //else that means get value is not -1 and therefore we cannot remove it
    }

    public int get(String config) {
        int hashIndex = (hash(config)) ;
        //hashIndex again
        if (hashDictionary[hashIndex] != null) {
            for (int i = 0; i < hashDictionary[hashIndex].size(); i++) {
                Data dataComparison = (Data) hashDictionary[hashIndex].get(i);
                //converting the index in our linkedList to a Data type and comparing it
                if ((dataComparison.getConfiguration().equals(config))) {
                    return dataComparison.getScore();
                    // if a successful comparison is made, and we find the correct string comparison, return score
                }
            }
        }
        return -1;
        //if we do not find the right string config this means it is not in the linkedList therefore we return -1
    }


    public int numRecords() {
        return numRecords;
        // we decrement and increment numRecords to keep track of it and here we return it at the end.
    }



}
