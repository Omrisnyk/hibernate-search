/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.backend.lucene.document.model.impl;

import java.util.Map;

import org.hibernate.search.backend.lucene.analysis.impl.ScopedAnalyzer;
import org.hibernate.search.engine.backend.document.converter.spi.ToDocumentIdentifierValueConverter;
import org.hibernate.search.util.EventContext;
import org.hibernate.search.engine.logging.spi.EventContexts;
import org.hibernate.search.util.impl.common.CollectionHelper;

/**
 * @author Guillaume Smet
 */
public class LuceneIndexModel implements AutoCloseable {

	private final String indexName;

	private final ToDocumentIdentifierValueConverter<?> idDslConverter;

	private final Map<String, LuceneIndexSchemaObjectNode> objectNodes;

	private final Map<String, LuceneIndexSchemaFieldNode<?>> fieldNodes;

	private final ScopedAnalyzer scopedAnalyzer;

	public LuceneIndexModel(String indexName,
			ToDocumentIdentifierValueConverter<?> idDslConverter,
			Map<String, LuceneIndexSchemaObjectNode> objectNodesBuilder,
			Map<String, LuceneIndexSchemaFieldNode<?>> fieldNodesBuilder,
			ScopedAnalyzer scopedAnalyzer) {
		this.indexName = indexName;
		this.idDslConverter = idDslConverter;
		this.fieldNodes = CollectionHelper.toImmutableMap( fieldNodesBuilder );
		this.objectNodes = CollectionHelper.toImmutableMap( objectNodesBuilder );
		this.scopedAnalyzer = scopedAnalyzer;
	}

	@Override
	public void close() {
		scopedAnalyzer.close();
	}

	public String getIndexName() {
		return indexName;
	}

	public EventContext getEventContext() {
		return EventContexts.fromIndexName( indexName );
	}

	public ToDocumentIdentifierValueConverter<?> getIdDslConverter() {
		return idDslConverter;
	}

	public LuceneIndexSchemaFieldNode<?> getFieldNode(String absoluteFieldPath) {
		return fieldNodes.get( absoluteFieldPath );
	}

	public LuceneIndexSchemaObjectNode getObjectNode(String absolutePath) {
		return objectNodes.get( absolutePath );
	}

	public ScopedAnalyzer getScopedAnalyzer() {
		return scopedAnalyzer;
	}

	@Override
	public String toString() {
		return new StringBuilder( getClass().getSimpleName() )
				.append( "[" )
				.append( "indexName=" ).append( indexName )
				.append( "]" )
				.toString();
	}
}
