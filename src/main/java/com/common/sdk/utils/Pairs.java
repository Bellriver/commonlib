/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.utils;

public class Pairs<F, S,T> {
	    public final F first;
	    public final S second;
	    public final T three;

	    /**
	     * Constructor for a Pairs.
	     *
	     * @param first the first object in the Pairs
	     * @param second the second object in the Pairs
	     */
	    public Pairs(F first, S second,T three) {
	        this.first = first;
	        this.second = second;
	        this.three = three;
	    }
	    
	    public static boolean equal(Object a, Object b) {
	        return a == b || (a != null && a.equals(b));
	    }


	    /**
	     * Checks the two objects for equality by delegating to their respective
	     * {@link Object#equals(Object)} methods.
	     *
	     * @param o the {@link Pairs} to which this one is to be checked for equality
	     * @return true if the underlying objects of the Pairs are both considered
	     *         equal
	     */
	    @Override
	    public boolean equals(Object o) {
	        if (!(o instanceof Pairs)) {
	            return false;
	        }
	        Pairs<?, ?,?> p = (Pairs<?,?,?>) o;
	        return equal(p.first, first) && equal(p.second, second)&&equal(p.three, three);
	    }

	    /**
	     * Compute a hash code using the hash codes of the underlying objects
	     *
	     * @return a hashcode of the Pairs
	     */
	    @Override
	    public int hashCode() {
	        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode()^ (three == null ? 0 : three.hashCode()));
	    }

	    /**
	     * Convenience method for creating an appropriately typed Pairs.
	     * @param a the first object in the Pairs
	     * @param b the second object in the Pairs
	     * @return a Pairs that is templatized with the types of a and b
	     */
	    public static <A, B,C> Pairs <A, B,C> create(A a, B b,C c) {
	        return new Pairs<A, B,C>(a, b,c);
	    }
	}

