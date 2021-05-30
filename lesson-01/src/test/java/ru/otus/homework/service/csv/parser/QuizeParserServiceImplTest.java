package ru.otus.homework.service.csv.parser;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.otus.homework.model.Question;
import ru.otus.homework.model.Quize;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
 */
public class QuizeParserServiceImplTest {
  private QuizeParserService parserService;

  @Before
  public void setUp() {
    ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
    parserService = context.getBean(QuizeParserService.class);
  }

  @Test
  public void parse_returnEmptyObject() {
    Quize result = parserService.parse(null);
    assertTrue(result.getQuestions().isEmpty());

    List<String> filledData = new ArrayList<>();
    result = parserService.parse(filledData);
    assertTrue(result.getQuestions().isEmpty());


    filledData.add("Java is program language?(y/n);y");
    result = parserService.parse(filledData);
    assertTrue(result.getQuestions().isEmpty());
  }

  @Test
  public void parse_returnFilledObject() {
    List<String> filledData = new ArrayList<>();
    filledData.add("Java is program language?(y/n);y,n;y");

    Quize result = parserService.parse(filledData);
    assertFalse(result.getQuestions().isEmpty());

    Question question = result.getQuestions().get(0);
    assertFalse(StringUtils.isEmpty(question.getQuestion()));
    assertFalse(StringUtils.isEmpty(question.getCorrectAnswer()));
    assertFalse(CollectionUtils.isEmpty(question.getVariantAnswers()));
  }

}