package org.dstu;

import org.dstu.domain.IUniversityPublication;
import org.dstu.domain.Book;
import org.dstu.domain.Journal;
import org.dstu.util.CsvReader;
import org.dstu.util.SerializeUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("You must specify data file name and serialize file name!");
            return;
        }

        String sourceFile = args[0];
        String binFile = args[1];

        List<String[]> strings = CsvReader.readCsvFile(sourceFile, ";");
        List<IUniversityPublication> publications = new ArrayList<>();
        for (String[] line: strings) {
            if (line[0].equals("0")) {
                publications.add(new Book(line));
            } else {
                publications.add(new Journal(line));
            }
        }

        publications.forEach(System.out::println);

        SerializeUtils.serialize(publications, binFile);
        List<IUniversityPublication> newPersons = (List<IUniversityPublication>) SerializeUtils.deserialize(binFile);
        System.out.println("Новое:");
        newPersons.forEach(System.out::println);
    }
}
