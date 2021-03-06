/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.backend.lucene.types.predicate.impl;

import org.hibernate.search.backend.lucene.search.impl.LuceneSearchContext;
import org.hibernate.search.backend.lucene.search.predicate.impl.AbstractLuceneStandardMatchPredicateBuilder;
import org.hibernate.search.backend.lucene.search.predicate.impl.LuceneSearchPredicateContext;
import org.hibernate.search.backend.lucene.types.codec.impl.LuceneNumericFieldCodec;
import org.hibernate.search.engine.backend.document.converter.ToDocumentFieldValueConverter;

import org.apache.lucene.search.Query;

class LuceneNumericMatchPredicateBuilder<F, E>
		extends AbstractLuceneStandardMatchPredicateBuilder<F, E, LuceneNumericFieldCodec<F, E>> {

	LuceneNumericMatchPredicateBuilder(
			LuceneSearchContext searchContext,
			String absoluteFieldPath,
			ToDocumentFieldValueConverter<?, ? extends F> converter,
			LuceneNumericFieldCodec<F, E> codec) {
		super( searchContext, absoluteFieldPath, converter, codec );
	}

	@Override
	protected Query doBuild(LuceneSearchPredicateContext context) {
		return codec.getDomain().createExactQuery( absoluteFieldPath, value );
	}
}
