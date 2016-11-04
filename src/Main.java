/**
 * Created by zac on 11/4/16.
 */

import java.util.*;

public class Main {

    private static class Person implements Comparable<Person>{
        public String name;
        public int ID;

        public Person(String nm){
            name = nm;
        }

        @Override
        public String toString(){
            return name;
        }

        @Override
        public boolean equals(Object other){
            return (other instanceof Person) && name == ((Person)other).name;
        }

        public int compareTo(Person other){
            return name.compareTo(other.name);
        }

    }
    public static void main(String[] args) {
        // Binary search tree controller
        List<Person> lst = new ArrayList<Person>();
        lst.add(new Person("Alice"));
        lst.add(new Person("Bob"));
        lst.add(new Person("Charlene"));
        lst.add(new Person("David"));
        lst.add(new Person("Erin"));
        lst.add(new Person("Frans"));
        lst.add(new Person("Gertrud"));
        lst.add(new Person("Hank"));
        lst.add(new Person("Ingrid"));
        lst.add(new Person("Jason"));
        lst.add(new Person("Katherine"));
        lst.add(new Person("Lyonel"));
        lst.add(new Person("Martha"));
        lst.add(new Person("Nolan"));
        lst.add(new Person("Olivia"));
        lst.add(new Person("Peter"));
        lst.add(new Person("Qadira"));
        lst.add(new Person("Ross"));
        lst.add(new Person("Susan"));
        lst.add(new Person("Timothy"));
        lst.add(new Person("Uma"));
        lst.add(new Person("Victor"));
        lst.add(new Person("Wilma"));
        lst.add(new Person("Xavier"));
        lst.add(new Person("Yvonne"));
        lst.add(new Person("Zachary"));

        BST<Person> bst = new BST<Person>(true);
//		bst.add(lst);
        bst.randomAdd(lst);
        System.out.println(bst);
        System.out.println(bst.preorder());
//		bst.remove(new Person("Ross"));
//		bst.remove(new Person("Charlene"));
//		bst.remove(new Person("Wilma"));
//		bst.remove(new Person("David"));
//		bst.remove(new Person("Susan"));
//		System.out.println(bst);
//		System.out.println(bst.preorder());
    }

}
