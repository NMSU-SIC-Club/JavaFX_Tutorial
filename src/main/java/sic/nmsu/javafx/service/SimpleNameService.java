package sic.nmsu.javafx.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleNameService {
	public String simplify(String name) {
		StringBuilder simpleName = new StringBuilder();
		int n = name.length();
		for (int i = 0; i < n; i++) {
			char c = name.charAt(i);
			if (Character.isLetterOrDigit(c))
				simpleName.append(c);
			else 
				simpleName.append('_');
		}
		return simpleName.toString();
	}
}
