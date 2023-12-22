package org.example;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        String p = new Parent().sayHello();
        System.out.println(p);
    }
}
