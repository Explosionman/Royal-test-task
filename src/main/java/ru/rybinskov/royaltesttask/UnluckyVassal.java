package ru.rybinskov.royaltesttask;

import java.util.*;

import static java.util.stream.Collectors.*;

public class UnluckyVassal {
    private Subordinate getKeyByValue(Map<Subordinate, Set<Subordinate>> map, Subordinate value) {
        for (Subordinate subordinate : map.keySet()) {
            if (subordinate.equals(value)) return subordinate;
        }
        return null;
    }

    public void printReportForKing(List<String> pollResults) {
        try {
            Subordinate king = new Subordinate("Король");
            king.setIndent(0);
            Map<Subordinate, Set<Subordinate>> subordinateMap = new HashMap<>();
            pollResults.stream()
                    .map(elem -> elem.split(":"))
                    .forEach(elem -> {
                        if (elem.length == 1) subordinateMap.put(new Subordinate(elem[0]), null);
                        else if (elem.length != 0) {
                            subordinateMap.put(new Subordinate(elem[0]), Arrays.stream(elem[1].split(","))
                                    .map(String::trim)
                                    .map(Subordinate::new)
                                    .collect(toSet()));
                        }
                    });
            subordinateMap.forEach((key, values) -> {
                if (values != null) values.forEach(key::addSubordinate);
                king.addSubordinate(key);
            });
            subordinateMap.forEach((key, values) -> {
                if (values != null) values.forEach(v -> {
                    if (subordinateMap.containsKey(v)) {
                        Subordinate subordinate = getKeyByValue(subordinateMap, v);
                        key.addSubordinate(subordinate);
                        king.removeSubordinate(subordinate);
                    }
                });
            });
            king.print();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
