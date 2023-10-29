package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Controller {
    public static void Start() {
        System.out.println("Программа \"Справочник сотрудников\" запущена");
        ArrayList<Catalog> myList = new ArrayList<>();
        myList.add(AddEmployee("1", "Jack", "10", "12345"));
        myList.add(AddEmployee("2", "Ben", "7", "12212"));
        myList.add(AddEmployee("3", "Gina", "12", "56742"));
        myList.add(AddEmployee("4", "Steve", "10", "98765"));
        myList.add(AddEmployee("5", "Jack", "20", "45698"));
        PrintList(myList);

        System.out.println("Поиск по стажу = 10");
        SearchExp(myList, 10);
        System.out.println("Поиск по имени Jack");
        SearchPhone(myList, "Jack");
        System.out.println("Поиск по Id = 2");
        SearchId(myList, 2);
    }
    private static void SearchExp(ArrayList<Catalog> myList, int experience) {
        ArrayList<Catalog> searchRes = new ArrayList<>();
        for (Catalog emp: myList) {
            if (Integer.parseInt(emp.getExperience()) == experience) {
                searchRes.add(emp);
            }
        }
        PrintList(searchRes);
    }
    private static void SearchPhone(ArrayList<Catalog> myList, String name) {
        ArrayList<Catalog> searchRes = new ArrayList<>();
        for (Catalog emp: myList) {
            if (emp.getName().equals(name)) {
                searchRes.add(emp);
            }
        }
        PrintList(searchRes);
    }
    private static void SearchId(ArrayList<Catalog> myList, int id) {
        for (Catalog emp: myList) {
            if (Integer.parseInt(emp.getId()) == id) {
                emp.Print();
            }
        }
    }
    private static Catalog AddEmployee(String id, String name, String experience, String phone) {
        return new Catalog(id, name, experience, phone);
    }
    private static void PrintList(ArrayList<Catalog> printList) {
        for (Catalog emp: printList) {
            emp.Print();
        }
        System.out.println("_______________________");
    }
}
