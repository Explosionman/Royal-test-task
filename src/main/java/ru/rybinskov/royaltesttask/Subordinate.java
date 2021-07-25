package ru.rybinskov.royaltesttask;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Subordinate implements Subordination, Comparable<Subordinate> {
    private String name;
    private SortedSet<Subordinate> subordinates;
    private int indent;

    public Subordinate(String name) {
        this.name = name;
        this.subordinates = new TreeSet<>();
    }

    private StringBuilder prepareReport() {
        StringBuilder report = new StringBuilder("\t".repeat(indent));
        report.append(name).append("\n");
        for (Subordinate subordinate : subordinates) {
            subordinate.setIndent(indent + 1);
            report.append(subordinate.prepareReport());
        }
        return report;
    }

    public int compareTo(Subordinate o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public void addSubordinate(Subordinate e) {
        if (e.equals(this))
            throw new IllegalArgumentException(String.format("Циклические подчинения запрещены! Cause %s", e));
        subordinates.removeIf(subordinate -> subordinate.equals(e));
        subordinates.add(e);
    }

    @Override
    public void removeSubordinate(Subordinate e) {
        subordinates.remove(e);
    }

    @Override
    public void setIndent(int indent) {
        this.indent = indent;
    }

    @Override
    public String toString() {
        return "name: " + this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subordinate that = (Subordinate) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public void print() {
        System.out.println(prepareReport());
    }
}
