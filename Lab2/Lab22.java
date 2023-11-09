
class Lab22 {
    public static void main(String[] args) {
        StringBuffer theString = new StringBuffer("We realizes that while our workers were thriving, the surrounding villages were still suffering. It became our goal to uplift their quality of life as well. I remember seeing a family of 4 on a motorbike in the heavy Bombay rain — I knew I wanted to do more for these families who were risking their lives for lack of an alternative. The alternative mentioned here is the Tata Nano, which soon after came as the world's cheapest car on retail at a starting price of only Rs 1 lakh. These were the words of Ratan Tata in a recent post by Humans of Bombay which formed the basis of his decision to come up with a car like Tata Nano.");

        System.out.println("The string is: \n" + theString);

        System.out.println("\nUsing append() method on the string:\n" + theString.append("This is the units sold of the Tata Nano: 30000"));
        System.out.println("\nUsing insert() method on the string:\n" + theString.insert(5, ", "));
        System.out.println("\nUsing replace() method on the string:\n" + theString.replace(2, 7, "Java"));
        System.out.println("\nUsing delete() method on the string:\n" + theString.delete(7, 11));
        System.out.println("\nUsing charAt() method on the string:\n" + theString.charAt(3));
        theString.setCharAt(3, 'A');
        System.out.println("\nUsing setCharAt() method on the string:\n" + theString);
        System.out.println("\nUsing length() method on the string:\n:" + theString.length());
        System.out.println("\nUsing capacity() method on the string:\n:" + theString.capacity());
        theString.ensureCapacity(100);
        System.out.println("\nUsing ensureCapacity() method on the string:\n" + theString);
        System.out.println("\nUsing toString() method on the string:\n" + theString.toString());
        System.out.println("\nUsing substring() method on the string:\n" + theString.substring(400));
        System.out.println("\nUsing substring() method on the string:\n" + theString.substring(2, 7));
    }
}
