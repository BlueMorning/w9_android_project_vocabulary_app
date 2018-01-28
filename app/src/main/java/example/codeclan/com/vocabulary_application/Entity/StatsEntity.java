package example.codeclan.com.vocabulary_application.Entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "stats", foreignKeys = @ForeignKey(entity        = WordEntity.class,
                                                       parentColumns = "wrd_id",
                                                       childColumns  = "sta_id"))
public class StatsEntity {


    // Properties

    @PrimaryKey
    @ColumnInfo(name = "sta_id")
    private final int id;

    @ColumnInfo(name = "sta_training_step")
    private int trainingStep;

    @ColumnInfo(name = "sta_confidence_level")
    private int confidenceLevel;

    @ColumnInfo(name = "sta_total_answers")
    private int totalAnswers;

    @ColumnInfo(name = "sta_total_correct_answers")
    private int totalCorrectAnswers;

    @ColumnInfo(name = "sta_total_incorrect_answers")
    private int totalIncorrectAnswers;

    @ColumnInfo(name = "sta_last_training_total_answers")
    private int lastTrainingTotalAnswers;

    @ColumnInfo(name = "sta_last_training_total_correct_answers")
    private int lastTrainingTotalCorrectAnswers;

    @ColumnInfo(name = "sta_last_training_total_incorrect_answers")
    private int lastTrainingTotalIncorrectAnswers;

    @ColumnInfo(name = "sta_wrd_id")
    private int wordId;

    public StatsEntity(int id, int trainingStep, int confidenceLevel, int totalAnswers, int totalCorrectAnswers, int totalIncorrectAnswers, int lastTrainingTotalAnswers, int lastTrainingTotalCorrectAnswers, int lastTrainingTotalIncorrectAnswers, int wordId) {
        this.id                                 = id;
        this.trainingStep                       = trainingStep;
        this.confidenceLevel                    = confidenceLevel;
        this.totalAnswers                       = totalAnswers;
        this.totalCorrectAnswers                = totalCorrectAnswers;
        this.totalIncorrectAnswers              = totalIncorrectAnswers;
        this.lastTrainingTotalAnswers           = lastTrainingTotalAnswers;
        this.lastTrainingTotalCorrectAnswers    = lastTrainingTotalCorrectAnswers;
        this.lastTrainingTotalIncorrectAnswers  = lastTrainingTotalIncorrectAnswers;
        this.wordId                             = wordId;
    }


    // Getters and Setters

    public int getId() {
        return id;
    }

    public int getTrainingStep() {
        return trainingStep;
    }

    public void setTrainingStep(int trainingStep) {
        this.trainingStep = trainingStep;
    }

    public int getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(int confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(int totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public void setTotalCorrectAnswers(int totalCorrectAnswers) {
        this.totalCorrectAnswers = totalCorrectAnswers;
    }

    public int getTotalIncorrectAnswers() {
        return totalIncorrectAnswers;
    }

    public void setTotalIncorrectAnswers(int totalIncorrectAnswers) {
        this.totalIncorrectAnswers = totalIncorrectAnswers;
    }

    public int getLastTrainingTotalAnswers() {
        return lastTrainingTotalAnswers;
    }

    public void setLastTrainingTotalAnswers(int lastTrainingTotalAnswers) {
        this.lastTrainingTotalAnswers = lastTrainingTotalAnswers;
    }

    public int getLastTrainingTotalCorrectAnswers() {
        return lastTrainingTotalCorrectAnswers;
    }

    public void setLastTrainingTotalCorrectAnswers(int lastTrainingTotalCorrectAnswers) {
        this.lastTrainingTotalCorrectAnswers = lastTrainingTotalCorrectAnswers;
    }

    public int getLastTrainingTotalIncorrectAnswers() {
        return lastTrainingTotalIncorrectAnswers;
    }

    public void setLastTrainingTotalIncorrectAnswers(int lastTrainingTotalIncorrectAnswers) {
        this.lastTrainingTotalIncorrectAnswers = lastTrainingTotalIncorrectAnswers;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }
}
