package ru.otus.lesson05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.lesson05.service.interview.InterviewService;
import ru.otus.lesson05.service.registration.RegistrationService;

/**
 * @author Aleksey.Potekhin
 * @date 21.06.2021
 */
@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellComponent {

  private final InterviewService interviewService;
  private final RegistrationService registrationService;

  @ShellMethod(value = "Login registration before interview", key = {"l", "login"})
  public void login() {
    registrationService.register();
  }

  @ShellMethod(value = "Start interview", key = {"s", "start"})
  public void start() {
    interviewService.start();
  }
}
