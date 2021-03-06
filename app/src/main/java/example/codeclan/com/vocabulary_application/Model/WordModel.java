package example.codeclan.com.vocabulary_application.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.R;

/**
 * Created by horizon on 29/01/2018.
 */

public class WordModel {

    private WordEntity  wordEntity;
    private StatsModel  statsModel;
    private ArrayList<MeaningModel> meaningsList;
    private WordsRoomDatabase db;

    public WordModel(WordEntity wordEntity, WordsRoomDatabase db){
        this.db             = db;
        this.meaningsList   = new ArrayList<>();
        this.wordEntity     = wordEntity;
        this.statsModel     = new StatsModel(db.statsDao().getStatsByWordId(this.wordEntity.getId()), this.db);

    }

    public WordModel(WordsRoomDatabase db){
        this.db           = db;
        this.meaningsList = new ArrayList<>();
        this.wordEntity   = null;
        this.statsModel   = null;
    }

    public WordEntity getWordEntity(){
        return this.wordEntity;
    }

    public StatsModel getStatsModel(){

        return this.statsModel;
    }

    public ArrayList<MeaningModel> getMeaningsList(){

        return this.meaningsList;
    }


    public void createWordEntity(String wordSpelling, EnumWordType enumWordType){
        this.wordEntity = new WordEntity(enumWordType, wordSpelling, "");
    }


    public void setStatsModel(StatsModel statsModel) {
        this.statsModel = statsModel;
    }

    public void addMeaning(MeaningModel meaningModel){

        if(meaningsList.contains(meaningModel)){
            meaningsList.remove(meaningModel);
        }

        meaningsList.add(meaningModel);
    }

    public void removeMeaning(MeaningModel meaningModel){

        if(meaningsList.contains(meaningModel)){
            meaningsList.remove(meaningModel);
        }
    }

    public void saveWord(){

        if(this.wordEntity.getId() == null){
            this.wordEntity.setId(db.wordDao().insertWord(this.wordEntity));
            this.statsModel = new StatsModel(this.wordEntity.getId(), this.db);
            db.statsDao().insertStats(this.statsModel.getStatsEntity());
        }
        else{
            db.wordDao().updateWord(this.wordEntity);
        }

        ArrayList<Long> meaningsListToKeep = new ArrayList<>(this.meaningsList.stream()
                                                .map(m ->  m.getMeaningEntity().getId()).collect(Collectors.toList()));
        List<MeaningEntity> meaningEntities = db.meaningDao().getMeaningsByWordId(this.wordEntity.getId());
        for(MeaningEntity meaningEntity : meaningEntities){

            if(! meaningsListToKeep.contains(meaningEntity.getId())){
                db.meaningDao().deleteMeaningByMeaningId(meaningEntity.getId());
            }
        }


        for(MeaningModel meaningModel : this.meaningsList){

            if(meaningModel.getMeaningEntity().getId() == null){
                meaningModel.getMeaningEntity().setWordId(this.wordEntity.getId());
                db.meaningDao().insertMeaning(meaningModel.getMeaningEntity());
            }
            else{
                db.meaningDao().updateMeaning(meaningModel.getMeaningEntity());
            }
        }
    }


    public void initializeStatsModel() {
        if(this.wordEntity.getId() != null) {
            this.statsModel = new StatsModel(this.db.statsDao().getStatsByWordId(this.wordEntity.getId()), this.db);
        }
    }

    public ArrayList<MeaningModel> initializeMeanings() {
        if(this.wordEntity.getId() != null) {
            this.meaningsList = new ArrayList<>(this.db.meaningDao().getMeaningsByWordId(this.wordEntity.getId()).stream().map(
                                    meaningEntity -> {
                                        return new MeaningModel(meaningEntity, this);
                                    }
                                ).collect(Collectors.toList()));
        }

        return this.meaningsList;
    }

    public void delete() {
        this.db.meaningDao().deleteMeaningByWordId(this.wordEntity.getId());
        this.db.statsDao().deleteStatsByWordId(this.wordEntity.getId());
        this.db.wordTrainingJoinDao().deleteWordTrainingJoinByWordId(this.wordEntity.getId());
        this.db.wordDao().deleteWordByWordId(this.wordEntity.getId());
    }
}
