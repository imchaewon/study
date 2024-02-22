//package com.example.java_.annotation.jackson.jsonTypeInfo.t1;
//
//import com.fasterxml.jackson.annotation.*;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class Run {
//
//	// defaultImpl could be Void.class to map unknown objects to null
//	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_type", visible = true, defaultImpl = Default.class)
//	@JsonSubTypes({ @JsonSubTypes.Type(value = Inner.class, name = Inner._TYPE) })
//	public static class Inner extends Base {
//		public static final String _TYPE = "inner";
//
//		public Inner() {
//			super(_TYPE);
//		}
//	}
//
//	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_type", visible = true)
//	@JsonSubTypes({ @JsonSubTypes.Type(value = Outer.class, name = Outer._TYPE) })
//	public static class Outer extends Base {
//		public static final String _TYPE = "outer";
//
//		private Inner inner;
//
//		public Outer() {
//			super(_TYPE);
//		}
//
//		@JsonGetter("inner")
//		public Inner inner() {
//			return inner;
//		}
//
//		@JsonSetter("inner")
//		public void setInner(Inner inner) {
//			this.inner = inner;
//		}
//	}
//
//	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_type", visible = true)
//	@JsonSubTypes({ @JsonSubTypes.Type(value = Default.class, name = Default._TYPE) })
//	public static class Default extends Base {
//
//		public static final String _TYPE = "default";
//		private final Map<String, Object> properties = new LinkedHashMap<>();
//
//		public Default() {
//			super("default");
//		}
//
//		@JsonAnySetter
//		public void set(String name, Object value) {
//			properties.put(name, value);
//		}
//
//		@JsonAnyGetter
//		public Map<String, Object> properties() {
//			return properties;
//		}
//
//	}
//
//	public static void main(String[] args) {
//		final ObjectMapper mapper = new ObjectMapper();
//
//		// leave 'inner' as null
//		final Outer originalOuter = new Outer();
//		try {
//			final JsonNode tree = mapper.valueToTree(originalOuter);
//			final Base base = mapper.treeToValue(tree, Base.class);
//			System.out.println(base.type());
//
//			// Serialize to default
//			final Base base2 = mapper.readValue("{\"value\":\"Hello World\"}", Base.class);
//			System.out.println(base2.type());
//
//			final Base base3 = mapper.readValue("{\"_type\":\"outer\",\"value\":null,\"inner\":null}", Base.class);
//			System.out.println(base3.type());
//
//			final Base base4 = mapper.readValue("{\"_type\":\"inner\",\"value\":null}", Base.class);
//			System.out.println(base4.type());
//
//		}
//
//		catch (final JsonProcessingException e) {
//			e.printStackTrace();
//		}
//	}
//}
