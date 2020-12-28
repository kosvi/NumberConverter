package logic;

public interface Converter {

	public String convertTo(long value);

	public long convertFrom(String value);
	
	public String getName();

}
