package com.rithsagea.pokebot.types.registry;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import com.rithsagea.pokebot.util.CSVUtil;

public class Registry<T> {
	
	private HashMap<Integer, T> registry;
	
	public Registry(Class<T> clazz, String csvPath) {
		registry = new HashMap<>();
		
		List<String[]> data = CSVUtil.readFile(csvPath);
		String[] header = data.remove(0);
		T t;
		
		//reflection
		Field[] fields = clazz.getDeclaredFields();
		RegistryField annotation;
		String val;
		int index;
		
		//instantiate all the members of the registry
		for(String[] str : data) {
			try {
				t = clazz.newInstance();
				//loop through each field in the member
				for(Field f : fields) {
					annotation = f.getAnnotation(RegistryField.class);
					if(annotation != null) {
						String label = annotation.fieldName();
						if(label.isEmpty()) label = f.getName();
						
						for(index = 0; index < header.length && !header[index].equals(label); index++);
						if(index == header.length) {
							continue; //issue with label
						}
						val = str[index];
						if(val.isEmpty()) val = "0"; // just in case
						
						if(f.getType() == String.class) {
							f.set(t, val);
						} else if(f.getType() == int.class) {
							f.set(t, Integer.parseInt(val));
						} else if(f.getType() == boolean.class) {
							f.set(t, new Boolean(Integer.parseInt(val) == 1));
						}
					}
				}
				registry.put(Integer.parseInt(str[0]), t);
			} catch (NumberFormatException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				break; //if can't instantiate something bad happened
			}
		}
	}
	
	public T getMember(int t) {
		return registry.get(t);
	}
}
