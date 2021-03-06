/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.engine.search.dsl.predicate;

import java.util.function.Function;

import org.hibernate.search.engine.search.SearchPredicate;

/**
 * The context used when defining a "nested" predicate, after the object field was mentioned.
 */
public interface NestedPredicateFieldContext {

	// TODO add tuning methods, like the "score_mode" in Elasticsearch (avg, min, ...)

	/**
	 * Set the inner predicate to a previously-built {@link SearchPredicate}.
	 * <p>
	 * Matching documents are those for which at least one element of the nested object field
	 * matches the inner predicate.
	 *
	 * @param searchPredicate The predicate that must be matched by at least one element of the nested object field.
	 * @return A context allowing to get the resulting predicate.
	 */
	SearchPredicateTerminalContext nest(SearchPredicate searchPredicate);

	/*
	 * Syntactic sugar allowing to skip the toPredicate() call by passing a SearchPredicateTerminalContext
	 * directly.
	 */

	/**
	 * Set the inner predicate to an almost-built {@link SearchPredicate}.
	 * <p>
	 * Matching documents are those for which at least one element of the nested object field
	 * matches the inner predicate.
	 *
	 * @param terminalContext The terminal context allowing to retrieve a {@link SearchPredicate}.
	 * @return A context allowing to get the resulting predicate.
	 */
	default SearchPredicateTerminalContext nest(SearchPredicateTerminalContext terminalContext) {
		return nest( terminalContext.toPredicate() );
	}

	/*
	 * Alternative syntax taking advantage of lambdas,
	 * allowing the structure of the predicate building code to mirror the structure of predicates,
	 * even for complex predicate building requiring for example if/else statements.
	 */

	/**
	 * Create a context allowing to define the inner predicate,
	 * and apply a consumer to it.
	 * <p>
	 * Best used with lambda expressions.
	 * <p>
	 * Matching documents are those for which at least one element of the nested object field
	 * matches the inner predicate.
	 *
	 * @param predicateContributor A function that will use the context passed in parameter to create a {@link SearchPredicate}.
	 * Should generally be a lambda expression.
	 * @return A context allowing to get the resulting predicate.
	 */
	SearchPredicateTerminalContext nest(Function<? super SearchPredicateFactoryContext, SearchPredicate> predicateContributor);

}
