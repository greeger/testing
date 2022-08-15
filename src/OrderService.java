import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderService {
    enum Type {DELIVERY, PICKUP}

    static class OrderData {
        final Type type;
        final String currency;
        final Long amount;

        OrderData (@NotNull Type type,
                   @NotNull String currency,
                   @NotNull Long amount) {
            this.type = type;
            this.currency = currency;
            this.amount = amount;
        }

        String getCurrency() {
            return currency;
        }

        Long getAmount() {
            return amount;
        }

        Type getType() {
            return type;
        }
    }
    /**
     *
     * Возвращает map вида [валюта (в порядке возрастания разницы) – разница между самым большим и маленьким заказом типа DELIVERY для валюты].
     Если по какой-то валюте только один заказ, то он является и самым большим и самым маленьким и разница равна 0.
     * Пример входных данных:
     * [
     * Order(DELIVERY, "EUR", 2000),
     * Order (DELIVERY, "USD", 15),
     * Order (DELIVERY, "RUB", 200),
     * Order (PICKUP, "RUB", 1250),
     * Order (DELIVERY, "USD", 35),
     * Order (PICKUP, "USD", 55),
     * Order (DELIVERY, "RUB", 100)
     * ]
     *
     * Ожидаемый результат:
     * ["EUR" -> 0.0, "USD" -> 20.0, "RUB" -> 100.0]
     */
    static Map<String, Double> getMaxMinusMinDeliveryMapByCurrency(List<OrderData> orderDataList) {
        return orderDataList.stream().filter(x -> x.getType()==Type.DELIVERY)
                .collect(Collectors.groupingBy(
                        OrderData::getCurrency,
                        Collectors.mapping(OrderData::getAmount, Collectors.toSet())
                ))
                .entrySet().stream().collect(
                    HashMap<String, Double>::new,
                    (acc, item) -> {
                        acc.put(item.getKey(),
                                (double)(item.getValue().stream().max((x1, x2) -> (int)(x1-x2)).get()
                                -item.getValue().stream().min((x1, x2) -> (int)(x1-x2)).get())
                        );
                    },
                    (acc1, acc2) -> {}
                ).entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
                        Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new)
                );
    }


}
