import spark.ResponseTransformer;

public class DemoObjectHTTP {

	int[] buffer;

	public DemoObjectHTTP(int buff_size) {
		if (buff_size <= 0) {
			return;
		}
		buffer = new int[buff_size];
		buffer[0] = 0;
		if (buff_size > 1) {
			buffer[1] = 1;
			for (int i = 2; i < buff_size; i++) {
				buffer[i] = buffer[i - 1] + buffer[i-2];
			}
		}
	}

	private static String stringRep(Object objR) {
		DemoObjectHTTP obj = (DemoObjectHTTP) objR;
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < obj.buffer.length; i++) {
			str.append(obj.buffer[i]);
			str.append("\t");
		}
		return str.toString();
	}

	public static ResponseTransformer rep() {
		return DemoObjectHTTP::stringRep;
	}
}