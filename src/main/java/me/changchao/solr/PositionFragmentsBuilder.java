package me.changchao.solr;

import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.document.Field;
import org.apache.lucene.search.highlight.Encoder;
import org.apache.lucene.search.vectorhighlight.FieldFragList.WeightedFragInfo;
import org.apache.lucene.search.vectorhighlight.SimpleFragmentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PositionFragmentsBuilder extends SimpleFragmentsBuilder {

	@Override
	protected String makeFragment(StringBuilder buffer, int[] index, Field[] values, WeightedFragInfo fragInfo,
			String[] preTags, String[] postTags, Encoder encoder) {
		Map<String, Object> map = new HashMap<>();
		map.put("values", values);
		map.put("fragInfo", fragInfo);

		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(map);
			return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}

	}

}
