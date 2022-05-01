import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //1. несовершеннолетние
        int underage = (int) persons.stream()
                .filter(Person -> Person.getAge() < 18)
                        .count();

        //2. призывники
        List<String> recruits = persons.stream()
                .filter(Person -> Person.getAge() >=18 && Person.getAge() <= 27)
                .filter(Person -> Person.getSex() == Sex.MAN)
                .map(Person::getName)
                .collect(Collectors.toList());

        //3. отсортированные фамилии работоспособных людей с высшим образованием
        List<String> people = persons.stream()
                .filter(Person -> Person.getSex()==Sex.WOMAN ? Person.getAge() >=18 && Person.getAge() <= 60 : Person.getAge() >=18 && Person.getAge() <= 65)
                .filter(Person -> Person.getEducation() == Education.HIGHER)
                .map(Person::getFamily)
                .sorted()
                .collect(Collectors.toList());
    }
}
