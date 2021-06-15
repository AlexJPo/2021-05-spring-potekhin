package ru.otus.lesson04.service.parser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.otus.lesson04.model.Question;
import ru.otus.lesson04.model.Quize;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@SpringBootTest
class QuizeParserServiceImplTest {

  @Autowired
  private QuizeParserService parserService;

  @Test
  public void parse_returnEmptyObject() {
    Quize result = parserService.parse(null);
    assertNull(result.getQuestions());

    List<String> filledData = new ArrayList<>();
    result = parserService.parse(filledData);
    assertNull(result.getQuestions());

    filledData.add("Java is program language?(y/n);y");
    result = parserService.parse(filledData);
    assertNull(result.getQuestions());

    filledData.set(0, "Java is program language?(y/n);y,n;y");
    result = parserService.parse(filledData);
    assertNull(result.getQuestions());
  }

  @Test
  public void parse_returnFilledObject() {
    List<String> filledData = new ArrayList<>();
    filledData.add("Java is program language?(y/n);y,n;1");

    Quize result = parserService.parse(filledData);
    assertFalse(result.getQuestions().isEmpty());

    Question question = result.getQuestions().get(0);
    assertTrue(StringUtils.hasLength(question.getQuestion()));
    assertNotNull(question.getCorrectAnswer());
    assertTrue(question.getCorrectAnswer() > 0);
    assertFalse(CollectionUtils.isEmpty(question.getVariantAnswers()));
  }
}