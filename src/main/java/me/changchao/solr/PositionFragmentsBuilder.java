package me.changchao.solr;

import org.apache.lucene.search.vectorhighlight.BoundaryScanner;
import org.apache.lucene.search.vectorhighlight.FragmentsBuilder;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.highlight.SimpleFragmentsBuilder;

public class PositionFragmentsBuilder extends SimpleFragmentsBuilder {
	@Override
	protected FragmentsBuilder getFragmentsBuilder(SolrParams params, String[] preTags, String[] postTags,
			BoundaryScanner bs) {
		LucenePositionFragmentsBuilder sfb = new LucenePositionFragmentsBuilder();
		sfb.setMultiValuedSeparator(getMultiValuedSeparatorChar(params));
		return sfb;
	}
}
