package ro.msg.learning.shop.util;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvHttpMessageConverter<T> extends AbstractGenericHttpMessageConverter<List<T>> {

    public CsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void writeInternal(List<T> ts, Type type, HttpOutputMessage httpOutputMessage) throws IOException {
        Class<T> tClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        new CsvUtil<T>().toCsv(ts, tClass, httpOutputMessage.getBody());
    }

    @Override
    protected List<T> readInternal(Class<? extends List<T>> aClass, HttpInputMessage httpInputMessage) {
        return new ArrayList<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException {
        Class<T> tClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        return new CsvUtil<T>().fromCsv(tClass, httpInputMessage.getBody());
    }
}
