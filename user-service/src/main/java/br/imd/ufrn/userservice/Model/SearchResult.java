package br.imd.ufrn.userservice.Model;

public class SearchResult {
    private String prompt;
    private String context;
    private String answer;

    public SearchResult(String prompt, String answer, String context) {
        this.prompt = prompt;
        this.answer = answer;
        this.context = context;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getContext() {
        return this.context;
    }
}
