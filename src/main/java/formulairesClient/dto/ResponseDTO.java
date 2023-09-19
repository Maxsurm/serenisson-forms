package formulairesClient.dto;

import formulairesClient.entities.Question;

public class ResponseDTO {

    private Question questions;
    private String response;

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
