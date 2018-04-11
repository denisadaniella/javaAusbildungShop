package ro.msg.learning.shop.util;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CsvUtil<T> {

    @SuppressWarnings("unchecked")
    public List<T> fromCsv(Class<T> tClass, InputStream inputStream) throws IOException {

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(tClass).withHeader();
        ObjectReader objectReader = mapper.readerFor(tClass).with(schema);

        return (List<T>) objectReader.readValues(inputStream).readAll();
    }

    public void toCsv(List<T> list, Class<T> tClass, OutputStream outputStream) throws IOException {

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(tClass).withHeader();
        mapper.writer(schema).writeValue(outputStream, list);
        outputStream.close();
    }

}
