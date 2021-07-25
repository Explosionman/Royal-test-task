package ru.rybinskov.royaltesttask;

public interface Subordination {

    void print();

    void addSubordinate(Subordinate e);

    void removeSubordinate(Subordinate e);

    void setIndent(int indent);
}
