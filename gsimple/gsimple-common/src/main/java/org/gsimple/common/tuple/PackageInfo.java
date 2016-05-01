/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */

/*
 * This package is on tuple implement.
 * 
 * So far, 5 tuple from 1 to 5 have been done.
 * 
 * If there is more requirement on the tuple which is more 5 members, 
 * it will be done customized easily as following tips.
 * 
 * 1. An extra tuple should extend the tuple whose member number is one more than the new one.
 * Like Pair extends Unit, and Triplet extends Pair.
 * 
 * 2. Only one member will be added in the new tuple, the others was provided by the parent tuple.
 * 
 * 3. Function equals and function hashcode are required.
 */

/*
 * Below are the name of tuple, and which have been done.
 * 
 * Unit<A> (1 element) -Done
 * 
 * Pair<A,B> (2 elements) -Done
 * 
 * Triplet<A,B,C> (3 elements) -Done
 * 
 * Quartet<A,B,C,D> (4 elements) -Done
 * 
 * Quintet<A,B,C,D,E> (5 elements) -Done
 * 
 * Sextet<A,B,C,D,E,F> (6 elements)
 * 
 * Septet<A,B,C,D,E,F,G> (7 elements)
 * 
 * Octet<A,B,C,D,E,F,G,H> (8 elements)
 * 
 * Ennead<A,B,C,D,E,F,G,H,I> (9 elements)
 * 
 * Decade<A,B,C,D,E,F,G,H,I,J> (10 elements)
 */
