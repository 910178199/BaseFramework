package com.yida.app.InstitutionForThrAged.model;

import java.util.List;

/**
 * @author think
 * @name InstitutionForTheAged
 * @class name：com.yida.app.InstitutionForThrAged.model
 * @class describe :
 * @time 2018-02-06 15:13
 */
public class MovieBean {

    private String title;
    private List<Subjects> subjects;


    public String getTitle() {
        return title;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }


    public class Subjects {
        private String title, year, id;

        public Subjects(String title, String year, String id) {
            this.title = title;
            this.year = year;
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public String getYear() {
            return year;
        }

        public String getId() {
            return id;
        }
    }

}


