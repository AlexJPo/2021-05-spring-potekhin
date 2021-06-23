package ru.otus.lesson05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.lesson05.service.interview.InterviewService;
import ru.otus.lesson05.service.student.StudentService;

/**
 * @author Aleksey.Potekhin
 * @date 21.06.2021
 */
@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellComponent {

  private final InterviewService interviewService;
  private final StudentService studentService;

  @ShellMethod(value = "Login student before interview", key = {"l", "login"})
  public void login() {
    interviewService.start(studentService.register());
  }

  @ShellMethod(value = "Start interview", key = {"s", "start"})
  public void start() {
    interviewService.start();
  }
}
