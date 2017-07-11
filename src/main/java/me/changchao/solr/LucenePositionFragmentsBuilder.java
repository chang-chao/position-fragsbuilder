package me.changchao.solr;

import org.apache.lucene.document.Field;
import org.apache.lucene.search.highlight.Encoder;
import org.apache.lucene.search.vectorhighlight.FieldFragList.WeightedFragInfo;
import org.apache.lucene.search.vectorhighlight.SimpleFragmentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class LucenePositionFragmentsBuilder extends SimpleFragmentsBuilder {
	private static final Logger log = LoggerFactory.getLogger(LucenePositionFragmentsBuilder.class);
	/**
	 * @see:https://stackoverflow.com/questions/3907929/should-i-declare-jacksons-objectmapper-as-a-static-field
	 */
	private static ObjectWriter mapper = new ObjectMapper().writer();

	@Override
	protected String makeFragment(StringBuilder buffer, int[] index, Field[] values, WeightedFragInfo fragInfo,
			String[] preTags, String[] postTags, Encoder encoder) {

		int[][] offsets = fragInfo.getSubInfos().stream().flatMap(subInfo -> subInfo.getTermsOffsets().stream())
				.map(toffs -> new int[] { toffs.getStartOffset(), toffs.getEndOffset() }).toArray(int[][]::new);
		try {
			return mapper.writeValueAsString(offsets);
		} catch (JsonProcessingException e) {
			log.error("error in writeValueAsString", e);
			return "[[]]";
		}

	}
}
