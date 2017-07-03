import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

public class QuizHandler extends DefaultHandler {
    TriviaQuestion[] quizData = new TriviaQuestion[30];
    int currentQuestion = 0;
    static int READING_QUIZ = 1;
    static int READING_ITEM = 2;
    static int READING_QUESTION = 3;
    static int READING_ANSWER = 4;
    static int READING_NOTHING = 0;
    int currentActivity = READING_NOTHING;
    int currentAnswer = 0;
    TriviaQuestion triviaItem = new TriviaQuestion();

    QuizHandler() {
        super();
    }

    public void startElement(String uri, String localName,
        String qName, Attributes attributes) {
    
        if (localName.equals("Quiz"))
            currentActivity = READING_QUIZ;
        else if (localName.equals("Item"))
            currentActivity = READING_ITEM;
        else if (localName.equals("Question"))
            currentActivity = READING_QUESTION;
        else if (localName.equals("Answer"))
            currentActivity = READING_ANSWER;

        if (currentActivity == READING_ANSWER) {
            String isCorrect = attributes.getValue("correct");
            if (isCorrect.equals("true"))
                triviaItem.correct = currentAnswer;
        }
    }

    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        if (currentActivity == READING_QUESTION) {
            triviaItem.question = value;
            currentActivity = READING_NOTHING;
        }
        if (currentActivity == READING_ANSWER) {
            triviaItem.answer[currentAnswer] = value;
            currentAnswer++;
            currentActivity = READING_NOTHING;
        }
   }

   public void endElement(String uri, String localName, String qName) {
       if (localName.equals("Item")) {
           // save question and answers to array
           if (currentQuestion < quizData.length) {
               quizData[currentQuestion] = triviaItem;
               currentQuestion++;
           }
           // set up new empty question
           triviaItem = new TriviaQuestion();
           currentAnswer = 0;
       }
    }

    public String[] getQuestions() {
        String[] questions = new String[currentQuestion];
        for (int i = 0; i < currentQuestion; i++) {
            questions[i] = quizData[i].question + "\r\n";
            for (int j = 0; j < 3; j++)
                questions[i] += j + ". " + quizData[i].answer[j] + "\r\n"; 
        }
        return questions;
    }

    public String[] getAnswers() {
        String[] answers = new String[currentQuestion];
        for (int i = 0; i < currentQuestion; i++)
           answers[i] = "" + quizData[i].correct;
        return answers;
    }                    
}

class TriviaQuestion {
    String question;
    String[] answer = new String[3];
    int correct;
}
