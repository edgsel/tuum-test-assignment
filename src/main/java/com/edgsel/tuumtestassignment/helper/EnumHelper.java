package com.edgsel.tuumtestassignment.helper;

import com.edgsel.tuumtestassignment.exception.InvalidEnumValueException;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Arrays.stream;

@SuppressWarnings("rawtypes")
public class EnumHelper {

    public static <T extends Enum> T create(Class<T> enumClazz, String value) {
        if (value == null) {
            return null;
        }

        Optional<T> enumInstance = getEnumByName(enumClazz, value);

        return enumInstance.orElseThrow(constructEnumException(enumClazz, value));
    }

    private static <T extends Enum> Optional<T> getEnumByName(Class<T> enumClazz, String name) {
        return stream(enumClazz.getEnumConstants())
            .filter(e -> e.name().equalsIgnoreCase(name))
            .findFirst();
    }

    private static <T extends Enum> Supplier<InvalidEnumValueException> constructEnumException(
        Class<T> enumClazz,
        String value
    ) {
        String supportedValues = stream(enumClazz.getEnumConstants())
            .filter(Objects::nonNull)
            .map(Enum::toString)
            .collect(Collectors.joining(", "));

        return () -> new InvalidEnumValueException(
            format(
                "Invalid value %s was provided for for class %s. Supported values: [%s]",
                value,
                enumClazz.getSimpleName(),
                supportedValues
            )
        );
    }
}
