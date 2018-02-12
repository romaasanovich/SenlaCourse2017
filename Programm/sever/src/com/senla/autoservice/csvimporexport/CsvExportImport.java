package com.senla.autoservice.csvimporexport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.senla.autoservice.annotations.CsvEntity;
import com.senla.autoservice.annotations.CsvProperty;
import com.senla.autoservice.annotations.CsvPropertyModel;
import com.senla.autoservice.api.ADao;
import com.senla.autoservice.api.AEntity;

public class CsvExportImport<T extends AEntity> extends ADao<T> {

	public  void csvExport(final String pathToFolder, List<T> repository) throws Exception {
		boolean isFirst = true;
		final StringBuilder sb = new StringBuilder();
		String fileName = "";
		String separator = ";";
		String entityId;
		final String ID_FIELD = "id";

		for (final T item : repository) {
			final Class entityClass = item.getClass();

			if (entityClass != null) {
				final CsvEntity entityClassAnnotation = (CsvEntity) entityClass.getAnnotation(CsvEntity.class);

				if (entityClassAnnotation != null) {
					final Field[] fields = entityClass.getDeclaredFields();
					final SortedMap<Integer, CsvPropertyModel> modelMap = getItemModelData(item, fields);
					Integer counter = 2;

					if (isFirst) {
						fileName = entityClassAnnotation.filename();
						separator = entityClassAnnotation.valueSeparator();
						entityId = entityClassAnnotation.entityId();
						sb.append(entityId).append(separator);

						for (final Map.Entry<Integer, CsvPropertyModel> mapRecord : modelMap.entrySet()) {
							while (!counter.equals(mapRecord.getKey())) {
								counter++;
								sb.append(";");
							}
							sb.append(mapRecord.getValue().getHeader()).append(separator);
							counter++;
						}
						sb.append("\n");
					}

					final Field superClassField = entityClass.getSuperclass().getDeclaredField(ID_FIELD);
					superClassField.setAccessible(true);
					sb.append(superClassField.get(item)).append(separator);

					counter = 2;
					for (final Map.Entry<Integer, CsvPropertyModel> mapRecord : modelMap.entrySet()) {
						while (!counter.equals(mapRecord.getKey())) {
							counter++;
							sb.append(";");
						}
						sb.append(mapRecord.getValue().getValue()).append(separator);
						counter++;
					}
					sb.append("\n");
				}
			}
			isFirst = false;
		}

		try (FileWriter fw = new FileWriter(new File(pathToFolder + fileName))) {
			fw.append(sb.toString());
			fw.flush();
		}
	}

	public void csvImport(final String path, final Class<?> clazz) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final String fileName;
		final String separator;
		String entityId = "id";

		final CsvEntity classAnnotation = clazz.getAnnotation(CsvEntity.class);
		if (classAnnotation != null) {
			final Field[] fields = clazz.getDeclaredFields();
			fileName = classAnnotation.filename();
			separator = classAnnotation.valueSeparator();
			entityId = classAnnotation.entityId();

			try (BufferedReader br = new BufferedReader(new FileReader(path + fileName))) {
				String line;
				Boolean isFirstLine = true;
				while ((line = br.readLine()) != null) {
					if (isFirstLine) {
						isFirstLine = false;
						continue;
					}
					try {
						final String[] stringFields = line.split(separator);
						if (isFreeId(Integer.valueOf(stringFields[0]))) {
							add(getItemFromCsv(fields, stringFields, clazz));
						} else {
							update(getItemFromCsv(fields, stringFields, clazz));
						}
					} catch (final NumberFormatException ignored) {
					}
				}
			}
		}

	}

	private SortedMap<Integer, String> getItemModel(final Field[] fields) {
		final SortedMap<Integer, String> modelMap = new TreeMap<>();

		for (final Field field : fields) {
			final CsvProperty fieldAnnotation = field.getAnnotation(CsvProperty.class);

			if (fieldAnnotation != null) {
				final Integer columnNumber = fieldAnnotation.columnNumber();
				String header = "";

				switch (fieldAnnotation.propertyType()) {
				case SimpleProperty:
					header = field.getName();
					break;
				case CompositeProperty:
					header = fieldAnnotation.keyField();
					break;
				}
				modelMap.put(columnNumber, header);
			}
		}
		return modelMap;
	}

	@SuppressWarnings("unchecked")
	private SortedMap<Integer, CsvPropertyModel> getItemModelData(final T item, final Field[] fields)
			throws IllegalAccessException {
		final SortedMap<Integer, CsvPropertyModel> modelMap = new TreeMap<>();
		for (final Field field : fields) {
			final CsvProperty fieldAnnotation = field.getAnnotation(CsvProperty.class);
			final CsvPropertyModel model = new CsvPropertyModel();
			field.setAccessible(true);

			if (fieldAnnotation != null) {
				final Integer columnNumber = fieldAnnotation.columnNumber();

				switch (fieldAnnotation.propertyType()) {
				case SimpleProperty:
					model.setHeader(field.getName());
					model.setValue(field.get(item).toString());
					break;
				case CompositeProperty:
					model.setHeader(fieldAnnotation.keyField());
					model.setValue(Integer.toString(((T) field.get(item)).getId()));
					break;
				}
				modelMap.put(columnNumber, model);
			}
		}
		return modelMap;
	}

	@SuppressWarnings("unchecked")
	private T getItemFromCsv(final Field[] fields, final String[] data, final Class clazz)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		@SuppressWarnings("unchecked")
		final T item = (T) clazz.getConstructor().newInstance();
		item.setId(Integer.valueOf(data[0]));
		for (final Field field : fields) {
			field.setAccessible(true);

			final CsvProperty property = field.getAnnotation(CsvProperty.class);
			if (property != null) {
				final Object value;
				final String line = data[property.columnNumber() - 1];
				final Type fieldType = field.getType();

				if (fieldType.equals(Integer.class)) {
					value = Integer.valueOf(line);
				} else if (fieldType.equals(Float.class)) {
					value = Float.valueOf(line);
				} else if (fieldType.equals(Boolean.class)) {
					value = Boolean.valueOf(line);
				} else if (((Class) fieldType).isEnum()) {
					value = Enum.valueOf((Class) fieldType, line);
				} else {
					value = line;
				}
				field.set(item, value);
			} else {
				field.set(item, null);
			}
		}
		return item;
	}



}
