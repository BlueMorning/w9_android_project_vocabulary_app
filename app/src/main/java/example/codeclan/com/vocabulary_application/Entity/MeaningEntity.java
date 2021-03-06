package example.codeclan.com.vocabulary_application.Entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "meanings", foreignKeys = @ForeignKey(   entity        = WordEntity.class,
                                                            parentColumns = "wrd_id",
                                                            childColumns  = "mig_wrd_id"))
public class MeaningEntity {


    // Properties

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="mig_id")
    @NonNull
    private Long id;

    @ColumnInfo(name="mig_definition")
    private String meaning;

    @ColumnInfo(name = "mig_example")
    private String example;

    @ColumnInfo(name = "mig_synonym")
    private String synonyms;

    @ColumnInfo(name = "mig_antonym")
    private String antonyms;

    @ColumnInfo(name= "mig_wrd_id")
    private Long wordId;

    public MeaningEntity(Long id, Long wordId, String meaning, String example, String synonyms, String antonyms) {
        this.id         = id;
        this.meaning = meaning;
        this.example    = example;
        this.synonyms   = synonyms;
        this.antonyms   = antonyms;
        this.wordId     = wordId;
    }

    @Ignore
    public MeaningEntity(Long wordId, String meaning, String example, String synonyms, String antonyms) {
        this.wordId     = wordId;
        this.meaning = meaning;
        this.example    = example;
        this.synonyms   = synonyms;
        this.antonyms   = antonyms;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(String antonyms) {
        this.antonyms = antonyms;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}
