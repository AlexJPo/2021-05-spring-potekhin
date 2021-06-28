package ru.otus.lesson05.service.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.otus.lesson05.config.CSVProperties;
import ru.otus.lesson05.model.Question;
import ru.otus.lesson05.model.Quize;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@SpringBootTest
@ContextConfiguration(classes = { QuizeParserServiceImpl.class, CSVProperties.class})
class QuizeParserServiceImplTest {

  @Autowired
  private QuizeParserService parserService;
  @MockBean
  private CSVProperties csvProperties;

  @BeforeEach
  public void setUp() {
    when(csvProperties.getFieldMinSize()).thenReturn(3);
    when(csvProperties.getAnswerVariantSeparator()).thenReturn(",");
    when(csvProperties.getSeparator()).thenReturn(";");
  }

  @DisplayName("Отсутвие вопросов для интервью, если была ошибка парсинга")
  @Test
  public void parse_returnEmptyObject() {
    Quize result = parserService.parse(null);
    assertTrue(result.getQuestions().isEmpty());

    final List<String> filledData = new ArrayList<>();
    result = parserService.parse(filledData);
    assertTrue(result.getQuestions().isEmpty());

    filledData.add("Java is program language?(y/n);y");
    result = parserService.parse(filledData);
    assertTrue(result.getQuestions().isEmpty());

    filledData.set(0, "Java is program language?(y/n);y,n;y");
    result = parserService.parse(filledData);
    assertTrue(result.getQuestions().isEmpty());
  }

  @DisplayName("Наличие вопросов после успешного парсинга")
  @Test
  public void parse_returnFilledObject() {
    final List<String> filledData = new ArrayList<>();
    filledData.add("Java is program language?(y/n);y,n;1");

    final Quize result = parserService.parse(filledData);
    assertFalse(result.getQuestions().isEmpty());

    Question question = result.getQuestions().get(0);
    assertTrue(StringUtils.hasLength(question.getQuestion()));
    assertNotNull(question.getCorrectAnswer());
    assertTrue(question.getCorrectAnswer() > 0);
    assertFalse(CollectionUtils.isEmpty(question.getVariantAnswers()));
  }
}