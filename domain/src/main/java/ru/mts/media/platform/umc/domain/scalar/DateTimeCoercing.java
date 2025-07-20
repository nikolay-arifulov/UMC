package ru.mts.media.platform.umc.domain.scalar;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.jetbrains.annotations.NotNull;

@DgsScalar(name = "DateTime")
public class DateTimeCoercing implements Coercing<LocalDateTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof LocalDateTime ldt) {
            return FORMATTER.format(ldt);
        }
        throw new CoercingSerializeException("Expected LocalDateTime");
    }

    @Override
    public LocalDateTime parseValue(@NotNull Object input) throws CoercingParseValueException {
        if (input instanceof String str) {
            return LocalDateTime.parse(str, FORMATTER);
        }
        throw new CoercingParseValueException("Expected a String to parse to LocalDateTime");
    }

    @Override
    public LocalDateTime parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue strVal) {
            return LocalDateTime.parse(strVal.getValue(), FORMATTER);
        }
        throw new CoercingParseLiteralException("Expected a StringValue");
    }
}
