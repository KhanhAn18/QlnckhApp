package model;

public class User
{
    private String username;
    private String password;
    private String question;
    private String answer;
    private String date;
    public User()
    {
    }
    public User(String username, String password, String question, String answer, String date)
    {
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.date = date;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getQuestion()
    {
        return question;
    }
    public void setQuestion(String question)
    {
        this.question = question;
    }
    public String getAnswer()
    {
        return answer;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
}