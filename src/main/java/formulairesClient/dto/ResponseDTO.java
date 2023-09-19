package formulairesClient.dto;

import formulairesClient.entities.Question;

public class ResponseDTO {

    private Question question;
    private String response;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
