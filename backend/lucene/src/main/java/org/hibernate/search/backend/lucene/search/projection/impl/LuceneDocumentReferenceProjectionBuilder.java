/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.backend.lucene.search.projection.impl;

import org.hibernate.search.engine.search.DocumentReference;
import org.hibernate.search.engine.search.SearchProjection;
import org.hibernate.search.engine.search.projection.spi.DocumentReferenceProjectionBuilder;


public class LuceneDocumentReferenceProjectionBuilder implements DocumentReferenceProjectionBuilder {

	private static final LuceneDocumentReferenceProjectionBuilder INSTANCE = new LuceneDocumentReferenceProjectionBuilder();

	public static LuceneDocumentReferenceProjectionBuilder get() {
		return INSTANCE;
	}

	private LuceneDocumentReferenceProjectionBuilder() {
	}

	@Override
	public SearchProjection<DocumentReference> build() {
		return LuceneDocumentReferenceProjection.get();
	}
}
