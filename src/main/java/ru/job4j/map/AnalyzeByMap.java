package ru.job4j.map;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        return pupils.stream()
                .flatMap(pupil -> pupil.subjects().stream())
                .mapToDouble(Subject::score)
                .average()
                .orElse(0);
    }

    public static List<Label> averageScoreByPupil(List<Pupil> list) {
        return list.stream()
                .map(pupil -> new Label(pupil.name(), pupil.subjects()
                        .stream()
                        .mapToDouble(Subject::score)
                        .average()
                        .orElse(0D)))
                .collect(Collectors.toList());
    }

    public static List<Label> averageScoreBySubject(List<Pupil> list) {
        return list.stream()
                .flatMap(pupil -> pupil.subjects()
                        .stream())
                .collect(Collectors
                        .groupingBy(Subject::name,
                                LinkedHashMap::new,
                                Collectors.averagingDouble(Subject::score)))
                .entrySet()
                .stream()
                .map(pupil -> new Label(pupil.getKey(), pupil.getValue()))
                .collect(Collectors.toList());
    }

    public static Label bestStudent(List<Pupil> list) {
        return list.stream()
                .map(pupil -> new Label(pupil.name(), pupil.subjects()
                        .stream()
                        .mapToInt(Subject::score)
                        .sum()))
                .max(Label::compareTo)
                .orElse(new Label("", 0D));
    }

    public static Label bestSubject(List<Pupil> list) {
        return list.stream()
                .flatMap(pupil -> pupil.subjects()
                        .stream())
                .collect(Collectors
                        .groupingBy(Subject::name,
                                Collectors.summingDouble(Subject::score)))
                .entrySet()
                .stream()
                .map(pupil -> new Label(pupil.getKey(), pupil.getValue()))
                .max(Label::compareTo)
                .orElse(new Label("", 0D));
    }
}