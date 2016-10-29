import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Command {

	public static List<String> fields;
	public List<String> values;

	public Map<String, String> getCommandFields() {
		assert fields.size() == values.size();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < fields.size(); i++) {
			map.put(fields.get(i), values.get(i));
		}
		return map;
	}

	public List<String> values() {
		return values;
	}

	public List<Integer> getNumericValues() {
		return values.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

}