package db;
import business.Product;

public interface ProductReader {
	Product get(String code);
	String getAll();
}
