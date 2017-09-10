package sic.nmsu.javafx.service;

import org.springframework.stereotype.Service;

/**
 * A service for basic name alternation to make them file-system friendly
 * 
 * 
 * @author Shane
 *
 */
@Service
public class SimpleNameService {
	public String simplify(String name) {
		StringBuilder simpleName = new StringBuilder();
		int n = name.length();
		for (int i = 0; i < n; i++) {
			char c = name.charAt(i);
			// keep characters and digits, replacing everything else with underscores 
			if (Character.isLetterOrDigit(c))
				simpleName.append(c);
			else 
				simpleName.append('_');
		}
		return simpleName.toString();
	}
}
